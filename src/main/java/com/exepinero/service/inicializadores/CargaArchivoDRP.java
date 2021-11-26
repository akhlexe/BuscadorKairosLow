package com.exepinero.service.inicializadores;


import com.exepinero.dto.ItemDRP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Carga la información del archivo drp.txt de Kairos
 */
public class CargaArchivoDRP {

    private ArchivoWinrar winrar;
    private List<ItemDRP> listadoItemsDRP = new ArrayList<>();

    public CargaArchivoDRP(ArchivoWinrar winrar) {
        this.winrar = winrar;
    }

    public List<ItemDRP> getListadoItemsDRP() {
        return listadoItemsDRP;
    }

    public void loadDataFromTxt(){
        try{


            ZipFile zip = winrar.getZipFile();
            ZipEntry entry = zip.getEntry("drp.txt");
            InputStream stream = zip.getInputStream(entry);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);

            String line = br.readLine();


            while(line != null){

                String codMonodroga = line.substring(0,4);
                String codProducto = line.substring(4,10);
                listadoItemsDRP.add(new ItemDRP(codMonodroga,codProducto));
                line = br.readLine();
            }


        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
