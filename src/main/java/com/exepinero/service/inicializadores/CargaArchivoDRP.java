package com.exepinero.service.inicializadores;


import com.exepinero.dto.ItemDRP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Carga la información del archivo drp.txt de Kairos
 */
public class CargaArchivoDRP {

    private List<ItemDRP> listadoItemsDRP = new ArrayList<>();

    public List<ItemDRP> getListadoItemsDRP() {
        return listadoItemsDRP;
    }

    public void loadDataFromTxt(){
        try{

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/txt/drp.txt"));
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
