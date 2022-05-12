package bosonit.formacion.appFinal.reservas.infraestructure.controllers;


import bosonit.formacion.appFinal.genericClasses.ErrorOutputDTO;
import bosonit.formacion.appFinal.reservas.domain.Reserva;
import bosonit.formacion.appFinal.reservas.infraestructure.DTO.input.inputReservaDTO;
import bosonit.formacion.appFinal.reservas.infraestructure.repository.autobusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class autobusController {

    @Autowired
    autobusRepository repositorio;

    @Autowired
    ErrorOutputDTO error;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v0/autobus")
    public ResponseEntity<Object> plazasLibres(@RequestParam("id") long ID_BUS) throws Exception {
        if(repositorio.obtenerBus(ID_BUS) == null)
            return ResponseEntity.status(503).body(error); //FIXME FALTA AÑADIR LOS DATOS DEL ERROR AL OBJETO QUE DEVUELVE
        return ResponseEntity.status(HttpStatus.OK).body(repositorio.plazasDisponibles(ID_BUS));
    }
}
