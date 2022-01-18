package com.cadenkoehl.blackbeard.render;

import com.cadenkoehl.blackbeard.game.GameClient;
import com.cadenkoehl.blackbeard.game.window.GameWindow;
import com.cadenkoehl.blackbeard.entity.Entity;

import javax.swing.*;

import java.awt.*;

import static com.cadenkoehl.blackbeard.game.window.GameWindow.GRAPHICS;

public class Renderer {

    public static final Camera CAMERA = new Camera();

    public static void render(Entity entity, int x, int y) {
        ImageIcon icon = entity.getTexture().getIcon();

        if(icon == null) {
            Color oldColor = GRAPHICS.getColor();
            GRAPHICS.setColor(entity.getTexture().getColor());
            GRAPHICS.fillRect(x - CAMERA.offset.x, y - CAMERA.offset.y, entity.getTexture().getWidth(), entity.getTexture().getHeight());
            GRAPHICS.setColor(oldColor);
        }
        else {
            icon.paintIcon(GameWindow.INSTANCE, GRAPHICS, x - CAMERA.offset.x, y - CAMERA.offset.y);
        }
        if(GameClient.getInstance().hitboxes) {
            GRAPHICS.setColor(Color.WHITE);
            GRAPHICS.drawRect(entity.getHitboxX(), entity.getHitboxY(), entity.width, entity.height);
        }
    }

    public static void render(Texture texture, int x, int y) {
        if(texture.getIcon() == null) {
            Color oldColor = GRAPHICS.getColor();
            GRAPHICS.setColor(texture.getColor());
            GRAPHICS.fillRect(x, y, texture.getWidth(), texture.getHeight());
            GRAPHICS.setColor(oldColor);
        }
        else {
            texture.getIcon().paintIcon(GameWindow.INSTANCE, GRAPHICS, x, y);
        }
    }
}
