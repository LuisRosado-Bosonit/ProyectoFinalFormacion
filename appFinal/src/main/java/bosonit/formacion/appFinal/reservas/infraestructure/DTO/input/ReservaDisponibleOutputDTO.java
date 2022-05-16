package bosonit.formacion.appFinal.reservas.infraestructure.DTO.input;

import lombok.Data;

import java.util.Date;

@Data
public class ReservaDisponibleOutputDTO {

    private String ciudadDestino;
    private Date fechaSalida;
    private int horaSalida;
    private Integer numeroPlazas;
}
