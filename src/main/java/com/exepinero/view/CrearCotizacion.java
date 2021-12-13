package com.exepinero.view;

import com.exepinero.model.Cotizacion;
import com.exepinero.service.Context;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearCotizacion extends JFrame {

    private JTextField fieldNombre;
    private JButton submit,close, elegirRuta;
    private Context context;
    private PanelLateral panelLateral;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JPanel lamina;

    public CrearCotizacion(PanelLateral panelLateral, Context context) throws HeadlessException {

        this.context = context;
        this.panelLateral = panelLateral;

        this.setTitle("Crear cotizacion");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth()/2;
        int height = (int) screenSize.getHeight()/2;
        this.setBounds(width/2,height/2,width/2,(height)/2);

        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

        lamina = new JPanel();
        lamina.setLayout(gbl);


        Font fontTitulo = new Font("Monospace",Font.BOLD,14);
        JLabel titulo = new JLabel("Nueva cotizacion");
        titulo.setFont(fontTitulo);

        JLabel espacio = new JLabel("");
        JLabel nombre = new JLabel("Nombre: ");
        nombre.setHorizontalAlignment(JLabel.RIGHT);
        fieldNombre = new JTextField(20);
        fieldNombre.setSize(new Dimension(50,15));
        submit = new JButton("Crear");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCotizacion();
            }
        });

        close = new JButton("Cancelar");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVentana();
            }
        });
        gbc.ipadx = 5;
        gbc.ipady = 5;
        gbc.insets = new Insets(5,5,5,5);

        gbc.weighty = 1.0;

        this.agregaComponente(titulo,0,0,2,1);
        gbc.fill = MAXIMIZED_HORIZ;

        gbc.weighty = 0.5;

        this.agregaComponente(nombre,0,1,1,1);
        this.agregaComponente(fieldNombre,1,1,1,1);
        this.agregaComponente(submit,0,2,2,1);
        this.agregaComponente(close,0,3,2,1);

        this.add(lamina);
        this.setVisible(true);

    }

    public void crearCotizacion(){
        Cotizacion cotizacion = new Cotizacion(fieldNombre.getText());
        panelLateral.setCurrentCotizacion(cotizacion);

        JTextField displayNombreCoti = panelLateral.getDisplayNombreCoti();
        displayNombreCoti.setEnabled(true);
        displayNombreCoti.setText(" "+cotizacion.getNombreCotizacion());
        displayNombreCoti.setEnabled(false);

        JTextArea itemsCotizacion = panelLateral.getItemsCotizacion();
        itemsCotizacion.setEnabled(true);
        itemsCotizacion.setText(" Monodrogas cotizadas:");
        itemsCotizacion.setEnabled(false);

        this.dispose();

    }

    public void agregaComponente(Component component, int x, int y, int width, int height){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth= width;
        gbc.gridheight = height;
        lamina.add(component, gbc);
    }

    private void cerrarVentana(){
        this.dispose();
    }
}
