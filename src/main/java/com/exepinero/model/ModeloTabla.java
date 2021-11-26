package com.exepinero.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModeloTabla extends AbstractTableModel {

    private String [] columnNames = {"Cod Prod","GTIN","Monodroga","Descripción","Laboratorio","Precio"};
    private List<Producto> productos = new ArrayList<>();
    private Producto product;

    public ModeloTabla(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        if (productos==null) return 25;
        return productos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (productos==null) return null;
        product = productos.get(rowIndex);
        return product.getVector().get(columnIndex);

    }

    public void setValueAt(Object value, int row, int col) {

        Object valor = productos.get(row).getVector().get(col);
        valor = value;
        this.fireTableCellUpdated(row, col);
    }

}
