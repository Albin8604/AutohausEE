package ch.bzz.autohaus.model;

import ch.bzz.autohaus.data.deserializer.ByteArrayDeserializer;
import ch.bzz.autohaus.data.deserializer.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.File;
import java.time.LocalDate;

/**
 * Model class of Kontaktperson
 *
 * @author Albin Smrqaku
 * @since 2022-05-19
 * @version 1.0
 *
 */

public class Kontaktperson {
    private String kontaktpersonUUID;
    private String nachname;
    private String vorname;
    private String email;
    private String tel;

    @JsonDeserialize(using = ByteArrayDeserializer.class)
    private Byte[] bild;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate gebDat;

    public Kontaktperson() {
    }

    public Kontaktperson(String kontaktpersonUUID, String nachname, String vorname, String email, String tel, Byte[] bild, LocalDate gebDat) {
        this.kontaktpersonUUID = kontaktpersonUUID;
        this.nachname = nachname;
        this.vorname = vorname;
        this.email = email;
        this.tel = tel;
        this.bild = bild;
        this.gebDat = gebDat;
    }

    public String getKontaktpersonUUID() {
        return kontaktpersonUUID;
    }

    public void setKontaktpersonUUID(String kontaktpersonUUID) {
        this.kontaktpersonUUID = kontaktpersonUUID;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Byte[] getBild() {
        return bild;
    }

    public void setBild(Byte[] bild) {
        this.bild = bild;
    }

    public LocalDate getGebDat() {
        return gebDat;
    }

    public void setGebDat(LocalDate gebDat) {
        this.gebDat = gebDat;
    }

}