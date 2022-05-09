package bosonit.formacion.appFinal.usuario.infraestructure;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class usuarioController {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v0/reserva")
    public ResponseEntity<Object> login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
        String rol = "USER";
        //if (persona.auth(username, pwd)) rol = "ADMIN" ;
        System.out.println("El rol definido es "+rol);
        return ResponseEntity.status(HttpStatus.OK).body(getJWTToken(username, "ROLE_"+rol));

    }

    private String getJWTToken(String username, String rol) {
        String secretKey = "LlevaLaTararaUnVestidoBlancoLlenoDeCascabeles";
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
