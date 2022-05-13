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

//    @Query("SELECT a.plazas FROM Autobus a WHERE a.ciudadDestino = ?1 AND a.horaSalida = '1600'" )
//    public Integer comprobarDisponibilidad(String ciudadDestino, long hora);
//    @Query("SELECT a.averiado FROM Autobus a WHERE a.ciudadDestino = ?1 AND a.horaSalida = '1600'" )
//    public String comprobarAverias(String ciudadDestino, long hora);


        //FIXME LOS VALIDOS SON LOS DE ARRIBA PERO ES NECESARIO REVISARLOS
    @Query("SELECT a.plazas FROM Autobus a WHERE a.ciudadDestino LIKE ?1 AND a.horaSalida = '2000'" ) //WHERE a.ciudadDestino = ?1 and a.horaSalida = '2000'
    public Integer comprobarDisponibilidad(String ciudadDestino, long hora);
    @Query("SELECT a.averiado FROM Autobus a WHERE a.ciudadDestino LIKE ?1 AND a.horaSalida = '2000'" )
    public String comprobarAverias(String ciudadDestino, long hora);

    @Query("SELECT a.id FROM Autobus a WHERE a.ciudadDestino LIKE ?1 AND a.horaSalida = '2000'" )
    public long obtenedID(String ciudadDestino, long hora);

    @Modifying
    @Transactional
    @Query("UPDATE Autobus a SET a.plazas = a.plazas-1 WHERE a.id = ?1 ")
    public void ocuparPlaza(Long id);

    @Query("SELECT * FROM Reserva r WHERE r.fechaReserva = ?1 AND r.horaSalida = ?2 AND r.ciudadDestino")
    public List<Reserva> reservas(Date fecha, long hora, String destino);
}
