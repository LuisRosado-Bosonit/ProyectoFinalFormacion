package bosonit.formacion.backWeb.Kafka.recepcion;

import bosonit.formacion.backWeb.Reserva.domain.Reserva;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;
@Data
@NoArgsConstructor
@Slf4j
public class deserializador implements Deserializer<Reserva> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Reserva deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                log.warn("----- SE ESTÁ INTENTANDO DESEARILIZAR UNA RESERVA NULA O VACÍA -----");
                return null;
            }
            //FIXME CAMBIAR MENSAJES DE ERROR
            return objectMapper.readValue(new String(data, "UTF-8"), Reserva.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Reserva");
        }
    }

    @Override
    public void close() {
    }
}
