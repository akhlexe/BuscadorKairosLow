package com.exepinero.service.inicializadores;


import com.exepinero.model.Monodroga;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CargaDatosMonodrogas {


    public List<Monodroga> loadDataFromTxt(){
        try{

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/txt/monodrogas.txt"));
            String line = br.readLine();

            List<Monodroga> monodrogas = new ArrayList<>();

            while(line != null){

                String code = line.substring(0,4);
                String descripcion = line.substring(4,49);
                monodrogas.add(new Monodroga(code,descripcion));
                line = br.readLine();
            }

            return monodrogas;

            //InputStream input = new FileInputStream(new File("/resources/mono/monodrogas.txt"));
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }
}
