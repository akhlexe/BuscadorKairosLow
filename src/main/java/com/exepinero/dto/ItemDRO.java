package com.exepinero.dto;

/**
 * Clase que modeliza fila del archivo dro.txt de Kairos
 * Mostrando la información relativa entre codigo de monodroga y nombre de monodroga.
 */

public class ItemDRO {

    private String codMonodroga;
    private String nombreMonodroga;

    public ItemDRO(String codMonodroga, String nombreMonodroga) {
        this.codMonodroga = codMonodroga;
        this.nombreMonodroga = nombreMonodroga;
    }

    @Override
    public String toString() {
        return "ItemDRO{" +
                "codMonodroga='" + codMonodroga + '\'' +
                ", nombreMonodroga='" + nombreMonodroga + '\'' +
                '}';
    }

    public ItemDRO() {
    }

    public String getCodMonodroga() {
        return codMonodroga;
    }

    public void setCodMonodroga(String codMonodroga) {
        this.codMonodroga = codMonodroga;
    }

    public String getNombreMonodroga() {
        return nombreMonodroga;
    }

    public void setNombreMonodroga(String nombreMonodroga) {
        this.nombreMonodroga = nombreMonodroga;
    }
}
