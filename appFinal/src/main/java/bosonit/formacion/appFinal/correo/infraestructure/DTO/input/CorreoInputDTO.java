package bosonit.formacion.appFinal.correo.infraestructure.DTO.input;

import lombok.Data;

import java.util.Date;

@Data
public class CorreoInputDTO {

    private String ciudadDestino;
    private String email;
    private Date fechaReserva;
    private float HoraReserva;

}
