package ch.bzz.autohaus.model;

import ch.bzz.autohaus.data.deserializer.ByteArrayListDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

/**
 * Model class of Auto
 *
 * @author Albin Smrqaku
 * @since 2022-05-19
 * @version 1.0
 *
 */

public class Auto {
    private String autoUUID;
    private String marke;
    private String modell;
    private String farbcodeHex;
    private String art;
    private Double ps;
    private Double nm;
    private Boolean isOccasion;

    @JsonDeserialize(using = NumberDeserializers.BigDecimalDeserializer.class)
    private BigDecimal preis;

    @JsonDeserialize(using = ByteArrayListDeserializer.class)
    private List<Byte[]> bilder;

    public Auto() {
    }

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

    public String getAutoUUID() {
        return autoUUID;
    }

    public void setAutoUUID(String autoUUID) {
        this.autoUUID = autoUUID;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getFarbcodeHex() {
        return farbcodeHex;
    }

    public void setFarbcodeHex(String farbcodeHex) {
        this.farbcodeHex = farbcodeHex;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public Double getPs() {
        return ps;
    }

    public void setPs(Double ps) {
        this.ps = ps;
    }

    public Double getNm() {
        return nm;
    }

    public void setNm(Double nm) {
        this.nm = nm;
    }

    public Boolean getOccasion() {
        return isOccasion;
    }

    public void setOccasion(Boolean occasion) {
        isOccasion = occasion;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    public List<Byte[]> getBilder() {
        return bilder;
    }

    public void setBilder(List<Byte[]> bilder) {
        this.bilder = bilder;
    }
}
