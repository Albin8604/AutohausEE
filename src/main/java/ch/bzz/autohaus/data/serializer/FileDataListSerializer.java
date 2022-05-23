package ch.bzz.autohaus.data.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * Serializer from List<Byte[]> to a base64 string
 *
 * @author Albin Smrqaku
 * @since 2022-05-23
 * @version 1.0
 *
 */
public class FileDataListSerializer extends JsonSerializer<List<Byte[]>> {

    /**
     * Serializes List<Byte[]> into Base64 strings
     *
     * @param bytes Data to be converted into base64 strings
     * @param jsonGenerator JsonGenerator from JsonSerializer
     * @param serializerProvider SerializerProvider from JsonSerializer
     */
    @Override
    public void serialize(List<Byte[]> bytes, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        //TODO in Milestone 02
    }
}
