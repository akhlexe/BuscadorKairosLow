package com.exepinero.model;

import java.awt.*;

public class Monodroga {

    private String id;
    private String nombre;

    @Override
    public String toString() {
        return "Monodroga{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public Monodroga() {
    }

    public Monodroga(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
