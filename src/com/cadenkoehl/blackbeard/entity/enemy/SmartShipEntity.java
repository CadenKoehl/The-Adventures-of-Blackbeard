package com.cadenkoehl.blackbeard.entity.enemy;

import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.entity.projectile.ProjectileEntity;
import com.cadenkoehl.blackbeard.game.GameClient;
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

    @Override
    public void launchProjectile(Direction direction, EntityType<? extends ProjectileEntity> entity) {
        if(GameClient.getInstance().day == 1) {
            super.launchProjectile(direction, EntityType.PROJECTILE);
        }
        else {
            super.launchProjectile(direction, EntityType.BOMB);
        }
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
        follow(entity, 1);
    }
    public void follow(Entity entity, int speed) {
        if(this.pos.x > entity.pos.x) {
            this.velocity.x = -speed;
            this.velocity.y = 0;
        }
        if(this.pos.x < entity.pos.x) {
            this.velocity.x = speed;
            this.velocity.y = 0;
        }
        if(this.pos.y > entity.pos.y) {
            this.velocity.y = -speed;
            this.velocity.x = speed;
        }
        if(this.pos.y < entity.pos.y) {
            this.velocity.y = speed;
            this.velocity.x = 0;
        }
        if(this.pos.x > entity.pos.x) {
            this.velocity.x = -speed;
        }
        if(this.pos.x < entity.pos.x) {
            this.velocity.x = speed;
        }
    }
}
