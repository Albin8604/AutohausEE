package ch.bzz.autohaus.data.deserializer;

import ch.bzz.autohaus.assets.Helper;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Deserializer of date string from json
 *
 * @author Albin Smrqaku
 *
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    /**
     * Deserializes localdate from text
     *
     * @param jsonParser JsonParser from JsonDeserializer
     * @param deserializationContext DeserializationContext from JsonDeserializer
     * @return Localdate or null if the date in json was empty
     */
    @Override
    public LocalDate deserialize(JsonParser jsonParser,
                                 DeserializationContext deserializationContext) throws IOException, JacksonException {
        TextNode node = jsonParser.getCodec().readTree(jsonParser);
        return node.textValue().isEmpty() ? null : Helper.getInstance().textToLocalDate(node.textValue());
    }
}
