package bosonit.formacion.appFinal.reservas.infraestructure.repository;

import bosonit.formacion.appFinal.reservas.domain.Autobus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface autobusRepository extends JpaRepository<Autobus, Long> {

    @Query("SELECT a.plazas FROM Autobus a WHERE a.id = ?1 ")
    public int plazasDisponibles(Long id);

    @Query("SELECT a.plazas FROM Autobus a WHERE a.id = ?1" )
    public Integer obtenerBus(long id);
}
