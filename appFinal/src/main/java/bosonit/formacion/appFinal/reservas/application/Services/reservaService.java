package bosonit.formacion.appFinal.reservas.application.Services;


import bosonit.formacion.appFinal.reservas.domain.Reserva;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface reservaService {
    public Optional<Reserva> guardarReserva(Reserva reserva) throws Exception;

    public List<Reserva> consultarPlazasOcupadas(Date fecha, int hora, String destino) throws Exception;

    public List<Reserva> getAllReservas();
}
