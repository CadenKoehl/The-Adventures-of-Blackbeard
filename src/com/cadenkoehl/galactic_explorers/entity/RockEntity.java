package com.cadenkoehl.galactic_explorers.entity;

import com.cadenkoehl.galactic_explorers.client.GameClient;
import com.cadenkoehl.galactic_explorers.entity.player.PlayerEntity;

import java.awt.*;

public class RockEntity extends Entity {

    private boolean canDealDamage = true;

    public RockEntity() {
        super("Asteroid");
    }

    @Override
    public void tick() {
        super.tick();
        if(!aboveInFrame()) {
            GameClient.getInstance().getStage().removeEntity(this);
        }
        PlayerEntity player = GameClient.getInstance().getPlayer();
        if(this.isCollidingWith(player)) {
            if(canDealDamage) {
                player.damage(1);
                canDealDamage = false;
                velocity.y = -3;
                velocity.x = player.velocity.x;
            }
        }
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

    @Override
    public int getStartHealth() {
        return 1;
    }
}