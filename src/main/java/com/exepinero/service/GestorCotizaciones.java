package com.exepinero.service;

import com.exepinero.model.Cotizacion;
import com.exepinero.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class GestorCotizaciones {

    private List<Cotizacion> cotizaciones = new ArrayList<>(); // Probablemente no haga falta
    private List<String> cotizacionesCreadas = new ArrayList<>();
    private Cotizacion currentCotizacion;

    public GestorCotizaciones() {
        iniciarGestorCotizaciones();
    }

    public void iniciarGestorCotizaciones() {
        //TODO lee todos los nombres de las cotizaciones creadas
    }

    public void exportarCotizacion(){
        // TODO exporta cotizacion en current cotizacion
    }

    public Cotizacion leeCotizacion(String nombre){
        //TODO lee txt y genera cotizacion
        return null;
    }

    public Cotizacion getCurrentCotizacion() {
        return currentCotizacion;
    }

    public void setCurrentCotizacion(Cotizacion currentCotizacion) {
        this.currentCotizacion = currentCotizacion;
    }

    public List<Producto> muestraProductosCotizacion(){
        return currentCotizacion.getProductosCotizados();
    }

}
