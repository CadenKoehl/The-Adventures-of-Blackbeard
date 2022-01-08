package com.cadenkoehl.galactic_explorers.client.input;

import com.cadenkoehl.galactic_explorers.client.GameClient;
import com.cadenkoehl.galactic_explorers.client.window.GameState;
import com.cadenkoehl.galactic_explorers.client.window.GameWindow;
import com.cadenkoehl.galactic_explorers.entity.player.PlayerEntity;
import com.cadenkoehl.galactic_explorers.physics.Vec2d;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public record Input(GameClient game) {

    private static boolean mousePressed;

    public void onKeyPressed(KeyEvent event) {
        PlayerEntity player = GameClient.getInstance().getPlayer();
        player.velocity.y = -1;

        switch (event.getKeyCode()) {
            case KeyEvent.VK_A -> {
                player.velocity.x = -1;
            }
            case KeyEvent.VK_D -> {
                player.velocity.x = 1;
            }
        }
    }

    public void onKeyReleased(KeyEvent event) {
        if(game.state != GameState.GAME) return;
    }

    public void onMousePressed(MouseEvent event) {
    }

    public void onMouseDragged(MouseEvent event) {
    }

    public void onMouseReleased(MouseEvent event) {
        mousePressed = false;
    }

    public static boolean isMousePressed() {
        return mousePressed;
    }

    public Vec2d getMousePosition() {
        Point pos = GameWindow.INSTANCE.getMousePosition();
        if (pos == null) return null;
        return new Vec2d(pos.x, pos.y);
    }
}
