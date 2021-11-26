package com.exepinero.service.inicializadores;

import com.exepinero.dto.ItemPRC;
import com.exepinero.dto.ItemPRO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CargaArchivoPRO {

    private List<ItemPRO> listadoItemsPRO = new ArrayList<>();

    public List<ItemPRO> getListadoItemsPRO() {
        return listadoItemsPRO;
    }

    public void loadDataFromTxt(){

        try{

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/txt/pro.txt"));
            String line = br.readLine();


            while(line != null){

                String codProducto = line.substring(0,6);
                String descripProducto = line.substring(6,46);
                String codLabo = line.substring(46,51);


                ItemPRO tempItem = new ItemPRO();

                tempItem.setCodProd(codProducto);
                tempItem.setDescripProd(descripProducto);
                tempItem.setCodLab(codLabo);

                listadoItemsPRO.add(tempItem);
                line = br.readLine();
            }


        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
