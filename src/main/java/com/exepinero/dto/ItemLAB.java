package com.exepinero.dto;

/**
 * Clase que modeliza fila del archivo lab.txt de Kairos
 * Mostrando la información relativa entre codigo de laboratorio, nombre abreviado y razón social.
 */

public class ItemLAB {

    private String codLab;
    private String descripLab;
    private String razonSocial;

    public ItemLAB() {
    }

    @Override
    public String toString() {
        return "ItemLAB{" +
                "codLab='" + codLab + '\'' +
                ", descripLab='" + descripLab + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                '}';
    }

    public String getCodLab() {
        return codLab;
    }

    public void setCodLab(String codLab) {
        this.codLab = codLab;
    }

    public String getDescripLab() {
        return descripLab;
    }

    public void setDescripLab(String descripLab) {
        this.descripLab = descripLab;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
}
