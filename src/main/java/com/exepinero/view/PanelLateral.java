package com.exepinero.view;

import com.exepinero.model.ItemEncontrado;
import com.exepinero.model.Monodroga;
import com.exepinero.model.Resultado;
import com.exepinero.service.BuscarEnKairos;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PanelLateral extends JPanel {


    private JComboBox elegir;
    private List<Monodroga> monodrogas = new ArrayList<>();
    private JButton botonBusquedaCompleta;
    private PanelMedio panelMedio;
    private BuscarEnKairos buscarEnKairos;

    public PanelLateral(PanelMedio panelMedio, BuscarEnKairos buscarEnKairos) {
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
                mostrarInfoEnTabla();
            }
        });

        this.add(titulo);
        this.add(elegir);
        this.add(botonBusquedaCompleta);
    }


    public void mostrarInfoEnTabla(){
        if(elegir.getSelectedItem().equals("")) return;
        String nombreMonodroga = (String) elegir.getSelectedItem();
        Optional<Monodroga> optionalMonodroga = monodrogas.stream().filter(p -> p.getNombre().equals(nombreMonodroga)).findFirst();
        if(!optionalMonodroga.isPresent()) return;

        List<ItemEncontrado> itemEncontrados = buscarEnKairos.ejecutaConsulta(optionalMonodroga.get());
        List<Resultado> resultados = buscarEnKairos.ejecutaBusqueda(itemEncontrados);

        resultados.stream().forEach(System.out::println);

    }

    public void actualizarOpciones (List<Monodroga> listaMonodrogas){

        // Limpia los campos anteriores
        elegir.removeAllItems();
        monodrogas.clear();

        for(Monodroga monodroga : listaMonodrogas){
            elegir.addItem(monodroga.getNombre());
        }

        monodrogas = new ArrayList<>(listaMonodrogas);
    }


    public List<Monodroga> getMonodrogas() {
        return monodrogas;
    }


}
