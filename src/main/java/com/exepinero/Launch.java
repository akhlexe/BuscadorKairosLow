package com.exepinero;

import com.exepinero.view.Marco;

public class Launch {

    public static void main(String[] args) {


        testingMarco();

        /*
        // Testeo datos
        CargaDatosMonodrogas datos = new CargaDatosMonodrogas();
        datos.loadDataFromTxt();

        // Testeo webscraper
        BuscarEnKairos buscador = new BuscarEnKairos(new DatosMonodrogas());
        try{
            //buscador.consultaOpciones("amlodi");

            List<Monodroga> opciones = buscador.consultaOpciones("raniti");
            List<ItemEncontrado> itemsEncontrados = buscador.ejecutaConsulta(opciones.get(0));
            List<Resultado> resultados = buscador.ejecutaBusqueda(itemsEncontrados);


        } catch (Exception e){
            e.printStackTrace();
        }
        */
    }

    public static void testingMarco(){

        Marco marco = new Marco();
    }

}
