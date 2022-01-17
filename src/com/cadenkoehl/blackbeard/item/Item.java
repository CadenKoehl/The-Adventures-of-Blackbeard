package com.cadenkoehl.blackbeard.item;

import com.cadenkoehl.blackbeard.entity.player.PlayerEntity;
import com.cadenkoehl.blackbeard.render.Texture;

public abstract class Item {

    public abstract void onAcquire(PlayerEntity player);
    public abstract Texture getTexture();

}
