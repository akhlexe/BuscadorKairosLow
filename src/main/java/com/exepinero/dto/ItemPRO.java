package com.exepinero.dto;

/**
 * Clase que modeliza fila del archivo pro.txt de Kairos
 * Mostrando la información relativa entre productos y laboratorio al que pertenece.
 */

public class ItemPRO {

    private String codProd;
    private String descripProd;
    private String codLab;

    @Override
    public String toString() {
        return "ItemPRO{" +
                "codProd='" + codProd + '\'' +
                ", descripProd='" + descripProd + '\'' +
                ", codLab='" + codLab + '\'' +
                '}';
    }

    public ItemPRO() {
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getDescripProd() {
        return descripProd;
    }

    public void setDescripProd(String descripProd) {
        this.descripProd = descripProd;
    }

    public String getCodLab() {
        return codLab;
    }

    public void setCodLab(String codLab) {
        this.codLab = codLab;
    }
}
