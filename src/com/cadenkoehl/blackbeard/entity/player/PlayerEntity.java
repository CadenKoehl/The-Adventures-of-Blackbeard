package com.cadenkoehl.blackbeard.entity.player;

import com.cadenkoehl.blackbeard.client.GameClient;
import com.cadenkoehl.blackbeard.client.window.GameState;
import com.cadenkoehl.blackbeard.entity.Entity;
import com.cadenkoehl.blackbeard.render.Texture;
import com.cadenkoehl.blackbeard.render.Textures;

import java.awt.*;

public class PlayerEntity extends Entity {

    public PlayerEntity() {
        super("Player");
    }

    @Override
    public void postSpawn() {
        //Renderer.CAMERA.centerOn(this);
    }

    @Override
    public void kill() {
        super.kill();
        GameClient.getInstance().state = GameState.DEATH_SCREEN;
        GameClient.getInstance().getWindow().repaint();
    }

    @Override
    public Texture getTexture() {
        return Textures.PLAYER;
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
