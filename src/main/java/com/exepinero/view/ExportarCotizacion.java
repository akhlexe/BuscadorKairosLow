package com.exepinero.view;

import com.exepinero.model.Cotizacion;

import javax.swing.*;
import java.awt.*;

public class ExportarCotizacion extends JFrame {

    private Cotizacion currentCotizacion;
    private JButton botonAbrirRuta;
    private JButton botonExportar;
    private JButton botonCancelar;
    private JFileChooser fileChooser;
    private JTextField rutaField;

    public ExportarCotizacion(Cotizacion currentCotizacion) throws HeadlessException {
        this.currentCotizacion = currentCotizacion;
        this.fileChooser = new JFileChooser();

        this.setBounds(350,200,500,200);
        this.setTitle("Exportando datos cotizados...");

        JPanel panelPrincipal = new JPanel();
        BoxLayout layoutPrincipal = new BoxLayout(panelPrincipal,BoxLayout.Y_AXIS);
        panelPrincipal.setLayout(layoutPrincipal);
        panelPrincipal.setBorder(BorderFactory.createTitledBorder("Exportar datos..."));

        JPanel panelRuta = new JPanel();
        panelRuta.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonAbrirRuta = new JButton("Ubicación...");
        rutaField = new JTextField(30);
        rutaField.setPreferredSize(new Dimension(180,30));
        rutaField.setEnabled(false);
        panelRuta.add(botonAbrirRuta);
        panelRuta.add(rutaField);


        JPanel panelBotonesDecision = new JPanel();
        panelBotonesDecision.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonExportar = new JButton("Exportar");
        botonCancelar = new JButton("Cancelar");

        botonExportar.setBackground(new Color(134,200,145));
        botonExportar.setPreferredSize(new Dimension(150,40));
        botonCancelar.setBackground(new Color(180,180,180));
        botonCancelar.setPreferredSize(new Dimension(150,40));

        panelBotonesDecision.add(botonExportar);
        panelBotonesDecision.add(botonCancelar);


        panelPrincipal.add(panelRuta);
        panelPrincipal.add(panelBotonesDecision);
        this.add(panelPrincipal);



        this.setVisible(true);
    }
}
