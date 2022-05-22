package ch.bzz.autohaus.data.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ByteArrayDeserializer extends JsonDeserializer<Byte[]> {
    @Override
    public Byte[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        TextNode node = jsonParser.getCodec().readTree(jsonParser);
        byte[] tempResult = Base64.getDecoder().decode(
                new String(
                        node.textValue().substring(node.textValue().indexOf(",") + 1).getBytes(StandardCharsets.UTF_8)
                )
        );
        Byte[] result = new Byte[tempResult.length];

        for (int i = 0; i < tempResult.length; i++) {
            result[i] = tempResult[i];
        }

        return node.textValue().isEmpty() ? null : result;
    }
}
