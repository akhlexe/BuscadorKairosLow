package com.exepinero.model;

import com.exepinero.dto.*;
import com.exepinero.service.Inicializador;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Maestro {

    private HashMap<String, Producto> maestroDeProductos = new HashMap<>();
    private Inicializador loader;

    public Maestro(Inicializador loader) {
        this.loader = loader;
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

    public void importarInfoArchivoDRO(){
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
    public void importarInfoArchivoPRO(){

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

    public void importarInfoArchivoLAB(){

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

    public void importarInfoArchivoPRC(){

        List<ItemPRC> listadoItemsPRC = loader.getArchivoPRC().getListadoItemsPRC();

        for(Producto p:maestroDeProductos.values()){

        }
    }
}
