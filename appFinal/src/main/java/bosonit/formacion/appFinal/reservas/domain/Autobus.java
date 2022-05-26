package bosonit.formacion.appFinal.reservas.domain;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name = "AUTOBUS")
public class Autobus {
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
