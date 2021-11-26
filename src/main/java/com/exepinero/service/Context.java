package com.exepinero.service;

import com.exepinero.model.Maestro;

/**
 *
 * Clase encarga de inicializar archivos y tareas necesarias para el correcto
 * funcionamiento de la aplicación.
 */

public class Context {

    private BuscarEnKairos buscarEnKairos;
    private Inicializador loader;
    private Maestro maestroDeProductos;


    public Context() {
        this.loader = new Inicializador();
        this.maestroDeProductos = new Maestro(loader);
        this.buscarEnKairos = new BuscarEnKairos(loader,maestroDeProductos);


    }

    public Maestro getMaestroDeProductos() {
        return maestroDeProductos;
    }

    public Inicializador getLoader() {
        return loader;
    }

    public BuscarEnKairos getBuscarEnKairos() {
        return buscarEnKairos;
    }

}
