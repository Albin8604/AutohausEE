package ch.bzz.autohaus.model;

import ch.bzz.autohaus.assets.Helper;
import ch.bzz.autohaus.data.deserializer.FileDataDeserializer;
import ch.bzz.autohaus.data.deserializer.LocalDateDeserializer;
import ch.bzz.autohaus.data.serializer.FileDataSerializer;
import ch.bzz.autohaus.data.serializer.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.time.LocalDate;

/**
 * Model class of Kontaktperson
 *
 * @author Albin Smrqaku
 */

public class Kontaktperson {

    private static final Integer MINDESTALTER = 18;
    @FormParam("id")
    @Pattern(regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89ABab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}")
    private String kontaktpersonUUID;
    @FormParam("nachname")
    @NotEmpty
    @Size(min = 3, max = 30)
    private String nachname;
    @FormParam("vorname")
    @NotEmpty
    @Size(min = 3, max = 30)
    private String vorname;
    @FormParam("email")
    @NotEmpty
    @Email
    private String email;
    @FormParam("tel")
    @NotEmpty
    @Pattern(regexp = "[0-9]{1,3}[0-9]{3}[0-9]{2}[0-9]{2}")
    private String tel;

    @JsonDeserialize(using = FileDataDeserializer.class)
    @JsonSerialize(using = FileDataSerializer.class)
    private Byte[] bild;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate gebDat;

    /**
     * empty constructor
     */
    public Kontaktperson() {
    }

    /**
     * constructor
     *
     * @param kontaktpersonUUID kontaktpersonUUID of Kontaktperson
     * @param nachname          nachname of Kontaktperson
     * @param vorname           vorname of Kontaktperson
     * @param email             email of Kontaktperson
     * @param tel               tel of Kontaktperson
     * @param bild              bild of Kontaktperson
     * @param gebDat            gebDat of Kontaktperson
     */
    public Kontaktperson(String kontaktpersonUUID,
                         String nachname,
                         String vorname,
                         String email,
                         String tel,
                         Byte[] bild,
                         LocalDate gebDat) {
        this.kontaktpersonUUID = kontaktpersonUUID;
        this.nachname = nachname;
        this.vorname = vorname;
        this.email = email;
        this.tel = tel;
        this.bild = bild;
        this.gebDat = gebDat;
    }

    /**
     * sets gebDat
     */
    @FormParam("gebDat")
    @NotNull
    public LocalDate setGebDatFromRequest(String gebDatFromRequest) {
        LocalDate gebDat = Helper.getInstance().textToLocalDate(gebDatFromRequest);
        if (gebDat.isBefore(LocalDate.now().minusYears(MINDESTALTER))){
            setGebDat(gebDat);
        }
        return getGebDat();
    }

    /**
     * sets Bild from Base64 String getting from the form
     *
     * @param bildBase64 Base64 String getting from the request
     */
    @FormParam("bild")
    @NotNull
    public Byte[] setBildFromBase64(String bildBase64) {
        this.bild = FileDataDeserializer.base64ToByteArray(bildBase64);
        return getBild();
    }

    /**
     * gets kontaktpersonUUID
     *
     * @return value of kontaktpersonUUID
     */
    public String getKontaktpersonUUID() {
        return kontaktpersonUUID;
    }

    /**
     * sets kontaktpersonUUID
     *
     * @param kontaktpersonUUID the value to set
     */
    public void setKontaktpersonUUID(String kontaktpersonUUID) {
        this.kontaktpersonUUID = kontaktpersonUUID;
    }

    /**
     * gets nachname
     *
     * @return value of nachname
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * sets nachname
     *
     * @param nachname the value to set
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * gets vorname
     *
     * @return value of vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * sets vorname
     *
     * @param vorname the value to set
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * gets email
     *
     * @return value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets email
     *
     * @param email the value to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets tel
     *
     * @return value of tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * sets tel
     *
     * @param tel the value to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * gets bild
     *
     * @return value of bild
     */
    public Byte[] getBild() {
        return bild;
    }

    /**
     * sets bild
     *
     * @param bild the value to set
     */
    public void setBild(Byte[] bild) {
        this.bild = bild;
    }

    /**
     * gets gebDat
     *
     * @return value of gebDat
     */
    public LocalDate getGebDat() {
        return gebDat;
    }

    /**
     * sets gebDat
     *
     * @param gebDat the value to set
     */
    public void setGebDat(LocalDate gebDat) {
        this.gebDat = gebDat;
    }
}