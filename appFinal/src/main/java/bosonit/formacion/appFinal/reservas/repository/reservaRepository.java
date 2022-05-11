package bosonit.formacion.appFinal.reservas.repository;

import bosonit.formacion.appFinal.reservas.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface reservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT a.plazas FROM Autobus a WHERE a.ciudadDestino = ?1 AND a.horaSalida = hora" )
    public int comprobarDisponibilidad(String ciudadDestino, long hora);
    @Query("SELECT a.averiado FROM Autobus a WHERE a.ciudadDestino = ?1 AND a.horaSalida = hora" )
    public boolean comprobarAverias(String ciudadDestino, long hora);
}
