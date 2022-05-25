package bosonit.formacion.appFinal.correo.infraestructure.controllers;

import bosonit.formacion.appFinal.correo.services.correoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.Date;

@RestController
public class correoController {

    @Autowired
    correoService servicio;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v0/correos")
    public ResponseEntity<Object> correosEnviados(@QueryParam("destino") String ciudadDestino,
                                                  @QueryParam("fechaInferior") Date fechaInferior,
                                                  @QueryParam("fechaSuperior") Date fechaSuperior,
                                                  @QueryParam("horaInferior") int horaInferior,
                                                  @QueryParam("horaSuperior") int horaSuperior) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(" ");
    }
}
