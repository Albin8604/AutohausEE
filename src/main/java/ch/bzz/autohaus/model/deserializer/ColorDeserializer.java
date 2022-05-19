package ch.bzz.autohaus.model.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.awt.*;
import java.io.IOException;

public class ColorDeserializer extends JsonDeserializer<Color> {

//TODO FIX DESERIALIZER
    @Override
    public Color deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return new Color(jsonParser.getValueAsInt());
/*
        String farbe = jsonParser.readValueAs(String.class);
        return new Color(Integer.parseInt(farbe , 16));
*/
    }
}
