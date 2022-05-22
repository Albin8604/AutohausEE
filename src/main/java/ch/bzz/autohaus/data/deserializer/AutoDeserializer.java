package ch.bzz.autohaus.data.deserializer;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;

public class AutoDeserializer extends JsonDeserializer<Auto> {
    @Override
    public Auto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        TextNode node = jsonParser.getCodec().readTree(jsonParser);
        return node.textValue().isEmpty() ? null : DataHandler.getInstance().readAutoByUUID(node.textValue());
    }
}
