package ch.bzz.autohaus.model;

import ch.bzz.autohaus.assets.Helper;
import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.data.deserializer.LocalDateDeserializer;
import ch.bzz.autohaus.data.serializer.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotEmpty;
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
    @Pattern(regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89ABab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}")
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
    @Size(min = 1, max = 10)
    private String nummer;
    @FormParam("ort")
    @NotEmpty
    @Size(min = 3, max = 50)
    private String ort;
    @FormParam("plz")
    @NotEmpty
    @Size(min = 3, max = 15)
    private String plz;

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
     * sets gruendung
     */
    @FormParam("gruendung")
    public void setGruendungFromRequest(String gruendungFromRequest) {
        LocalDate gruendung = Helper.getInstance().textToLocalDate(gruendungFromRequest);
        if (gruendung.isBefore(LocalDate.now()) || gruendung.isEqual(LocalDate.now())){
            setGruendung(gruendung);
        }
    }


    /**
     * sets inhaberUUID
     *
     * @param inhaberUUID the value to set
     */
    @FormParam("inhaberUUID")
    public void setInhaberUUID(String inhaberUUID) {
        setInhaber(DataHandler.getInstance().readUserByUUID(inhaberUUID));
    }

    /**
     * gets inhaberUUID
     *
     * @return value of inhaberUUID
     */
    public String getInhaberUUID() {
        return getInhaber().getUserUUID();
    }

    /**
     * sets autosUUID
     *
     * @param autosUUID the value to set
     */
    @FormParam("autosUUID")
    public void setAutosUUID(List<String> autosUUID) {
        setAutos(new ArrayList<>());
        for (String autoUUID : autosUUID) {
            getAutos().add(DataHandler.getInstance().readAutoByUUID(autoUUID));
        }
    }

    /**
     * gets autosUUID
     *
     * @return value of autosUUID
     */
    public List<String> getAutosUUID() {
        List<String> autosUUID = new ArrayList<>();
        for (Auto auto : getAutos()) {
            autosUUID.add(auto.getAutoUUID());
        }
        return autosUUID;
    }

    /**
     * sets kontaktpersonenUUID
     *
     * @param kontaktpersonenUUID the value to set
     */
    @FormParam("kontaktpersonenUUID")
    public void setKontaktpersonenUUID(List<String> kontaktpersonenUUID) {
        setKontaktpersonen(new ArrayList<>());
        for (String kontaktpersonUUID : kontaktpersonenUUID) {
            getKontaktpersonen().add(DataHandler.getInstance().readKontaktpersonByUUID(kontaktpersonUUID));
        }
    }

    /**
     * gets kontaktpersonenUUID
     *
     * @return value of kontaktpersonenUUID
     */
    public List<String> getKontaktpersonenUUID() {
        List<String> kontaktpersonenUUID = new ArrayList<>();
        for (Kontaktperson kontaktperson : getKontaktpersonen()) {
            kontaktpersonenUUID.add(kontaktperson.getKontaktpersonUUID());
        }
        return kontaktpersonenUUID;
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
