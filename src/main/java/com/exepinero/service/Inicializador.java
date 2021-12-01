package com.exepinero.service;

import com.exepinero.model.Config;
import com.exepinero.model.Producto;
import com.exepinero.service.inicializadores.*;
import com.exepinero.view.VentanaInicializador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Inicializador {


    private ArchivoWinrar winrar;
    private CargaArchivoDRO archivoDRO;
    private CargaArchivoDRP archivoDRP;
    private CargaArchivoPRC archivoPRC;
    private CargaArchivoLAB archivoLAB;
    private CargaArchivoPRE archivoPRE;
    private CargaArchivoPRO archivoPRO;
    private Config config;

    public Inicializador() {

        // Por defecto el sistema considera que no se actualizó aún.
        // Por lo que lee el archivo de config.txt y verifica la última vez que se actualizó la lista
        config = new Config();
        winrar = new ArchivoWinrar();
        System.out.println("Winrar leido...");

        archivoDRO = new CargaArchivoDRO(winrar);
        archivoDRO.loadDataFromTxt();

        archivoDRP = new CargaArchivoDRP(winrar);
        archivoDRP.loadDataFromTxt();

        archivoPRC = new CargaArchivoPRC(winrar);
        archivoPRC.loadDataFromTxt();

        archivoLAB = new CargaArchivoLAB(winrar);
        archivoLAB.loadDataFromTxt();

        archivoPRE = new CargaArchivoPRE(winrar);
        archivoPRE.loadDataFromTxt();

        archivoPRO = new CargaArchivoPRO(winrar);
        archivoPRO.loadDataFromTxt();

        System.out.println("Listo lectura de archivos");

    }


    public boolean isActualizado(){
        String fechaWinrar = winrar.getFechaInicialAdaptada();
        System.out.println(fechaWinrar);
        HashMap<String, String> configMap = config.getConfigMap();
        String fechaConfig = configMap.get("fechaActualizacion");
        System.out.println(fechaConfig);
        if(fechaConfig.equals(fechaWinrar)){
            return true;
        } else {
            return false;
        }
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
