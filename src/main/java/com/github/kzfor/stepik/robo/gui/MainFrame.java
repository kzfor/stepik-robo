package com.github.kzfor.stepik.robo.gui;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(JPanel panel) {
        add(panel);
        init();
    }
    public void init() {
        setTitle("GUI Robot by kzfor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
