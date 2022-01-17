package com.cadenkoehl.blackbeard.item;

import com.cadenkoehl.blackbeard.entity.player.PlayerEntity;
import com.cadenkoehl.blackbeard.render.Texture;
import com.cadenkoehl.blackbeard.render.Textures;

public class HeartItem extends Item {

    @Override
    public void onAcquire(PlayerEntity player) {
        player.health++;
    }

    @Override
    public Texture getTexture() {
        return Textures.ITEM_HEART;
    }
}
