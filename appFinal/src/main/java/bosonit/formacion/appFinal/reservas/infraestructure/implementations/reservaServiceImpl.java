package bosonit.formacion.appFinal.reservas.infraestructure.implementations;


import bosonit.formacion.appFinal.reservas.domain.Reserva;
import bosonit.formacion.appFinal.reservas.infraestructure.Services.reservaService;
import bosonit.formacion.appFinal.reservas.repository.reservaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class reservaServiceImpl implements reservaService {

    @Autowired
    reservaRepository repositorio;
    @Override
    public Optional<Reserva> guardarReserva(Reserva reserva) throws Exception {
        if(repositorio.comprobarDisponibilidad(reserva.getCiudadDestino(), Long.parseLong(reserva.getHora())) <= 0
            && repositorio.comprobarAverias(reserva.getCiudadDestino(), Long.parseLong(reserva.getHora()))) {  //TODO DEVOLVER UNA EXCEPCION ES LA MEJOR SOLUCION ??
            log.error("----- SE HA INTENTADO REALIZAR UNA RESERVA PARA UN AUTOBUS COMPLETO O AVERIADO ------");
            throw new Exception("No hay disponibilidad para la ruta y hora seleccionada");
            }
        log.warn("----- SE HA CREADO UNA NUEVA RESERVA -----");
        return Optional.of(repositorio.save(reserva)) ;
    }
}
