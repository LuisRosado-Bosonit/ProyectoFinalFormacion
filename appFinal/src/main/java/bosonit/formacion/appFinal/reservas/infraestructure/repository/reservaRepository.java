package bosonit.formacion.appFinal.reservas.infraestructure.repository;

import bosonit.formacion.appFinal.reservas.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface reservaRepository extends JpaRepository<Reserva, Long> {



    @Query("SELECT r FROM Reserva r WHERE r.fechaReserva LIKE ?1 AND r.horaSalida = ?2 AND r.ciudadDestino = ?3")
    public List<Reserva> reservas(Date fecha, long hora, String destino);
}
