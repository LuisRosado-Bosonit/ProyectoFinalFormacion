package bosonit.formacion.appFinal.correo.services.implementations;

import bosonit.formacion.appFinal.correo.domain.Correo;
import bosonit.formacion.appFinal.correo.infraestructure.repository.correoRepository;
import bosonit.formacion.appFinal.correo.services.correoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class correoServiceImpl implements correoService {

    @Autowired
    correoRepository repositorio;
    @Override
    public Optional<Correo> guardarCorreo(Correo c) {
        log.info("----- SE HA GUARDADO UN NUEVO CORREO EN LA BASE DE DATOS -----");
        return Optional.of(repositorio.save(c));
    }

    @Override
    public List<Correo> getAll(String destino, Date fechaInferior, Date fechaSuperior, long horaInferior, long horaSuperior) {
        log.warn("----- SE ESTÁ REALIZANDO UNA BÚSQUEDA EN LOS CORREOS ENVIADOS -----");
        return repositorio.correosEnviados(destino, fechaInferior, fechaSuperior, horaInferior,horaSuperior);
    }


}
