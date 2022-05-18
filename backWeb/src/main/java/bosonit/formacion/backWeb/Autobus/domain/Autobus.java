package bosonit.formacion.backWeb.Autobus.domain;

import lombok.Data;

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
    private int horaSalida;

    @Column
    @NotNull
    private String ciudadDestino;

    @Column
    @NotNull
    private int plazas;


}
