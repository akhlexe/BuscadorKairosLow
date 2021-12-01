package com.exepinero.model;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class Config {

    private HashMap<String,String> configMap = new HashMap<>();

    public Config() {
        this.iniciaConfig();
    }

    public HashMap<String, String> getConfigMap() {
        return configMap;
    }

    public void guardarConfig(){
        Set<String> keys = configMap.keySet();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("P:\\Usuarios\\Exequiel\\AppCotizaciones\\config.txt"));
            for(String key:keys){
                bw.write(key+"="+"NUEVAKEY");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iniciaConfig(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("P:\\Usuarios\\Exequiel\\AppCotizaciones\\config.txt"));
            String line = br.readLine();

            while(line != null){
                String[] split = line.split("=");
                String key = split[0];
                String value = split[1];
                configMap.put(key,value);
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
