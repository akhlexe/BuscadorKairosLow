package com.exepinero.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Modeliza el concepto de cotizacion, se encarga de guardar un listado con toda la info
 * buscada para poder exportarla luego.
 */

public class Cotizacion {

    private List<Producto> productosBuscados = new ArrayList<>();
    private String nombreCotizacion;

    public Cotizacion(String rutaExportacion, String nombreCotizacion) {
        this.nombreCotizacion = nombreCotizacion;
    }

    public Cotizacion() {

    }

    public void agregarProductos(List<Producto> productos){
        productos.addAll(productos);
    }

    public void exportarCotizacion(String ruta){

    }
}
