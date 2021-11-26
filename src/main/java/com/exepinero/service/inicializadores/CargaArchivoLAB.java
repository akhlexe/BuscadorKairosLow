package com.exepinero.service.inicializadores;

import com.exepinero.dto.ItemLAB;
import com.exepinero.dto.ItemPRE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Carga la info del archivo lab.txt de Kairos.
 */

public class CargaArchivoLAB {

    private List<ItemLAB> listadoItemsLAB = new ArrayList<>();

    public List<ItemLAB> getListadoItemsLAB() {
        return listadoItemsLAB;
    }

    public void loadDataFromTxt(){

        try{

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/txt/lab.txt"));
            String line = br.readLine();


            while(line != null){

                String codLab = line.substring(0,5);
                String descripcionLab = line.substring(5,20);
                String razonSocial = line.substring(20,70);

                ItemLAB tempItem = new ItemLAB();

                tempItem.setCodLab(codLab);
                tempItem.setDescripLab(descripcionLab);
                tempItem.setRazonSocial(razonSocial);

                listadoItemsLAB.add(tempItem);
                line = br.readLine();
            }


        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
