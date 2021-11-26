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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BuscarEnKairos {

    private List<ItemEncontrado> itemsEncontrados = new ArrayList<>();
    private List<Resultado> resultados = new ArrayList<>();
    private Inicializador loader;


    /**
     *
     * Constructor con parametro Inicializador.
     */

    public BuscarEnKairos(Inicializador loader){
        this.loader = loader;
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

    public List<Resultado> ejecutaConsulta(ItemDRO monodroga){

        // TODO ejecutaPrimeraBusqueda



        return null;
    }

    public List<Resultado> AGREGARNOMBREEEEEEEEEEEE (ItemDRO monodroga){

        List<Resultado> resultadosOutput = new ArrayList<>();
        String idMonodroga = monodroga.getCodMonodroga();

        List<ItemDRP> listadoItemsMonoVsProd = loader.getArchivoDRP().getListadoItemsDRP();

        List<ItemDRP> itemsDRPs = listadoItemsMonoVsProd.stream()
                .filter(p -> p.getCodMonodroga().equals(idMonodroga))
                .collect(Collectors.toList());

        for(ItemDRP item:itemsDRPs){
            Resultado tempResult = new Resultado();
            tempResult.setCodMonodroga(idMonodroga);
            tempResult.setMonodroga(monodroga.getNombreMonodroga());
            tempResult.setCodProd(item.getCodProd());

            resultadosOutput.add(tempResult);
        }

        return resultadosOutput;
    }


    public List<Resultado> agregaInfoDeCodLabYnombreProd (List<Resultado> resultadosInput){

        List<ItemPRO> listadoItemsPRO = loader.getArchivoPRO().getListadoItemsPRO();
        List<Resultado> resultadosOutput = new ArrayList<>();


        for(Resultado item : resultadosInput){

            /*
            Agarro el item y veo el cod prod, filtro todos los itemPRO con ese codProd y genero nuevos
            resultados con la info agregada.
             */

            List<ItemPRO> listadoItemsPROfiltrado = listadoItemsPRO.stream()
                    .filter(itemPRO -> itemPRO.getCodProd().equals(item.getCodProd()))
                    .toList();

            for(ItemPRO itemPRO:listadoItemsPROfiltrado){

                Resultado tempResult = new Resultado();

                tempResult.setCodMonodroga(item.getCodMonodroga());
                tempResult.setMonodroga(item.getMonodroga());
                tempResult.setCodProd(item.getCodProd());
                tempResult.setProducto(itemPRO.getDescripProd());
                tempResult.setCodLab(itemPRO.getCodLab());

                resultadosOutput.add(tempResult);

            }

        }
        return resultadosOutput;
    }

    /*


    public List<Resultado> ejecutaBusqueda(List<ItemEncontrado> items) throws InterruptedException {


        // TODO: Testear si es rentable que muestre toda la info o filtrada por laboratorios activos... capaz es contraproducente
        //List<ItemEncontrado> itemsFiltrados = this.filtraItemsSegunLaboratorioActivo(items, datosLaboratorios.getLaboratorios());



        int cantidad = items.size();


        List<Resultado> resultadosFinales = new ArrayList<>();

        //###############################################################################################
        //  Concurrencia. Corre todos los hilos de busqueda a Kairos y va llenando el objeto resultados.
        //###############################################################################################

        ExecutorService es = Executors.newCachedThreadPool();
        for(ItemEncontrado item:items){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    List<Resultado> resultados = buscaItem(item);
                    resultados.stream().forEach(resultadosFinales::add);
                }
            });
        }

        es.shutdown();
        boolean finished = es.awaitTermination(1, TimeUnit.MINUTES);


        return resultadosFinales;
    }


    public List<ItemEncontrado> filtraItemsSegunLaboratorioActivo(List<ItemEncontrado> items, List<Laboratorio> laboratorios){

        List<String> nombresLabos = laboratorios.stream().
                map(p -> p.getNombre())
                .collect(Collectors.toList());


        List<ItemEncontrado> itemsFiltados = items.stream()
                .filter(p -> nombresLabos.equals(p.getLaboratorio()))
                .collect(Collectors.toList());

        return itemsFiltados;
    }
    */


}


















