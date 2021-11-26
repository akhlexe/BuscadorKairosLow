package com.exepinero.model;

import com.exepinero.dto.ItemDRP;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa los resultados finales que se mostraran en la tabla y se podrán
 * exportar a excel.
 */

public class Resultado {

    private String codMonodroga;
    private String monodroga;
    private String codProd;
    private String codPresentacion;
    private String codProdCarena;
    private String producto;
    private String presentacion;
    private String descripcionCarena;
    private String codLab;
    private String laboratorio;
    private String precio;
    private LocalDate ultimoAumento;



    public List<String> getVector(){
        List<String> vector = new ArrayList<>();
        vector.add(monodroga);
        vector.add(codProdCarena);
        vector.add(descripcionCarena);
        vector.add(laboratorio);
        vector.add(precio);
        return vector;
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "codMonodroga='" + codMonodroga + '\'' +
                ", monodroga='" + monodroga + '\'' +
                ", codProd='" + codProd + '\'' +
                ", codPresentacion='" + codPresentacion + '\'' +
                ", codProdCarena='" + codProdCarena + '\'' +
                ", producto='" + producto + '\'' +
                ", presentacion='" + presentacion + '\'' +
                ", descripcionCarena='" + descripcionCarena + '\'' +
                ", laboratorio='" + laboratorio + '\'' +
                ", precio='" + precio + '\'' +
                ", ultimoAumento=" + ultimoAumento +
                '}';
    }

    public String getCodLab() {
        return codLab;
    }

    public void setCodLab(String codLab) {
        this.codLab = codLab;
    }

    public String getCodMonodroga() {
        return codMonodroga;
    }

    public void setCodMonodroga(String codMonodroga) {
        this.codMonodroga = codMonodroga;
    }

    public String getMonodroga() {
        return monodroga;
    }

    public void setMonodroga(String monodroga) {
        this.monodroga = monodroga;
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

    public String getCodProdCarena() {
        return codProdCarena;
    }

    public void setCodProdCarena(String codProdCarena) {
        this.codProdCarena = codProdCarena;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getDescripcionCarena() {
        return descripcionCarena;
    }

    public void setDescripcionCarena(String descripcionCarena) {
        this.descripcionCarena = descripcionCarena;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public LocalDate getUltimoAumento() {
        return ultimoAumento;
    }

    public void setUltimoAumento(LocalDate ultimoAumento) {
        this.ultimoAumento = ultimoAumento;
    }
}
