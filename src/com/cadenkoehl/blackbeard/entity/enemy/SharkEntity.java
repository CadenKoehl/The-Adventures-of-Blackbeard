package com.cadenkoehl.blackbeard.entity.enemy;

import com.cadenkoehl.blackbeard.entity.Entity;
import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.entity.projectile.ProjectileEntity;
import com.cadenkoehl.blackbeard.physics.Direction;
import com.cadenkoehl.blackbeard.render.Texture;
import com.cadenkoehl.blackbeard.render.Textures;

public class SharkEntity extends SmartShipEntity {

    @Override
    public Texture getTexture() {
        return Textures.SHARK;
    }

    @Override
    public void follow(Entity entity, int speed) {
        super.follow(entity, 2);
    }

    @Override
    public void launchProjectile(Direction direction, EntityType<? extends ProjectileEntity> entity) {

    }
}
