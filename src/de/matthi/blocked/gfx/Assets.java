package de.matthi.blocked.gfx;

import de.matthi.blocked.main.Hotbar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets
{
    private static final int WIDTH = 15;
    private static final int HEIGHT = 15;

    public static BufferedImage
        spieler1, spieler2, spieler3, spieler4,
        grass, trunk, leaves, stone, glass, stone_bricks, air, dirt, mud_bricks, sand,
        pig, pig2,
        pigSpawner, wallItemOverlay, entityRemover, testFood, testDrink,
        worldBackground, menuBackground, wallBlockOverlay,
        button1, button2, button3, button4, button5, button6, button7, button8,
        select, inv, selInv, flight, heart, heart_inactive, food, foodEmpty, water, waterEmpty,
        sliderBox, sliderHandleOff, sliderHandleOn, sliderName,
        inventory, inventorySelectBox, hotbar,
        gameCursorImage;

    public static void init()
    {
        SpriteSheet background = new SpriteSheet(Texture.load("/textures/sprite_background.png"));
        SpriteSheet button = new SpriteSheet(Texture.load("/textures/sprite_button.png"));
        SpriteSheet slider = new SpriteSheet(Texture.load("/textures/sprite_slider.png"));
        SpriteSheet block = new SpriteSheet(Texture.load("/textures/sprite_block.png"));
        SpriteSheet entity = new SpriteSheet(Texture.load("/textures/sprite_entity.png"));
        SpriteSheet item = new SpriteSheet(Texture.load("/textures/sprite_item.png"));
        SpriteSheet player = new SpriteSheet(Texture.load("/textures/sprite_player.png"));
        SpriteSheet ui = new SpriteSheet(Texture.load("/textures/sprite_ui.png"));
        gameCursorImage = Texture.load("/textures/game_cursor.png");


        worldBackground = background.pacman(0,0,512, 512);
        menuBackground = background.pacman(513, 0, 511, 512);

        button1 = button.pacman(1, 9, 31, 15);
        button2 = button.pacman(1, 41, 31, 15);
        button3 = button.pacman(33, 9, 31, 15);
        button4 = button.pacman(33, 41, 31, 15);
        button5 = button.pacman(65, 9, 31, 15);
        button6 = button.pacman(65, 41, 31, 15);
        button7 = button.pacman(97, 9, 31, 15);
        button8 = button.pacman(97, 41, 31, 15);

        sliderBox = slider.pacman(1,7,31, 17);
        sliderHandleOff = slider.pacman(16, 1, 3, 5);
        sliderHandleOn = slider.pacman(20, 1, 3, 5);
        sliderName = slider.pacman(33, 1, 31, 7);

        spieler1 = player.pacman(1,1,WIDTH, HEIGHT);
        spieler2 = player.pacman(WIDTH + 2, 1, WIDTH, HEIGHT);
        spieler3 = player.pacman(WIDTH*2 + 3, 1, WIDTH, HEIGHT);
        spieler4 = player.pacman(WIDTH*3 + 4, 1, WIDTH, HEIGHT);

        grass = block.pacman(1, 1, WIDTH, HEIGHT);
        trunk = block.pacman(WIDTH+2, 1, WIDTH, HEIGHT);
        leaves = block.pacman(WIDTH*2+3, 1, WIDTH, HEIGHT);
        stone = block.pacman(WIDTH*3+4, 1, WIDTH, HEIGHT);
        dirt = block.pacman(WIDTH*4+5, 1, WIDTH, HEIGHT);
        mud_bricks = block.pacman(WIDTH*5+6, 1, WIDTH, HEIGHT);
        sand = block.pacman(WIDTH*6+7, 1, WIDTH, HEIGHT);
        glass = block.pacman(1, HEIGHT+2, WIDTH, HEIGHT);
        stone_bricks = block.pacman(WIDTH+2, HEIGHT+2, WIDTH, HEIGHT);

        wallBlockOverlay = block.pacman(WIDTH*7+8, 1, WIDTH, HEIGHT);

        pig = entity.pacman(1, 7, WIDTH, 9);
        pig2 = entity.pacman(WIDTH+2, 7, WIDTH, 9);

        select = ui.pacman(1, 1, WIDTH, HEIGHT);
        inv = ui.pacman(WIDTH + 2, 1, WIDTH, HEIGHT);
        flight = ui.pacman(WIDTH*2+3, 1, WIDTH, HEIGHT);
        selInv = ui.pacman(1, HEIGHT + 2, WIDTH, HEIGHT);
        heart = ui.pacman(53, 5, 7, 7);
        heart_inactive = ui.pacman(53, 21, 7, 7);
        water = ui.pacman(20, 20, 9, 9);
        waterEmpty = ui.pacman(36, 20, 9, 9);
        food = ui.pacman(20, 36, 9, 9);
        foodEmpty = ui.pacman(36, 36, 9, 9);

        air = item.pacman(1, 1, WIDTH, HEIGHT);
        wallItemOverlay = item.pacman(WIDTH + 2, 1, WIDTH, HEIGHT);
        pigSpawner = item.pacman(WIDTH*2+3, 1, WIDTH, HEIGHT);
        entityRemover = item.pacman(WIDTH*3+4, 1, WIDTH, HEIGHT);
        testFood = item.pacman(WIDTH*4+5, 1, WIDTH, HEIGHT);
        testDrink = item.pacman(WIDTH*5+6, 1, WIDTH, HEIGHT);

        inventory = Texture.load("/textures/inventory.png");
        inventorySelectBox = ui.pacman(1, 33, 15, 15);
        hotbar = Texture.load("/textures/hotbar.png");
    }
}
