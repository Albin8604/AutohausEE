package ch.bzz.autohaus.data.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Serializer from Byte[] to a base64 string
 *
 * @author Albin Smrqaku
 * @since 2022-05-23
 * @version 1.0
 *
 */
public class FileDataSerializer extends JsonSerializer<Byte[]> {

    /**
     * Serializes Byte[] into a Base64 string
     *
     * @param bytes Data to be converted into a base64 string
     * @param jsonGenerator JsonGenerator from JsonSerializer
     * @param serializerProvider SerializerProvider from JsonSerializer
     */
    @Override
    public void serialize(Byte[] bytes,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        //TODO in Milestone 02
    }
}
