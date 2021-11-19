package com.exepinero.view;

import javax.swing.*;
import java.awt.*;

public class Marco extends JFrame {

    public Marco() throws HeadlessException {

        PanelPrincipal miPanel = new PanelPrincipal();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);
        this.setTitle("Búsqueda según monodroga");
        this.add(miPanel);
        this.setVisible(true);
    }

    private PanelPrincipal panel;

}
