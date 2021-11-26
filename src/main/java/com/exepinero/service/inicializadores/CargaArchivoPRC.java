package com.exepinero.service.inicializadores;


import com.exepinero.dto.ItemDRP;
import com.exepinero.dto.ItemPRC;

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

public class CargaArchivoPRC {

    private ArchivoWinrar winrar;
    private List<ItemPRC> listadoItemsPRC = new ArrayList<>();

    public CargaArchivoPRC(ArchivoWinrar winrar) {
        this.winrar = winrar;
    }

    public List<ItemPRC> getListadoItemsPRC() {
        return listadoItemsPRC;
    }

    public void loadDataFromTxt(){
        try{

            ZipFile zip = winrar.getZipFile();
            ZipEntry entry = zip.getEntry("prc.txt");
            InputStream stream = zip.getInputStream(entry);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);

            String line = br.readLine();


            while(line != null){

                String codProducto = line.substring(0,6);
                String codPresentacion = line.substring(6,8);
                String pvp = line.substring(8,23);
                double tempPvp = Double.parseDouble(pvp);
                pvp = Double.toString(tempPvp).replace(".",",");

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
