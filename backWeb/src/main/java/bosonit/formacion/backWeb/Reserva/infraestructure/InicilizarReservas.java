package bosonit.formacion.backWeb.Reserva.infraestructure;

import bosonit.formacion.backWeb.Autobus.domain.Autobus;
import bosonit.formacion.backWeb.Kafka.recepcion.deserializadorAutobus;
import bosonit.formacion.backWeb.Kafka.recepcion.deserializadorReserva;
import bosonit.formacion.backWeb.Reserva.domain.Reserva;
import bosonit.formacion.backWeb.Reserva.infraestructure.repository.autobusRepository;
import bosonit.formacion.backWeb.Reserva.infraestructure.repository.reservaRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Component
public class InicilizarReservas {

    @Autowired
    reservaRepository repositorio;

    @Autowired
    autobusRepository repositorioAutobuses;
    @KafkaListener(topics = "inicializacionReservas", groupId = "web")
    public void inicializarreDeCero(String dat) {
        deserializadorReserva des = new deserializadorReserva();
        deserializadorAutobus busDeserializer = new deserializadorAutobus();
        byte[] datos = dat.getBytes(StandardCharsets.UTF_8);
        Reserva reserv  = des.deserialize("",datos);
        Optional<Reserva> resultado = Optional.of(repositorio.save(reserv));
        if(resultado.isEmpty()){
            log.error("  "); //FIXME FALTAN COMPROBACIONES VARIAS Y GESTION DE ERRORES
        }else{
            log.info("----- SE HA RECIBIDO UNA RESERVA NUEVA DESDE EL BACKEMPRESA -----");
        }
    }
    @KafkaListener(topics = "inicializacionAutobuses", groupId = "web")
    public void inicializarreDeUna(String dat) {
        deserializadorAutobus busDeserializer = new deserializadorAutobus();
        byte[] datos = dat.getBytes(StandardCharsets.UTF_8);
        Autobus autob  = busDeserializer.deserialize("",datos);
        Optional<Autobus> resultado = Optional.of(repositorioAutobuses.save(autob));
        if(resultado.isEmpty()){
            log.error("  "); //FIXME FALTAN COMPROBACIONES VARIAS Y GESTION DE ERRORES
        }else{
            log.info("----- SE HA RECIBIDO UN NUEVO AUTOBUS DESDE EL BACKEMPRESA -----");
        }
    }

    private static KafkaConsumer<String, Reserva> createKafkaConsumerReserva() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //props.put(ConsumerConfig.CLIENT_ID_CONFIG, CONSUMER_APP_ID);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "web");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "bosonit.formacion.backWeb.Kafka.recepcion.deserializadorReserva");

        return new KafkaConsumer<>(props);
    }
    private static KafkaConsumer<String, Autobus> createKafkaConsumerBus() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //props.put(ConsumerConfig.CLIENT_ID_CONFIG, CONSUMER_APP_ID);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "web");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "bosonit.formacion.backWeb.Kafka.recepcion.deserializadorAutobus");

        return new KafkaConsumer<>(props);
    }

}
