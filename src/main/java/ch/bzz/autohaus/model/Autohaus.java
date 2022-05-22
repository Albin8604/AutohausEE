package ch.bzz.autohaus.model;

import ch.bzz.autohaus.data.deserializer.AutoDeserializer;
import ch.bzz.autohaus.data.deserializer.AutoListDeserializer;
import ch.bzz.autohaus.data.deserializer.KonktaktpersonDeserializer;
import ch.bzz.autohaus.data.deserializer.KontaktpersonListDeserializer;
import ch.bzz.autohaus.data.deserializer.LocalDateDeserializer;
import ch.bzz.autohaus.data.deserializer.UserDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;
import java.util.List;

/**
 * Model class of Autohaus
 *
 * @author Albin Smrqaku
 * @since 2022-05-19
 * @version 1.0
 *
 */

public class Autohaus {
    private String autohausUUID;
    @JsonDeserialize(using = AutoListDeserializer.class)
    private List<Auto> autos;
    @JsonDeserialize(using = KontaktpersonListDeserializer.class)
    private List<Kontaktperson> kontaktpersonen;

    @JsonDeserialize(using = UserDeserializer.class)
    private User inhaber;
    private String strasse;
    private String nummer;
    private String ort;
    private String plz;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate gruendung;

    public Autohaus() {
    }

    public Autohaus(String autohausUUID, List<Auto> autos, List<Kontaktperson> kontaktpersonen, User inhaber, String strasse, String nummer, String ort, String plz, LocalDate gruendung) {
        this.autohausUUID = autohausUUID;
        this.autos = autos;
        this.kontaktpersonen = kontaktpersonen;
        this.inhaber = inhaber;
        this.strasse = strasse;
        this.nummer = nummer;
        this.ort = ort;
        this.plz = plz;
        this.gruendung = gruendung;
    }

    public String getAutohausUUID() {
        return autohausUUID;
    }

    public Autohaus setAutohausUUID(String autohausUUID) {
        this.autohausUUID = autohausUUID;
        return this;
    }

    public List<Auto> getAutos() {
        return autos;
    }

    public Autohaus setAutos(List<Auto> autos) {
        this.autos = autos;
        return this;
    }

    public List<Kontaktperson> getKontaktpersonen() {
        return kontaktpersonen;
    }

    public Autohaus setKontaktpersonen(List<Kontaktperson> kontaktpersonen) {
        this.kontaktpersonen = kontaktpersonen;
        return this;
    }

    public User getInhaber() {
        return inhaber;
    }

    public Autohaus setInhaber(User inhaber) {
        this.inhaber = inhaber;
        return this;
    }

    public String getStrasse() {
        return strasse;
    }

    public Autohaus setStrasse(String strasse) {
        this.strasse = strasse;
        return this;
    }

    public String getNummer() {
        return nummer;
    }

    public Autohaus setNummer(String nummer) {
        this.nummer = nummer;
        return this;
    }

    public String getOrt() {
        return ort;
    }

    public Autohaus setOrt(String ort) {
        this.ort = ort;
        return this;
    }

    public String getPlz() {
        return plz;
    }

    public Autohaus setPlz(String plz) {
        this.plz = plz;
        return this;
    }

    public LocalDate getGruendung() {
        return gruendung;
    }

    public Autohaus setGruendung(LocalDate gruendung) {
        this.gruendung = gruendung;
        return this;
    }
}
