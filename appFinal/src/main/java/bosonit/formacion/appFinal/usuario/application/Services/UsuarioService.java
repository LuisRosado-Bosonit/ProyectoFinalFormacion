package bosonit.formacion.appFinal.usuario.application.Services;

import bosonit.formacion.appFinal.usuario.domain.Usuario;
import org.springframework.data.util.Pair;


import java.util.Optional;


public interface UsuarioService {

    public Optional<Usuario> guardarUsuario(Usuario p);
    public Pair<Boolean, Boolean> comprobarExistenciaYRol(String correo, String password);
}
