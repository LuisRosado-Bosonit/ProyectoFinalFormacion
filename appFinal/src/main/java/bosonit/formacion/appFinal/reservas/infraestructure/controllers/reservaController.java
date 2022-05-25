package bosonit.formacion.appFinal.reservas.infraestructure.controllers;

import bosonit.formacion.appFinal.correo.domain.Correo;
import bosonit.formacion.appFinal.correo.infraestructure.Utils.TLSEmail;
import bosonit.formacion.appFinal.correo.services.correoService;
import bosonit.formacion.appFinal.genericClasses.ErrorOutputDTO;
import bosonit.formacion.appFinal.reservas.application.Services.autobusService;
import bosonit.formacion.appFinal.reservas.domain.Autobus;
import bosonit.formacion.appFinal.reservas.domain.Reserva;
import bosonit.formacion.appFinal.reservas.infraestructure.DTO.input.inputReservaDTO;
import bosonit.formacion.appFinal.reservas.application.Services.reservaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;


@RestController
@Slf4j
public class reservaController {



    @Autowired
    private KafkaTemplate<String, Reserva> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Autobus> kafkaTemplateBus;

    @Autowired
    reservaService servicio;

    @Autowired
    autobusService servicioAutobus;

    @Autowired
    correoService servicioCorreo;

    @Autowired
    ErrorOutputDTO error;

    @Autowired
    private TLSEmail email;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v0/reserva")
    public ResponseEntity<Object> realizarReserva(@RequestBody inputReservaDTO input)  {
        Optional<Reserva> reserva = null;
        try {
            reserva = servicio.guardarReserva(input.toEntity());
        } catch (Exception e) {
            if(input.isFromWeb())actualizar();
            error = new ErrorOutputDTO(e.hashCode(),e.getMessage(),String.valueOf(e.getCause()));
            email.mandarEmail(input.getCorreo(),"Error en la reserva","Se ha producido un error al realizar su reserva ");
            return ResponseEntity.status(503).body(error);
        }
        email.mandarEmail(input.getCorreo(),"Confirmación de la reserva","El identificador de su reserva es " +
                                                        String.valueOf(reserva.get().getId()));
        Correo mensj = new Correo(input);
        servicioCorreo.guardarCorreo(mensj);
        return ResponseEntity.status(HttpStatus.OK).body("La reserva se ha añadido satisfactoriamente");
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v0/reservas/consultarOcupadas")
    public ResponseEntity<Object> consultarReservas(@RequestParam("Fecha") Date fecha,
                                                    @RequestParam("Hora") int hora,
                                                    @RequestParam("Destino") String destino
                                                    ) {
        List<Reserva> plazas = null;
        try {
            plazas = servicioAutobus.consultarPlazasOcupadas(fecha,hora,destino);
        } catch (Exception e) {
            error = new ErrorOutputDTO(406, e.getMessage(), e.getCause().toString());
            return ResponseEntity.status(HttpStatus.OK).body(error);
        }
        return ResponseEntity.status(HttpStatus.OK).body(plazas);
    }

    @ResponseStatus(HttpStatus.OK)

    @PostConstruct
    @GetMapping("/api/v0/reservas/updateME")
    public ResponseEntity<Object> actualizar() {

        log.warn("----- SE ESTÁN SINCRONIZANDO LAS TABLAS DE AUTOBUSES Y RESERVAS CON LOS BACKEMPRESA -----");
        KafkaProducer<String, Reserva> producer = createKafkaProducer();
        KafkaProducer<String, Autobus> producerBus = createKafkaProducerBus();
        servicio.getAllReservas().
                forEach(
                        reserva ->
                                producer.send(new ProducerRecord<String, Reserva>("inicializacionReservas",  reserva))
                );

        producer.close();

        servicioAutobus.getAllBuses().
                forEach(
                        autobus ->
                            producerBus.send(new ProducerRecord<String, Autobus>("inicializacionAutobuses", autobus)));
        producerBus.close();

        //FIXME FALTAN AJUSTILLOS LEVES
        return ResponseEntity.status(HttpStatus.OK).body("todo bien");
    }

    private static KafkaProducer<String, Reserva> createKafkaProducer() { //FIXME ESTO DEBERÍA MOVERLO A OTRA CLASE
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        //props.put(ProducerConfig.CLIENT_ID_CONFIG, CONSUMER_APP_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "bosonit.formacion.appFinal.genericClasses.kafka.serialzer.CustomSerializerReserva");

        return new KafkaProducer(props);
    }
    private static KafkaProducer<String, Autobus> createKafkaProducerBus() { //FIXME ESTO DEBERÍA MOVERLO A OTRA CLASE
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        //props.put(ProducerConfig.CLIENT_ID_CONFIG, CONSUMER_APP_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "bosonit.formacion.appFinal.genericClasses.kafka.serialzer.CustomSerializerAutobus");

        return new KafkaProducer(props);
    }


}
