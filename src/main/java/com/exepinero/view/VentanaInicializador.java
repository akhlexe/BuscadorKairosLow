package com.exepinero.view;

import javax.swing.*;
import java.awt.*;

public class VentanaInicializador extends JFrame {

    private JPanel panel;
    private JTextArea logWindow;

    public VentanaInicializador() throws HeadlessException {

        this.setTitle("App Cotizaciones");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth()/2;
        int height = (int) screenSize.getHeight()/2;

        this.setBounds(width/2,height/2,width,height+10);

        panel = new JPanel();


        logWindow = new JTextArea(17,80);
        logWindow.setBackground(Color.BLACK);
        logWindow.setForeground(Color.LIGHT_GRAY);
        logWindow.setFont(new Font(Font.MONOSPACED, Font.PLAIN,14));
        this.initProgram();
        logWindow.setEnabled(false);

        panel.add(logWindow);
        this.add(panel);

        this.setVisible(true);

    }

    public void initProgram(){
        logWindow.append(" ~~~~~~~~~~~~~~~~~~~~~~~~\n");
        logWindow.append("    App Cotizaciones\n");
        logWindow.append(" ~~~~~~~~~~~~~~~~~~~~~~~~\n");
        logWindow.append(" Cargando archivos...\n");
        logWindow.append(" Esto puede demorar algunos minutos\n");
    }

    public void escribirEnConsola(String text){
        logWindow.setEnabled(true);
        logWindow.append(" "+text+"\n");
        logWindow.setEnabled(false);
    }

    public void cerrarVentanaInicio(){
        this.dispose();
    }

    public JTextArea getLogWindow() {
        return logWindow;
    }

}
