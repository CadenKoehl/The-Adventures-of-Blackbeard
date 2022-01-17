package com.cadenkoehl.blackbeard.game.window;

import com.cadenkoehl.blackbeard.game.Blackbeard;

import javax.swing.*;

public class GameFrame extends JFrame {

    public static int WIDTH = 1000;
    public static int HEIGHT = 1000;

    public GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(Blackbeard.TITLE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}