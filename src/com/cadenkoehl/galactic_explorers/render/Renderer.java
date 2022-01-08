package com.cadenkoehl.galactic_explorers.render;

import com.cadenkoehl.galactic_explorers.client.window.GameWindow;
import com.cadenkoehl.galactic_explorers.entity.Entity;

import javax.swing.*;

import java.awt.*;

import static com.cadenkoehl.galactic_explorers.client.window.GameWindow.GRAPHICS;

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
