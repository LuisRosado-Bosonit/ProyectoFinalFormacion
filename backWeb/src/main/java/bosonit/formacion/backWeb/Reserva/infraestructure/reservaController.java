package bosonit.formacion.backWeb.Reserva.infraestructure;

import bosonit.formacion.backWeb.Reserva.domain.Reserva;
import bosonit.formacion.backWeb.Reserva.infraestructure.DTO.input.inputReservaDTO;
import bosonit.formacion.backWeb.Reserva.infraestructure.services.reservaService;
import bosonit.formacion.backWeb.Utils.ErrorOutputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
public class reservaController {

    @Autowired
    ErrorOutputDTO error;

    @Autowired
    reservaService servicio;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v0/token)")
    public ResponseEntity<Object> login(@RequestParam("user") String email, @RequestParam("password") String pwd) {
        //comprobar en local si se puede hacer la reserva
        //enviar mensaje asincrono a bb si es que si
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v0/reserva")
    public ResponseEntity<Object> realizarReserva(@RequestBody inputReservaDTO input)  {
        Optional<Reserva> reserva = null;
        try {
            reserva = servicio.guardarReserva(input.toEntity());
        } catch (Exception e) {
            error = new ErrorOutputDTO(e.hashCode(),e.getMessage(),String.valueOf(e.getCause()));
            return ResponseEntity.status(503).body(error);
        }
        if(!servicio.comprobarPlazas(reserva.get())) return ResponseEntity.status(HttpStatus.OK).body("ERRRRRRRROR"); //FIXME DEVOLVER RESPUESTA HTML INDICANDO QUE NO HAY PLAZAS
        servicio.avisarAlBack(reserva.get());
        return ResponseEntity.status(HttpStatus.OK).body("Gracias por su reserva, se le enviará un correo con su identificador de la reserva");
    }
}
