package ch.bzz.autohaus.data.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Deserializer of File data give in a base64 string from json
 *
 * @author Albin Smrqaku
 *
 */

public class FileDataDeserializer extends JsonDeserializer<Byte[]> {

    /**
     * Deserializes file data from Base64 string to a Byte[]
     *
     * @param jsonParser JsonParser from JsonDeserializer
     * @param deserializationContext DeserializationContext from JsonDeserializer
     * @return Byte[] with the file data
     */
    @Override
    public Byte[] deserialize(JsonParser jsonParser,
                              DeserializationContext deserializationContext) throws IOException, JacksonException {
        TextNode node = jsonParser.getCodec().readTree(jsonParser);
        return node.textValue().isEmpty() ? null : base64ToByteArray(node.textValue());
    }

    /**
     * Decodes a base64 String into a byte[]
     *
     * @param base64String The base64 String of the file
     * @return toNonPrimitive -> converts byte[] into Byte[]
     */
    public static Byte[] base64ToByteArray(String base64String){
        byte[] tempResult = Base64.getDecoder().decode(
                new String(
                        //Starts after the first "," char in the Base64 string and then it gets the bytes in UTF-8
                        base64String.substring(base64String.indexOf(",") + 1).getBytes(StandardCharsets.UTF_8)
                )
        );

        return toNonPrimitive(tempResult);
    }

    /**
     * Converts byte[] into Byte[] and copies the data
     *
     * @param data byte[] to be converted into Byte[]
     * @return Byte[] with the copied data
     */

    private static Byte[] toNonPrimitive(byte[] data){
        Byte[] result = new Byte[data.length];

        for (int i = 0; i < data.length; i++) {
            result[i] = data[i];
        }

        return result;
    }
}
