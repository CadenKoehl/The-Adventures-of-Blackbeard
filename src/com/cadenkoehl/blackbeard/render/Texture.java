package com.cadenkoehl.blackbeard.render;

import com.cadenkoehl.blackbeard.entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Texture {

    private ImageIcon icon;
    private Color color;
    private File file;
    private String path;
    private final int width;
    private final int height;

    public Texture(String filePath, int width, int height) {
        this.path = filePath;
        this.file = new File(filePath);
        this.icon = this.createIcon(filePath, width, height);
        this.height = icon.getIconHeight();
        this.width = icon.getIconWidth();
    }

    public Texture(Color color, int height, int width) {
        this.color = color;
        this.height = height;
        this.width = width;
    }

    private ImageIcon createIcon(String filePath, int width, int height) {
        ImageIcon icon = new ImageIcon(filePath);

        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ImageIcon getIcon() {
        return this.icon;
    }

    public File getFile() {
        return this.file;
    }

    public String getPath() {
        return this.path;
    }
}
