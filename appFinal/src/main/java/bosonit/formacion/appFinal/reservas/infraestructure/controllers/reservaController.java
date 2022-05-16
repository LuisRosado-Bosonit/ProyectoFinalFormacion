package bosonit.formacion.appFinal.reservas.infraestructure.controllers;

import bosonit.formacion.appFinal.correo.domain.Correo;
import bosonit.formacion.appFinal.correo.infraestructure.Utils.TLSEmail;
import bosonit.formacion.appFinal.correo.services.correoService;
import bosonit.formacion.appFinal.genericClasses.ErrorOutputDTO;
import bosonit.formacion.appFinal.reservas.domain.Reserva;
import bosonit.formacion.appFinal.reservas.infraestructure.DTO.input.inputReservaDTO;
import bosonit.formacion.appFinal.reservas.application.Services.reservaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;


@RestController
@Slf4j
public class reservaController {


    @Autowired
    reservaService servicio;

    @Autowired
    correoService servicioCorreo;

    @Autowired
    ErrorOutputDTO error;

    @Autowired
    private TLSEmail email;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v0/reserva")
    public ResponseEntity<Object> login(@RequestBody inputReservaDTO input)  {
        Optional<Reserva> reserva = null;
        try {
            reserva = servicio.guardarReserva(input.toEntity());
        } catch (Exception e) {
            email.mandarEmail(input.getCorreo(),"Error en la reserva","Se ha producido un error al realizar su reserva ");
            error.setFecha(new Date(System.currentTimeMillis()));
            error.setHttpCode(e.hashCode());
            error.setType(String.valueOf(e.getCause()));
            error.setMsgError(e.getMessage());
            return ResponseEntity.status(503).body(error);
        }
        email.mandarEmail(input.getCorreo(),"Confirmación de la reserva","El identificador de su reserva es " +
                                                        String.valueOf(reserva.get().getId()));
        Correo mensj = new Correo(input);
        servicioCorreo.guardarCorreo(mensj);
        return ResponseEntity.status(HttpStatus.OK).body("La reserva se ha añadido satisfactoriamente");
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v0/reservas/consultarOcupadas")
    public ResponseEntity<Object> consultarReservas(@RequestParam("Fecha") Date fecha,
                                                    @RequestParam("Hora") long hora,
                                                    @RequestParam("Destino") String destino
                                                    ) {
//        Optional<Reserva> reserva =  servicio.guardarReserva(input.toEntity());
//        if(reserva.isEmpty()){
//            email.mandarEmail(input.getCorreo(),"Error en la reserva","Se ha producido un error al realizar su reserva ");
//            return ResponseEntity.status(503).body(error);
//        }
//        email.mandarEmail(input.getCorreo(),"Confirmación de la reserva","El identificador de su reserva es " +
//                String.valueOf(reserva.get().getId()));

        return ResponseEntity.status(HttpStatus.OK).body(servicio.consultarPlazasOcupadas(fecha,hora,destino));  //TODO ES CORRECTO QUE LOS METODOS DEVUELVAN EXCEPCIONES O MEJOR CODIGOS DE ERROR ?
    }

}
