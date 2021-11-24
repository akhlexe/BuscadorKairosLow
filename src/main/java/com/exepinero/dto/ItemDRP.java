package com.exepinero.dto;

/**
 * Clase que modeliza la fila de información del archivo drp.txt de Kairos
 * Los campos muestran la información relativa entre monodrogas y producto que la contiene.
 */

public class ItemDRP {

    private String codMonodroga;
    private String codProd;

    public ItemDRP(String codMonodroga, String codProd) {
        this.codMonodroga = codMonodroga;
        this.codProd = codProd;
    }

    @Override
    public String toString() {
        return "ItemDRP{" +
                "codMonodroga='" + codMonodroga + '\'' +
                ", codProd='" + codProd + '\'' +
                '}';
    }

    public ItemDRP() {
    }

    public String getCodMonodroga() {
        return codMonodroga;
    }

    public void setCodMonodroga(String codMonodroga) {
        this.codMonodroga = codMonodroga;
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }
}
