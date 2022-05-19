package bosonit.formacion.backWeb.Reserva.infraestructure.repository;

import bosonit.formacion.backWeb.Autobus.domain.Autobus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface autobusRepository extends JpaRepository<Autobus, Long> {

    @Query("SELECT a.plazas FROM Autobus a WHERE a.ciudadDestino LIKE ?1 AND a.horaSalida = ?2" ) //WHERE a.ciudadDestino = ?1 and a.horaSalida = '2000'
    public Integer comprobarDisponibilidad(String ciudadDestino, int hora);

    @Modifying
    @Transactional
    @Query("UPDATE Autobus a SET a.plazas = a.plazas-1 WHERE a.id = ?1 ")
    public void ocuparPlaza(Long id);

    @Query("SELECT a.id FROM Autobus a WHERE a.ciudadDestino LIKE ?1 AND a.horaSalida = ?2" )
    public long obtenedID(String ciudadDestino, int hora);
}
