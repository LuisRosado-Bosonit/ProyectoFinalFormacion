package bosonit.formacion.appFinal.usuario.infraestructure.DTO.input;

import bosonit.formacion.appFinal.usuario.domain.Usuario;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@Data
public class inputUsuarioDTO {

    private String nombre;
    private String apellidos;
    private String numero;
    private String correo;
    private String password;
    private Date fechaRegistro;
    private boolean administrador;

    public Usuario toEntity() throws Exception {
        if(correo == null || password == null || numero == null
        || password.length() < 8 || password.length() > 64
        || numero.length() > 18 ||numero.length() < 3
        || correo.length() > 64 || correo.length() < 3
        || !correo.contains("@"))
            throw new Exception("Faltan parámetros por especificar, o la longitud de ellos no es correcta");
        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre);
        nuevo.setApellidos(apellidos);
        nuevo.setNumero(numero);
        nuevo.setCorreo(correo);
        nuevo.setPassword(password);
        nuevo.setFechaRegistro(new Date(System.currentTimeMillis()));
        nuevo.setAdministrador(administrador);
        return nuevo;
    }

}
