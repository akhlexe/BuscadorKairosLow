package com.exepinero.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModeloTabla extends AbstractTableModel {

    private String [] columnNames = {"ID","Monodroga","Descripción","Laboratorio","Precio","URL"};
    private List<Resultado> resultados = new ArrayList<>();
    private Resultado resul;

    public ModeloTabla(List<Resultado> resultados) {
        this.resultados = resultados;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        if (resultados==null) return 25;
        return resultados.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (resultados==null) return null;
        resul = resultados.get(rowIndex);
        return resul.getVector().get(columnIndex);

    }

    public void setValueAt(Object value, int row, int col) {

        Object valor = resultados.get(row).getVector().get(col);
        valor = value;
        this.fireTableCellUpdated(row, col);
    }

}
