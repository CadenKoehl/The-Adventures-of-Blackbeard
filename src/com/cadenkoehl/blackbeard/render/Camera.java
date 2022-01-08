package com.cadenkoehl.blackbeard.render;

import com.cadenkoehl.blackbeard.entity.player.PlayerEntity;
import com.cadenkoehl.blackbeard.physics.Vec2d;

public class Camera {

    public Vec2d offset;

    Camera() {
        this.offset = new Vec2d(0, 0);
    }

    public void centerOn(PlayerEntity player) {

        if(this.offset.y == player.pos.y) return;

        this.offset.y = player.pos.y - 380;
    }
}
