package bosonit.formacion.backWeb.Reserva.infraestructure.DTO.input;

import bosonit.formacion.backWeb.Reserva.domain.Reserva;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
public class inputReservaDTO {

    private String ciudadDestino;


    private String nombre;

    private String apellidos;

    private String telefono;

    private String correo;

    private int horaSalida;

    private Date fechaReserva;

    public Reserva toEntity() throws Exception {
        if(nombre == null || apellidos == null || telefono == null || correo == null) //TODO no se si es mejor hacer las comprobaciones de los atributos aquí o en el servicio cuando reciba la persona directamente, para desacoplar
            throw new Exception("Falta alguno de los parámetros necesarios para la creación de la reserva"); //FIXME FALTA ALGUNA COMPROBACION
        Reserva reserva = new Reserva();
        reserva.setNombre(nombre);
        reserva.setTelefono(telefono);
        reserva.setFechaReserva(/*new Date(System.currentTimeMillis())*/  fechaReserva );
        reserva.setCorreo(correo);
        reserva.setApellidos(apellidos);
        reserva.setCiudadDestino(ciudadDestino);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");
        reserva.setHoraSalida(horaSalida);
        return reserva;
    }
}
