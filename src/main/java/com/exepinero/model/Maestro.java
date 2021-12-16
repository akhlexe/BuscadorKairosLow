package com.exepinero.model;

import com.exepinero.dto.*;
import com.exepinero.service.Inicializador;
import com.exepinero.view.VentanaInicializador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
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

            tempProd.setCompuesto(false);
            tempProd.setNombreMonodrogaBuscada("null");
            tempProd.setCodLab("null");
            tempProd.setNombreLab("null");
            tempProd.setRazonSocial("null");
            tempProd.setPrecio("null");
            tempProd.setFechaVigencia("null");
            tempProd.setNombreProducto("null");
            tempProd.setMonodrogas(new ArrayList<>());


            maestroDeProductos.put(codLowsedo,tempProd);
        }
    }

    public void importaInfoArchivoDRP(){

        List<ItemDRP> listadoMonodrogas = loader.getArchivoDRP().getListadoItemsDRP();

        for(Producto p: maestroDeProductos.values()){

            List<ItemDRP> monodrogas = listadoMonodrogas.stream()
                    .filter(i -> i.getCodProd().equals(p.getCodProducto()))
                    .collect(Collectors.toList());

            if(!monodrogas.isEmpty()){

                for(ItemDRP monodroga:monodrogas) {
                    Monodroga tempMono = new Monodroga();
                    tempMono.setId(monodroga.getCodMonodroga());
                    tempMono.setNombre("");
                    p.getMonodrogas().add(tempMono);
                }

                if(p.getMonodrogas().size()>1) p.setCompuesto(true);

            } else{
                p.setMonodrogas(new ArrayList<>());
            }
            maestroDeProductos.put(p.getCodProdLowsedo(),p);
        }
    }

    public void importaInfoArchivoDRO(){
        List<ItemDRO> listadoItemsDRO = loader.getArchivoDRO().getListadoItemsDRO();

        for(Producto p : maestroDeProductos.values()) {

            List<Monodroga> monodrogas = p.getMonodrogas();

            for (Monodroga mono : monodrogas) {

                Optional<ItemDRO> itemDRO = listadoItemsDRO.stream()
                        .filter(i -> i.getCodMonodroga().equals(mono.getId()))
                        .findFirst();

                if (itemDRO.isPresent()) {
                    ItemDRO datosMonodroga = itemDRO.get();
                    mono.setNombre(datosMonodroga.getNombreMonodroga());
                }
            }

            p.setMonodrogas(monodrogas);
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

        try {
            File archivo = new File("P:\\Usuarios\\Exequiel\\AppCotizaciones\\maestro.txt");
            File archivoMonodrogas = new File("P:\\Usuarios\\Exequiel\\AppCotizaciones\\tablaMonodrogas.txt");
            FileWriter writer = new FileWriter(archivo, StandardCharsets.ISO_8859_1);
            FileWriter writerMonodrogas = new FileWriter(archivoMonodrogas);

            for(Producto producto:productosSinPreciosNulos){
                writer.write(producto.getDatosProducto());
                writerMonodrogas.write(producto.getDatosMonodrogas());
            }
            writer.close();
            writerMonodrogas.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leeMaestroDesdeTxt(){

        try {

            BufferedReader br = new BufferedReader(new FileReader("P:\\Usuarios\\Exequiel\\AppCotizaciones\\maestro.txt", StandardCharsets.ISO_8859_1));
            String line = br.readLine();

            while(line!=null){

                String[] registro = line.split(";",-1);

                String codProducto = registro[0];
                String codPresentacion = registro[1];
                String codProdLowsedo = registro[2];
                String codLab = registro[3];
                String GTIN = registro[4];
                String nombreProducto = registro[5];
                String nombrePresentacion = registro[6];
                String nombreLab = registro[7];
                String razonSocial = registro[8];
                String precio = registro[9];
                String fechaVigencia = registro[10];

                Producto tempProd = new Producto();
                tempProd.setCodProducto(codProducto);
                tempProd.setCodPresentacion(codPresentacion);
                tempProd.setCodProdLowsedo(codProdLowsedo);
                tempProd.setCodLab(codLab);
                tempProd.setGTIN(GTIN);
                tempProd.setNombreMonodrogaBuscada("");
                tempProd.setNombreProducto(nombreProducto);
                tempProd.setNombrePresentacion(nombrePresentacion);
                tempProd.setNombreLab(nombreLab);
                tempProd.setRazonSocial(razonSocial);
                tempProd.setPrecio(precio);
                tempProd.setFechaVigencia(fechaVigencia);
                tempProd.setMonodrogas(new ArrayList<>());

                maestroDeProductos.put(codProdLowsedo,tempProd);
                line = br.readLine();
            }
            System.out.println("Maestro cargado");
            br.close();

            BufferedReader br2 = new BufferedReader(new FileReader("P:\\Usuarios\\Exequiel\\AppCotizaciones\\tablaMonodrogas.txt"));
            String lineMonodroga = br2.readLine();

            while(lineMonodroga!=null){

                String [] registro = lineMonodroga.split(";",-1);
                String codProdLowsedo = registro[0];
                String codMonodroga = registro[1];
                String nombreMonodroga = registro[2];
                Monodroga tempMono = new Monodroga();
                tempMono.setId(codMonodroga);
                tempMono.setNombre(nombreMonodroga);
                Producto producto = maestroDeProductos.get(codProdLowsedo);
                producto.getMonodrogas().add(tempMono);
                maestroDeProductos.put(codProdLowsedo,producto);

                lineMonodroga = br2.readLine();
            }
            System.out.println("Monodrogas cargas a los productos");
            br2.close();

            for(Producto prod:maestroDeProductos.values()){
                if(prod.getMonodrogas().size() > 1){
                    prod.setCompuesto(true);
                }
                maestroDeProductos.put(prod.getCodProdLowsedo(),prod);
            }

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
