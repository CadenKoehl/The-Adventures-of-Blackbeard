package com.cadenkoehl.blackbeard.client.input;

import com.cadenkoehl.blackbeard.client.GameClient;
import com.cadenkoehl.blackbeard.client.window.GameState;
import com.cadenkoehl.blackbeard.client.window.GameWindow;
import com.cadenkoehl.blackbeard.entity.player.PlayerEntity;
import com.cadenkoehl.blackbeard.physics.Direction;
import com.cadenkoehl.blackbeard.physics.Vec2d;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public record Input(GameClient game) {

    private static boolean mousePressed;

    public void onKeyPressed(KeyEvent event) {
        if(game.state != GameState.GAME) return;
        PlayerEntity player = GameClient.getInstance().getPlayer();

        switch (event.getKeyCode()) {
            case KeyEvent.VK_A -> {
                player.velocity.x = -3;
            }
            case KeyEvent.VK_D -> {
                player.velocity.x = 3;
            }
            case KeyEvent.VK_W -> {
                player.velocity.y = -3;
            }
            case KeyEvent.VK_S -> {
                player.velocity.y = 3;
            }
            case KeyEvent.VK_UP -> {
                player.shotDirection = Direction.UP;
            }
            case KeyEvent.VK_DOWN -> {
                player.shotDirection = Direction.DOWN;
            }
            case KeyEvent.VK_LEFT -> {
                player.shotDirection = Direction.LEFT;
            }
            case KeyEvent.VK_RIGHT -> {
                player.shotDirection = Direction.RIGHT;
            }
        }
    }

    public void onKeyReleased(KeyEvent event) {
        if(game.state != GameState.GAME) return;
        PlayerEntity player = GameClient.getInstance().getPlayer();
        switch (event.getKeyCode()) {
            case KeyEvent.VK_A -> {
                if(player.velocity.x < 0) {
                    player.velocity.x = 0;
                }
            }
            case KeyEvent.VK_D -> {
                if(player.velocity.x > 0) {
                    player.velocity.x = 0;
                }
            }
            case KeyEvent.VK_W -> {
                if(player.velocity.y < 0) {
                    player.velocity.y = 0;
                }
            }
            case KeyEvent.VK_S -> {
                if(player.velocity.y > 0) {
                    player.velocity.y = 0;
                }
            }
            case KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> {
                player.shotDirection = null;
            }
        }
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
