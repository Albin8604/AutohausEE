package ch.bzz.autohaus.model;


import ch.bzz.autohaus.model.serializer.ColorSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.awt.*;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;

public class Auto {
    private String autoUUID;
    private String marke;
    private String modell;
    private Color farbe;
    private String art;
    private Double ps;
    private Double nm;
    private Boolean isOccasion;
    private BigDecimal preis;
    private List<File> bilder;

    public Auto() {
    }

    public Auto(String autoUUID, String marke, String modell, Color farbe, String art, Double ps, Double nm, Boolean isOccasion, BigDecimal preis, List<File> bilder) {
        this.autoUUID = autoUUID;
        this.marke = marke;
        this.modell = modell;
        this.farbe = farbe;
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

    public Auto setAutoUUID(String autoUUID) {
        this.autoUUID = autoUUID;
        return this;
    }

    public String getMarke() {
        return marke;
    }

    public Auto setMarke(String marke) {
        this.marke = marke;
        return this;
    }

    public String getModell() {
        return modell;
    }

    public Auto setModell(String modell) {
        this.modell = modell;
        return this;
    }

    public Color getFarbe() {
        return farbe;
    }

    public Auto setFarbe(Color farbe) {
        this.farbe = farbe;
        return this;
    }

    public String getArt() {
        return art;
    }

    public Auto setArt(String art) {
        this.art = art;
        return this;
    }

    public Double getPs() {
        return ps;
    }

    public Auto setPs(Double ps) {
        this.ps = ps;
        return this;
    }

    public Double getNm() {
        return nm;
    }

    public Auto setNm(Double nm) {
        this.nm = nm;
        return this;
    }

    public Boolean getOccasion() {
        return isOccasion;
    }

    public Auto setOccasion(Boolean occasion) {
        isOccasion = occasion;
        return this;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public Auto setPreis(BigDecimal preis) {
        this.preis = preis;
        return this;
    }

    public List<File> getBilder() {
        return bilder;
    }

    public Auto setBilder(List<File> bilder) {
        this.bilder = bilder;
        return this;
    }
}
