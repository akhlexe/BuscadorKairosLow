package com.exepinero.service.inicializadores;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.zip.ZipFile;

public class ArchivoWinrar {

    private ZipFile zipFile;
    private Date fechaInicial = new Date();
    private String fechaInicialAdaptada = "";
    private Path ruta;

    public ArchivoWinrar() {

        boolean rutaVerificada = false;

        while(!rutaVerificada){
            fechaInicialAdaptada = new SimpleDateFormat("yyyyMMdd").format(fechaInicial);
            rutaVerificada = verificaRuta(fechaInicialAdaptada);
            if(rutaVerificada){
                ruta = Path.of("F:\\Actualizacion\\Kairos\\bd_"+fechaInicialAdaptada+".zip");
                break;
            }
            restaUnDia();

        }

        try{
            zipFile = new ZipFile(String.valueOf(ruta));
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public boolean verificaRuta (String fecha){

        Path rutaBuscada = Path.of("F:\\Actualizacion\\Kairos\\bd_"+fecha+".zip");

        if(Files.exists(rutaBuscada)){
            return true;
        } else {
            return false;
        }
    }

    public void restaUnDia(){
        Instant before = fechaInicial.toInstant().minus(Duration.ofDays(1));
        fechaInicial = Date.from(before);
        System.out.println(fechaInicial);
    }

    public ZipFile getZipFile() {
        return zipFile;
    }
}
