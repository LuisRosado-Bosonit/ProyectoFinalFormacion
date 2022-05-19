package bosonit.formacion.appFinal.reservas.infraestructure.kafkaListeners;

import bosonit.formacion.appFinal.genericClasses.kafka.deserializer.deserializadorReserva;
import bosonit.formacion.appFinal.reservas.domain.Reserva;
import bosonit.formacion.appFinal.reservas.infraestructure.DTO.input.inputReservaDTO;
import bosonit.formacion.appFinal.reservas.infraestructure.controllers.reservaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.nio.charset.StandardCharsets;

public class reservaListener {

    @Autowired
    reservaController controladorReservas;

    @KafkaListener(topics = "addReserva", groupId = "web")   //TODO COMO DE BIEN ESTÁ LLAMAR A UN CONTROLADOR ?
    public void addReservaAsincrona(String data){
        deserializadorReserva des = new deserializadorReserva();
        byte[] datos = data.getBytes(StandardCharsets.UTF_8);
        Reserva reserv  = des.deserialize("",datos);
        inputReservaDTO input = new inputReservaDTO();
        controladorReservas.realizarReserva(input.toInput(reserv));
    }
}
