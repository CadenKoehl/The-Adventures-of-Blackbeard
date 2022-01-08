package com.cadenkoehl.galactic_explorers.client;

import com.cadenkoehl.galactic_explorers.client.window.GameState;
import com.cadenkoehl.galactic_explorers.entity.spawns.EntitySpawns;
import com.cadenkoehl.galactic_explorers.render.Renderer;

public class GalacticExplorers extends GameClient {

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
