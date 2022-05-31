package ch.bzz.autohaus.model;

import ch.bzz.autohaus.data.deserializer.FileDataListDeserializer;
import ch.bzz.autohaus.data.serializer.FileDataListSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.math.BigDecimal;
import java.util.List;

/**
 * Model class of Auto
 *
 * @author Albin Smrqaku
 */

public class Auto {
    @FormParam("id")
    @Pattern(regexp = "^[0-9A-F]{8}-[0-9A-F]{4}-4[0-9A-F]{3}-[89AB][0-9A-F]{3}-[0-9A-F]{12}$\n")
    private String autoUUID;
    @FormParam("marke")
    @NotNull
    @Size(min = 2, max = 15)
    private String marke;
    @FormParam("modell")
    @NotNull
    @Size(min = 2, max = 30)
    private String modell;
    @FormParam("farbcodeHex")
    @NotNull
    @Size(min = 4, max = 7)
    private String farbcodeHex;
    @FormParam("farbcodeHex")
    @NotNull
    @Size(min = 3, max = 15)
    private String art;
    @FormParam("ps")
    @NotNull
    @DecimalMin(value = "1")
    @DecimalMax(value = "2500")
    @Digits(integer = 4, fraction = 2)
    private Double ps;
    @FormParam("nm")
    @NotNull
    @DecimalMin(value = "1")
    @DecimalMax(value = "4000")
    @Digits(integer = 4, fraction = 2)
    private Double nm;
    @FormParam("occasion")
    @NotNull
    private Boolean isOccasion;

    @FormParam("preis")
    @NotNull
    @DecimalMin(value = "1")
    @DecimalMax(value = "1000000")
    @Digits(integer = 7, fraction = 2)
    @JsonDeserialize(using = NumberDeserializers.BigDecimalDeserializer.class)
    private BigDecimal preis;

    @FormParam("bilder")
    @JsonDeserialize(using = FileDataListDeserializer.class)
    @JsonSerialize(using = FileDataListSerializer.class)
    private List<Byte[]> bilder;

    /**
     * empty constructor
     */
    public Auto() {
    }

    /**
     * constructor
     *
     * @param autoUUID    UUID of Auto
     * @param marke       marke of Auto
     * @param modell      modell of Auto
     * @param farbcodeHex farbcodeHex of Auto
     * @param art         art of Auto
     * @param ps          ps of Auto
     * @param nm          nm of Auto
     * @param isOccasion  isOccasion of Auto
     * @param preis       preis of Auto
     * @param bilder      bilderList of Auto
     */
    public Auto(String autoUUID,
                String marke,
                String modell,
                String farbcodeHex,
                String art,
                Double ps,
                Double nm,
                Boolean isOccasion,
                BigDecimal preis,
                List<Byte[]> bilder) {
        this.autoUUID = autoUUID;
        this.marke = marke;
        this.modell = modell;
        this.farbcodeHex = farbcodeHex;
        this.art = art;
        this.ps = ps;
        this.nm = nm;
        this.isOccasion = isOccasion;
        this.preis = preis;
        this.bilder = bilder;
    }

    /**
     * gets autoUUID
     *
     * @return value of autoUUID
     */
    public String getAutoUUID() {
        return autoUUID;
    }

    /**
     * sets autoUUID
     *
     * @param autoUUID the value to set
     */
    public void setAutoUUID(String autoUUID) {
        this.autoUUID = autoUUID;
    }

    /**
     * gets marke
     *
     * @return value of marke
     */
    public String getMarke() {
        return marke;
    }

    /**
     * sets marke
     *
     * @param marke the value to set
     */
    public void setMarke(String marke) {
        this.marke = marke;
    }

    /**
     * gets modell
     *
     * @return value of modell
     */
    public String getModell() {
        return modell;
    }

    /**
     * sets modell
     *
     * @param modell the value to set
     */
    public void setModell(String modell) {
        this.modell = modell;
    }

    /**
     * gets farbcodeHex
     *
     * @return value of farbcodeHex
     */
    public String getFarbcodeHex() {
        return farbcodeHex;
    }

    /**
     * sets farbcodeHex
     *
     * @param farbcodeHex the value to set
     */
    public void setFarbcodeHex(String farbcodeHex) {
        this.farbcodeHex = farbcodeHex;
    }

    /**
     * gets art
     *
     * @return value of art
     */
    public String getArt() {
        return art;
    }

    /**
     * sets art
     *
     * @param art the value to set
     */
    public void setArt(String art) {
        this.art = art;
    }

    /**
     * gets ps
     *
     * @return value of ps
     */
    public Double getPs() {
        return ps;
    }

    /**
     * sets ps
     *
     * @param ps the value to set
     */
    public void setPs(Double ps) {
        this.ps = ps;
    }

    /**
     * gets nm
     *
     * @return value of nm
     */
    public Double getNm() {
        return nm;
    }

    /**
     * sets nm
     *
     * @param nm the value to set
     */
    public void setNm(Double nm) {
        this.nm = nm;
    }

    /**
     * gets isOccasion
     *
     * @return value of isOccasion
     */
    public Boolean getOccasion() {
        return isOccasion;
    }

    /**
     * sets isOccasion
     *
     * @param occasion the value to set
     */
    public void setOccasion(Boolean occasion) {
        isOccasion = occasion;
    }

    /**
     * gets preis
     *
     * @return value of preis
     */
    public BigDecimal getPreis() {
        return preis;
    }

    /**
     * sets preis
     *
     * @param preis the value to set
     */
    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    /**
     * gets bilder
     *
     * @return value of bilder
     */
    public List<Byte[]> getBilder() {
        return bilder;
    }

    /**
     * sets bilder
     *
     * @param bilder the value to set
     */
    public void setBilder(List<Byte[]> bilder) {
        this.bilder = bilder;
    }
}
