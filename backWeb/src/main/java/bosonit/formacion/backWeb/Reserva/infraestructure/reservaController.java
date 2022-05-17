package bosonit.formacion.backWeb.Reserva.infraestructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class reservaController {
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v0/token)")
    public ResponseEntity<Object> login(@RequestParam("user") String email, @RequestParam("password") String pwd) {
        //comprobar en local si se puede hacer la reserva
        //enviar mensaje asincrono a bb si es que si
        return ResponseEntity.status(HttpStatus.OK).body("");
    }


}
