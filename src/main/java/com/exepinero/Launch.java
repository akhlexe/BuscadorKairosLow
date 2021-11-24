package com.exepinero;

import com.exepinero.service.inicializadores.CargaArchivoDRP;
import com.exepinero.service.inicializadores.CargaArchivoPRC;
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

        CargaArchivoPRC datos = new CargaArchivoPRC();
        datos.loadDataFromTxt();
    }

}
