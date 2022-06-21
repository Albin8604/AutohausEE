package ch.bzz.autohaus;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Helper {
    private static Helper instance = null;
    private static final String FORMAT = "dd.MM.yyyy";

    private Helper() {
    }

    public static Helper getInstance() {
        if (instance == null) {
            instance = new Helper();
        }
        return instance;
    }

    public LocalDate textToLocalDate(String text) {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(FORMAT));
    }

    public String localDateToText(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(FORMAT));
    }

    public String hashPassword(String passwordToHash) {
        String hashedPassword = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }
}