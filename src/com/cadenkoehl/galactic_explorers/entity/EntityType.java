package com.cadenkoehl.galactic_explorers.entity;

import com.cadenkoehl.galactic_explorers.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityType<E extends Entity> {

    public static final Map<String, EntityType<?>> registries = new HashMap<>();

    public static final EntityType<PlayerEntity> PLAYER = register(new EntityType<>("Player", PlayerEntity::new));
    public static final EntityType<RockEntity> ROCK = register(new EntityType<>("Asteroid", RockEntity::new));

    public static List<EntityType<?>> values() {
        return new ArrayList<>(registries.values());
    }

    public static EntityType<? extends Entity> valueOf(String entityName) {
        return registries.get(entityName);
    }

    private static <T extends Entity> EntityType<T> register(EntityType<T> entity) {
        registries.put(entity.getName(), entity);
        return entity;
    }

    private final Builder<E> builder;
    private final String displayName;
    private final String name;

    public EntityType(String displayName, Builder<E> builder) {
        this.builder = builder;
        this.displayName = displayName;
        this.name = displayName.replace(" ", "_").toLowerCase();
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getName() {
        return name;
    }

    public E createEntity() {
        return builder.build();
    }

    public interface Builder<E extends Entity> {
        E build();
    }
}
