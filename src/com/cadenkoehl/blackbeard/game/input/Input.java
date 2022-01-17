package com.cadenkoehl.blackbeard.game.input;

import com.cadenkoehl.blackbeard.game.GameClient;
import com.cadenkoehl.blackbeard.game.GameState;
import com.cadenkoehl.blackbeard.game.window.GameWindow;
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
        if(game.state == GameState.TITLE_SCREEN) {
            switch(event.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    game.init();
                    break;

                case KeyEvent.VK_UP:
                    if(game.difficulty < 3) {
                        game.difficulty++;
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if(game.difficulty > 1) {
                        game.difficulty--;
                    }
            }
            GameWindow.INSTANCE.repaint();
        }
        if(game.state == GameState.TREASURE_BOX) {
            if(event.getKeyCode() == KeyEvent.VK_SPACE) {
                if(game.chestOpen) {
                    game.item.onAcquire(game.player);
                    game.newDay();
                }
                else {
                    game.chestOpen = true;
                    game.getWindow().repaint();
                }
            }
        }
        if(game.state == GameState.DEATH_SCREEN) {
            if(event.getKeyCode() == KeyEvent.VK_SPACE) {
                game.state = GameState.TITLE_SCREEN;
                game.stage.clear();
                GameWindow.INSTANCE.repaint();
            }
        }
        if (game.state != GameState.GAME) return;
        PlayerEntity player = GameClient.getInstance().getPlayer();

        switch (event.getKeyCode()) {
            case KeyEvent.VK_A:
                player.velocity.x = -2;
                break;
            case KeyEvent.VK_D:
                player.velocity.x = 2;
                break;
            case KeyEvent.VK_W:
                player.velocity.y = -2;
                break;
            case KeyEvent.VK_S:
                player.velocity.y = 2;
                break;
            case KeyEvent.VK_UP:
                player.shotDirection = Direction.UP;
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
        if(game.state == GameState.GAME) {
            game.player.shotDirection = Direction.UP;
        }
    }

    public void onMouseDragged(MouseEvent event) {
    }

    public void onMouseReleased(MouseEvent event) {
        mousePressed = false;
        if(game.state == GameState.GAME) {
            game.player.shotDirection = null;
        }
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
