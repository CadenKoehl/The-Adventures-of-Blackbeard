package com.cadenkoehl.blackbeard.entity;

import com.cadenkoehl.blackbeard.entity.enemy.EnemyShipEntity;
import com.cadenkoehl.blackbeard.entity.enemy.SharkEntity;
import com.cadenkoehl.blackbeard.entity.enemy.SmartShipEntity;
import com.cadenkoehl.blackbeard.entity.enemy.SpreadShipEntity;
import com.cadenkoehl.blackbeard.entity.enemy.boss.BossEntity;
import com.cadenkoehl.blackbeard.entity.enemy.boss.MaynardBossEntity;
import com.cadenkoehl.blackbeard.entity.land.IslandEntity;
import com.cadenkoehl.blackbeard.entity.player.PlayerEntity;
import com.cadenkoehl.blackbeard.entity.projectile.BombEntity;
import com.cadenkoehl.blackbeard.entity.projectile.ProjectileEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityType<E extends Entity> {

    public static final Map<String, EntityType<?>> registries = new HashMap<>();

    public static final EntityType<PlayerEntity> PLAYER = register(new EntityType<>("Player", PlayerEntity::new));
    public static final EntityType<ProjectileEntity> PROJECTILE = register(new EntityType<>("Projectile", ProjectileEntity::new));
    public static final EntityType<SpreadShipEntity> SPREAD_SHIP = register(new EntityType<>("Spread Ship", SpreadShipEntity::new));
    public static final EntityType<SmartShipEntity> SMART_SHIP = register(new EntityType<>("Smart Ship", SmartShipEntity::new));
    public static final EntityType<SharkEntity> SHARK = register(new EntityType<>("Shark", SharkEntity::new));
    public static final EntityType<MaynardBossEntity> ROBERT_MAYNARD = register(new EntityType<>("Robert Maynard", MaynardBossEntity::new));
    public static final EntityType<BombEntity> BOMB = register(new EntityType<>("", BombEntity::new));
    public static final EntityType<IslandEntity> ISLAND = register(new EntityType<>("Island", IslandEntity::new));

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
