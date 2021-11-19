package com.exepinero.view;

import com.exepinero.model.ModeloTabla;
import com.exepinero.model.Resultado;
import com.exepinero.service.BuscarEnKairos;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.ArrayList;
import java.util.List;

public class PanelMedio extends JPanel{

    private JTable tabla;
    private ModeloTabla modeloTabla;
    private List<Resultado> resultados = new ArrayList<>();

    public PanelMedio() {
        modeloTabla = new ModeloTabla(new ArrayList<>());
        tabla = new JTable(modeloTabla);
        this.setFormatoTabla();
        JScrollPane scrollpane = new JScrollPane(tabla);
        this.add(scrollpane);
    }


    public void mostrarData(List<Resultado> resultados){
        this.resultados = resultados;
        ModeloTabla modelo = new ModeloTabla(resultados);
        tabla.setModel(modelo);
    }

    public void setFormatoTabla(){

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.RIGHT);

        if (tabla.getAutoResizeMode() == JTable.AUTO_RESIZE_ALL_COLUMNS) tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(35);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(35);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(35);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(35);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(35);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(35);


    }

}
