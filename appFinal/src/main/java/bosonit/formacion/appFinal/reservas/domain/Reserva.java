package bosonit.formacion.appFinal.reservas.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @GenericGenerator(name = "product_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "product_sequence"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "12"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "5"),
            @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo")
    })
    private Long id;

    @Column
    private String ciudadDestino;

    @NotNull
    private String nombre;

    @NotNull
    private String apellidos;

    @NotNull
        @Size(min = 6, max= 20, message = "La longitud mínima del teléfono debe ser de 6 dígitos")
    private String telefono;

    @NotNull
        @Size(min = 3, max= 64, message = "La longitud del correo suministrado no se ajusta al tamaño permitido, entre 3 y 64 caracteres")
    private String correo;


    private Date fechaReserva;

    private int horaSalida;

}
