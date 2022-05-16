package bosonit.formacion.appFinal.usuario.infraestructure.controllers;

import bosonit.formacion.appFinal.genericClasses.ErrorOutputDTO;
import bosonit.formacion.appFinal.usuario.infraestructure.DTO.input.inputUsuarioDTO;
import bosonit.formacion.appFinal.usuario.application.Services.Implementations.UsuarioServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class usuarioController {


    @Autowired
    UsuarioServiceImpl servicio;

    @Autowired
    ErrorOutputDTO error;


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v0/usuario/register")
    public ResponseEntity<Object> register(@RequestBody inputUsuarioDTO input){
        try {
            servicio.guardarUsuario(input.toEntity());
        } catch (Exception e) {
            log.warn("----- EL ENDPOINT POST(/api/v0/usuario/register) HA DEVUELTO UN ERROR -----");
            error = new ErrorOutputDTO(e.hashCode(),e.getMessage(), String.valueOf(e.getCause()));
            return ResponseEntity.status(501).body(error);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Se ha registrado al usuario correctamente");
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v0/usuario/login")
    public ResponseEntity<Object> login(@RequestParam("user") String email, @RequestParam("password") String pwd) {
        String rol = "USER";
        if(!servicio.comprobarExistenciaYRol(email,pwd).getFirst()) {
            error = new ErrorOutputDTO(404,
                    "Ningún usuario con esa contraseña se encuentra registrado en la base de datos",
                    "Usuario no encontrado o contraseña incorrecta" );
            return ResponseEntity.status(501).body(error);
        }
        if(servicio.comprobarExistenciaYRol(email,pwd).getSecond())
            rol = "ADMIN";
        log.warn("----- SE HA GENERADO UN TOKEN CON EL ROL "+rol+ " -----");
        return ResponseEntity.status(HttpStatus.OK).body(getJWTToken(email, "ROLE_"+rol));

    }

    private String getJWTToken(String username, String rol) {
        String secretKey = "LlevaLaTararaUnVestidoBlancoLlenoDeCascabelesUnVestidoBlancoLleno";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(rol);

        String token = Jwts
                .builder()
                .setId("BosonitSL")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;
    }
}
