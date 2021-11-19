package com.exepinero.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModeloTabla extends AbstractTableModel {

    private String [] columnNames = {"ID","Monodroga","Presentación","Laboratorio","Precio"};
    private List<Resultado> resultados = new ArrayList<>();
    private Object[][] data;

    public ModeloTabla(List<Resultado> resultados) {
        this.resultados = resultados;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return resultados.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    public Object[] monodrogaAVector(Monodroga monodroga){
        String id = monodroga.getId();
        String nombre = monodroga.getNombre();
        return null;
    }
}
