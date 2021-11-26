package com.exepinero.service.inicializadores;

import com.exepinero.dto.ItemPRE;
import com.exepinero.dto.ItemPRO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Lee la info del archivo pre.txt de Kairos, y la modeliza para poder trabajar.
 */
public class CargaArchivoPRE {

    private ArchivoWinrar winrar;
    private List<ItemPRE> listadoItemsPRE = new ArrayList<>();

    public CargaArchivoPRE(ArchivoWinrar winrar) {
        this.winrar = winrar;
    }

    public List<ItemPRE> getListadoItemsPRE() {
        return listadoItemsPRE;
    }

    public void loadDataFromTxt(){

        try{

            ZipFile zip = winrar.getZipFile();
            ZipEntry entry = zip.getEntry("pre.txt");
            InputStream stream = zip.getInputStream(entry);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);

            String line = br.readLine();


            while(line != null){

                String codProducto = line.substring(0,6);
                String codPresentacion = line.substring(6,8);
                String descripPresentacion = line.substring(8,68);
                String descripPresentacionTrim = descripPresentacion.trim();
                String GTIN = line.substring(99,113);

                ItemPRE tempItem = new ItemPRE();

                tempItem.setCodProd(codProducto);
                tempItem.setCodPresent(codPresentacion);
                tempItem.setDescripPresentacion(descripPresentacionTrim);
                tempItem.setGTIN(GTIN);

                listadoItemsPRE.add(tempItem);
                line = br.readLine();
            }


        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
