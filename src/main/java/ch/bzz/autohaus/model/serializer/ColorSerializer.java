package ch.bzz.autohaus.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.awt.*;
import java.io.IOException;

public class ColorSerializer extends JsonSerializer<Color> {
    @Override
    public void serialize(Color color, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (color == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeNumber(color.getRGB());
    }
}
