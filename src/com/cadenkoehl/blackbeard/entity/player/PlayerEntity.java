package com.cadenkoehl.blackbeard.entity.player;

import com.cadenkoehl.blackbeard.game.GameClient;
import com.cadenkoehl.blackbeard.game.GameState;
import com.cadenkoehl.blackbeard.entity.Entity;
import com.cadenkoehl.blackbeard.game.input.Input;
import com.cadenkoehl.blackbeard.render.Texture;
import com.cadenkoehl.blackbeard.render.Textures;

import java.awt.*;

public class PlayerEntity extends Entity {

    public int damageTicks;

    public PlayerEntity() {
        super("Player");
    }

    @Override
    public void postSpawn() {
        //Renderer.CAMERA.centerOn(this);
    }

    @Override
    public void tick() {
        super.tick();
        if(damageTicks > 0) {
            damageTicks--;
        }
    }


    @Override
    public void damage(int amount, Entity damager) {
        if(damageTicks != 0) return;
        if(GameClient.getInstance().victory) return;
        if(GameClient.getInstance().difficulty != 3) {
            damageTicks = 200 / GameClient.getInstance().difficulty;
        }
        super.damage(amount * GameClient.getInstance().difficulty, damager);
    }

    @Override
    public void kill() {
        super.kill();
        GameClient.getInstance().state = GameState.DEATH_SCREEN;
        GameClient.getInstance().getWindow().repaint();
    }

    @Override
    public int getWidth() {
        return 25;
    }

    @Override
    public int getHeight() {
        return 35;
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

    @Override
    public int getBaseShotSpeed() {
        return 6;
    }
}
