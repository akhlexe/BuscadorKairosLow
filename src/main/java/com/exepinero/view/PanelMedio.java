package com.exepinero.view;

import com.exepinero.service.BuscarEnKairos;

import javax.swing.*;

public class PanelMedio extends JPanel{

    private JTextArea areaDeTexto;

    public PanelMedio() {
        areaDeTexto = new JTextArea();
        areaDeTexto.setColumns(30);
        areaDeTexto.setRows(20);
        JScrollPane texto = new JScrollPane(areaDeTexto);
        this.add(texto);

    }

    public JTextArea getAreaDeTexto() {
        return areaDeTexto;
    }

    public void setAreaDeTexto(JTextArea areaDeTexto) {
        this.areaDeTexto = areaDeTexto;
    }
}
