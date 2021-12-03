package com.exepinero.view;

import com.exepinero.service.BuscarEnKairos;
import com.exepinero.service.Context;
import com.exepinero.service.Inicializador;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {

    private JPanel panelSuperior, panelMedio,panelLateral;
    private JTextField buscarField;
    private JButton buscar, exportar;
    private Context contexto;



    public PanelPrincipal() {

        setLayout(new BorderLayout());
        contexto = new Context();

        PanelMedio panelMedio = new PanelMedio();
        PanelLateral lateral = new PanelLateral(panelMedio, contexto.getBuscarEnKairos(), contexto.getLoader());
        PanelSuperior panelSuperior = new PanelSuperior(contexto.getBuscarEnKairos(),panelMedio, lateral,contexto);

        this.add(lateral, BorderLayout.WEST);
        this.add(panelSuperior,BorderLayout.NORTH);
        this.add(panelMedio, BorderLayout.CENTER);




    }


}
