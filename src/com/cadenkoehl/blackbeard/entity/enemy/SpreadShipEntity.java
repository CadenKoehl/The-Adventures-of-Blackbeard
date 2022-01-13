package com.cadenkoehl.blackbeard.entity.enemy;

import com.cadenkoehl.blackbeard.client.GameClient;
import com.cadenkoehl.blackbeard.entity.EntityType;
import com.cadenkoehl.blackbeard.entity.projectile.ProjectileEntity;
import com.cadenkoehl.blackbeard.physics.Direction;

//A ship entity with scaling bullet spread
public class SpreadShipEntity extends EnemyShipEntity {

    @Override
    public void launchProjectile(Direction direction) {
        if(shotCooldown != 0) return;

        shotCooldown = this.getMaxShotCooldown();
        if(direction == Direction.DOWN) {
            ProjectileEntity proj1 = GameClient.getInstance().getStage().spawnEntity(EntityType.PROJECTILE, pos);
            proj1.velocity.x = 1;
            proj1.velocity.y = 6;
            proj1.setSource(this);

            ProjectileEntity proj3 = GameClient.getInstance().getStage().spawnEntity(EntityType.PROJECTILE, pos);
            proj3.velocity.x = -1;
            proj3.velocity.y = 6;
            proj3.setSource(this);
        }
    }
}
