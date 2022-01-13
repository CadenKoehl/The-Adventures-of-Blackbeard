package com.cadenkoehl.blackbeard.entity.enemy;

import com.cadenkoehl.blackbeard.client.GameClient;
import com.cadenkoehl.blackbeard.entity.Entity;
import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.entity.player.PlayerEntity;
import com.cadenkoehl.blackbeard.entity.projectile.ProjectileEntity;
import com.cadenkoehl.blackbeard.physics.Direction;
import com.cadenkoehl.blackbeard.render.Texture;
import com.cadenkoehl.blackbeard.render.Textures;
import com.cadenkoehl.blackbeard.world.Stage;

import java.awt.*;

public class EnemyShipEntity extends Entity {

    public EnemyShipEntity() {
        super("Enemy Ship");
        this.shotDirection = Direction.DOWN;
    }

    @Override
    public void tick() {
        super.tick();
        if(this.pos.y < 100) {
            velocity.y = 1;
        }
        else {
            velocity.y = 0;
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

    @Override
    public Texture getTexture() {
        return Textures.ENEMY_SHIP;
    }
}
