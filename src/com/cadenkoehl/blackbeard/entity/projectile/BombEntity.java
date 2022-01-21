package com.cadenkoehl.blackbeard.entity.projectile;

import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.game.GameClient;
import com.cadenkoehl.blackbeard.physics.Vec2d;
import com.cadenkoehl.blackbeard.render.Texture;
import com.cadenkoehl.blackbeard.render.Textures;
import com.cadenkoehl.blackbeard.world.Stage;

import java.util.Random;

public class BombEntity extends ProjectileEntity {

    private long fuse;
    private EntityType<? extends ProjectileEntity> shrapnel;

    public BombEntity() {
        this.shrapnel = EntityType.PROJECTILE;
        this.fuse = 200;
    }

    @Override
    public void tick() {
        super.tick();

        if(fuse != 0) {
            fuse--;
            return;
        }

        this.explode();
    }

    public void explode() {
        Stage stage = GameClient.getInstance().getStage();

        int speed = 3;

        ProjectileEntity proj1 = newProj();
        ProjectileEntity proj2 = newProj();
        ProjectileEntity proj3 = newProj();
        ProjectileEntity proj4 = newProj();
        ProjectileEntity proj5 = newProj();
        ProjectileEntity proj6 = newProj();
        ProjectileEntity proj7 = newProj();
        ProjectileEntity proj8 = newProj();

        proj1.velocity.x = speed;

        proj2.velocity.x = -speed;

        proj3.velocity.y = speed;

        proj4.velocity.y = -speed;

        if(new Random().nextInt(2) == 1) {
            speed = 2;
        }

        proj5.velocity.x = speed;
        proj5.velocity.y = -speed;

        proj6.velocity.x = -speed;
        proj6.velocity.y = speed;

        proj7.velocity.x = speed;
        proj7.velocity.y = speed;

        proj8.velocity.x = -speed;
        proj8.velocity.y = -speed;

        proj1.setSource(this.getSource());
        proj2.setSource(this.getSource());
        proj3.setSource(this.getSource());
        proj4.setSource(this.getSource());
        proj5.setSource(this.getSource());
        proj6.setSource(this.getSource());
        proj7.setSource(this.getSource());
        proj8.setSource(this.getSource());

        this.kill();
    }

    private ProjectileEntity newProj() {
        ProjectileEntity entity = GameClient.getInstance().getStage().spawnEntity(shrapnel, new Vec2d(pos.x, pos.y));
        if(entity instanceof BombEntity) {
            ((BombEntity) entity).setFuse(100);
        }
        return entity;
    }

    public void setFuse(long fuse) {
        this.fuse = fuse;
    }

    public void setShrapnel(EntityType<? extends ProjectileEntity> shrapnel) {
        this.shrapnel = shrapnel;
    }

    @Override
    public Texture getTexture() {
        return Textures.BOMB;
    }
}
