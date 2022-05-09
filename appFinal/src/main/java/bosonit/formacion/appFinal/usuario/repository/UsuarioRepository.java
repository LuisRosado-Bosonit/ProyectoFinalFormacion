package bosonit.formacion.appFinal.usuario.repository;

import bosonit.formacion.appFinal.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
