package bosonit.formacion.appFinal.reservas.infraestructure.controllers;

import bosonit.formacion.appFinal.correo.domain.Correo;
import bosonit.formacion.appFinal.correo.infraestructure.Utils.TLSEmail;
import bosonit.formacion.appFinal.correo.services.correoService;
import bosonit.formacion.appFinal.genericClasses.ErrorOutputDTO;
import bosonit.formacion.appFinal.reservas.domain.Reserva;
import bosonit.formacion.appFinal.reservas.infraestructure.DTO.input.inputReservaDTO;
import bosonit.formacion.appFinal.reservas.application.Services.reservaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.retrytopic.DestinationTopic;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;


@RestController
@Slf4j
public class reservaController {



    @Autowired
    private KafkaTemplate<String, Reserva> kafkaTemplate;

    @Value(value = "inicializacion")
    private String inicializacion;

    @Autowired
    reservaService servicio;

    @Autowired
    correoService servicioCorreo;

    @Autowired
    ErrorOutputDTO error;

    @Autowired
    private TLSEmail email;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v0/reserva")
    public ResponseEntity<Object> login(@RequestBody inputReservaDTO input)  {
        Optional<Reserva> reserva = null;
        try {
            reserva = servicio.guardarReserva(input.toEntity());
        } catch (Exception e) {
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
            plazas = servicio.consultarPlazasOcupadas(fecha,hora,destino);
        } catch (Exception e) {
            error = new ErrorOutputDTO(406, e.getMessage(), e.getCause().toString());
            return ResponseEntity.status(HttpStatus.OK).body(error);
        }
        return ResponseEntity.status(HttpStatus.OK).body(plazas);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v0/reservas/updateME")
    public ResponseEntity<Object> actualizar() {


        KafkaProducer<String, Reserva> producer = createKafkaProducer();
        servicio.getAllReservas().
                forEach(
                        reserva ->
                                producer.send(new ProducerRecord<String, Reserva>(inicializacion,  reserva))
                );

        producer.close();

          /*  ListenableFuture<SendResult<String, List<Reserva>>> future = */ //kafkaTemplate.send(actualizacion, servicio.getAllReservas().get(1));
     //   ListenableFuture<SendResult<String, Reserva>> future = kafkaTemplate.send(inicializacion,servicio.getAllReservas().get(0));
//            future.addCallback(new ListenableFutureCallback<SendResult<String, List<Reserva>>>() {
//                @Override
//                public void onSuccess(SendResult<String, List<Reserva>> result) {
//                    log.info("----- LA LISTA DE RESERVAS HA LLEGADO CORRECTAMENTE AL DESTINATARIO -----");
//                }
//                @Override
//                public void onFailure(Throwable ex) {System.err.println("Unable to send message=["  + "] due to : " + ex.getMessage());
//                }
//            });
//                    future.addCallback(new ListenableFutureCallback<SendResult<String, Reserva>>() {
//                        @Override
//                        public void onSuccess(SendResult<String, Reserva> result) {
//                            log.error("TODO HA SALIDO BIEN "+ result.toString());
//                        }
//
//                        @Override
//                        public void onFailure(Throwable ex) {System.err.println("Unable to send message=["  + "] due to : " + ex.getMessage());
//                        }
//            }
//            );
//FIXME FALTAN AJUSTILLOS LEVES
        return ResponseEntity.status(HttpStatus.OK).body("todo bien");
    }

    private static KafkaProducer<String, Reserva> createKafkaProducer() { //FIXME ESTO DEBERÍA MOVERLO A OTRA CLASE
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //props.put(ProducerConfig.CLIENT_ID_CONFIG, CONSUMER_APP_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "bosonit.formacion.appFinal.genericClasses.kafka.serialzer.CustomSerializer");

        return new KafkaProducer(props);
    }


}
