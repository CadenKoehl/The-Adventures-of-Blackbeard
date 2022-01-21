package com.cadenkoehl.blackbeard.entity.enemy.boss;

import com.cadenkoehl.blackbeard.entity.enemy.SpreadShipEntity;
import com.cadenkoehl.blackbeard.game.GameClient;
import com.cadenkoehl.blackbeard.physics.Direction;

public class BossEntity extends SpreadShipEntity {

    @Override
    public void postSpawn() {
        GameClient.getInstance().bossBar = new BossBar(this);
    }

    @Override
    public void kill() {
        super.kill();
        GameClient.getInstance().bossBar = null;
    }

    @Override
    public int getStartHealth() {
        return 50;
    }
}