package ch.bzz.autohaus.data.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Deserializer of File data lists given in a base64 string list from json
 *
 * @author Albin Smrqaku
 * @since 2022-05-23
 * @version 1.0
 *
 */
public class FileDataListDeserializer extends JsonDeserializer<List<Byte[]>> {

    /**
     * Deserializes file data from Base64 strings to a List<Byte[]>
     *
     * @param jsonParser JsonParser from JsonDeserializer
     * @param deserializationContext DeserializationContext from JsonDeserializer
     * @return List<Byte[]> with the file data in it
     */
    @Override
    public List<Byte[]> deserialize(JsonParser jsonParser,
                                    DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        ArrayNode nodes = jsonParser.getCodec().readTree(jsonParser);
        if (nodes.size() < 1){
            return null;
        }
        List<Byte[]> result = new ArrayList<>();

        for (JsonNode node : nodes) {
            result.add(FileDataDeserializer.base64ToByteArray(node.get("bild").textValue()));
        }

        return result;
    }
}
