package com.exepinero.service;

import com.exepinero.model.ItemEncontrado;
import com.exepinero.model.Monodroga;
import com.exepinero.model.Resultado;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BuscarEnKairos {

    private List<ItemEncontrado> itemsEncontrados = new ArrayList<>();
    private List<Resultado> resultados = new ArrayList<>();
    private DatosMonodrogas datosMonodrogas;



    /**
     *
     * Constructor
     */

    public BuscarEnKairos(DatosMonodrogas datosMonodrogas) {
        this.datosMonodrogas = datosMonodrogas;
    }

    /**
     * Pasaje de info desde el field inicial a panel Lateral
     */

    public List<Monodroga> consultaOpciones(String monodroga) throws IOException {

        List<Monodroga> monodrogasMatcheadas = datosMonodrogas.getMonodrogasByName(monodroga);

        return monodrogasMatcheadas;
    }

    /**
     * Ejecuta consulta de la monodroga seleccionada y devuelve un listado
     * de todas las presentaciones que contienen esa monodroga
     */

    public List<ItemEncontrado> ejecutaConsulta(Monodroga monodroga){

        String idMonodroga = monodroga.getId();

        try {
            Document doc = Jsoup.connect("https://ar.kairosweb.com/principio-activo/?droga="+idMonodroga).get();
            Elements doclist = doc.getElementsByClass("doclist");

            // Return lista vacía si no matchea nada.
            if(doclist.isEmpty()) return new ArrayList<ItemEncontrado>();

            Element principal = doclist.get(0);
            Elements productosPrincipales = principal.getElementsByTag("article");

            /**
             * Obtiene las presentaciones y laboratorios de la monodroga a buscar
             */
            for(Element elem:productosPrincipales){

                Element datosPresentacion = elem.getElementsByTag("a").get(0);
                Attributes attributes = datosPresentacion.attributes();
                String nombrePresentacion = attributes.get("title");
                String href = attributes.get("href");

                Element datosLabo = elem.getElementsByTag("a").get(1);
                Attributes atributosLabo = datosLabo.attributes();
                String laboratorio = atributosLabo.get("title");

                ItemEncontrado item = new ItemEncontrado();
                item.setId(idMonodroga);
                item.setMonodroga(monodroga.getNombre());
                item.setPresentacion(nombrePresentacion);
                item.setLaboratorio(laboratorio);
                item.setHref(href);

                itemsEncontrados.add(item);

            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemsEncontrados;

    }

    public void mostrarResultados(List<Resultado> res){

        res.stream()
                .forEach(System.out::println);
    }

    /**
     * Partiendo de una lista de presentaciones de la monodroga de la query devuelve un
     * listado de resultados finales con la info los medicamentos.
     * @param items
     * @return
     */

    public List<Resultado> ejecutaBusqueda(List<ItemEncontrado> items) throws InterruptedException {

        int sizeItems = items.size();
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

    /**
     *  Busqueda unitaria de un item devolviendo todas las filas resultados de ese item en particular.
     * @param item
     * @return
     */

    public List<Resultado> buscaItem(ItemEncontrado item){

        List<Resultado> resultadosParciales = new ArrayList<>();

        String href = item.getHref();


        try {
            Document doc = Jsoup.connect(href).get();
            Elements row_presentacion = doc.getElementsByClass("row presentacion");
            for (Element elem:row_presentacion){

                Resultado resultado = new Resultado();

                Element elementoDescripcion = elem.getElementsByClass("ttl-pres").get(0);
                Element elementoPrecio = elem.getElementsByClass("col-sm-5 col-xs-7 precio").get(0);


                resultado.setId(item.getId());
                resultado.setMonodroga(item.getMonodroga());
                resultado.setDescripcion(item.getPresentacion()
                        .concat(" ")
                        .concat(elementoDescripcion.text()));
                resultado.setLaboratorio(item.getLaboratorio());
                resultado.setHref(href);
                resultado.setPrecio(elementoPrecio.text());

                resultadosParciales.add(resultado);

            }
        return resultadosParciales;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
