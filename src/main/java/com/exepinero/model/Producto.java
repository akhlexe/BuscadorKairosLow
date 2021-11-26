package com.exepinero.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Producto {

    private String codMonodroga;
    private String codProducto;
    private String codPresentacion;
    private String codProdLowsedo;
    private String codLab;
    private String GTIN;
    private String nombreMonodroga;
    private String nombreProducto;
    private String nombrePresentacion;
    private String nombreLab;
    private String razonSocial;
    private String precio;
    private String fechaVigencia;

    private double precioDouble;
    private LocalDate fechaNueva;

    @Override
    public String toString() {
        return "Producto{" +
                "codMonodroga='" + codMonodroga + '\'' +
                ", codProducto='" + codProducto + '\'' +
                ", codPresentacion='" + codPresentacion + '\'' +
                ", codProdLowsedo='" + codProdLowsedo + '\'' +
                ", codLab='" + codLab + '\'' +
                ", GTIN='" + GTIN + '\'' +
                ", nombreMonodroga='" + nombreMonodroga + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", nombrePresentacion='" + nombrePresentacion + '\'' +
                ", nombreLab='" + nombreLab + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", precio='" + precio + '\'' +
                ", fechaVigencia='" + fechaVigencia + '\'' +
                ", precioDouble=" + precioDouble +
                ", fechaNueva=" + fechaNueva +
                '}';
    }

    public Producto() {
    }

    public String getGTIN() {
        return GTIN;
    }

    public void setGTIN(String GTIN) {
        this.GTIN = GTIN;
    }

    public String getCodMonodroga() {
        return codMonodroga;
    }

    public void setCodMonodroga(String codMonodroga) {
        this.codMonodroga = codMonodroga;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getCodPresentacion() {
        return codPresentacion;
    }

    public void setCodPresentacion(String codPresentacion) {
        this.codPresentacion = codPresentacion;
    }

    public String getCodProdLowsedo() {
        return codProdLowsedo;
    }

    public void setCodProdLowsedo(String codProdLowsedo) {
        this.codProdLowsedo = codProdLowsedo;
    }

    public String getCodLab() {
        return codLab;
    }

    public void setCodLab(String codLab) {
        this.codLab = codLab;
    }

    public String getNombreMonodroga() {
        return nombreMonodroga;
    }

    public void setNombreMonodroga(String nombreMonodroga) {
        this.nombreMonodroga = nombreMonodroga;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombrePresentacion() {
        return nombrePresentacion;
    }

    public void setNombrePresentacion(String nombrePresentacion) {
        this.nombrePresentacion = nombrePresentacion;
    }

    public String getNombreLab() {
        return nombreLab;
    }

    public void setNombreLab(String nombreLab) {
        this.nombreLab = nombreLab;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(String fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public double getPrecioDouble() {
        return precioDouble;
    }

    public void setPrecioDouble(double precioDouble) {
        this.precioDouble = precioDouble;
    }

    public LocalDate getFechaNueva() {
        return fechaNueva;
    }

    public void setFechaNueva(LocalDate fechaNueva) {
        this.fechaNueva = fechaNueva;
    }

    public List<String> getVector(){
        List<String> vector = new ArrayList<>();
        vector.add(codProdLowsedo);
        vector.add(GTIN);
        vector.add(nombreMonodroga);
        String nombreCompleto = nombreProducto.concat(" ").concat(nombrePresentacion);
        vector.add(nombreCompleto);
        vector.add(nombreLab);
        vector.add(precio);
        return vector;
    }
}
