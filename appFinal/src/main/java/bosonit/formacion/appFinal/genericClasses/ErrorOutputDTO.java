package bosonit.formacion.appFinal.genericClasses;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class ErrorOutputDTO {

    String mensajeError;
    int codigoErro;
    Date momentoProduccion;
}
