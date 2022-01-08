package com.cadenkoehl.galactic_explorers.render;

import com.cadenkoehl.galactic_explorers.entity.player.PlayerEntity;
import com.cadenkoehl.galactic_explorers.physics.Vec2d;

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
