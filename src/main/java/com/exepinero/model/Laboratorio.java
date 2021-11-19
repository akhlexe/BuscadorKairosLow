package com.exepinero.model;

public class Laboratorio {

    private String id;
    private String nombre;
    private boolean active;

    @Override
    public String toString() {
        return "Laboratorio{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", active=" + active +
                '}';
    }

    public Laboratorio() {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
