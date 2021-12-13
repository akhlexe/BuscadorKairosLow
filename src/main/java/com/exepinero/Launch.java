package com.exepinero;

import com.exepinero.model.Cotizacion;
import com.exepinero.service.Inicializador;
import com.exepinero.service.inicializadores.CargaArchivoLAB;
import com.exepinero.view.ExportarCotizacion;
import com.exepinero.view.Marco;
import com.exepinero.view.VentanaInicializador;

public class Launch {

    public static void main(String[] args) {

        Marco marco = new Marco();
        testExportaCotizacion();

    }

    public static void launchProgram(){
        Marco app = new Marco();
    }

    public static void testExportaCotizacion(){

        ExportarCotizacion exportarCotizacion = new ExportarCotizacion(new Cotizacion("asdsa"));
    }

}
