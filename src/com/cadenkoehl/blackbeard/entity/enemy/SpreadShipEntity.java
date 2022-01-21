package com.cadenkoehl.blackbeard.entity.enemy;

import com.cadenkoehl.blackbeard.game.GameClient;
import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.entity.projectile.ProjectileEntity;
import com.cadenkoehl.blackbeard.physics.Direction;
import com.cadenkoehl.blackbeard.physics.Vec2d;

//A ship entity with scaling bullet spread
public class SpreadShipEntity extends EnemyShipEntity {

    @Override
    public void launchProjectile(Direction direction, EntityType<? extends ProjectileEntity> entity) {
        if(shotCooldown != 0) return;

        shotCooldown = this.getMaxShotCooldown();
        int speed = this.getBaseShotSpeed();
        if(direction == Direction.DOWN) {
            ProjectileEntity proj1 = GameClient.getInstance().getStage().spawnEntity(EntityType.PROJECTILE, new Vec2d(this.getCenterX(), this.getCenterY()));
            proj1.velocity.x = 1;
            proj1.velocity.y = speed;
            proj1.setSource(this);

            ProjectileEntity proj3 = GameClient.getInstance().getStage().spawnEntity(EntityType.PROJECTILE, new Vec2d(this.getCenterX(), this.getCenterY()));
            proj3.velocity.x = -1;
            proj3.velocity.y = speed;
            proj3.setSource(this);
        }
    }

    @Override
    public int getStartHealth() {
        return 4;
    }
}
