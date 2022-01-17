package com.cadenkoehl.blackbeard.render;

public class Textures {

    public static Texture MENU_BACKGROUND;
    public static Texture SCROLL;
    public static Texture OCEAN_BACKGROUND;
    public static Texture PLAYER;
    public static Texture ENEMY_SHIP;
    public static Texture ROCK;
    public static Texture HP_HEART;
    public static Texture ITEM_HEART;
    public static Texture SHARK;
    public static Texture ISLAND;
    public static Texture CHEST_OPEN;
    public static Texture CHEST_CLOSED;

    public static void initTextures() {
        System.out.println("Initializing textures...");
        MENU_BACKGROUND = new Texture("resources/background.png", 1000,1000);
        SCROLL = new Texture("resources/scroll.png", 1000,1000);
        OCEAN_BACKGROUND = new Texture("resources/ocean_background.png", 1000, 1000);
        PLAYER = new Texture("resources/player_ship.png", 50, 75);
        ENEMY_SHIP = new Texture("resources/enemy_ship.png", 50, 75);
        ROCK = new Texture("resources/rock.png", 20,20);
        HP_HEART = new Texture("resources/heart.png", 50, 50);
        ITEM_HEART = new Texture("resources/heart.png", 300, 300);
        SHARK = new Texture("resources/shark.png", 75, 50);
        ISLAND = new Texture("resources/island.png", 800, 400);
        CHEST_OPEN = new Texture("resources/chest_open.png", 1000, 1000);
        CHEST_CLOSED = new Texture("resources/chest_closed.png", 1000, 1000);
    }
}
