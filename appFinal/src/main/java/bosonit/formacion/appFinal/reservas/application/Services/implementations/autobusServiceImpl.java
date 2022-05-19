package bosonit.formacion.appFinal.reservas.application.Services.implementations;

import bosonit.formacion.appFinal.reservas.application.Services.autobusService;
import bosonit.formacion.appFinal.reservas.domain.Autobus;
import bosonit.formacion.appFinal.reservas.domain.Reserva;
import bosonit.formacion.appFinal.reservas.infraestructure.repository.autobusRepository;
import bosonit.formacion.appFinal.reservas.infraestructure.repository.reservaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class autobusServiceImpl implements autobusService {

    @Autowired
    private autobusRepository repositorio;

    @Autowired
    private reservaRepository repositorioReservas;

    public List<Autobus> getAllBuses(){
        return repositorio.findAll();
    }

    @Override
    public List<Reserva> consultarPlazasOcupadas(Date fecha, int hora, String destino) throws Exception {
        log.info("----- SE ESTÁN CONSULTANDO LAS RESERVAS REALIZADAS PARA UN TRAYECTO -----");
        if(fecha == null || hora > 24 || hora < 0 || ( !destino.contains("Madrid")
                && !destino.contains("Valencia")
                && !destino.contains("Bilbao")
                && !destino.contains("Barcelona")) )
            throw new NoSuchFieldException("Faltan parámetros por especificar en la consulta o el destino no existe");
        return repositorioReservas.reservas(fecha, hora, destino); //FIXME FALTAN COMPROBACIONES POR REALIZAR
    }

}
