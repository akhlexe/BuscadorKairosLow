package com.exepinero.service.inicializadores;


import com.exepinero.dto.ItemDRO;
import com.exepinero.model.Monodroga;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Lee la info del archivo dro.txt de Kairos, y la modeliza para poder trabajar.
 */

public class CargaArchivoDRO {

    private ArchivoWinrar winrar;
    private List<ItemDRO> listadoItemsDRO = new ArrayList<>();

    public CargaArchivoDRO(ArchivoWinrar winrar) {
        this.winrar = winrar;
    }

    public List<ItemDRO> getListadoItemsDRO() {
        return listadoItemsDRO;
    }

    public void loadDataFromTxt(){
        try{

            ZipFile zip = winrar.getZipFile();
            ZipEntry entry = zip.getEntry("dro.txt");
            InputStream stream = zip.getInputStream(entry);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);

            String line = br.readLine();


            while(line != null){

                String codMondoroga = line.substring(0,4);
                String nombreMonodroga = line.substring(4,49);
                String nombreMonodrogaTrim = nombreMonodroga.trim();

                listadoItemsDRO.add(new ItemDRO(codMondoroga,nombreMonodrogaTrim));
                line = br.readLine();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
