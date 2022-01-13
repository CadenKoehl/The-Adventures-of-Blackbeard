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

public class Input {

    private static boolean mousePressed;
    private final GameClient game = GameClient.getInstance();

    public void onKeyPressed(KeyEvent event) {
        if (game.state != GameState.GAME) return;
        PlayerEntity player = GameClient.getInstance().getPlayer();

        switch (event.getKeyCode()) {
            case KeyEvent.VK_A:
                player.velocity.x = -3;
                break;
            case KeyEvent.VK_D:
                player.velocity.x = 3;
                break;
            case KeyEvent.VK_W:
                player.velocity.y = -3;
                break;
            case KeyEvent.VK_S:
                player.velocity.y = 3;
                break;
            case KeyEvent.VK_UP:
                player.shotDirection = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                player.shotDirection = Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                player.shotDirection = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                player.shotDirection = Direction.RIGHT;
                break;
        }
    }

    public void onKeyReleased(KeyEvent event) {
        if (game.state != GameState.GAME) return;
        PlayerEntity player = GameClient.getInstance().getPlayer();
        switch (event.getKeyCode()) {
            case KeyEvent.VK_A:
                if (player.velocity.x < 0) {
                    player.velocity.x = 0;
                }
                break;
            case KeyEvent.VK_D:
                if (player.velocity.x > 0) {
                    player.velocity.x = 0;
                }
                break;
            case KeyEvent.VK_W:
                if (player.velocity.y < 0) {
                    player.velocity.y = 0;
                }
                break;
            case KeyEvent.VK_S:
                if (player.velocity.y > 0) {
                    player.velocity.y = 0;
                }
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                player.shotDirection = null;
                break;
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
