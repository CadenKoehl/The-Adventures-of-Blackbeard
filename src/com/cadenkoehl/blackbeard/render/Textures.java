package com.cadenkoehl.blackbeard.render;

import com.cadenkoehl.blackbeard.game.GameClient;

public class Textures {

    //Menu
    public static Texture INTRO;
    public static Texture TITLE_SCREEN;
    public static Texture DEATH;
    public static Texture NORMAL;
    public static Texture HARD;
    public static Texture BRUTAL;

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
        INTRO = new Texture("resources/intro.gif", 1000, 1000);
        TITLE_SCREEN = new Texture("resources/title_screen.png", 1000, 1000);
        DEATH = new Texture("resources/death.png", 1000, 1000);
        NORMAL = new Texture("resources/normal.png", 1000, 1000);
        HARD = new Texture("resources/hard.png", 1000, 1000);
        BRUTAL = new Texture("resources/brutal.png", 1000, 1000);
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
