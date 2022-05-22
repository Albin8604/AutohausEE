package ch.bzz.autohaus.data.deserializer;

import ch.bzz.autohaus.data.DataHandler;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ByteArrayListDeserializer extends JsonDeserializer<List<Byte[]>> {
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
            byte[] tempPartResult = Base64.getDecoder().decode(
                    new String(
                            node.textValue().substring(node.textValue().indexOf(",") + 1).getBytes(StandardCharsets.UTF_8)
                    )
            );
            Byte[] partResult = new Byte[tempPartResult.length];

            for (int i = 0; i < tempPartResult.length; i++) {
                partResult[i] = tempPartResult[i];
            }
            result.add(partResult);
        }

        return result;
    }
}
