package com.exepinero.view;

import com.exepinero.dto.ItemDRO;
import com.exepinero.model.Cotizacion;
import com.exepinero.model.Producto;
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
import java.util.Set;
import java.util.stream.Collectors;

public class PanelLateral extends JPanel {


    private JComboBox elegir;
    private List<ItemDRO> monodrogas = new ArrayList<>();
    private JButton botonBusquedaCompleta, botonAgregaMonodroga, botonRemueveMonodroga, botonAbrirCotizacion, botonExportarCotizacion;
    private PanelMedio panelMedio;
    private BuscarEnKairos buscarEnKairos;
    private Inicializador loader;
    private List<Producto> productos = new ArrayList<>();
    private ItemDRO seleccionado;
    private Cotizacion currentCotizacion = new Cotizacion("Sin info...");
    private JCheckBox seleccionaCompuesto;
    private boolean updateActiveLabos = false;
    private JTextField displayNombreCoti;
    private JTextArea itemsCotizacion;
    private JScrollPane scrollPaneItemsCotizacion;


    public PanelLateral(PanelMedio panelMedio, BuscarEnKairos buscarEnKairos, Inicializador loader) {
        this.loader = loader;
        this.buscarEnKairos = buscarEnKairos;
        this.panelMedio = panelMedio;

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        JPanel panelconGrid = new JPanel();
        panelconGrid.setMaximumSize(new Dimension(300,300));
        GridLayout gridLayout = new GridLayout(6,0,0,5);
        panelconGrid.setLayout(gridLayout);

        Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        panelconGrid.setBorder(padding);


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
                    Desktop.getDesktop().open(new File("P:\\Usuarios\\Exequiel\\AppCotizaciones\\laboratorios.xlsx"));
                    updateActiveLabos = true;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        seleccionaCompuesto = new JCheckBox("Compuesto?");

        JSeparator separator = new JSeparator();

        /******************************************************************************
         View de panel de cotización
         *******************************************************************************/

        JPanel panelCotizacion = new JPanel();
        panelCotizacion.setBorder(BorderFactory.createTitledBorder("Cotización"));
        panelCotizacion.setMaximumSize(new Dimension(300,500));
        BoxLayout boxLayoutCotizacion = new BoxLayout(panelCotizacion,BoxLayout.Y_AXIS);
        panelCotizacion.setLayout(boxLayoutCotizacion);

        displayNombreCoti = new JTextField(20);
        displayNombreCoti.setText("Sin info...");
        displayNombreCoti.setEnabled(false);
        displayNombreCoti.setMaximumSize(new Dimension(220,35));


        itemsCotizacion = new JTextArea(4,10);
        scrollPaneItemsCotizacion = new JScrollPane(itemsCotizacion);
        scrollPaneItemsCotizacion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneItemsCotizacion.setMaximumSize(new Dimension(220,250));
        //scrollPaneItemsCotizacion.setMaximumSize(new Dimension(220,400));

        itemsCotizacion.append("Hola");

        panelCotizacion.add(displayNombreCoti);
        panelCotizacion.add(Box.createRigidArea(new Dimension(0,5)));
        panelCotizacion.add(scrollPaneItemsCotizacion);

        /**
         * Agrego elementos al panel lateral
         */


        panelconGrid.add(titulo);
        panelconGrid.add(elegir);
        panelconGrid.add(seleccionaCompuesto);
        panelconGrid.add(botonBusquedaCompleta);
        panelconGrid.add(abrirLabos);
        panelconGrid.add(separator);


        this.add(panelconGrid);
        this.add(panelCotizacion);
    }




    public void mostrarInfoEnTabla(){
        if(elegir.getSelectedItem().equals("")) return;


        String nombreMonodroga = (String) elegir.getSelectedItem();
        boolean condicionCompuesto = seleccionaCompuesto.isSelected();

        productos.clear();


        Optional<ItemDRO> optionalMonodroga = monodrogas.stream()
                .filter(p -> p.getNombreMonodroga().equals(nombreMonodroga))
                .findFirst();

        refresheaLabos();

        if(optionalMonodroga.isPresent()){
            seleccionado = optionalMonodroga.get();
            seleccionado.setCompuesto(condicionCompuesto);
            List<Producto> productos = buscarEnKairos.ejecutaConsulta(seleccionado);
            panelMedio.mostrarData(productos);
        }
    }

    /**
     * Funcion encargada de actualizar las opciones de monodrogas a elegir
     *
     */

    public void actualizarOpciones (List<ItemDRO> listaMonodrogas){

        // Limpia los campos anteriores
        elegir.removeAllItems();
        monodrogas.clear();
        seleccionaCompuesto.setSelected(false);

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

    /**
     * En caso de que hubiesen cambios en el archivo de Laboratorios activos, actualiza los cambios
     * y setea en false el refresher.
     */

    public void refresheaLabos(){
        if(updateActiveLabos){
            buscarEnKairos.updateLabos();
            updateActiveLabos = false;
        }
    }

}
