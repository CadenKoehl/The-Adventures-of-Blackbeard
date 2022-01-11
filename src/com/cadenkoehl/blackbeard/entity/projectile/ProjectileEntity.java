package com.cadenkoehl.blackbeard.entity.projectile;

import com.cadenkoehl.blackbeard.entity.Entity;
import com.cadenkoehl.blackbeard.render.Texture;

import java.awt.*;

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
    public Color getColor() {
        return Color.YELLOW;
    }

    @Override
    public int getHeight() {
        return 10;
    }

    @Override
    public int getWidth() {
        return 10;
    }
}
