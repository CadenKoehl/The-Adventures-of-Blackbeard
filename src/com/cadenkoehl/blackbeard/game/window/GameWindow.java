package com.cadenkoehl.blackbeard.game.window;

import com.cadenkoehl.blackbeard.game.GameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GameWindow extends JPanel {

    public static GameWindow INSTANCE = null;
    public static Graphics GRAPHICS = null;
    private final GameFrame FRAME;
    private final GameClient game;
    public static boolean readyForNextFrame;

    public GameWindow(GameFrame frame, GameClient game) {
        Thread.currentThread().setName("Render thread");
        this.FRAME = frame;
        this.game = game;
        INSTANCE = this;
        this.setFocusable(true);
        readyForNextFrame = true;
        setUpInput();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        GRAPHICS = g;
        GameFrame.HEIGHT = FRAME.getHeight();
        GameFrame.WIDTH = FRAME.getWidth();
        game.renderFrame(g);
    }

    public void setUpInput() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                game.inputManager.onKeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                game.inputManager.onKeyReleased(e);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                game.inputManager.onMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                game.inputManager.onMouseReleased(e);
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                game.inputManager.onMouseDragged(e);
            }
        });
    }
}
