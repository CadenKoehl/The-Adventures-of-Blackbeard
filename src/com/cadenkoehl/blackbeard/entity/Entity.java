package com.cadenkoehl.blackbeard.entity;

import com.cadenkoehl.blackbeard.client.GameClient;
import com.cadenkoehl.blackbeard.client.window.GameFrame;
import com.cadenkoehl.blackbeard.entity.projectile.ProjectileEntity;
import com.cadenkoehl.blackbeard.physics.Direction;
import com.cadenkoehl.blackbeard.physics.Vec2d;
import com.cadenkoehl.blackbeard.render.Renderer;
import com.cadenkoehl.blackbeard.render.Texture;
import com.cadenkoehl.blackbeard.world.Stage;

import java.awt.*;

public class Entity {

    public static final int SIZE_MULTIPLIER = 1;

    public Vec2d pos;
    public Vec2d velocity;
    private final Texture texture;
    private final String displayName;
    private final String name;
    public long ticksLived;
    public final int height;
    public final int width;
    public int health;
    public int shotCooldown;
    public Direction shotDirection;

    public Entity(String displayName) {
        this.pos = null;
        this.health = this.getStartHealth();
        this.velocity = new Vec2d(0, 0);
        this.displayName = displayName;
        this.name = displayName.replace(" ", "_").toLowerCase();
        this.texture = new Texture(getColor(), getHeight(), getWidth());
        this.height = texture.getHeight();
        this.width = texture.getWidth();
    }

    public Color getColor() {
        return null;
    }

    public int getHeight() {
        return 100;
    }

    public int getWidth() {
        return 100;
    }

    public int getMaxShotCooldown() {
        return 25;
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

    public void launchProjectile(Direction direction) {

        if(shotCooldown != 0) return;

        shotCooldown = this.getMaxShotCooldown();

        Vec2d pos = new Vec2d(0,0);
        Vec2d velocity = new Vec2d(0,0);


        if(direction == Direction.UP) {
            pos.x = this.pos.x + (this.width /2);
            pos.y = this.pos.y;
            velocity.y = -6;
        }
        if(direction == Direction.DOWN) {
            pos.x = this.pos.x + (this.width /2);
            pos.y = this.pos.y + (this.height / 2);
            velocity.y = 6;
        }
        if(direction == Direction.LEFT) {
            pos.y = this.pos.y + (this.height /2);
            pos.x = this.pos.x;
            velocity.x = -6;
        }
        if(direction == Direction.RIGHT) {
            pos.x = this.pos.x + this.width;
            pos.y = this.pos.y + (this.height / 2);
            velocity.x = 6;
        }

        ProjectileEntity projectile = GameClient.getInstance().getStage().spawnEntity(EntityType.PROJECTILE, pos);
        projectile.velocity.x = velocity.x;
        projectile.velocity.y = velocity.y;
        projectile.setSource(this);
    }

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
    public boolean isCollidingWithX(Entity entity) {
        return entity.pos.x < this.pos.x + this.width &&
                entity.pos.x + entity.width > this.pos.x;
    }
    public boolean isCollidingWithY(Entity entity) {
        return entity.pos.y < this.pos.y + this.height &&
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
        if(pos == null) return;

        ticksLived++;

        if(shotCooldown > 0) shotCooldown--;

        if(shotDirection != null) {
            launchProjectile(shotDirection);
        }

        checkProjectiles();

        if (velocity.x == 0 && velocity.y == 0) return;

        updatePos();
    }

    public void checkProjectiles() {
        if(this instanceof ProjectileEntity) return;

        for(Entity entity : Stage.getEntities()) {
            if(!(entity instanceof ProjectileEntity)) continue;
            if(this.isCollidingWith(entity)) {
                if(!((ProjectileEntity) entity).getSource().equals(this)) {
                    this.damage(1);
                    entity.kill();
                }
            }
        }
    }

    public void updatePos() {
        updatePosX();
        updatePosY();
    }
    private void updatePosX() {
        if(velocity.x > 0 && pos.x >= (GameFrame.WIDTH - (this.width * 1.1))) {
            velocity.x = 0;
            if(this instanceof ProjectileEntity) kill();
            return;
        }
        if(velocity.x < 0 && pos.x <= 0) {
            velocity.x = 0;
            if(this instanceof ProjectileEntity) kill();
            return;
        }
        setPos(new Vec2d(pos.x + velocity.x, pos.y));
    }
    private void updatePosY() {
        if(velocity.y > 0 && pos.y >= (GameFrame.HEIGHT - (this.height * 1.4))) {
            velocity.y = 0;
            if(this instanceof ProjectileEntity) kill();
            return;
        }
        if(velocity.y < 0 && pos.y <= 0) {
            velocity.y = 0;
            if(this instanceof ProjectileEntity) kill();
            return;
        }
        setPos(new Vec2d(pos.x, pos.y + velocity.y));
    }
}
