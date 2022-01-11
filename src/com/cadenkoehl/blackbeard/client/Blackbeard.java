package com.cadenkoehl.blackbeard.client;

import com.cadenkoehl.blackbeard.client.window.GameState;

public class Blackbeard extends GameClient {

    public static final String TITLE = "Galactic Explorers";

    @Override
    public void init() {
        this.state = GameState.GAME;
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
