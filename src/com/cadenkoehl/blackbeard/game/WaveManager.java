package com.cadenkoehl.blackbeard.game;

import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.entity.land.IslandEntity;
import com.cadenkoehl.blackbeard.game.window.GameFrame;
import com.cadenkoehl.blackbeard.physics.Vec2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WaveManager {

    public static final List<List<EntityType<?>>> WAVES = new ArrayList<>();

    private static final List<EntityType<?>> WAVE_1 = new ArrayList<>();
    private static final List<EntityType<?>> WAVE_2 = new ArrayList<>();
    private static final List<EntityType<?>> WAVE_3 = new ArrayList<>();
    private static final List<EntityType<?>> WAVE_4 = new ArrayList<>();
    private static final List<EntityType<?>> WAVE_5 = new ArrayList<>();

    public static void initSpawns() {

        WAVE_1.add(EntityType.SPREAD_SHIP);
        WAVE_1.add(EntityType.SPREAD_SHIP);
        WAVE_1.add(EntityType.SMART_SHIP);
        WAVE_1.add(EntityType.SPREAD_SHIP);

        WAVE_2.add(EntityType.SPREAD_SHIP);
        WAVE_2.add(EntityType.SPREAD_SHIP);
        WAVE_2.add(EntityType.SHARK);
        WAVE_2.add(EntityType.SPREAD_SHIP);
        WAVE_2.add(EntityType.SPREAD_SHIP);

        WAVE_3.add(EntityType.SPREAD_SHIP);
        WAVE_3.add(EntityType.SPREAD_SHIP);
        WAVE_3.add(EntityType.SMART_SHIP);
        WAVE_3.add(EntityType.SPREAD_SHIP);
        WAVE_3.add(EntityType.SPREAD_SHIP);
        WAVE_3.add(EntityType.SPREAD_SHIP);

        WAVE_4.add(EntityType.SHARK);
        WAVE_4.add(EntityType.SPREAD_SHIP);
        WAVE_4.add(EntityType.SPREAD_SHIP);
        WAVE_4.add(EntityType.SPREAD_SHIP);
        WAVE_4.add(EntityType.SPREAD_SHIP);
        WAVE_4.add(EntityType.SMART_SHIP);

        WAVE_5.add(EntityType.SHARK);
        WAVE_5.add(EntityType.SPREAD_SHIP);
        WAVE_5.add(EntityType.SPREAD_SHIP);
        WAVE_5.add(EntityType.SPREAD_SHIP);
        WAVE_5.add(EntityType.SPREAD_SHIP);
        WAVE_5.add(EntityType.SMART_SHIP);


        WAVES.add(WAVE_1);
        WAVES.add(WAVE_2);
        WAVES.add(WAVE_3);
        WAVES.add(WAVE_4);
        WAVES.add(WAVE_5);
    }

    public static void nextWave() {
        GameClient game = GameClient.getInstance();

        if(game.wave > 4) {
            game.stage.spawnEntity(EntityType.ISLAND, new Vec2d(60, -400));
            return;
        }

        game.dayComplete = false;

        List<EntityType<?>> wave = WAVES.get(game.wave);

        if(wave.size() == 0) return;

        int addedWidth = GameFrame.WIDTH / wave.size();

        int i = 0;
        for(EntityType<?> type : wave) {
            int x;
            x = (addedWidth * i) + addedWidth / 3;
            game.stage.spawnEntity(type, new Vec2d(x, -100));
            game.enemyCount++;
            i++;
        }
        game.wave++;
    }
}
