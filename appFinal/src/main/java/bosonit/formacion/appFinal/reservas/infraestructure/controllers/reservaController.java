package bosonit.formacion.appFinal.reservas.infraestructure.controllers;

import bosonit.formacion.appFinal.genericClasses.ErrorOutputDTO;
import bosonit.formacion.appFinal.reservas.infraestructure.DTO.input.inputReservaDTO;
import bosonit.formacion.appFinal.reservas.infraestructure.Services.reservaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Slf4j
public class reservaController {


    @Autowired
    reservaService servicio;

    @Autowired
    ErrorOutputDTO error;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v0/reserva")
    public ResponseEntity<Object> login(@RequestBody inputReservaDTO input) throws Exception {
        if(servicio.guardarReserva(input.toEntity()).isEmpty())
            return ResponseEntity.status(503).body(error); //FIXME FALTA AÑADIR LOS DATOS DEL ERROR
        return ResponseEntity.status(HttpStatus.OK).body("La reserva se ha añadido satisfactoriamente");
    }





}
