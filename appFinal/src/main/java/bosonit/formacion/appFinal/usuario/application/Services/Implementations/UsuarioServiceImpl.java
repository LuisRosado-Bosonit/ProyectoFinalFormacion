package bosonit.formacion.appFinal.usuario.application.Services.Implementations;


import bosonit.formacion.appFinal.usuario.domain.Usuario;
import bosonit.formacion.appFinal.usuario.application.Services.UsuarioService;
import bosonit.formacion.appFinal.usuario.infraestructure.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    UsuarioRepository repositorio;


    @Override
    public Optional<Usuario> guardarUsuario(Usuario p) {
        repositorio.save(p);
        log.warn("----- SE HA GUARDADO UN USUARIO EN LA BASE DE DATOS -----");
        log.info("----- SE HA AÑADIDO AL USUARIO "+p+" A LA BASE DE DATOS -----");
        return repositorio.findByEmail(p.getCorreo());
    }

    @Override
    public Pair<Boolean, Boolean> comprobarExistenciaYRol(String correo, String password) {
        log.info("----- SE HA COMPROBADOR EL ROL DE UN USUARIO DE LA BASE DE DATOS -----");
        System.out.println("    "+repositorio.findRol(correo, password)+ "    ");
        if(repositorio.findRol(correo, password).isEmpty())
            return Pair.of(
                    false,
                    false);
        return Pair.of(
                true,
                repositorio.findRol(correo, password).get().isAdministrador());
    }
}
