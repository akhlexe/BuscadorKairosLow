package com.exepinero.view;

import javax.swing.*;
import java.awt.*;

public class Marco extends JFrame {

    public Marco() throws HeadlessException {

        Panel miPanel = new Panel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,800);
        this.add(miPanel);
        this.setVisible(true);
    }

    Panel panel;

}
