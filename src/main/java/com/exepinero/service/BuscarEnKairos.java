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

public class BuscarEnKairos {

    public BuscarEnKairos(DatosMonodrogas datosMonodrogas) {
        this.datosMonodrogas = datosMonodrogas;
    }

    public List<Monodroga> consultaOpciones(String monodroga) throws IOException {

        List<Monodroga> monodrogasMatcheadas = datosMonodrogas.getMonodrogasByName(monodroga);

        return monodrogasMatcheadas;
    }


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

    public List<Resultado> ejecutaBusqueda(List<ItemEncontrado> items){

        for(ItemEncontrado item:items){

            String href = item.getHref();

            try {
                Document doc = Jsoup.connect(href).get();
                Elements row_presentacion = doc.getElementsByClass("row presentacion");
                for (Element elem:row_presentacion){

                    Element elementoDescripcion = elem.getElementsByClass("ttl-pres").get(0);
                    Element elementoPrecio = elem.getElementsByClass("col-sm-5 col-xs-7 precio").get(0);
                    System.out.print(item.getPresentacion()+" ");
                    System.out.print(elementoDescripcion.text()+" ");
                    System.out.println(elementoPrecio.text());


                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }


    /*

    Document doc = Jsoup.connect("https://ar.kairosweb.com/principio-activo/?droga="+primerMatch).get();

    Elements doclist = doc.getElementsByClass("doclist");
        doclist.stream().forEach(System.out::println);

        if(doclist.isEmpty()) return;

        Element principal = doclist.get(0);
        Element secundario = doclist.get(1);

        Elements productosPrincipales = principal.getElementsByTag("article");
        for(Element elem:productosPrincipales){


            Element datosPresentacion = elem.getElementsByTag("a").get(0);
            Attributes attributes = datosPresentacion.attributes();
            String nombrePresentacion = attributes.get("title");
            String href = attributes.get("href");

            Element datosLabo = elem.getElementsByTag("a").get(1);
            Attributes atributosLabo = datosLabo.attributes();
            String laboratorio = atributosLabo.get("title");

            System.out.println("Datos: "+nombrePresentacion+" "+href);
            System.out.println("Del laboratorio: "+laboratorio);


     */

    private List<ItemEncontrado> itemsEncontrados = new ArrayList<>();
    private List<Resultado> resultados = new ArrayList<>();
    private DatosMonodrogas datosMonodrogas;
}
