package bosonit.formacion.backWeb.Reserva.infraestructure.services.implementation;

import bosonit.formacion.backWeb.Reserva.domain.Reserva;
import bosonit.formacion.backWeb.Reserva.infraestructure.repository.autobusRepository;
import bosonit.formacion.backWeb.Reserva.infraestructure.repository.reservaRepository;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Slf4j
@Service
public class reservaServiceImpl {

    @Autowired
    reservaRepository repositorio;

    @Autowired
    autobusRepository repositorioBus;

    public Optional<Reserva> guardarReserva(Reserva reserva) throws Exception {
       // Integer resultConsulta = repositorioBus.comprobarDisponibilidad(reserva.getCiudadDestino(), reserva.getHoraSalida()); //TODO segun dicen hay que guardar la reserva aunque no haya sitio en el bus
//        if((resultConsulta == null) || (resultConsulta <= 0)) {
//            log.error("----- SE HA INTENTADO REALIZAR UNA RESERVA PARA UN AUTOBUS COMPLETO------");
//            throw new Exception("No hay disponibilidad para la ruta y hora seleccionada");
//        }
        log.warn("----- SE HA CREADO UNA NUEVA RESERVA -----");
        //repositorioBus.ocuparPlaza(repositorioBus.obtenedID(reserva.getCiudadDestino(), reserva.getHoraSalida()));
       // log.info("----- SE HA RESTADO UNA PLAZA DISPONIBLE AL AUTOBUS DE LA RESERVA ACTUAL -----");
        return Optional.of(repositorio.save(reserva));
    }

    public boolean comprobarPlazas(Reserva reserva){
        Integer resultConsulta = repositorioBus.comprobarDisponibilidad(reserva.getCiudadDestino(), reserva.getHoraSalida());
        log.info("----- SE ESTÁ COMPROBANDO LA OCUPACIÓN DE UN AUTOBUS -----");
        if((resultConsulta == null) || (resultConsulta <= 0)) return false;
        return true;
    }

    public void avisarAlBack(Reserva reserv ){
//        URL url = new URL("https://www.Pharos.sh.com");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    }

}
