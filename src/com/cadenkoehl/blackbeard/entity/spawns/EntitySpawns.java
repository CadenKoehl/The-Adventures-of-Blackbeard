package com.cadenkoehl.blackbeard.entity.spawns;

import com.cadenkoehl.blackbeard.client.GameClient;
import com.cadenkoehl.blackbeard.entity.Entity;
import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.physics.Vec2d;
import com.cadenkoehl.blackbeard.util.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EntitySpawns {

    public static int credits = 0;

    private static final Map<EntityType<? extends Entity>, Integer> entities = new HashMap<>();
    private static Map<EntityType<? extends Entity>, Integer> sortedMap;

    public static void initSpawns() {
        assignValues();
        sortedMap = Util.sortByValue(entities);
        trySpawn();
    }

    public static void trySpawn() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(EntitySpawns::trySpawn, 2, TimeUnit.SECONDS);
        for(Map.Entry<EntityType<? extends Entity>, Integer> entry : sortedMap.entrySet()) {
            for(int i = 1; i < 3; i++) {
                if(!trySpawnEntity(entry.getKey(), entry.getValue(), i)) return;
            }
        }
    }

    private static boolean trySpawnEntity(EntityType<? extends Entity> entity, int cost, int offset) {
        if(cost > credits) {
            credits+=10;
            return false;
        }

        GameClient game = GameClient.getInstance();

        game.getStage().spawnEntity(entity, new Vec2d(game.getPlayer().pos.x + ((offset - 1) * 200), game.getPlayer().pos.y - 2000 + ((offset  * 300))));
        return true;
    }

    private static void assignValues() {
        entities.put(EntityType.ROCK, 10);
    }
}
