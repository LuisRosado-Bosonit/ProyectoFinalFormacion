package bosonit.formacion.backWeb.Reserva.infraestructure;

import bosonit.formacion.backWeb.Kafka.recepcion.deserializador;
import bosonit.formacion.backWeb.Reserva.domain.Reserva;
import bosonit.formacion.backWeb.Reserva.infraestructure.repository.reservaRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.retrytopic.DestinationTopic;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Slf4j
@Component
public class InicilizarReservas {

    @Autowired
    reservaRepository repositorio;

    @KafkaListener(topics = "inicializacion", groupId = "web")
    public void inicializarreDeCero(String dat) {
        deserializador des = new deserializador();
        byte[] datos = dat.getBytes(StandardCharsets.UTF_8);
        Reserva reserv  = des.deserialize("",datos);
        Optional<Reserva> resultado = Optional.of(repositorio.save(reserv));
        if(resultado.isEmpty()){
            log.error("  "); //FIXME FALTAN COMPROBACIONES VARIAS Y GESTION DE ERRORES
        }else{
            log.info("----- SE HA RECIBIDO UNA RESERVA NUEVA DESDE EL BACKEMPRESA -----");
        }
    }

    private static KafkaConsumer<String, Reserva> createKafkaConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //props.put(ConsumerConfig.CLIENT_ID_CONFIG, CONSUMER_APP_ID);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "web");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "bosonit.formacion.backWeb.Kafka.recepcion.deserializador");

        return new KafkaConsumer<>(props);
    }

//    @KafkaListener(topics = "${message.topic.name2:profesorp-group}", groupId = "${message.group.name:profegroup}")
//    public void listenTopic2(String message) {
//        System.out.println("Recieved Message of topic2 in  listener "+message);
//    }

}
