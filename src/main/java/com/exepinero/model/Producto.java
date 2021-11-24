package com.exepinero.model;

import java.time.LocalDate;

/**
 * Producto correspondiente al maestro de Kairos.
 */

public class Producto {

    private String codMonodroga;
    private String descripMonodroga;
    private String codProd;
    private String codPresentacion;
    private String descripProd;
    private String descripPresentacion;
    private String codLabo;
    private String descripLab;
    private String razonSocial;
    private String GTIN;
    private String PVP;
    private String fechaVigencia;

    @Override
    public String toString() {
        return "Producto{" +
                "codMonodroga='" + codMonodroga + '\'' +
                ", descripMonodroga='" + descripMonodroga + '\'' +
                ", codProd='" + codProd + '\'' +
                ", codPresentacion='" + codPresentacion + '\'' +
                ", descripProd='" + descripProd + '\'' +
                ", descripPresentacion='" + descripPresentacion + '\'' +
                ", codLabo='" + codLabo + '\'' +
                ", descripLab='" + descripLab + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", GTIN='" + GTIN + '\'' +
                ", PVP='" + PVP + '\'' +
                ", fechaVigencia='" + fechaVigencia + '\'' +
                '}';
    }

    public String getCodPresentacion() {
        return codPresentacion;
    }

    public void setCodPresentacion(String codPresentacion) {
        this.codPresentacion = codPresentacion;
    }

    public Producto() {
    }

    public String getCodMonodroga() {
        return codMonodroga;
    }

    public void setCodMonodroga(String codMonodroga) {
        this.codMonodroga = codMonodroga;
    }

    public String getDescripMonodroga() {
        return descripMonodroga;
    }

    public void setDescripMonodroga(String descripMonodroga) {
        this.descripMonodroga = descripMonodroga;
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

    public String getDescripPresentacion() {
        return descripPresentacion;
    }

    public void setDescripPresentacion(String descripPresentacion) {
        this.descripPresentacion = descripPresentacion;
    }

    public String getCodLabo() {
        return codLabo;
    }

    public void setCodLabo(String codLabo) {
        this.codLabo = codLabo;
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

    public String getGTIN() {
        return GTIN;
    }

    public void setGTIN(String GTIN) {
        this.GTIN = GTIN;
    }

    public String getPVP() {
        return PVP;
    }

    public void setPVP(String PVP) {
        this.PVP = PVP;
    }

    public String getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(String fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }
}
