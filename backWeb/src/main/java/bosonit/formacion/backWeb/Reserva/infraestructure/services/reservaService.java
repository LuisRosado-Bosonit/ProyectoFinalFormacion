package bosonit.formacion.backWeb.Reserva.infraestructure.services;

import bosonit.formacion.backWeb.Reserva.domain.Reserva;

import java.util.Optional;

public interface reservaService {

    public Optional<Reserva> guardarReserva(Reserva reserva) throws Exception;

    public boolean comprobarPlazas(Reserva reserva);
    public int comprobarPlazas(String ciudad, int horaSalida, int dia);

    public void avisarAlBack(Reserva reserv );
}
