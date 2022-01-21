package com.cadenkoehl.blackbeard.entity.enemy.boss;

import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.entity.projectile.BombEntity;
import com.cadenkoehl.blackbeard.entity.projectile.ProjectileEntity;
import com.cadenkoehl.blackbeard.game.GameClient;
import com.cadenkoehl.blackbeard.physics.Direction;
import com.cadenkoehl.blackbeard.physics.Vec2d;
import com.cadenkoehl.blackbeard.render.Texture;
import com.cadenkoehl.blackbeard.render.Textures;
import com.cadenkoehl.blackbeard.world.Stage;

import java.util.Random;

public class MaynardBossEntity extends BossEntity {

    @Override
    public void launchProjectile(Direction direction, EntityType<? extends ProjectileEntity> entity) {
        if(this.shotCooldown != 0) return;
        Stage stage = GameClient.getInstance().getStage();

        if(new Random().nextInt((this.health / 10) + 1) == 1) {
            BombEntity bomb = stage.spawnEntity(EntityType.BOMB, new Vec2d(this.getCenterX(), this.getCenterY()));
            bomb.setSource(this);
            bomb.setShrapnel(EntityType.BOMB);

            bomb.velocity.y = 2;

            int randomVel = new Random().nextInt(3);

            if(randomVel == 2) {
                bomb.velocity.x = 1;
            }
            if(randomVel == 1) {
                bomb.velocity.x = -1;
            }
        }
        super.launchProjectile(direction, entity);
    }

    @Override
    public void kill() {
        GameClient.getInstance().victory();
        super.kill();
    }

    @Override
    public Texture getTexture() {
        return Textures.ROBERT_MAYNARD;
    }

    @Override
    public int getWidth() {
        return getTexture().getWidth() / 2;
    }

    @Override
    public int getHeight() {
        return getTexture().getHeight() / 2;
    }

    @Override
    public int getStartHealth() {
        return 40 + (GameClient.getInstance().difficulty * 20);
    }
}
