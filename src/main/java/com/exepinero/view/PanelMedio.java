package com.exepinero.view;

import com.exepinero.model.ModeloTabla;
import com.exepinero.model.Resultado;
import com.exepinero.service.BuscarEnKairos;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelMedio extends JPanel{

    private JTable tabla;
    private ModeloTabla modeloTabla;
    private List<Resultado> resultados = new ArrayList<>();

    public PanelMedio() {

        this.setLayout(new GridLayout());

        Border borde = BorderFactory.createEtchedBorder(0);
        this.setBorder(borde);

        modeloTabla = new ModeloTabla(null);
        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);
        this.add(new JScrollPane(tabla));
    }


    public void mostrarData(List<Resultado> resultados){
        this.resultados = resultados;
        ModeloTabla modelo = new ModeloTabla(resultados);
        tabla.setModel(modelo);
        this.setFormatoTabla();
    }

    public void setFormatoTabla(){

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.RIGHT);

        JTable tempTabla = tabla;
        if (tempTabla.getAutoResizeMode() == JTable.AUTO_RESIZE_ALL_COLUMNS) tempTabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // ID
        tabla.getColumnModel().getColumn(0).setPreferredWidth(35);
        // Monodroga
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        // Descripción
        tabla.getColumnModel().getColumn(2).setPreferredWidth(200);
        // Laboratorio
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        // Precio
        tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
        // URL
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);


    }

}
