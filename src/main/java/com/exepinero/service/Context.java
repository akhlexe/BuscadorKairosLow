package com.exepinero.service;

import com.exepinero.model.Maestro;
import com.exepinero.view.PanelLateral;
import com.exepinero.view.PanelMedio;
import com.exepinero.view.PanelSuperior;

/**
 *
 * Clase encarga de inicializar archivos y tareas necesarias para el correcto
 * funcionamiento de la aplicación.
 */

public class Context {

    private BuscarEnKairos buscarEnKairos;
    private Inicializador loader;
    private Maestro maestroDeProductos;
    private PanelLateral panelLateral;
    private PanelMedio panelMedio;
    private PanelSuperior panelSuperior;


    public Context() {
        this.loader = new Inicializador();
        this.maestroDeProductos = new Maestro(loader);
        this.buscarEnKairos = new BuscarEnKairos(loader,maestroDeProductos);
        panelMedio = new PanelMedio();
        panelLateral = new PanelLateral(panelMedio, loader,buscarEnKairos);
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

    public PanelLateral getPanelLateral() {
        return panelLateral;
    }

    public PanelMedio getPanelMedio() {
        return panelMedio;
    }
}
