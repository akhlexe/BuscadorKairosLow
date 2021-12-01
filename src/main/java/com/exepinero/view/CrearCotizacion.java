package com.exepinero.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearCotizacion extends JFrame {

    private JTextField fieldNombre;
    private JButton submit,close, elegirRuta;
    private JFileChooser fileChooser;

    public CrearCotizacion(PanelLateral panelLateral) throws HeadlessException {
        this.setTitle("Crear cotizacion");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth()/2;
        int height = (int) screenSize.getHeight()/2;
        this.setBounds(width/2,height/2,width,height+10);

        JPanel lamina = new JPanel();
        lamina.setLayout(new GridLayout(4,2));

        JLabel titulo = new JLabel("Nueva cotizacion");
        JLabel nombre = new JLabel("Nombre: ");
        fieldNombre = new JTextField(20);
        JLabel ruta = new JLabel("Exportar en: ");
        elegirRuta = new JButton("Elegir ruta...");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
