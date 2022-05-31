package ch.bzz.autohaus.data.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sun.org.apache.xpath.internal.operations.String;
import javafx.scene.Scene;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Serializer from Byte[] to a base64 string
 *
 * @author Albin Smrqaku
 */
public class FileDataSerializer extends JsonSerializer<Byte[]> {

    /**
     * Serializes Byte[] into a Base64 string
     *
     * @param bytes              Data to be converted into a base64 string
     * @param jsonGenerator      JsonGenerator from JsonSerializer
     * @param serializerProvider SerializerProvider from JsonSerializer
     */
    @Override
    public void serialize(Byte[] bytes,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(byteArrayToBase64String(bytes));
        jsonGenerator.writeEndObject();
    }


    /**
     * Decodes a Byte[] to a Base64 String
     *
     * @param fileData The fileData Byte[]
     * @return base64 string
     */
    public static java.lang.String byteArrayToBase64String(Byte[] fileData) {
        return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(toPrimitive(fileData));
    }

    /**
     * Converts Byte[] into byte[] and copies the data
     *
     * @param data Byte[] to be converted into byte[]
     * @return byte[] with the copied data
     */

    private static byte[] toPrimitive(Byte[] data) {
        byte[] result = new byte[data.length];

        for (int i = 0; i < data.length; i++) {
            result[i] = data[i];
        }

        return result;
    }
}
