package com.cadenkoehl.blackbeard.game;

import com.cadenkoehl.blackbeard.Main;
import com.cadenkoehl.blackbeard.game.input.Input;
import com.cadenkoehl.blackbeard.game.window.GameFrame;
import com.cadenkoehl.blackbeard.game.window.GameWindow;
import com.cadenkoehl.blackbeard.entity.player.PlayerEntity;
import com.cadenkoehl.blackbeard.item.Item;
import com.cadenkoehl.blackbeard.render.Renderer;
import com.cadenkoehl.blackbeard.render.Textures;
import com.cadenkoehl.blackbeard.world.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    public ImageIcon intro;
    public int introTime;
    public boolean hitboxes;

    public GameClient() {
        instance = this;
        Textures.initTextures();
        intro = new ImageIcon("resources/intro.gif");
        this.state = GameState.INTRO;
        this.frame = new GameFrame();
        this.window = new GameWindow(frame, this);
        this.inputManager = new Input();
        frame.setVisible(true);
        frame.add(window);
        WaveManager.initSpawns();
        difficulty = 1;
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
        if(state == GameState.INTRO) {
            intro.paintIcon(window, g, 0,0);
            if(introTime == 0) {
                playSound("resources/intro.wav");
            }
            introTime++;
            if(introTime > 20000) state = GameState.TITLE_SCREEN;
            window.repaint();
        }
        if (state == GameState.TITLE_SCREEN) {
            Renderer.render(Textures.TITLE_SCREEN, 0, 0);
        }
        if (state == GameState.DIFFICULTY_SELECT) {
            if (difficulty == 1) {
                Renderer.render(Textures.NORMAL, 0, 0);
            }
            if (difficulty == 2) {
                Renderer.render(Textures.HARD, 0, 0);
            }
            if (difficulty == 3) {
                Renderer.render(Textures.BRUTAL, 0, 0);
            }
        }
        if (state == GameState.GAME) {
            stage.render();
            for (int i = 0; i < player.health; i++) {
                Renderer.render(Textures.HP_HEART, (i * 40) + 20, 20);
            }
        }
        if (state == GameState.TREASURE_BOX) {
            if (chestOpen) {
                Renderer.render(Textures.CHEST_OPEN, 0, 0);
                Renderer.render(item.getTexture(), 400, 300);
            } else {
                Renderer.render(Textures.CHEST_CLOSED, 0, 0);
            }
        } else if (state == GameState.DEATH_SCREEN) {
            stage.clear();
            Renderer.render(Textures.DEATH, 0, 0);
        }
    }

    public static void playSound(String sound) {
        try {
            File file = new File("resources/intro.wav");
        Clip clip = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
        clip.open(inputStream);
        clip.start();
        }
        catch (Exception e) {
        System.err.println(e.getMessage());
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
