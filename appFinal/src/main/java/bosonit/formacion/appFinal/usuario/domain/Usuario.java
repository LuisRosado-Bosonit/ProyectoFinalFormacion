package bosonit.formacion.appFinal.usuario.domain;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column
    private String nombre;
    @Column
    private String apellidos;

    @NotNull
    @Size(min = 8, max= 64, message =  "La contraseña no tiene la longitud recomendada ")
    private String password;

    @Size(min = 3, max = 18, message = "La longitud del número de teléfono no se ajusta a lo permitido")
    private String numero;

    @NotNull
    @Size(min = 3, max= 64, message = "La longitud del correo suministrado no se ajusta al tamaño permitido, entre 3 y 64 caracteres")
    private String correo;
    @Column
    private Date fechaRegistro;
    @Column
    private boolean administrador;

}
