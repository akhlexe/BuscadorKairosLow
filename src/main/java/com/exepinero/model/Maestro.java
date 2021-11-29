package com.exepinero.model;

import com.exepinero.dto.*;
import com.exepinero.service.Inicializador;
import com.exepinero.view.VentanaInicializador;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Maestro {

    private HashMap<String, Producto> maestroDeProductos = new HashMap<>();
    private Inicializador loader;
    private VentanaInicializador ventanaInicio;


    public Maestro(Inicializador loader) {
        ventanaInicio = new VentanaInicializador();
        this.loader = loader;
        procesaMaestro();
    }

    public void procesaMaestro(){

        inicializaMaestro();
        System.out.println("Maestro inicializado...");
        ventanaInicio.escribirEnConsola("Maestro inicializado...");

        importaInfoArchivoDRP();
        System.out.println("Archivo DRP Cargado...");
        ventanaInicio.escribirEnConsola("Archivo DRP Cargado...");

        importaInfoArchivoDRO();
        System.out.println("Archivo DRO Cargado...");
        ventanaInicio.escribirEnConsola("Archivo DRO Cargado...");

        importaInfoArchivoPRO();
        System.out.println("Archivo PRO Cargado...");
        ventanaInicio.escribirEnConsola("Archivo PRO Cargado...");

        importaInfoArchivoLAB();
        System.out.println("Archivo LAB Cargado...");
        ventanaInicio.escribirEnConsola("Archivo LAB Cargado...");

        importaInfoArchivoPRC();
        System.out.println("Archivo PRC Cargado...");
        ventanaInicio.escribirEnConsola("Archivo PRC Cargado...");

        ventanaInicio.cerrarVentanaInicio();

    }

    public void inicializaMaestro(){

        List<ItemPRE> listadoItemsPRE = loader.getArchivoPRE().getListadoItemsPRE();

        for (ItemPRE item:listadoItemsPRE){
            Producto tempProd = new Producto();
            String codLowsedo = item.getCodProd().concat(item.getCodPresent());
            tempProd.setCodProducto(item.getCodProd());
            tempProd.setCodPresentacion(item.getCodPresent());
            tempProd.setNombrePresentacion(item.getDescripPresentacion());
            tempProd.setGTIN(item.getGTIN());
            tempProd.setCodProdLowsedo(codLowsedo);

            // ------------------- Inicializo vacio los demás parametros para evitar null pointer -----
            tempProd.setCodMonodroga("");
            tempProd.setNombreMonodroga("");
            tempProd.setCodLab("");
            tempProd.setNombreLab("");
            tempProd.setRazonSocial("");
            tempProd.setPrecio("");
            tempProd.setFechaVigencia("");
            tempProd.setNombreProducto("");

            maestroDeProductos.put(codLowsedo,tempProd);
        }
    }

    public void importaInfoArchivoDRP(){

        List<ItemDRP> listadoMonodrogas = loader.getArchivoDRP().getListadoItemsDRP();

        for(Producto p: maestroDeProductos.values()){

            Optional<ItemDRP> first = listadoMonodrogas.stream().filter(i -> i.getCodProd().equals(p.getCodProducto())).findFirst();
            if(first.isPresent()){
                ItemDRP itemDRP = first.get();
                p.setCodMonodroga(itemDRP.getCodMonodroga());
                maestroDeProductos.put(p.getCodProdLowsedo(),p);
            }
        }
    }

    public void importaInfoArchivoDRO(){
        List<ItemDRO> listadoItemsDRO = loader.getArchivoDRO().getListadoItemsDRO();

        for(Producto p : maestroDeProductos.values()){

            String codProducto = p.getCodProducto();
            Optional<ItemDRO> itemDRO = listadoItemsDRO.stream().filter(i -> i.getCodMonodroga().equals(p.getCodMonodroga())).findFirst();
            if(itemDRO.isPresent()){
                ItemDRO datosMondroga = itemDRO.get();
                p.setNombreMonodroga(datosMondroga.getNombreMonodroga());
                maestroDeProductos.put(p.getCodProdLowsedo(),p);
            }
        }
    }
    public void importaInfoArchivoPRO(){

        List<ItemPRO> listadoItemsPRO = loader.getArchivoPRO().getListadoItemsPRO();

        for(Producto p:maestroDeProductos.values()){

            Optional<ItemPRO> first = listadoItemsPRO.stream().filter(i -> i.getCodProd().equals(p.getCodProducto())).findFirst();
            if(first.isPresent()){
                ItemPRO itemPRO = first.get();
                p.setNombreProducto(itemPRO.getDescripProd());
                p.setCodLab(itemPRO.getCodLab());
                maestroDeProductos.put(p.getCodProdLowsedo(), p);
            }
        }
    }

    public void importaInfoArchivoLAB(){

        List<ItemLAB> listadoItemsLAB = loader.getArchivoLAB().getListadoItemsLAB();

        for(Producto p:maestroDeProductos.values()){

            Optional<ItemLAB> first = listadoItemsLAB.stream().filter(i -> i.getCodLab().equals(p.getCodLab())).findFirst();
            if(first.isPresent()){
                ItemLAB itemLAB = first.get();
                p.setNombreLab(itemLAB.getDescripLab());
                p.setRazonSocial(itemLAB.getRazonSocial());
                maestroDeProductos.put(p.getCodProdLowsedo(),p);
            }
        }
    }

    public void importaInfoArchivoPRC(){

        List<ItemPRC> listadoItemsPRC = loader.getArchivoPRC().getListadoItemsPRC();

        for(Producto p:maestroDeProductos.values()){
            String codProdLowsedo = p.getCodProdLowsedo();
            Optional<ItemPRC> first = listadoItemsPRC.stream().filter(i -> i.getCodProdLowsedo().equals(codProdLowsedo)).findFirst();
            if(first.isPresent()){
                ItemPRC itemPRC = first.get();
                p.setPrecio(itemPRC.getPvp());
                p.setFechaVigencia(itemPRC.getFecha_vigencia());
                maestroDeProductos.put(codProdLowsedo,p);
            }
        }
    }



    public HashMap<String, Producto> getMaestroDeProductos() {
        return maestroDeProductos;
    }
}
