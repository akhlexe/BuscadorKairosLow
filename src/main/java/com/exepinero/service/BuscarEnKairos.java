package com.exepinero.service;

import com.exepinero.dto.ItemDRO;
import com.exepinero.model.Laboratorio;
import com.exepinero.model.Maestro;
import com.exepinero.model.Monodroga;
import com.exepinero.model.Producto;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BuscarEnKairos {

    private List<Producto> productos = new ArrayList<>();
    private Inicializador loader;
    private HashMap<String, Producto> maestroDeProductos;
    private DatosLaboratorios datosLaboratorios;



    /**
     *
     * Constructor con parametro Inicializador.
     */

    public BuscarEnKairos(Inicializador loader, Maestro maestro){

        this.updateLabos();
        this.loader = loader;
        this.maestroDeProductos = maestro.getMaestroDeProductos();
    }

    /**
     * Pasaje de info desde el field inicial a panel Lateral
     */

    public List<ItemDRO> consultaOpciones(String monodroga) throws IOException {

        List<ItemDRO> itemDROs = loader.getArchivoDRO().getListadoItemsDRO();

        List<ItemDRO> opciones = itemDROs.stream()
                .filter(p -> p.getNombreMonodroga().toLowerCase(Locale.ROOT).contains(monodroga.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());

        return opciones;
    }

    /**
     * Ejecuta consulta de la monodroga seleccionada y devuelve un listado
     * de todas las presentaciones que contienen esa monodroga
     */

    public List<Producto> ejecutaConsulta(ItemDRO monodroga){

        productos = obtieneResultados(monodroga);
        aplicaFiltros(productos);
        return productos;
    }

    public List<Producto> obtieneResultados (ItemDRO monodroga){

        List<Producto> productosOutput = new ArrayList<>();
        List<Producto> listadoParcial;

        String idMonodroga = monodroga.getCodMonodroga();
        boolean compuesto = monodroga.isCompuesto();


        if(compuesto){
            listadoParcial = maestroDeProductos.values().stream()
                    .filter(Producto::isCompuesto)
                    .collect(Collectors.toList());
        } else {
            listadoParcial = maestroDeProductos.values().stream()
                    .filter(producto -> !producto.isCompuesto())
                    .collect(Collectors.toList());
        }

        for(Producto prod:listadoParcial){

            List<String> idMonodrogas = prod.getMonodrogas().stream()
                    .map(Monodroga::getId)
                    .collect(Collectors.toList());


            if(idMonodrogas.contains(idMonodroga)){
                productosOutput.add(prod);
            }
        }



        //productosOutput.forEach(producto -> producto.setNombreMonodrogaBuscada(monodroga.getNombreMonodroga()));
        productosOutput.forEach(producto -> {
            List<String> monodrogasString = producto.getMonodrogas().stream().map(Monodroga::getNombre).collect(Collectors.toList());
            int cantidadDeMonodrogas = producto.getMonodrogas().size();
            String cadenaDeMonodrogas = "";

            for(int i=0; i<cantidadDeMonodrogas; i++){
                if (i == (cantidadDeMonodrogas - 1)){
                    cadenaDeMonodrogas = cadenaDeMonodrogas.concat(monodrogasString.get(i));
                    break;
                }
                cadenaDeMonodrogas = cadenaDeMonodrogas.concat(monodrogasString.get(i)).concat(" + ");
            }

            producto.setNombreMonodrogaBuscada(cadenaDeMonodrogas);
        });


        return productosOutput;
    }

    public void aplicaFiltros(List<Producto> productosInput){

        List<Producto> productos1 = productosInput.stream()
                .filter(p -> !p.getPrecio().equals("null"))
                .sorted((Comparator.comparing(Producto::getNombreLab)))
                .collect(Collectors.toList());

        List<Laboratorio> laboratorios = datosLaboratorios.getLaboratorios();

        List<String> activos = laboratorios.stream()
                .map(Laboratorio::getId)
                .collect(Collectors.toList());

        List<Producto> productos2 = productos1.stream()
                .filter(producto -> activos.contains(producto.getCodLab()))
                .collect(Collectors.toList());

        productos = productos2;
    }

    public void updateLabos(){

        datosLaboratorios = new DatosLaboratorios();
        Thread thread = new Thread(datosLaboratorios);
        thread.start();

    }
}


















