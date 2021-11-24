package com.exepinero.service;

import com.exepinero.model.Monodroga;
import com.exepinero.service.inicializadores.CargaDatosMonodrogas;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Archivo con la información del dro.txt de Kairos
 */

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
