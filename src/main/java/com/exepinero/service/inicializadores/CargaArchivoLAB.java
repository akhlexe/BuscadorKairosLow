package com.exepinero.service.inicializadores;

import com.exepinero.dto.ItemLAB;
import com.exepinero.dto.ItemPRE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Carga la info del archivo lab.txt de Kairos.
 */

public class CargaArchivoLAB {

    private List<ItemLAB> listadoItemsLAB = new ArrayList<>();
    private ArchivoWinrar winrar;

    public CargaArchivoLAB(ArchivoWinrar winrar) {
        this.winrar = winrar;
    }

    public List<ItemLAB> getListadoItemsLAB() {
        return listadoItemsLAB;
    }

    public void loadDataFromTxt(){

        try{

            ZipFile zip = winrar.getZipFile();
            ZipEntry entry = zip.getEntry("lab.txt");
            InputStream stream = zip.getInputStream(entry);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);

            String line = br.readLine();


            while(line != null){

                String codLab = line.substring(0,5);
                String descripcionLab = line.substring(5,20);
                String descripcionLabTrim = descripcionLab.trim();

                String razonSocial = line.substring(20,70);
                String razonSocialTrim = razonSocial.trim();

                ItemLAB tempItem = new ItemLAB();

                tempItem.setCodLab(codLab);
                tempItem.setDescripLab(descripcionLabTrim);
                tempItem.setRazonSocial(razonSocialTrim);

                listadoItemsLAB.add(tempItem);
                line = br.readLine();
            }


        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
