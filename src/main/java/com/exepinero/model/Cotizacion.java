package com.exepinero.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Modeliza el concepto de cotizacion, se encarga de guardar un listado con toda la info
 * buscada para poder exportarla luego.
 */

public class Cotizacion {

    private String author;
    private String nombreCotizacion;
    private List<Producto> productosCotizados = new ArrayList<>();

    public Cotizacion(String nombreCotizacion) {
        this.nombreCotizacion = nombreCotizacion;
    }

    public void agregarProductos(List<Producto> productosNuevos){
        productosCotizados.addAll(productosNuevos);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNombreCotizacion() {
        return nombreCotizacion;
    }

    public void setNombreCotizacion(String nombreCotizacion) {
        this.nombreCotizacion = nombreCotizacion;
    }

    public List<Producto> getProductosCotizados() {
        return productosCotizados;
    }

    public void setProductosCotizados(List<Producto> productosCotizados) {
        this.productosCotizados = productosCotizados;
    }

}
