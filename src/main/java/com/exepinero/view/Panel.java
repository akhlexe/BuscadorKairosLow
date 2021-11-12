package com.exepinero.view;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    public Panel() {

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Titulo de prueba");
        buscarField = new JTextField(10);
        buscar = new JButton("Buscar");

        panelSuperior = new JPanel();
        panelSuperior.add(titulo);
        panelSuperior.add(buscarField);
        panelSuperior.add(buscar);

        this.add(panelSuperior,BorderLayout.NORTH);


    }

    private JPanel panelSuperior;
    private JTextField buscarField;
    private JButton buscar, exportar;
}
