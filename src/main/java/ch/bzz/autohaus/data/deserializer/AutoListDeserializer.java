package ch.bzz.autohaus.data.deserializer;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutoListDeserializer extends JsonDeserializer<List<Auto>> {
    @Override
    public List<Auto> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ArrayNode nodes = jsonParser.getCodec().readTree(jsonParser);
        if (nodes.size() < 1){
            return null;
        }
        List<Auto> result = new ArrayList<>();

        for (JsonNode node : nodes) {
            result.add(DataHandler.getInstance().readAutoByUUID(node.textValue()));
        }

        return result;
    }
}
