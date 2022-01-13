package com.cadenkoehl.blackbeard.entity.enemy;

import com.cadenkoehl.blackbeard.client.GameClient;
import com.cadenkoehl.blackbeard.entity.Entity;
import com.cadenkoehl.blackbeard.entity.player.PlayerEntity;
import com.cadenkoehl.blackbeard.physics.Direction;

public class SmartShipEntity extends EnemyShipEntity {

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
        if(isCollidingWithY(entity)) {
            if(this.pos.x > entity.pos.x) {
                this.shotDirection = Direction.LEFT;
            }
            if(this.pos.x < entity.pos.x) {
                this.shotDirection = Direction.RIGHT;
            }
        }
        follow(entity);
    }
    public void follow(Entity entity) {
        if(this.pos.x > entity.pos.x) {
            this.velocity.x = -1;
            this.velocity.y = 0;
        }
        if(this.pos.x < entity.pos.x) {
            this.velocity.x = 1;
            this.velocity.y = 0;
        }
        if(this.pos.y > entity.pos.y) {
            this.velocity.y = -1;
            this.velocity.x = 0;
        }
        if(this.pos.y < entity.pos.y) {
            this.velocity.y = 1;
            this.velocity.x = 0;
        }
        if(this.pos.x > entity.pos.x) {
            this.velocity.x = -1;
        }
        if(this.pos.x < entity.pos.x) {
            this.velocity.x = 1;
        }
    }
}
