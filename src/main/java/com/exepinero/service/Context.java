package com.exepinero.service;

/**
 *
 * Clase encarga de inicializar archivos y tareas necesarias para el correcto
 * funcionamiento de la aplicación.
 */

public class Context {

    private BuscarEnKairos buscarEnKairos;
    private Inicializador loader;


    public Context() {
        this.loader = new Inicializador();
        this.buscarEnKairos = new BuscarEnKairos(loader);

    }

    public Inicializador getLoader() {
        return loader;
    }

    public BuscarEnKairos getBuscarEnKairos() {
        return buscarEnKairos;
    }

}
