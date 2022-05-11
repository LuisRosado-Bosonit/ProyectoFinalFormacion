package bosonit.formacion.appFinal.genericClasses;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class ErrorOutputDTO {


    private int httpCode;
    private String msgError;
    private String tipe;
    private Date fecha;
}
