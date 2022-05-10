package bosonit.formacion.appFinal.reservas.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table
@Data
public class Reserva {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String ciudadDestino;

    @NotNull
    private String nombre;

    @NotNull
    private String apellidos;

    @NotNull
    private String telefono;

    @NotNull
    @Size(min = 3, max= 64, message = "La longitud del correo suministrado no se ajusta al tamaño permitido, entre 3 y 64 caracteres")
    private String correo;

    private Date fechaReserva;

    private long hora;

}
