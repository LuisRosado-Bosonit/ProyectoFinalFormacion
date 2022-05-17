package bosonit.formacion.backWeb.Reserva.infraestructure.repository;

import bosonit.formacion.backWeb.Reserva.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface reservaRepository  extends JpaRepository<Reserva, Long> {

}
