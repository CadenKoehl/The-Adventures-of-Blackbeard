package com.cadenkoehl.blackbeard.entity.spawns;

import com.cadenkoehl.blackbeard.client.GameClient;
import com.cadenkoehl.blackbeard.entity.Entity;
import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.entity.player.PlayerEntity;
import com.cadenkoehl.blackbeard.physics.Vec2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntitySpawns {

    public static final List<EntityType<?>> commonSpawns = new ArrayList<>();

    static {
        commonSpawns.add(EntityType.ENEMY_SHIP);
    }

    public static void spawnEnemies() {
        GameClient game = GameClient.getInstance();
        game.stage.spawnEntity(randomEntityType(), new Vec2d(game.player.pos.x, -100));
    }

    private static EntityType<?> randomEntityType() {
        Random random = new Random();
        int type = random.nextInt(commonSpawns.size());
        return commonSpawns.get(type);
    }
}
