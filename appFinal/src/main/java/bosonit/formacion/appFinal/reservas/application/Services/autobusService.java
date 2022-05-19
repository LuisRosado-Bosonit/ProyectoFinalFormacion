package bosonit.formacion.appFinal.reservas.application.Services;

import bosonit.formacion.appFinal.reservas.domain.Autobus;
import bosonit.formacion.appFinal.reservas.domain.Reserva;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface autobusService {

    public List<Autobus> getAllBuses();

    public List<Reserva> consultarPlazasOcupadas(Date fecha, int hora, String destino) throws Exception;
}
