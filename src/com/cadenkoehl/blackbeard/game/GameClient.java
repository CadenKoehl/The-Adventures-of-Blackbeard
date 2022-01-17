package com.cadenkoehl.blackbeard.game;

import com.cadenkoehl.blackbeard.game.input.Input;
import com.cadenkoehl.blackbeard.game.window.GameFrame;
import com.cadenkoehl.blackbeard.game.window.GameWindow;
import com.cadenkoehl.blackbeard.entity.player.PlayerEntity;
import com.cadenkoehl.blackbeard.item.Item;
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
    public int difficulty;
    public int day;
    public int wave;
    public boolean dayComplete;
    public boolean chestOpen;
    public Item item;

    public GameClient() {
        instance = this;
        Textures.initTextures();
        this.state = GameState.TITLE_SCREEN;
        this.frame = new GameFrame();
        this.window = new GameWindow(frame, this);
        this.inputManager = new Input();
        frame.setVisible(true);
        frame.add(window);
        WaveManager.initSpawns();
        difficulty = 2;
        day = 0;
        wave = 0;
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
            Renderer.render(Textures.MENU_BACKGROUND, 0, 0);
            Renderer.render(Textures.SCROLL, 0,0);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Comic Sans", Font.BOLD, 50));
            g.drawString("PLAY", (GameFrame.WIDTH / 2) - 70, 300);
            g.setFont(new Font("Comic Sans", Font.BOLD, 15));
            g.drawString("[SPACE]", (GameFrame.WIDTH / 2) - 30, 330);

            g.setFont(new Font("Comic Sans", Font.BOLD, 25));

            String difficulty = "";
            if(this.difficulty == 1) difficulty = "Difficulty: EASY";
            if(this.difficulty == 2) difficulty = "Difficulty: NORMAL";
            if(this.difficulty == 3) difficulty = "Difficulty: ASAIN";

            g.drawString(difficulty, (GameFrame.WIDTH / 2) - 100, 400);

        }
        if(state == GameState.GAME) {
            stage.render();
            for(int i = 0; i < player.health; i++) {
                Renderer.render(Textures.HP_HEART, (i * 40) + 20, 20);
            }
        }
        if(state == GameState.TREASURE_BOX) {
            if(chestOpen) {
                Renderer.render(Textures.CHEST_OPEN, 0,0);
                Renderer.render(item.getTexture(), 400, 300);
            }
            else {
                Renderer.render(Textures.CHEST_CLOSED, 0, 0);
            }
        }
        else if(state == GameState.DEATH_SCREEN) {
            stage.clear();
            Renderer.render(Textures.MENU_BACKGROUND, 0, 0);
            Renderer.render(Textures.SCROLL, 0,0);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Comic Sans", Font.BOLD, 50));
            g.drawString("YOU DIED", (GameFrame.WIDTH / 2) - 120, 300);
            g.setFont(new Font("Comic Sans", Font.BOLD, 15));
            g.drawString("[PRESS SPACE TO CONTINUE]", (GameFrame.WIDTH / 2) - 106, 330);
        }
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
    public abstract void newDay();
    public abstract void stop();
}
