package com.exepinero.service;

import com.exepinero.model.Monodroga;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DatosMonodrogas {

    private List<Monodroga> monodrogas;

    public DatosMonodrogas() {

        CargaDatosMonodrogas loader = new CargaDatosMonodrogas();
        monodrogas = loader.loadDataFromTxt();
    }

    public List<Monodroga> getMonodrogasByName(String name){

        List<Monodroga> matches = monodrogas.stream()
                .filter(monodroga -> monodroga.getNombre().toLowerCase(Locale.ROOT).contains(name))
                .collect(Collectors.toList());

        return matches;
    }


}
