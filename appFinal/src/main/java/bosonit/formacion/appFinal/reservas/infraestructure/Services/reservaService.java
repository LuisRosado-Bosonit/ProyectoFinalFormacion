package bosonit.formacion.appFinal.reservas.infraestructure.Services;


import bosonit.formacion.appFinal.reservas.domain.Reserva;

import java.util.Optional;

public interface reservaService {
    public Optional<Reserva> guardarReserva(Reserva reserva);
}
