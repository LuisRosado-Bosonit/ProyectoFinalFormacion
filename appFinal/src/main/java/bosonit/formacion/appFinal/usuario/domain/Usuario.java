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



    private String nombre;

    private String apellidos;

    private String numero;

    @NotNull
    @Size(min = 3, max= 64, message = "La longitud del correo suministrado no se ajusta al tamaño permitido, entre 3 y 64 caracteres")
    private String correo;

    private Date fechaRegistro;

    private boolean administrador;

}
