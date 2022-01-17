package com.cadenkoehl.blackbeard.entity.projectile;

import com.cadenkoehl.blackbeard.entity.Entity;
import com.cadenkoehl.blackbeard.render.Texture;
import com.cadenkoehl.blackbeard.render.Textures;

public class ProjectileEntity extends Entity {

    private Entity source;

    public ProjectileEntity() {
        super("Projectile");
    }

    @Override
    public void tick() {
        super.tick();
    }

    public void setSource(Entity source) {
        this.source = source;
    }

    public Entity getSource() {
        return source;
    }

    @Override
    public int getStartHealth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 5;
    }

    @Override
    public int getWidth() {
        return 5;
    }

    @Override
    public Texture getTexture() {
        return Textures.ROCK;
    }
}
