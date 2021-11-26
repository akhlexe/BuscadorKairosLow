package com.exepinero.view;

import com.exepinero.dto.ItemDRO;
import com.exepinero.model.Monodroga;
import com.exepinero.model.Resultado;
import com.exepinero.service.BuscarEnKairos;
import com.exepinero.service.Inicializador;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PanelLateral extends JPanel {


    private JComboBox elegir;
    private List<ItemDRO> monodrogas = new ArrayList<>();
    private JButton botonBusquedaCompleta;
    private PanelMedio panelMedio;
    private BuscarEnKairos buscarEnKairos;
    private Inicializador loader;
    private List<Resultado> resultados = new ArrayList<>();


    public PanelLateral(PanelMedio panelMedio, BuscarEnKairos buscarEnKairos, Inicializador loader) {
        this.loader = loader;
        this.buscarEnKairos = buscarEnKairos;
        this.panelMedio = panelMedio;
        GridLayout layout = new GridLayout(15,0,0,5);

        // Layout y padding
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(200,200));
        Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setBorder(padding);


        JLabel titulo = new JLabel("Opciones a buscar:");
        elegir = new JComboBox<>();
        elegir.setPreferredSize(new Dimension(200,1));



        botonBusquedaCompleta = new JButton("Mostrar info");
        botonBusquedaCompleta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInfo();
            }
        });

        JButton abrirLabos = new JButton("Abrir labos");
        abrirLabos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new File("src/main/resources/excel/laboratorios.xlsx"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        this.add(titulo);
        this.add(elegir);
        this.add(botonBusquedaCompleta);
        this.add(abrirLabos);
    }




    public void mostrarInfoEnTabla(){
        if(elegir.getSelectedItem().equals("")) return;
        String nombreMonodroga = (String) elegir.getSelectedItem();
        resultados.clear();

        Optional<ItemDRO> optionalMonodroga = monodrogas.stream().filter(p -> p.getNombreMonodroga().equals(nombreMonodroga)).findFirst();
        List<Resultado> primeraBusqueda = buscarEnKairos.ejecutaConsulta(optionalMonodroga.get());
        List<Resultado> segundaBusqueda = buscarEnKairos.agregaInfoDeCodLabYnombreProd(primeraBusqueda);


    /*
        try {
            resultados = buscarEnKairos.ejecutaBusqueda(itemEncontrados);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //resultados.stream().forEach(System.out::println);
        panelMedio.mostrarData(resultados);
    */

    }

    public void actualizarOpciones (List<ItemDRO> listaMonodrogas){

        // Limpia los campos anteriores
        elegir.removeAllItems();
        monodrogas.clear();

        for(ItemDRO item : listaMonodrogas){
            elegir.addItem(item.getNombreMonodroga());
        }

        monodrogas = new ArrayList<>(listaMonodrogas);
    }

    public void mostrarInfo(){

        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                mostrarInfoEnTabla();
            }
        });

        hilo.start();
    }


}
