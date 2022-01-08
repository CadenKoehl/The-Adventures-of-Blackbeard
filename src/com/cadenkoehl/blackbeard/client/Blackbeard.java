package com.cadenkoehl.blackbeard.client;

import com.cadenkoehl.blackbeard.client.window.GameState;
import com.cadenkoehl.blackbeard.entity.spawns.EntitySpawns;
import com.cadenkoehl.blackbeard.render.Renderer;

public class Blackbeard extends GameClient {

    public static final String TITLE = "Galactic Explorers";

    @Override
    public void init() {
        this.state = GameState.GAME;
        EntitySpawns.initSpawns();
        new Thread(this::startGameLoop, "Game thread").start();
    }

    @Override
    public void tick() {
        Renderer.CAMERA.centerOn(player);
        stage.tick();
    }

    @Override
    public void stop() {

    }
}
