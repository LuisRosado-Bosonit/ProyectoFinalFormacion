package bosonit.formacion.backWeb.Utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class ErrorOutputDTO {


    private int httpCode;
    private String msgError;
    private String type;
    private Date fecha;

    public ErrorOutputDTO(int httpCode, String msgError, String type){
        this.httpCode = httpCode;
        this.msgError = msgError;
        this.type = type;
        this.fecha = new Date(System.currentTimeMillis());
    }
}
