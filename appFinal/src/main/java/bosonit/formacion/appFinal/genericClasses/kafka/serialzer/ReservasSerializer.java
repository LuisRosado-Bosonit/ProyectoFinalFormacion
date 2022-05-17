package bosonit.formacion.appFinal.genericClasses.kafka.serialzer;

import bosonit.formacion.appFinal.reservas.domain.Reserva;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.List;
import java.util.Map;

public class ReservasSerializer implements Serializer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, List<Reserva> data) {
        try {
            if (data == null){
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("Serializing...");
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException(""); //FIXME INTRODUCIR AQUI EL ERROR
        }
    }

    @Override
    public void close() {
    }
}
