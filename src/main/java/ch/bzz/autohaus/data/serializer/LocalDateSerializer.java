package ch.bzz.autohaus.data.serializer;

import ch.bzz.autohaus.Helper;
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
        jsonGenerator.writeString(Helper.getInstance().localDateToText(localDate));
    }
}
