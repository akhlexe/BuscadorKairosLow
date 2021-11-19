package com.exepinero.service;

import com.exepinero.model.Laboratorio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CargaDatosLaboratorios {

    public List<Laboratorio> loadTxtLaboratorios() {


        BufferedReader br = null;
        List<Laboratorio> laboratorios = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader("src/main/resources/txt/lab.txt"));
            String line = br.readLine();

            while (line != null) {

                Laboratorio tempLab = new Laboratorio();

                String idLabo = line.substring(0, 5);
                String laboratorio = line.substring(5, 20);

                tempLab.setId(idLabo);
                tempLab.setNombre(laboratorio);
                tempLab.setActive(false);

                laboratorios.add(tempLab);
                line = br.readLine();
            }

            laboratorios.stream()
                    .sorted(Comparator.comparing(Laboratorio::getNombre))
                    .forEach(System.out::println);

            //.sorted(Comparator.comparing(Laboratorio::getNombre))
            //.sorted((o1, o2) -> o1.getNombre().compareTo(o2.getNombre()))

        } catch (Exception e) {
            e.printStackTrace();
        }
        return laboratorios;
    }

}