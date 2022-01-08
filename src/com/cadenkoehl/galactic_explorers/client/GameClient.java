package com.cadenkoehl.galactic_explorers.client;

import com.cadenkoehl.galactic_explorers.client.input.Input;
import com.cadenkoehl.galactic_explorers.client.window.GameFrame;
import com.cadenkoehl.galactic_explorers.client.window.GameState;
import com.cadenkoehl.galactic_explorers.client.window.GameWindow;
import com.cadenkoehl.galactic_explorers.entity.EntityType;
import com.cadenkoehl.galactic_explorers.entity.player.PlayerEntity;
import com.cadenkoehl.galactic_explorers.physics.Vec2d;
import com.cadenkoehl.galactic_explorers.world.Stage;

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

    public GameClient() {
        instance = this;
        this.state = GameState.GAME;
        this.frame = new GameFrame();
        this.window = new GameWindow(frame, this);
        this.stage = new Stage();
        this.player = stage.spawnEntity(EntityType.PLAYER, new Vec2d(100, 100));
        this.inputManager = new Input(this);
        frame.setVisible(true);
        frame.add(window);
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop, "Shutdown thread"));
        this.init();
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
        if(state == GameState.GAME) {
            stage.render();
            g.setColor(Color.WHITE);
            g.setFont(new Font("Comic Sans", Font.PLAIN, 14));
            g.drawString("Health: " + player.health, 50, 50);
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
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void init();
    public abstract void tick();
    public abstract void stop();
}
