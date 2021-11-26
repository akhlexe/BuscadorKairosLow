package com.exepinero.service;

import com.exepinero.model.Producto;
import com.exepinero.service.inicializadores.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Inicializador {


    private CargaArchivoDRO archivoDRO;
    private CargaArchivoDRP archivoDRP;
    private CargaArchivoPRC archivoPRC;
    private CargaArchivoLAB archivoLAB;
    private CargaArchivoPRE archivoPRE;
    private CargaArchivoPRO archivoPRO;

    public Inicializador() {

        archivoDRO = new CargaArchivoDRO();
        archivoDRO.loadDataFromTxt();

        archivoDRP = new CargaArchivoDRP();
        archivoDRP.loadDataFromTxt();

        archivoPRC = new CargaArchivoPRC();
        archivoPRC.loadDataFromTxt();

        archivoLAB = new CargaArchivoLAB();
        archivoLAB.loadDataFromTxt();

        archivoPRE = new CargaArchivoPRE();
        archivoPRE.loadDataFromTxt();

        archivoPRO = new CargaArchivoPRO();
        archivoPRO.loadDataFromTxt();

        System.out.println("Listo lectura de archivos");

    }



    public CargaArchivoDRO getArchivoDRO() {
        return archivoDRO;
    }

    public CargaArchivoDRP getArchivoDRP() {
        return archivoDRP;
    }

    public CargaArchivoPRC getArchivoPRC() {
        return archivoPRC;
    }

    public CargaArchivoLAB getArchivoLAB() {
        return archivoLAB;
    }

    public CargaArchivoPRE getArchivoPRE() {
        return archivoPRE;
    }

    public CargaArchivoPRO getArchivoPRO() {
        return archivoPRO;
    }
}
