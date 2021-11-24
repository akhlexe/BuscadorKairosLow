package com.exepinero.service;

import com.exepinero.dto.ItemDRP;
import com.exepinero.dto.ItemPRC;
import com.exepinero.model.Producto;
import com.exepinero.service.inicializadores.*;

import java.util.ArrayList;
import java.util.List;


public class Inicializador {

    private List<Producto> maestroDeProductos = new ArrayList<>();

    private CargaArchivoDRO archivoDRO;
    private CargaArchivoDRP archivoDRP;
    private CargaArchivoPRC archivoPRC;
    private CargaArchivoLAB archivoLAB;
    private CargaArchivoPRE archivoPRE;
    private CargaArchivoPRO archivoPRO;


    public Inicializador() {

        archivoDRO = new CargaArchivoDRO();
        archivoDRP = new CargaArchivoDRP();
        archivoPRC = new CargaArchivoPRC();
        archivoLAB = new CargaArchivoLAB();
        archivoPRE = new CargaArchivoPRE();
        archivoPRO = new CargaArchivoPRO();

        System.out.println("Listo lectura de archivos");

        updatePrcToMaestro();

    }

    public void updatePrcToMaestro(){
        List<ItemPRC> listadoItemsPRC = archivoPRC.getListadoItemsPRC();

        for(ItemPRC item:listadoItemsPRC){

            Producto producto = new Producto();
            producto.setCodProd(item.getCodProd());
            producto.setCodPresentacion(item.getCodPresentacion());
            producto.setPVP(item.getPvp());
            producto.setFechaVigencia(item.getFecha_vigencia());

            maestroDeProductos.add(producto);
        }

    }

    public void updateDrpToMaestro(){

        List<ItemDRP> listadoItemsDRP = archivoDRP.getListadoItemsDRP();


    }



}
