package com.exepinero.dto;

/**
 * Clase que modeliza la fila de información del archivo prc.txt de Kairos
 * Los campos muestran la información relativa entre presentación y su PVP con fecha de último aum.
 */

public class ItemPRC {

    private String codProd;
    private String codPresentacion;
    private String pvp;
    private String fecha_vigencia;
    private String codProdLowsedo;

    @Override
    public String toString() {
        return "ItemPRC{" +
                "codProd='" + codProd + '\'' +
                ", codPresentacion='" + codPresentacion + '\'' +
                ", pvp='" + pvp + '\'' +
                ", fecha_vigencia='" + fecha_vigencia + '\'' +
                ", codProdLowsedo='" + codProdLowsedo + '\'' +
                '}';
    }

    public ItemPRC() {
    }

    public String getCodProdLowsedo() {
        return codProdLowsedo;
    }

    public void setCodProdLowsedo(String codProdLowsedo) {
        this.codProdLowsedo = codProdLowsedo;
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getCodPresentacion() {
        return codPresentacion;
    }

    public void setCodPresentacion(String codPresentacion) {
        this.codPresentacion = codPresentacion;
    }

    public String getPvp() {
        return pvp;
    }

    public void setPvp(String pvp) {
        this.pvp = pvp;
    }

    public String getFecha_vigencia() {
        return fecha_vigencia;
    }

    public void setFecha_vigencia(String fecha_vigencia) {
        this.fecha_vigencia = fecha_vigencia;
    }
}
