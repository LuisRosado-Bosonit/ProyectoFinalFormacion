package bosonit.formacion.appFinal.reservas.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Autobus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @NotNull
    private String matricula;

    @Column
    private boolean averiado;

    @Column
    private Date fechaViaje;

    @Column
    private long horaSalida;

    @Column
    @NotNull
    private String ciudadDestino;

    @Column
    @NotNull
    private int plazas;


}
