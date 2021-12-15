package com.exepinero.view;

import com.exepinero.dto.ItemDRO;
import com.exepinero.model.Cotizacion;
import com.exepinero.model.Producto;
import com.exepinero.service.BuscarEnKairos;
import com.exepinero.service.Context;
import com.exepinero.service.GestorCotizaciones;
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

    /**
     * Variables estructurales
     */

    private JComboBox elegir;

    private JButton botonBusquedaCompleta,botonGuardarCotizacion, botonSalir, botonAgregaMonodroga, botonRemueveMonodroga, botonAbrirCotizacion, botonExportarCotizacion;
    private PanelMedio panelMedio;
    private BuscarEnKairos buscarEnKairos;
    private Inicializador loader;
    private JCheckBox seleccionaCompuesto;
    private JTextField displayNombreCoti;
    private JTextArea itemsCotizacion;
    private JScrollPane scrollPaneItemsCotizacion;
    private GestorCotizaciones gestorCotizaciones;

    /**
     * Variables en runtime
     */
    private Cotizacion currentCotizacion = null;
    private boolean updateActiveLabos = false;
    private boolean cotiActiva = false;
    private List<ItemDRO> monodrogas = new ArrayList<>();
    private ItemDRO seleccionado;
    private List<Producto> productos = new ArrayList<>();

    /**
     * Constructor
     *
     */

    public PanelLateral(PanelMedio panelMedio,Inicializador loader, BuscarEnKairos buscarEnKairos) {
        this.loader = loader;
        this.buscarEnKairos = buscarEnKairos;
        this.panelMedio = panelMedio;
        this.gestorCotizaciones = new GestorCotizaciones(this);

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
        panelCotizacion.setBorder(BorderFactory.createTitledBorder("Cotizacion"));
        panelCotizacion.setMaximumSize(new Dimension(300,500));
        BoxLayout boxLayoutCotizacion = new BoxLayout(panelCotizacion,BoxLayout.Y_AXIS);
        panelCotizacion.setLayout(boxLayoutCotizacion);

        displayNombreCoti = new JTextField(20);
        displayNombreCoti.setText("Sin info...");
        displayNombreCoti.setEnabled(false);
        displayNombreCoti.setMaximumSize(new Dimension(210,35));
        displayNombreCoti.setDisabledTextColor(Color.BLACK);

        itemsCotizacion = new JTextArea(4,10);
        itemsCotizacion.setDisabledTextColor(Color.BLACK);
        itemsCotizacion.setEnabled(false);
        scrollPaneItemsCotizacion = new JScrollPane(itemsCotizacion);
        scrollPaneItemsCotizacion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneItemsCotizacion.setMaximumSize(new Dimension(210,180));
        //scrollPaneItemsCotizacion.setMaximumSize(new Dimension(220,400));



        JPanel panelBotonesCoti = new JPanel();
        panelBotonesCoti.setLayout(new GridLayout(3,2));
        panelBotonesCoti.setMaximumSize(new Dimension(210,135));

        /**
         * Load de imagenes de botones cotizacion
         */

        ImageIcon iconoAddMonodroga = new ImageIcon("P:\\Usuarios\\Exequiel\\AppCotizaciones\\imagenes\\plus.png");
        ImageIcon iconoRemueveMonodroga = new ImageIcon("P:\\Usuarios\\Exequiel\\AppCotizaciones\\imagenes\\minus.png");
        ImageIcon iconoGuardarCotizacion = new ImageIcon("P:\\Usuarios\\Exequiel\\AppCotizaciones\\imagenes\\save.png");
        ImageIcon iconoExportarCotizacion = new ImageIcon("P:\\Usuarios\\Exequiel\\AppCotizaciones\\imagenes\\export-excel.png");
        ImageIcon iconoAbrirCotizacion = new ImageIcon("P:\\Usuarios\\Exequiel\\AppCotizaciones\\imagenes\\abrir.png");
        ImageIcon iconoSalir = new ImageIcon("P:\\Usuarios\\Exequiel\\AppCotizaciones\\imagenes\\logout.png");

        /**
         * Inicializacion de botones del panel de cotizacion
         */

        botonAgregaMonodroga = new JButton(iconoAddMonodroga);
        botonRemueveMonodroga = new JButton(iconoRemueveMonodroga);
        botonGuardarCotizacion = new JButton(iconoGuardarCotizacion);
        botonExportarCotizacion = new JButton(iconoExportarCotizacion);
        botonAbrirCotizacion = new JButton(iconoAbrirCotizacion);
        botonSalir = new JButton(iconoSalir);

        /**
         * Funcionalidad botones panel cotizacion
         */
        botonAgregaMonodroga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMonodrogaToCurrentCotizacion();
            }
        });
        botonRemueveMonodroga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remueveMonodrogaFromCurrentCotizacion();
            }
        });

        botonExportarCotizacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportaCotizacion();
            }
        });

        botonGuardarCotizacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"sin funcion");
            }
        });

        botonAbrirCotizacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"sin funcion");
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cierraPrograma();
            }
        });

        /**
         * Logica de ubicacion de componentes
         */

        panelBotonesCoti.add(botonAgregaMonodroga);
        panelBotonesCoti.add(botonRemueveMonodroga);
        panelBotonesCoti.add(botonGuardarCotizacion);
        panelBotonesCoti.add(botonExportarCotizacion);
        panelBotonesCoti.add(botonAbrirCotizacion);
        panelBotonesCoti.add(botonSalir);

        panelCotizacion.add(displayNombreCoti);
        panelCotizacion.add(Box.createRigidArea(new Dimension(0,5)));
        panelCotizacion.add(scrollPaneItemsCotizacion);
        panelCotizacion.add(Box.createRigidArea(new Dimension(0,5)));
        panelCotizacion.add(panelBotonesCoti);

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

    /**
     * Metodos funcionales de cada boton del panel de cotizacion
     */

    public void addMonodrogaToCurrentCotizacion(){
        gestorCotizaciones.agregaMonodroga(seleccionado,productos);
    }

    public void remueveMonodrogaFromCurrentCotizacion(){
        gestorCotizaciones.remueveMonodroga(seleccionado,productos);
    }

    public void exportaCotizacion(){
        new ExportarCotizacion(this.currentCotizacion,this.gestorCotizaciones);
    }

    /**
     * Métodos que muestran la info en tabla
     */

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
            productos = buscarEnKairos.ejecutaConsulta(seleccionado);
            panelMedio.mostrarData(productos);
        }
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

    public void cierraPrograma(){

        PanelPrincipal parent = (PanelPrincipal) this.getParent();
        parent.getMarco().dispose();


    }


    /**
     *
     *  Metodos varios de get y setters.
     * @return
     */


    public boolean isCotiActiva() {
        return cotiActiva;
    }

    public void setCotiActiva(boolean cotiActiva) {
        this.cotiActiva = cotiActiva;
    }

    public JTextField getDisplayNombreCoti() {
        return displayNombreCoti;
    }

    public JTextArea getItemsCotizacion() {
        return itemsCotizacion;
    }

    public GestorCotizaciones getGestorCotizaciones() {
        return gestorCotizaciones;
    }

    public void setCurrentCotizacion(Cotizacion currentCotizacion) {
        this.currentCotizacion = currentCotizacion;
    }

    public Cotizacion getCurrentCotizacion() {
        return currentCotizacion;
    }
}
