package com.cadenkoehl.blackbeard.client;

import com.cadenkoehl.blackbeard.client.window.GameFrame;
import com.cadenkoehl.blackbeard.client.window.GameState;
import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.entity.spawns.EntitySpawns;
import com.cadenkoehl.blackbeard.physics.Vec2d;
import com.cadenkoehl.blackbeard.world.Stage;

public class Blackbeard extends GameClient {

    public static final String TITLE = "Galactic Explorers";

    @Override
    public void init() {
        this.state = GameState.GAME;
        this.stage = new Stage();
        this.player = stage.spawnEntity(EntityType.PLAYER, new Vec2d((GameFrame.WIDTH / 2) - 50, 500));
        EntitySpawns.spawnEnemies();
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
