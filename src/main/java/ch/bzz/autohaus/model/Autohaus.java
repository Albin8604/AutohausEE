package ch.bzz.autohaus.model;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.data.deserializer.LocalDateDeserializer;
import ch.bzz.autohaus.data.serializer.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ArrayNode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class of Autohaus
 *
 * @author Albin Smrqaku
 */

public class Autohaus {
    @FormParam("id")
    @Pattern(regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89AB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}")
    private String autohausUUID;
    @JsonIgnore
    private List<Auto> autos;
    @JsonIgnore
    private List<Kontaktperson> kontaktpersonen;

    @JsonIgnore
    private User inhaber;
    @FormParam("strasse")
    @NotEmpty
    @Size(min = 3, max = 50)
    private String strasse;
    @FormParam("nummer")
    @NotEmpty
    @Size(min = 3, max = 50)
    private String nummer;
    @FormParam("ort")
    @NotEmpty
    @Size(min = 3, max = 50)
    private String ort;
    @FormParam("plz")
    @NotEmpty
    @Size(min = 3, max = 50)
    private String plz;

    @FormParam("gruendung")
    @NotNull
    @Size(min = 10, max = 10)
    @PastOrPresent
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate gruendung;

    /**
     * empty constructor
     */
    public Autohaus() {
    }

    /**
     * constructor
     *
     * @param autohausUUID    autohausUUID of Autohaus
     * @param autos           autos of Autohaus
     * @param kontaktpersonen kontaktpersonen of Autohaus
     * @param inhaber         inhaber of Autohaus
     * @param strasse         strasse of Autohaus
     * @param nummer          nummber of Autohaus
     * @param ort             ort of Autohaus
     * @param plz             plz of Autohaus
     * @param gruendung       gruendung of Autohaus
     */
    public Autohaus(String autohausUUID,
                    List<Auto> autos,
                    List<Kontaktperson> kontaktpersonen,
                    User inhaber,
                    String strasse,
                    String nummer,
                    String ort,
                    String plz,
                    LocalDate gruendung) {
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

    /**
     * sets ingaberUUID -> In use because of JSON
     *
     * @param inhaberUUID the value to set
     */
    @FormParam("inhaberUUID")
    public void setInhaberUUID(String inhaberUUID) {
        setInhaber(DataHandler.getInstance().readUserByUUID(inhaberUUID));
    }

    /**
     * sets autosUUID -> In use because of JSON
     *
     * @param autosUUID the value to set
     */
    @FormParam("autosUUID")
    public void setAutosUUID(ArrayNode autosUUID) {
        setAutos(new ArrayList<>());
        for (JsonNode autoUUIDNode : autosUUID) {
            String autoUUID = autoUUIDNode.textValue();
            getAutos().add(DataHandler.getInstance().readAutoByUUID(autoUUID));
        }
    }

    /**
     * sets kontaktpersonenUUID -> In use because of JSON
     *
     * @param kontaktpersonenUUID the value to set
     */
    @FormParam("kontaktpersonenUUID")
    @NotNull
    public void setKontaktpersonenUUID(ArrayNode kontaktpersonenUUID) {
        setKontaktpersonen(new ArrayList<>());
        for (JsonNode kontaktpersonUUIDNode : kontaktpersonenUUID) {
            String kontaktpersonUUID = kontaktpersonUUIDNode.textValue();
            getKontaktpersonen().add(DataHandler.getInstance().readKontaktpersonByUUID(kontaktpersonUUID));
        }
    }

    /**
     * gets autohausUUID
     *
     * @return value of autohausUUID
     */
    public String getAutohausUUID() {
        return autohausUUID;
    }

    /**
     * sets autohausUUID
     *
     * @param autohausUUID the value to set
     */
    public void setAutohausUUID(String autohausUUID) {
        this.autohausUUID = autohausUUID;
    }

    /**
     * gets autos
     *
     * @return value of autos
     */
    public List<Auto> getAutos() {
        return autos;
    }

    /**
     * sets autos
     *
     * @param autos the value to set
     */
    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }

    /**
     * gets kontaktpersonen
     *
     * @return value of kontaktpersonen
     */
    public List<Kontaktperson> getKontaktpersonen() {
        return kontaktpersonen;
    }

    /**
     * sets kontaktpersonen
     *
     * @param kontaktpersonen the value to set
     */
    public void setKontaktpersonen(List<Kontaktperson> kontaktpersonen) {
        this.kontaktpersonen = kontaktpersonen;
    }

    /**
     * gets inhaber
     *
     * @return value of inhaber
     */
    public User getInhaber() {
        return inhaber;
    }

    /**
     * sets inhaber
     *
     * @param inhaber the value to set
     */
    public void setInhaber(User inhaber) {
        this.inhaber = inhaber;
    }

    /**
     * gets strasse
     *
     * @return value of strasse
     */
    public String getStrasse() {
        return strasse;
    }

    /**
     * sets strasse
     *
     * @param strasse the value to set
     */
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    /**
     * gets nummer
     *
     * @return value of nummer
     */
    public String getNummer() {
        return nummer;
    }

    /**
     * sets nummer
     *
     * @param nummer the value to set
     */
    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    /**
     * gets ort
     *
     * @return value of ort
     */
    public String getOrt() {
        return ort;
    }

    /**
     * sets ort
     *
     * @param ort the value to set
     */
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     * gets plz
     *
     * @return value of plz
     */
    public String getPlz() {
        return plz;
    }

    /**
     * sets plz
     *
     * @param plz the value to set
     */
    public void setPlz(String plz) {
        this.plz = plz;
    }

    /**
     * gets gruendung
     *
     * @return value of gruendung
     */
    public LocalDate getGruendung() {
        return gruendung;
    }

    /**
     * sets gruendung
     *
     * @param gruendung the value to set
     */
    public void setGruendung(LocalDate gruendung) {
        this.gruendung = gruendung;
    }
}
