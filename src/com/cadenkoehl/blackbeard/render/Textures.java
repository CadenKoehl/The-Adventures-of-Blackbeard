package com.cadenkoehl.blackbeard.render;

public class Textures {

    public static Texture MENU_BACKGROUND;
    public static Texture OCEAN_BACKGROUND;
    public static Texture PLAYER;
    public static Texture ENEMY_SHIP;
    public static Texture ROCK;

    public static void initTextures() {
        MENU_BACKGROUND = new Texture("resources/menu_background.jpg", 1000,1000);
        OCEAN_BACKGROUND = new Texture("resources/ocean_background.png", 1000, 1000);
        PLAYER = new Texture("resources/player_ship.png", 50, 75);
        ENEMY_SHIP = new Texture("resources/enemy_ship.png", 50, 75);
        ROCK = new Texture("resources/rock.png", 20,20);
    }
}
