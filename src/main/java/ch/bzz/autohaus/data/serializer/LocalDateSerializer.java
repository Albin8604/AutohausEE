package ch.bzz.autohaus.data.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Serializer of localdate to json
 *
 * @author Albin Smrqaku
 *
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate> {
    //Pattern of String
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Serializes localdate
     *
     * @param localDate LocalDate to be serialized
     * @param jsonGenerator JsonGenerator from JsonSerializer
     * @param serializerProvider SerializerProvider from JsonSerializer
     */
    @Override
    public void serialize(LocalDate localDate,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeString(localDate.format(FORMATTER));
        jsonGenerator.writeEndObject();
    }
}
