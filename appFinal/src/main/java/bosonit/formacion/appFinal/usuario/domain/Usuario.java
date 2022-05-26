package bosonit.formacion.appFinal.usuario.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @GenericGenerator(name = "product_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "product_sequence"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "12"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "5"),
            @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
    })
    private Integer id;


    @Column
    private String nombre;
    @Column
    private String apellidos;

    @NotNull
    @Size(min = 8, max= 64, message =  "La contraseña no tiene la longitud recomendada ")
    private String password;

    @NotNull
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
