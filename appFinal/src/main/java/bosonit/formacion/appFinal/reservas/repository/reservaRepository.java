package bosonit.formacion.appFinal.reservas.repository;

import bosonit.formacion.appFinal.reservas.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface reservaRepository extends JpaRepository<Reserva, Long> {

}
