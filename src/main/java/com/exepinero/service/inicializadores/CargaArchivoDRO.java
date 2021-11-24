package com.exepinero.service.inicializadores;


import com.exepinero.dto.ItemDRO;
import com.exepinero.model.Monodroga;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Lee la info del archivo dro.txt de Kairos, y la modeliza para poder trabajar.
 */

public class CargaArchivoDRO {

    private List<ItemDRO> listadoItemsDRO = new ArrayList<>();

    public List<ItemDRO> getListadoItemsDRO() {
        return listadoItemsDRO;
    }

    public void loadDataFromTxt(){
        try{

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/txt/dro.txt"));
            String line = br.readLine();


            while(line != null){

                String codMondoroga = line.substring(0,4);
                String nombreMonodroga = line.substring(4,49);

                listadoItemsDRO.add(new ItemDRO(codMondoroga,nombreMonodroga));
                line = br.readLine();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
