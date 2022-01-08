package com.cadenkoehl.galactic_explorers.client.window;

import com.cadenkoehl.galactic_explorers.client.GalacticExplorers;

import javax.swing.*;

public class GameFrame extends JFrame {

    public static int WIDTH = 1000;
    public static int HEIGHT = 600;

    public GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(GalacticExplorers.TITLE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
    }
}
