package com.exepinero.service.inicializadores;


import com.exepinero.dto.ItemDRP;
import com.exepinero.dto.ItemPRC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Carga la información del archivo drp.txt de Kairos
 */

public class CargaArchivoPRC {

    private List<ItemPRC> listadoItemsPRC = new ArrayList<>();

    public List<ItemPRC> getListadoItemsPRC() {
        return listadoItemsPRC;
    }

    public void loadDataFromTxt(){
        try{

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/txt/prc.txt"));
            String line = br.readLine();


            while(line != null){

                String codProducto = line.substring(0,6);
                String codPresentacion = line.substring(6,8);
                String pvp = line.substring(8,23);
                String fechaVigencia = line.substring(23,31);

                ItemPRC tempItem = new ItemPRC();
                tempItem.setCodProd(codProducto);
                tempItem.setCodPresentacion(codPresentacion);
                tempItem.setPvp(pvp);
                tempItem.setFecha_vigencia(fechaVigencia);
                String codProdLowsedo = codProducto.concat(codPresentacion);
                tempItem.setCodProdLowsedo(codProdLowsedo);

                listadoItemsPRC.add(tempItem);
                line = br.readLine();
            }


        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
