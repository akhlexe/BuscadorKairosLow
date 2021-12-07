package com.exepinero.model;

import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Producto {


    private String codProducto;
    private String codPresentacion;
    private String codProdLowsedo;
    private String codLab;
    private String GTIN;
    private String nombreProducto;
    private String nombrePresentacion;
    private String nombreLab;
    private String razonSocial;
    private String precio;
    private String fechaVigencia;

    private String nombreMonodrogaBuscada;
    private List<Monodroga> monodrogas;
    private boolean compuesto;

    private double precioDouble;
    private LocalDate fechaNueva;


    public String getDatosProducto(){

        return codProducto + ";" +
                codPresentacion + ";" +
                codProdLowsedo + ";" +
                codLab + ";" +
                GTIN + ";" +
                nombreProducto + ";" +
                nombrePresentacion + ";" +
                nombreLab + ";" +
                razonSocial + ";" +
                precio + ";" +
                fechaVigencia+"\n";
    }

    public String getDatosMonodrogas(){
        if(monodrogas.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();

        for(Monodroga monodroga:monodrogas){
            sb.append(codProdLowsedo);
            sb.append(";");
            sb.append(monodroga.getId());
            sb.append(";");
            sb.append(monodroga.getNombre());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codProducto='" + codProducto + '\'' +
                ", codPresentacion='" + codPresentacion + '\'' +
                ", codProdLowsedo='" + codProdLowsedo + '\'' +
                ", codLab='" + codLab + '\'' +
                ", GTIN='" + GTIN + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", nombrePresentacion='" + nombrePresentacion + '\'' +
                ", nombreLab='" + nombreLab + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", precio='" + precio + '\'' +
                ", fechaVigencia='" + fechaVigencia + '\'' +
                ", nombreMonodrogaBuscada='" + nombreMonodrogaBuscada + '\'' +
                ", monodrogas=" + monodrogas +
                ", compuesto=" + compuesto +
                ", precioDouble=" + precioDouble +
                ", fechaNueva=" + fechaNueva +
                '}';
    }

    public Producto() {
    }


    public String getNombreMonodrogaBuscada() {
        return nombreMonodrogaBuscada;
    }

    public void setNombreMonodrogaBuscada(String nombreMonodrogaBuscada) {
        this.nombreMonodrogaBuscada = nombreMonodrogaBuscada;
    }

    public List<Monodroga> getMonodrogas() {
        return monodrogas;
    }

    public void setMonodrogas(List<Monodroga> monodrogas) {
        this.monodrogas = monodrogas;
    }

    public boolean isCompuesto() {
        return compuesto;
    }

    public void setCompuesto(boolean compuesto) {
        this.compuesto = compuesto;
    }

    public String getGTIN() {
        return GTIN;
    }

    public void setGTIN(String GTIN) {
        this.GTIN = GTIN;
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
        vector.add(nombreMonodrogaBuscada);
        String nombreCompleto = nombreProducto.concat(" ").concat(nombrePresentacion);
        vector.add(nombreCompleto);
        vector.add(nombreLab);
        vector.add(precio);
        return vector;
    }
}
