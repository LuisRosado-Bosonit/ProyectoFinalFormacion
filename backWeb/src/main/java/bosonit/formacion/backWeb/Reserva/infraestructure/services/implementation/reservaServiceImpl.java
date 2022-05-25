package bosonit.formacion.backWeb.Reserva.infraestructure.services.implementation;

import bosonit.formacion.backWeb.Reserva.domain.Reserva;
import bosonit.formacion.backWeb.Reserva.infraestructure.repository.autobusRepository;
import bosonit.formacion.backWeb.Reserva.infraestructure.repository.reservaRepository;
import bosonit.formacion.backWeb.Reserva.infraestructure.services.reservaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Service
public class reservaServiceImpl implements reservaService {

    @Autowired
    reservaRepository repositorio;

    @Autowired
    autobusRepository repositorioBus;

    public Optional<Reserva> guardarReserva(Reserva reserva){
        log.warn("----- SE HA CREADO UNA NUEVA RESERVA -----");
        return Optional.of(repositorio.save(reserva));
    }

    public boolean comprobarPlazas(Reserva reserva){
        Integer resultConsulta = repositorioBus.comprobarDisponibilidad(reserva.getCiudadDestino(), reserva.getHoraSalida());
        log.info("----- SE ESTÁ COMPROBANDO LA OCUPACIÓN DE UN AUTOBUS -----");
        return (resultConsulta != null) && (resultConsulta > 0);
    }

    public int comprobarPlazas(String ciudad, int horaSalida, int dia){
        Integer resultConsulta = repositorioBus.comprobarDisponibilidad(ciudad, horaSalida);
        log.info("----- SE ESTÁ COMPROBANDO LA OCUPACIÓN DE UN AUTOBUS -----");
        return resultConsulta;
    }

    public void avisarAlBack(Reserva reserv ){
        KafkaProducer<String, Reserva> producer = createKafkaProducer();
        producer.send(new ProducerRecord<String, Reserva>("addReserva",  reserv));
        log.warn("----- SE HA ENVIADO UNA RESERVA AL BACKEMPRESA PARA QUE LA PROCESE -----"+ reserv);
        producer.close();
    }

    @Override
    public List<Reserva> getAllReservas() {
        log.warn("----- SE ESTÁN CONSULTANDO TODAS LAS RESERVAS -----");
        return repositorio.findAll();
    }

    private static KafkaProducer<String, Reserva> createKafkaProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //props.put(ProducerConfig.CLIENT_ID_CONFIG, CONSUMER_APP_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "bosonit.formacion.backWeb.Kafka.envio.CustomSerializerReserva");

        return new KafkaProducer(props);
    }

}
