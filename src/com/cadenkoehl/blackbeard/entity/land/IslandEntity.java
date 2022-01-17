package com.cadenkoehl.blackbeard.entity.land;

import com.cadenkoehl.blackbeard.entity.Entity;
import com.cadenkoehl.blackbeard.game.GameClient;
import com.cadenkoehl.blackbeard.render.Texture;
import com.cadenkoehl.blackbeard.render.Textures;

public class IslandEntity extends Entity {

    public IslandEntity() {
        super("Island");
    }

    @Override
    public void tick() {
        super.tick();
        if(this.pos.y < 0) {
            velocity.y = 1;
        }
        else {
            velocity.y = 0;
            GameClient.getInstance().dayComplete = true;
        }
    }

    @Override
    public void damage(int amount, Entity damager) {

    }

    @Override
    public Texture getTexture() {
        return Textures.ISLAND;
    }
}
