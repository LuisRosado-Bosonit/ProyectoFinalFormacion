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

    public Usuario toEntity() throws Exception { //TODO SI ALGUN PARAMETRO NO NULLABLE ES NULL MEJOR EXCEPCION O HTTPSTATUS DE ERROR ?
        if(correo == null || password == null ) throw new Exception(""); //FIXME FALTAN COMPROBACIONES SOBRE CORREO Y CONTRASEÑA EN EL INPUTDTO DE USUARIO
        Usuario nuevo = new Usuario();
        nuevo.setCorreo(correo);
        nuevo.setPassword(password);
        nuevo.setFechaRegistro(new Date(System.currentTimeMillis()));
        return nuevo;
    }

}
