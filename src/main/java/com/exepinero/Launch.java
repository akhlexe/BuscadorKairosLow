package com.exepinero;

import com.exepinero.model.ItemEncontrado;
import com.exepinero.model.Monodroga;
import com.exepinero.model.Resultado;
import com.exepinero.service.BuscarEnKairos;
import com.exepinero.service.CargaDatosMonodrogas;
import com.exepinero.service.DatosMonodrogas;
import com.exepinero.view.Marco;

import java.util.List;

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