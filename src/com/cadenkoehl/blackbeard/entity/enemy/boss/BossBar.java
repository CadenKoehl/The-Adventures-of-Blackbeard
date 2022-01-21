package com.cadenkoehl.blackbeard.entity.enemy.boss;

import com.cadenkoehl.blackbeard.entity.Entity;

public class BossBar {

    private final Entity entity;

    public BossBar(Entity entity) {
        this.entity = entity;
    }

    public double getProgress() {
        return (double) entity.health / entity.getStartHealth();
    }

    public Entity getEntity() {
        return entity;
    }
}
