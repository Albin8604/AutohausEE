package ch.bzz.autohaus.model;

import java.io.File;
import java.time.LocalDate;

public class Kontaktperson {
    private String kontaktpersonUUID;
    private String nachname;
    private String vorname;
    private String email;
    private String tel;
    private File bild;
    private LocalDate gebDat;

    public Kontaktperson() {
    }

    public Kontaktperson(String kontaktpersonUUID, String nachname, String vorname, String email, String tel, File bild, LocalDate gebDat) {
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

    public Kontaktperson setKontaktpersonUUID(String kontaktpersonUUID) {
        this.kontaktpersonUUID = kontaktpersonUUID;
        return this;
    }

    public String getNachname() {
        return nachname;
    }

    public Kontaktperson setNachname(String nachname) {
        this.nachname = nachname;
        return this;
    }

    public String getVorname() {
        return vorname;
    }

    public Kontaktperson setVorname(String vorname) {
        this.vorname = vorname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Kontaktperson setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public Kontaktperson setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public File getBild() {
        return bild;
    }

    public Kontaktperson setBild(File bild) {
        this.bild = bild;
        return this;
    }

    public LocalDate getGebDat() {
        return gebDat;
    }

    public Kontaktperson setGebDat(LocalDate gebDat) {
        this.gebDat = gebDat;
        return this;
    }
}
