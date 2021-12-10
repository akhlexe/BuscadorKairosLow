package com.exepinero.view;

import com.exepinero.dto.ItemDRO;
import com.exepinero.service.BuscarEnKairos;
import com.exepinero.service.Context;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class PanelSuperior extends JPanel {

    private JTextField fieldBuscador;
    private JButton botonBuscar, crearCotizacion;
    private BuscarEnKairos buscarEnKairos;
    private PanelMedio panelMedio;
    private PanelLateral panelLateral;
    private Context context;


    public PanelSuperior(BuscarEnKairos buscador,
                         PanelMedio panelMedio,
                         PanelLateral panelLateral,
                         Context context) {

        this.context = context;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));


        JLabel titulo = new JLabel("Buscador según monodroga                                                                            ");
        titulo.setFont(new Font("Monospace",Font.BOLD,16));
        Border padding = BorderFactory.createEmptyBorder(5,5,5,5);
        this.setBorder(padding);
        JLabel labelBuscador = new JLabel("Monodroga:");
        labelBuscador.setFont(new Font("Monospace",Font.BOLD,13));
        fieldBuscador = new JTextField(30);
        fieldBuscador.setPreferredSize(new Dimension(150,30));
        botonBuscar = new JButton("Buscar");
        buscarEnKairos = buscador;
        this.panelMedio = panelMedio;

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Safecheck por búsquedas vacías
                if(fieldBuscador.getText().equals("")) return;

                String monodroga = fieldBuscador.getText();

                try {
                    List<ItemDRO> opcionesMonodrogas = buscarEnKairos.consultaOpciones(monodroga);

                    panelLateral.actualizarOpciones(opcionesMonodrogas);

                    fieldBuscador.setText("");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        JLabel espacio2 = new JLabel("                ");

        crearCotizacion = new JButton("Nueva cotización");
        crearCotizacion.setBackground(new Color(134,234,145));
        crearCotizacion.setPreferredSize(new Dimension(160,40));
        crearCotizacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CrearCotizacion(panelLateral,context);
            }
        });


        this.add(titulo);
        this.add(labelBuscador);
        this.add(fieldBuscador);
        this.add(botonBuscar);
        this.add(espacio2);
        this.add(crearCotizacion);

    }

}





















