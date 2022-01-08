package com.cadenkoehl.galactic_explorers.entity;

import com.cadenkoehl.galactic_explorers.client.GameClient;
import com.cadenkoehl.galactic_explorers.client.window.GameFrame;
import com.cadenkoehl.galactic_explorers.physics.Vec2d;
import com.cadenkoehl.galactic_explorers.render.Renderer;
import com.cadenkoehl.galactic_explorers.render.Texture;

import java.awt.*;

public class Entity {

    public static final int SIZE_MULTIPLIER = 1;

    public Vec2d pos;
    public Vec2d velocity;
    private final Texture texture;
    private final String displayName;
    private final String name;

    public final int height;
    public final int width;
    public int health;

    public Entity(String displayName) {
        this.pos = null;
        this.health = this.getStartHealth();
        this.velocity = new Vec2d(0, 0);
        this.displayName = displayName;
        this.name = displayName.replace(" ", "_").toLowerCase();
        this.texture = new Texture(getColor(), 100, 100);
        this.height = texture.getHeight();
        this.width = texture.getWidth();
    }

    public Color getColor() {
        return null;
    }

    public void render() {
        Renderer.render(this, pos.x, pos.y);
    }

    public boolean inFrame() {
        return this.pos.y - Renderer.CAMERA.offset.y > -50 &&
                this.pos.y - Renderer.CAMERA.offset.y < 570 &&
                this.pos.x - Renderer.CAMERA.offset.x > -50 &&
                this.pos.x - Renderer.CAMERA.offset.x < GameFrame.WIDTH;
    }

    public boolean aboveInFrame() {
        return this.pos.y - Renderer.CAMERA.offset.y < 570;
    }

    public void postSpawn() {}

    public void damage(int amount) {
        health = health - amount;
        if(health <= 0) {
            kill();
        }
    }

    public void kill() {
        GameClient.getInstance().getStage().removeEntity(this);
    }

    public boolean isCollidingWith(Entity entity) {
        return entity.pos.x < this.pos.x + this.width &&
                entity.pos.x + entity.width > this.pos.x &&
                entity.pos.y < this.pos.y + this.height &&
                entity.health + entity.pos.y > this.pos.y;
    }

    public void setPos(Vec2d pos) {
        this.pos = pos;
    }

    public int getStartHealth() {
        return 0;
    }

    public Texture getTexture() {
        return texture;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getName() {
        return name;
    }

    public void tick() {
        if (pos == null) return;

        if (velocity.x == 0 && velocity.y == 0) return;

        updatePos();
    }

    public void updatePos() {
        setPos(new Vec2d(pos.x + velocity.x, pos.y + velocity.y));
    }
}
