package bosonit.formacion.backWeb.Reserva.infraestructure.repository;

import bosonit.formacion.backWeb.Autobus.domain.Autobus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface autobusRepository extends JpaRepository<Autobus, Long> {
}
