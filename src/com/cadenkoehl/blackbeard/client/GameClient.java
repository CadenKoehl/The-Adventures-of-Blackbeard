package com.cadenkoehl.blackbeard.client;

import com.cadenkoehl.blackbeard.client.input.Input;
import com.cadenkoehl.blackbeard.client.window.GameFrame;
import com.cadenkoehl.blackbeard.client.window.GameState;
import com.cadenkoehl.blackbeard.client.window.GameWindow;
import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.entity.player.PlayerEntity;
import com.cadenkoehl.blackbeard.physics.Vec2d;
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

    public GameClient() {
        instance = this;
        this.state = GameState.GAME;
        this.frame = new GameFrame();
        this.window = new GameWindow(frame, this);
        this.stage = new Stage();
        this.player = stage.spawnEntity(EntityType.PLAYER, new Vec2d((GameFrame.WIDTH / 2) - 50, 500));
        stage.spawnEntity(EntityType.ENEMY_SHIP, new Vec2d((GameFrame.WIDTH / 2) - 50, 200));
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
