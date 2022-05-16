package bosonit.formacion.appFinal.correo.infraestructure.repository;

import bosonit.formacion.appFinal.correo.domain.Correo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface correoRepository extends JpaRepository<Correo, Long> {


    @Query("SELECT c FROM Correo c WHERE c.ciudadDestino LIKE ?1 AND c.fechaReserva BETWEEN ?2 AND ?3 AND c.horaReserva BETWEEN ?4 AND ?5")
    public List<Correo> correosEnviados(String ciudadDestino, Date fechaInferior, Date fechaSuperior, long horaInferior, long horaDuperior);
}
