package bosonit.formacion.appFinal.reservas.application.Services.implementations;


import bosonit.formacion.appFinal.reservas.domain.Reserva;
import bosonit.formacion.appFinal.reservas.application.Services.reservaService;
import bosonit.formacion.appFinal.reservas.infraestructure.repository.reservaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class reservaServiceImpl implements reservaService {

    @Autowired
    reservaRepository repositorio;
    @Override
    public Optional<Reserva> guardarReserva(Reserva reserva) throws Exception {
        Integer resultConsulta = repositorio.comprobarDisponibilidad(reserva.getCiudadDestino(), reserva.getHoraSalida()); //TODO GUARDO EL RESULTADO ASI PARA NO EJECUTAR LA CONSULTA PARA COMPROBACION, NO SE SI HAGO BIEN
        String resultContultaAveria = repositorio.comprobarAverias(reserva.getCiudadDestino(), reserva.getHoraSalida());
        if((resultConsulta == null) || (resultConsulta <= 0)    //FIXME PARTE DEL IF COMENTADA
            || (resultContultaAveria == null) /*|| (resultContultaAveria == "true")*/) {  //TODO DEVOLVER UNA EXCEPCION ES LA MEJOR SOLUCION ??
            log.error("----- SE HA INTENTADO REALIZAR UNA RESERVA PARA UN AUTOBUS COMPLETO O AVERIADO ------");
            throw new Exception("No hay disponibilidad para la ruta y hora seleccionada");
            }
        log.warn("----- SE HA CREADO UNA NUEVA RESERVA -----");
        return Optional.of(repositorio.save(reserva)) ;
    }
}
