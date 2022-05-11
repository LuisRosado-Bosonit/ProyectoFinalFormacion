package bosonit.formacion.appFinal.reservas.repository;

import bosonit.formacion.appFinal.reservas.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface reservaRepository extends JpaRepository<Reserva, Long> {

//    @Query("SELECT a.plazas FROM Autobus a WHERE a.ciudadDestino = ?1 AND a.horaSalida = '1600'" )
//    public Integer comprobarDisponibilidad(String ciudadDestino, long hora);
//    @Query("SELECT a.averiado FROM Autobus a WHERE a.ciudadDestino = ?1 AND a.horaSalida = '1600'" )
//    public String comprobarAverias(String ciudadDestino, long hora);


        //FIXME LOS VALIDOS SON LOS DE ARRIBA PERO ES NECESARIO REVISARLOS
    @Query("SELECT a.plazas FROM Autobus a WHERE a.ciudadDestino = ?1 AND a.horaSalida = '2000'" ) //WHERE a.ciudadDestino = ?1 and a.horaSalida = '2000'
    public Integer comprobarDisponibilidad(String ciudadDestino, long hora);
    @Query("SELECT a.averiado FROM Autobus a WHERE a.ciudadDestino = ?1 AND a.horaSalida = '2000'" )
    public String comprobarAverias(String ciudadDestino, long hora);
}
