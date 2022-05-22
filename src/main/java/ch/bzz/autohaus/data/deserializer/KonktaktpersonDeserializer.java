package ch.bzz.autohaus.data.deserializer;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Kontaktperson;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.util.List;

public class KonktaktpersonDeserializer extends JsonDeserializer<Kontaktperson> {
    @Override
    public Kontaktperson deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        TextNode node = jsonParser.getCodec().readTree(jsonParser);
        return node.textValue().isEmpty() ? null : DataHandler.getInstance().readKontaktpersonByUUID(node.textValue());
    }
}
