package com.exepinero.service;

/**
 *
 * Clase encarga de inicializar archivos y tareas necesarias para el correcto
 * funcionamiento de la aplicación.
 */

public class Context {

    private DatosMonodrogas datosMonodrogas;
    private DatosLaboratorios datosLaboratorios;
    private BuscarEnKairos buscarEnKairos;


    public Context() {
        this.datosMonodrogas = new DatosMonodrogas();
        this.buscarEnKairos = new BuscarEnKairos(datosMonodrogas);
        this.datosLaboratorios = new DatosLaboratorios();

    }


    public DatosLaboratorios getDatosLaboratorios() {
        return datosLaboratorios;
    }

    public DatosMonodrogas getDatosMonodrogas() {
        return datosMonodrogas;
    }

    public BuscarEnKairos getBuscarEnKairos() {
        return buscarEnKairos;
    }

}