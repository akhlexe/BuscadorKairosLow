package com.exepinero.service;

import com.exepinero.dto.ItemDRO;
import com.exepinero.dto.ItemDRP;
import com.exepinero.dto.ItemPRE;
import com.exepinero.dto.ItemPRO;
import com.exepinero.model.*;
import org.apache.poi.ss.formula.functions.T;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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

        datosLaboratorios = new DatosLaboratorios();
        Thread thread = new Thread(datosLaboratorios);
        thread.start();

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
        String idMonodroga = monodroga.getCodMonodroga();

        List<Producto> listadoProductos = maestroDeProductos.values().stream()
                .filter(producto -> producto.getCodMonodroga().equals(idMonodroga))
                .collect(Collectors.toList());

        // ----------- Filtrado de información --------------------------
        // EN CONSTRUCCION

        return listadoProductos;
    }

    public void aplicaFiltros(List<Producto> productosInput){

        List<Producto> productos1 = productosInput.stream()
                .filter(p -> !p.getPrecio().equals(""))
                .sorted((Comparator.comparing(Producto::getNombreLab)))
                .collect(Collectors.toList());

        List<Laboratorio> laboratorios = datosLaboratorios.getLaboratorios();
        List<String> activos = laboratorios.stream().map(Laboratorio::getId).collect(Collectors.toList());
        List<Producto> productos2 = productos1.stream().filter(producto -> activos.contains(producto.getCodLab())).collect(Collectors.toList());

        productos = productos2;
    }
}


















