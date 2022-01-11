package com.cadenkoehl.blackbeard.world;

import com.cadenkoehl.blackbeard.entity.Entity;
import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.physics.Vec2d;

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

    public static List<Entity> getEntities() {
        return entities;
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
