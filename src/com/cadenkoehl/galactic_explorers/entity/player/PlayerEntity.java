package com.cadenkoehl.galactic_explorers.entity.player;

import com.cadenkoehl.galactic_explorers.client.GameClient;
import com.cadenkoehl.galactic_explorers.client.window.GameState;
import com.cadenkoehl.galactic_explorers.entity.Entity;
import com.cadenkoehl.galactic_explorers.render.Renderer;

import java.awt.*;

public class PlayerEntity extends Entity {

    public PlayerEntity() {
        super("Player");
    }

    @Override
    public void postSpawn() {
        Renderer.CAMERA.centerOn(this);
    }

    @Override
    public void kill() {
        super.kill();
        GameClient.getInstance().state = GameState.DEATH_SCREEN;
        GameClient.getInstance().getWindow().repaint();
    }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    @Override
    public int getStartHealth() {
        return 3;
    }
}
