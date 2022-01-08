package com.cadenkoehl.galactic_explorers.world;

import com.cadenkoehl.galactic_explorers.entity.Entity;
import com.cadenkoehl.galactic_explorers.entity.EntityType;
import com.cadenkoehl.galactic_explorers.physics.Vec2d;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Stage {

    private static final List<Entity> entities = new ArrayList<>();

    public void spawnEntity(Entity entity) {
        entities.add(entity);
        entity.render();
    }

    public <E extends Entity> E spawnEntity(EntityType<E> entityType, Vec2d pos) {
        Entity entity = entityType.createEntity();
        entity.setPos(pos);
        entity.postSpawn();
        entities.add(entity);
        return (E) entity;
    }

    public void tick() {
        for(Entity entity : entities) {
            entity.tick();
        }
    }

    public void render() {
        try {
            for(Entity entity : entities) {
                entity.render();
            }
        }
        catch (ConcurrentModificationException ex) {
            render();
        }
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void clear() {
        entities.clear();
    }
}
