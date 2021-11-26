package com.exepinero.service.inicializadores;

import com.exepinero.dto.ItemPRC;
import com.exepinero.dto.ItemPRO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class CargaArchivoPRO {

    private ArchivoWinrar winrar;
    private List<ItemPRO> listadoItemsPRO = new ArrayList<>();

    public CargaArchivoPRO(ArchivoWinrar winrar) {
        this.winrar = winrar;
    }

    public List<ItemPRO> getListadoItemsPRO() {
        return listadoItemsPRO;
    }

    public void loadDataFromTxt(){

        try{

            ZipFile zip = winrar.getZipFile();
            ZipEntry entry = zip.getEntry("pro.txt");
            InputStream stream = zip.getInputStream(entry);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);

            String line = br.readLine();


            while(line != null){

                String codProducto = line.substring(0,6);
                String descripProducto = line.substring(6,46);
                String descripProductoTrim = descripProducto.trim();
                String codLabo = line.substring(46,51);


                ItemPRO tempItem = new ItemPRO();

                tempItem.setCodProd(codProducto);
                tempItem.setDescripProd(descripProductoTrim);
                tempItem.setCodLab(codLabo);

                listadoItemsPRO.add(tempItem);
                line = br.readLine();
            }


        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
