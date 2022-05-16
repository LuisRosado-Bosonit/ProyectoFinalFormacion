package bosonit.formacion.appFinal.correo.services;

import bosonit.formacion.appFinal.correo.domain.Correo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface correoService {
    public Optional<Correo> guardarCorreo(Correo c);

    public List<Correo> getAll(String destino, Date fechaInferior, Date fechaSuperior, long horaInferior, long horaSuperior);
}
