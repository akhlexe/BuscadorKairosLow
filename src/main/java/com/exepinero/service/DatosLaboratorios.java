package com.exepinero.service;

import com.exepinero.model.Laboratorio;

import java.util.List;

public class DatosLaboratorios {

    private List<Laboratorio> laboratorios;

    public DatosLaboratorios() {
        CargaDatosLaboratorios loader = new CargaDatosLaboratorios();
        laboratorios = loader.loadTxtLaboratorios();
    }
}
