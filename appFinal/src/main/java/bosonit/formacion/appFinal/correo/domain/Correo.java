package bosonit.formacion.appFinal.correo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Correo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column
    private String ciudadDestino;


    @Column
    @NotNull
    private String email;

    @Column
    private Date fechaReserva;

    @Column
    private float horaReserva;

}
