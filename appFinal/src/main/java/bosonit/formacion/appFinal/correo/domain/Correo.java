package bosonit.formacion.appFinal.correo.domain;

import bosonit.formacion.appFinal.reservas.infraestructure.DTO.input.inputReservaDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
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

    @FutureOrPresent
    @Column
    private Date fechaReserva;

    @Column
    private float horaReserva;

    public Correo(String destino, Date fechaReserva, float horaReserva){
        this.ciudadDestino = destino;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
    }

    public Correo(inputReservaDTO dto){
        this.ciudadDestino = dto.getCiudadDestino();
        this.fechaReserva = dto.getFechaReserva();
        this.horaReserva = dto.getHoraSalida();
        this.email = dto.getCorreo();
    }

}
