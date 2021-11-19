package com.exepinero.view;

import com.exepinero.service.BuscarEnKairos;
import com.exepinero.service.Context;

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
        PanelLateral lateral = new PanelLateral(panelMedio, contexto.getBuscarEnKairos());

        this.add(lateral, BorderLayout.WEST);
        PanelSuperior panelSuperior = new PanelSuperior(contexto.getBuscarEnKairos(),panelMedio, lateral);

        this.add(panelSuperior,BorderLayout.NORTH);
        this.add(panelMedio, BorderLayout.CENTER);




    }


}
