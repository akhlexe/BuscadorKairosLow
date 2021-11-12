package com.exepinero.model;

public class ItemEncontrado {

    private String id;
    private String monodroga;
    private String presentacion;
    private String laboratorio;
    private String href;

    @Override
    public String toString() {
        return "ItemEncontrado{" +
                "id='" + id + '\'' +
                ", monodroga='" + monodroga + '\'' +
                ", nombre='" + presentacion + '\'' +
                ", laboratorio='" + laboratorio + '\'' +
                ", href='" + href + '\'' +
                '}';
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

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
