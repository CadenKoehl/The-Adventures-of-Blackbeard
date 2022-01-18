package com.cadenkoehl.blackbeard.game;

import com.cadenkoehl.blackbeard.game.window.GameFrame;
import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.physics.Vec2d;
import com.cadenkoehl.blackbeard.world.Stage;

import java.awt.*;

public class Blackbeard extends GameClient {

    //TODO
    //Fix shark AI
    //Slow key [space]
    //Hitbox fix

    public static final String TITLE = "The Adventures of Blackbeard";

    @Override
    public void newDay() {
        this.state = GameState.GAME;
        this.stage = new Stage();
        stage.spawnEntity(player);
        player.velocity.x = 0;
        player.velocity.y = 0;
        this.day++;
        this.wave = 0;
        this.enemyCount = 0;
        this.dayComplete = false;
        WaveManager.nextWave();
        this.getWindow().setBackground(new Color(0x75CEFF));
        new Thread(this::startGameLoop, "Game thread").start();
    }

    @Override
    public void init() {
        this.state = GameState.GAME;
        this.stage = new Stage();
        this.player = stage.spawnEntity(EntityType.PLAYER, new Vec2d((GameFrame.WIDTH / 2) - 50, 500));
        this.day = 1;
        this.wave = 0;
        this.enemyCount = 0;
        this.dayComplete = false;
        WaveManager.nextWave();
        this.getWindow().setBackground(new Color(0x75CEFF));
        new Thread(this::startGameLoop, "Game thread").start();
    }

    @Override
    public void tick() {
        stage.tick();
    }

    @Override
    public void stop() {

    }
}
