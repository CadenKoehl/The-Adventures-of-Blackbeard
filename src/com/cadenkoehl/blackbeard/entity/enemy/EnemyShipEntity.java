package com.cadenkoehl.blackbeard.entity.enemy;

import com.cadenkoehl.blackbeard.client.GameClient;
import com.cadenkoehl.blackbeard.entity.Entity;
import com.cadenkoehl.blackbeard.entity.player.PlayerEntity;
import com.cadenkoehl.blackbeard.physics.Direction;

import java.awt.*;

public class EnemyShipEntity extends Entity {

    public EnemyShipEntity() {
        super("Enemy Ship");
        this.shotCooldown = 200;
    }

    @Override
    public void tick() {
        super.tick();
        PlayerEntity player = GameClient.getInstance().getPlayer();
        target(player);

    }

    public void target(Entity entity) {
        if(isCollidingWithX(entity)) {
            if(this.pos.y > entity.pos.y) {
                this.shotDirection = Direction.UP;
            }
            if(this.pos.y < entity.pos.y) {
                this.shotDirection = Direction.DOWN;
            }
        }
        else if(isCollidingWithY(entity)) {
            if(this.pos.x > entity.pos.x) {
                this.shotDirection = Direction.LEFT;
            }
            if(this.pos.x < entity.pos.x) {
                this.shotDirection = Direction.RIGHT;
            }
        }
        else {
            follow(entity);
        }
    }

    public void follow(Entity entity) {
        if(this.pos.y > entity.pos.y) {
            this.velocity.y = -1;
        }
        if(this.pos.y < entity.pos.y) {
            this.velocity.y = 1;
        }
        if(this.pos.x > entity.pos.x) {
            this.velocity.x = -1;
        }
        if(this.pos.x < entity.pos.x) {
            this.velocity.x = 1;
        }
    }

    @Override
    public int getMaxShotCooldown() {
        return 100;
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

    @Override
    public int getStartHealth() {
        return 4;
    }
}
