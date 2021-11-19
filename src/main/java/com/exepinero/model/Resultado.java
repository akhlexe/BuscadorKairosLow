package com.exepinero.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Resultado {

    private String id;
    private String monodroga;
    private String descripcion;
    private String laboratorio;
    private String precio;
    private LocalDate ultimoAumento;
    private String href;


    public List<String> getVector(){
        List<String> vector = new ArrayList<>();
        vector.add(id);
        vector.add(monodroga);
        vector.add(descripcion);
        vector.add(laboratorio);
        vector.add(precio);
        vector.add(href);

        return vector;
    }


    @Override
    public String toString() {
        return "Resultado{" +
                "id='" + id + '\'' +
                ", monodroga='" + monodroga + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", laboratorio='" + laboratorio + '\'' +
                ", precio='" + precio + '\'' +
                ", ultimoAumento=" + ultimoAumento +
                ", href='" + href + '\'' +
                '}';
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonodroga() {
        return monodroga;
    }

    public void setMonodroga(String monodroga) {
        this.monodroga = monodroga;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
