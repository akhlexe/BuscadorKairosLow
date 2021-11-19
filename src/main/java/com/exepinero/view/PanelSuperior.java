package com.exepinero.view;

import com.exepinero.model.Monodroga;
import com.exepinero.service.BuscarEnKairos;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class PanelSuperior extends JPanel {

    private JTextField fieldBuscador;
    private JButton botonBuscar;
    private BuscarEnKairos buscarEnKairos;
    private PanelMedio panelMedio;
    private PanelLateral panelLateral;


    public PanelSuperior(BuscarEnKairos buscador, PanelMedio panelMedio, PanelLateral panelLateral) {


        Border padding = BorderFactory.createEmptyBorder(5,5,5,5);
        this.setBorder(padding);
        JLabel labelBuscador = new JLabel("Monodroga:");
        fieldBuscador = new JTextField(30);
        botonBuscar = new JButton("Buscar");
        buscarEnKairos = buscador;
        this.panelMedio = panelMedio;

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String monodroga = fieldBuscador.getText();

                try {
                    List<Monodroga> opcionesMonodrogas = buscarEnKairos.consultaOpciones(monodroga);

                    panelLateral.actualizarOpciones(opcionesMonodrogas);

                    fieldBuscador.setText("");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        this.add(labelBuscador);
        this.add(fieldBuscador);
        this.add(botonBuscar);
    }
}