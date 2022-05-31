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
    @PostMapping("/api/v0/reserva")
    public ResponseEntity<Object> realizarReserva(@RequestBody inputReservaDTO input)  {
        Optional<Reserva> reserva ;
        try {
            reserva = servicio.guardarReserva(input.toEntity());
        } catch (Exception e) {
            error = new ErrorOutputDTO(e.hashCode(),e.getMessage(),String.valueOf(e.getCause()));
            return ResponseEntity.status(503).body(error);
        }
        try {
            servicio.comprobarPlazas(reserva.get());
        } catch (Exception e) {
            error = new ErrorOutputDTO(e.hashCode(),e.getMessage(),String.valueOf(e.getCause()));
            return ResponseEntity.status(503).body(error);
        }
        servicio.avisarAlBack(reserva.get());
        return ResponseEntity.status(HttpStatus.OK).body("Gracias por su reserva, se le enviará un correo con su identificador de la reserva");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v0/reserva")
    public ResponseEntity<Object> consultarPlazas(@RequestParam("hora") int hora,
                                                  @RequestParam("dia") int dia,
                                                  @RequestParam("destino") String destino) throws Exception {
        Integer plazas ;
        try {
            plazas = servicio.comprobarPlazas(destino,hora,dia);
        } catch (Exception e) {
            error = new ErrorOutputDTO(e.hashCode(),e.getMessage(),String.valueOf(e.getCause()));
            return ResponseEntity.status(500).body(error);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Las plazas disponibles para el trayecto solicitado son "+
                plazas+
                " plazas");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v0/reserva/getAll")
    public ResponseEntity<Object> consultarReservas( )  {
        return ResponseEntity.status(HttpStatus.OK).body(servicio.getAllReservas());
    }

}
