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
        jsonGenerator.writeStartObject();

        for (Byte[] fileData : bytes) {
            jsonGenerator.writeObject(FileDataSerializer.byteArrayToBase64String(fileData));
        }

        jsonGenerator.writeEndObject();
    }
}
