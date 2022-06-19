package ch.bzz.autohaus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Helper {
    private static Helper instance = null;
    private static final String FORMAT = "dd.MM.yyyy";
    private Helper() {
    }

    public static Helper getInstance(){
        if (instance == null){
            instance = new Helper();
        }
        return instance;
    }

    public LocalDate textToLocalDate (String text){
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(FORMAT));
    }

    public String localDateToText (LocalDate localDate){
        return localDate.format(DateTimeFormatter.ofPattern(FORMAT));
    }
}
