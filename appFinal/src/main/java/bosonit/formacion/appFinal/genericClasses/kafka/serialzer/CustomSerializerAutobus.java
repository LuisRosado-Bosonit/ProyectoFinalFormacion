package bosonit.formacion.appFinal.genericClasses.kafka.serialzer;

import bosonit.formacion.appFinal.reservas.domain.Autobus;
import bosonit.formacion.appFinal.reservas.domain.Reserva;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;


import java.util.Map;

@Slf4j
public class CustomSerializerAutobus implements Serializer<Autobus> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }


    @Override
    public byte[] serialize(String topic, Autobus data) {
        try {
            if (data == null){
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("Serializing..."); //FIXME ARREGLAR MENSAJES DE EEROR
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing MessageDto to byte[]");
        }
    }

    @Override
    public void close() {
    }
}