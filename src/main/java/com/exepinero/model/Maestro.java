package com.exepinero.model;

import com.exepinero.dto.*;
import com.exepinero.service.Inicializador;
import com.exepinero.view.VentanaInicializador;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

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

        ventanaInicio.cerrarVentanaInicio();

        generaTxtMaestroProcesado();

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



    public void generaTxtMaestroProcesado(){

        List<Producto> listadoProductos = new ArrayList<>(maestroDeProductos.values());
        System.out.println(listadoProductos.size());

        try {
            OutputStream outputStream = new FileOutputStream(new File("P:\\Usuarios\\Exequiel\\AppCotizaciones\\maestro.txt"));
            OutputStreamWriter osw = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw);

            for(Producto producto:listadoProductos){
                bw.write(producto.getDatosProducto());
                bw.newLine();
            }

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

                Arrays.stream(registro).forEach(System.out::print);

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
                System.out.println(registro.length);

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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public HashMap<String, Producto> getMaestroDeProductos() {
        return maestroDeProductos;
    }
}
