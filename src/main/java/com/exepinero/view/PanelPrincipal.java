package com.exepinero.view;

import com.exepinero.service.BuscarEnKairos;
import com.exepinero.service.Context;
import com.exepinero.service.Inicializador;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {

    private JPanel panelSuperior, panelMedio,panelLateral;
    private Context contexto;

    public PanelPrincipal() {

        setLayout(new BorderLayout());
        contexto = new Context();

        panelMedio = contexto.getPanelMedio();
        panelLateral = contexto.getPanelLateral();

        panelSuperior = new PanelSuperior(contexto.getBuscarEnKairos(),
                contexto.getPanelMedio(),
                contexto.getPanelLateral(),
                contexto);


        this.add(panelLateral, BorderLayout.WEST);
        this.add(panelSuperior,BorderLayout.NORTH);
        this.add(panelMedio, BorderLayout.CENTER);
    }
}
