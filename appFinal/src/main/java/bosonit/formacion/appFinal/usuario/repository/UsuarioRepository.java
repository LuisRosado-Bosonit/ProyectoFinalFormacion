package bosonit.formacion.appFinal.usuario.repository;

import bosonit.formacion.appFinal.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT p FROM Usuario p WHERE correo = ?1")
    public Optional<Usuario> findByEmail (String email);

    @Query(value =  "SELECT p FROM Usuario p WHERE p.correo = ?1 AND p.password = ?2")
    public Optional<Usuario> findRol(String email, String password);
}
