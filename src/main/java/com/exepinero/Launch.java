package com.exepinero;

import com.exepinero.service.inicializadores.CargaArchivoLAB;
import com.exepinero.view.Marco;

public class Launch {

    public static void main(String[] args) {

        testingCargaDatos();
        //launchProgram();

    }

    public static void launchProgram(){
        Marco app = new Marco();
    }

    public static void testingCargaDatos(){

        CargaArchivoLAB datos = new CargaArchivoLAB();
        datos.loadDataFromTxt();
    }

}
