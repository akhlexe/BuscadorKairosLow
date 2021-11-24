package com.exepinero.dto;

/**
 * Clase que modeliza fila del archivo pre.txt de Kairos
 * Mostrando la información relativa entre producto y presentación, con la info descriptiva y de GTIN.
 */

public class ItemPRE {

    private String codProd;
    private String codPresent;
    private String descripPresentacion;
    private String GTIN;

    @Override
    public String toString() {
        return "ItemPRE{" +
                "codProd='" + codProd + '\'' +
                ", codPresent='" + codPresent + '\'' +
                ", descripPresentacion='" + descripPresentacion + '\'' +
                ", GTIN='" + GTIN + '\'' +
                '}';
    }

    public ItemPRE() {
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getCodPresent() {
        return codPresent;
    }

    public void setCodPresent(String codPresent) {
        this.codPresent = codPresent;
    }

    public String getDescripPresentacion() {
        return descripPresentacion;
    }

    public void setDescripPresentacion(String descripPresentacion) {
        this.descripPresentacion = descripPresentacion;
    }

    public String getGTIN() {
        return GTIN;
    }

    public void setGTIN(String GTIN) {
        this.GTIN = GTIN;
    }
}
