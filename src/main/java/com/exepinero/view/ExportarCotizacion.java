package com.exepinero.view;

import com.exepinero.model.Cotizacion;
import com.exepinero.service.GestorCotizaciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ExportarCotizacion extends JFrame {

    private Cotizacion currentCotizacion;
    private JButton botonAbrirRuta;
    private JButton botonExportar;
    private JButton botonCancelar;
    private JFileChooser fileChooser;
    private JTextField rutaField;
    private GestorCotizaciones gestorCotizaciones;
    private File ruta;

    public ExportarCotizacion(Cotizacion currentCotizacion, GestorCotizaciones gestorCotizaciones) throws HeadlessException {
        this.currentCotizacion = currentCotizacion;
        this.gestorCotizaciones = gestorCotizaciones;
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
        botonAbrirRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscaRutaDestino();
            }
        });
        rutaField = new JTextField(30);
        rutaField.setPreferredSize(new Dimension(180,30));
        rutaField.setDisabledTextColor(Color.BLACK);
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


        botonExportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!(ruta == null)){
                    Thread t = new Thread(() -> confirmarExportar(ruta.toString()));
                    t.start();
                }
                dispose();
            }
        });

        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panelBotonesDecision.add(botonExportar);
        panelBotonesDecision.add(botonCancelar);


        panelPrincipal.add(panelRuta);
        panelPrincipal.add(panelBotonesDecision);
        this.add(panelPrincipal);



        this.setVisible(true);
    }

    public void buscaRutaDestino(){
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle("Carpeta destino");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            ruta = fileChooser.getSelectedFile();
            actualizaDisplayRuta(ruta.toString());
        } else{
            System.out.println("No selection");
        }
    }

    public void actualizaDisplayRuta(String ruta){
        rutaField.setEnabled(true);
        rutaField.setText(ruta);
        rutaField.setEnabled(false);
    }

    public void confirmarExportar(String ubicacion){
        gestorCotizaciones.exportarCotizacion(ubicacion);
    }
}
