package com.exepinero.model;

import com.exepinero.dto.*;
import com.exepinero.service.Inicializador;
import com.exepinero.view.VentanaInicializador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Maestro {

    private HashMap<String, Producto> maestroDeProductos = new HashMap<>();
    private Inicializador loader;
    private VentanaInicializador ventanaInicio;


    public Maestro(Inicializador loader) {
        ventanaInicio = new VentanaInicializador();
        this.loader = loader;
        boolean actualizado = loader.isActualizado();

        if(actualizado){
            leeMaestroDesdeTxt();
        }else{
            procesaMaestro();
        }

    }



    public void procesaMaestro(){

        inicializaMaestro();
        System.out.println("Maestro inicializado...");
        ventanaInicio.escribirEnConsola("Maestro inicializando...");

        importaInfoArchivoDRP();
        System.out.println("Archivo DRP Cargado...");
        ventanaInicio.escribirEnConsola("Cargando archivo DRP...");

        importaInfoArchivoDRO();
        System.out.println("Archivo DRO Cargado...");
        ventanaInicio.escribirEnConsola("Cargando archivo DRO...");

        importaInfoArchivoPRO();
        System.out.println("Archivo PRO Cargado...");
        ventanaInicio.escribirEnConsola("Cargando archivo PRO...");

        importaInfoArchivoLAB();
        System.out.println("Archivo LAB Cargado...");
        ventanaInicio.escribirEnConsola("Cargando archivo LAB...");

        importaInfoArchivoPRC();
        System.out.println("Archivo PRC Cargado...");
        ventanaInicio.escribirEnConsola("Cargando archivo PRC...");

        generaTxtMaestroProcesado();

        this.actualizaFechaMaestroEnConfig();

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
            tempProd.setCodMonodroga("null");
            tempProd.setNombreMonodroga("null");
            tempProd.setCodLab("null");
            tempProd.setNombreLab("null");
            tempProd.setRazonSocial("null");
            tempProd.setPrecio("null");
            tempProd.setFechaVigencia("null");
            tempProd.setNombreProducto("null");


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
            } else{
                p.setCodMonodroga("null");
            }
            maestroDeProductos.put(p.getCodProdLowsedo(),p);
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

            }else{
                p.setNombreMonodroga(null);
            }
            maestroDeProductos.put(p.getCodProdLowsedo(),p);
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

            }else{
                p.setNombreProducto("null");
                p.setCodLab("null");
            }
            maestroDeProductos.put(p.getCodProdLowsedo(), p);
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

            }else{
                p.setNombreLab("null");
                p.setRazonSocial("null");
            }

            maestroDeProductos.put(p.getCodProdLowsedo(),p);
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

            }else{
                p.setPrecio("null");
                p.setFechaVigencia("null");
            }
            maestroDeProductos.put(codProdLowsedo,p);
        }
    }



    public void generaTxtMaestroProcesado(){

        List<Producto> listadoProductos = new ArrayList<>(maestroDeProductos.values());
        List<Producto> productosSinPreciosNulos = listadoProductos.stream().filter(producto -> !producto.getPrecio().equals("null")).collect(Collectors.toList());

        System.out.println(listadoProductos.size());

        try {
            File archivo = new File("P:\\Usuarios\\Exequiel\\AppCotizaciones\\maestro.txt");
            FileWriter writer = new FileWriter(archivo);

            for(Producto producto:productosSinPreciosNulos){
                writer.write(producto.getDatosProducto());
            }
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leeMaestroDesdeTxt(){

        try {
            BufferedReader br = new BufferedReader(new FileReader("P:\\Usuarios\\Exequiel\\AppCotizaciones\\maestro.txt"));
            String line = br.readLine();

            while(line!=null){

                String[] registro = line.split(";",-1);

                String codMonodroga = registro[0];
                String codProducto = registro[1];
                String codPresentacion = registro[2];
                String codProdLowsedo = registro[3];
                String codLab = registro[4];
                String GTIN = registro[5];
                String nombreMonodroga = registro[6];
                String nombreProducto = registro[7];
                String nombrePresentacion = registro[8];
                String nombreLab = registro[9];
                String razonSocial = registro[10];
                String precio = registro[11];
                String fechaVigencia = registro[12];


                Producto tempProd = new Producto();
                tempProd.setCodMonodroga(codMonodroga);
                tempProd.setCodProducto(codProducto);
                tempProd.setCodPresentacion(codPresentacion);
                tempProd.setCodProdLowsedo(codProdLowsedo);
                tempProd.setCodLab(codLab);
                tempProd.setGTIN(GTIN);
                tempProd.setNombreMonodroga(nombreMonodroga);
                tempProd.setNombreProducto(nombreProducto);
                tempProd.setNombrePresentacion(nombrePresentacion);
                tempProd.setNombreLab(nombreLab);
                tempProd.setRazonSocial(razonSocial);
                tempProd.setPrecio(precio);
                tempProd.setFechaVigencia(fechaVigencia);

                maestroDeProductos.put(codProdLowsedo,tempProd);
                line = br.readLine();
            }
            System.out.println("Maestro cargado");
            br.close();
            ventanaInicio.cerrarVentanaInicio();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void actualizaFechaMaestroEnConfig(){
        String fechaInicialAdaptada = loader.getWinrar().getFechaInicialAdaptada();
        Config config = loader.getConfig();
        HashMap<String, String> configMap = config.getConfigMap();
        configMap.put("fechaActualizacion",fechaInicialAdaptada);
        config.guardarConfig();
    }



    public HashMap<String, Producto> getMaestroDeProductos() {
        return maestroDeProductos;
    }
}
