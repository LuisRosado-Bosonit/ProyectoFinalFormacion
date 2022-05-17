package bosonit.formacion.backWeb.Reserva.infraestructure;

import bosonit.formacion.backWeb.Reserva.domain.Reserva;
import bosonit.formacion.backWeb.Reserva.infraestructure.repository.reservaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class InicilizarReservas {

    @Autowired
    reservaRepository repositorio;

//    @KafkaListener(topics = "inicializacion", groupId = "web")
//    public void inicializarDeCero(List<Reserva> reservas) {
//        log.warn("----- SE ESTÁ INICIALIZANDO LA BASE DE DATOS DE BACKWEB -----");
//        repositorio.saveAll(reservas);
//        log.warn("----- SE HAN GUARDADO "+ repositorio.count() +" RESERVAS EN LA BASE DE DATOS -----");
//    }
    @KafkaListener(topics = "inicializacion", groupId = "web")
    public void inicializarreDeCero(String MESJ) {
        log.error("RECIBIDO MENSAJE " + MESJ);
        System.out.println("RECIBIDO MENSAJE "+ MESJ);
    }

//    @KafkaListener(topics = "${message.topic.name2:profesorp-group}", groupId = "${message.group.name:profegroup}")
//    public void listenTopic2(String message) {
//        System.out.println("Recieved Message of topic2 in  listener "+message);
//    }

}
