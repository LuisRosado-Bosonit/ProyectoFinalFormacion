package bosonit.formacion.appFinal.reservas.infraestructure.DTO.input;

import bosonit.formacion.appFinal.reservas.domain.Reserva;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
public class inputReservaDTO {

    private String ciudadDestino;

    @NotNull
    private String nombre;

    @NotNull
    private String apellidos;

    @NotNull
    private String telefono;

    @NotNull
    @Size(min = 3, max= 64, message = "La longitud del correo suministrado no se ajusta al tamaño permitido, entre 3 y 64 caracteres")
    private String correo;

    public Reserva toEntity() throws Exception {
        if(nombre == null || apellidos == null || telefono == null || correo == null) //TODO no se si es mejor hacer las comprobaciones de los atributos aquí o en el servicio cuando reciba la persona directamente, para desacoplar
            throw new Exception("Falta alguno de los parámetros necesarios para la creación de la reserva"); //FIXME FALTA ALGUNA COMPROBACION
        Reserva reserva = new Reserva();
        reserva.setNombre(nombre);
        reserva.setTelefono(telefono);
        reserva.setFechaReserva(new Date(System.currentTimeMillis()));
        reserva.setCorreo(correo);
        reserva.setApellidos(apellidos);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");
        reserva.setHora((String)dtf.format(LocalDateTime.now()));
        return reserva;
    }
}
