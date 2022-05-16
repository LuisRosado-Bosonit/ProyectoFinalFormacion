package bosonit.formacion.appFinal.correo.infraestructure.DTO.output;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CorreoOutputDTO {

    private String ciudadDestino;
    private String email;
    private Date fechaReserva;
    private float horaReserva;
}
