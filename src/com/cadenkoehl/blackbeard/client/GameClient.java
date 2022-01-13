package com.cadenkoehl.blackbeard.client;

import com.cadenkoehl.blackbeard.client.input.Input;
import com.cadenkoehl.blackbeard.client.window.GameFrame;
import com.cadenkoehl.blackbeard.client.window.GameState;
import com.cadenkoehl.blackbeard.client.window.GameWindow;
import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.entity.player.PlayerEntity;
import com.cadenkoehl.blackbeard.entity.spawns.EntitySpawns;
import com.cadenkoehl.blackbeard.physics.Vec2d;
import com.cadenkoehl.blackbeard.render.Renderer;
import com.cadenkoehl.blackbeard.render.Textures;
import com.cadenkoehl.blackbeard.world.Stage;

import java.awt.*;
import java.util.ConcurrentModificationException;

public abstract class GameClient {

    private static GameClient instance;
    private final GameFrame frame;
    private final GameWindow window;
    public GameState state;
    public Input inputManager;
    public PlayerEntity player;
    public Stage stage;
    public int enemyCount;

    public GameClient() {
        instance = this;
        Textures.initTextures();
        this.state = GameState.TITLE_SCREEN;
        this.frame = new GameFrame();
        this.window = new GameWindow(frame, this);
        this.inputManager = new Input();
        frame.setVisible(true);
        frame.add(window);
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop, "Shutdown thread"));
    }

    public static GameClient getInstance() {
        return instance;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public Stage getStage() {
        return stage;
    }

    public GameFrame getFrame() {
        return frame;
    }

    public GameWindow getWindow() {
        return window;
    }

    public void renderFrame(Graphics g) {
        if(state == GameState.TITLE_SCREEN) {
            Renderer.render(Textures.MENU_BACKGROUND, 0,0);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Comic Sans", Font.BOLD, 50));
            g.drawString("Play", (GameFrame.WIDTH / 2) - 50, 300);
            g.setFont(new Font("Comic Sans", Font.BOLD, 15));
            g.drawString("[SPACE]", (GameFrame.WIDTH / 2) - 33, 330);
        }
        if(state == GameState.GAME) {
            stage.render();
            g.setColor(Color.WHITE);
            g.setFont(new Font("Comic Sans", Font.PLAIN, 14));
            g.drawString("Health: " + player.health, 50, 50);
            g.drawString("Kills: " + player.kills, 50, 70);
        }
        else if(state == GameState.DEATH_SCREEN) {
            stage.clear();
            g.setColor(Color.RED);
            g.setFont(new Font("Comic Sans", Font.PLAIN, 50));
            g.drawString("YOU DIED", 360, 270);
        }
        window.setBackground(Color.BLACK);
    }

    public void startGameLoop() {
        while(state == GameState.GAME) {
            try {
                this.tick();
                window.repaint();
            }
            catch(ConcurrentModificationException ex) {
                //empty catch block
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void init();
    public abstract void tick();
    public abstract void stop();
}
