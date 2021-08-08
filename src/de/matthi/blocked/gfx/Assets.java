package de.matthi.blocked.gfx;

import java.awt.image.BufferedImage;

public class Assets
{
    private static final int WIDTH = 15;
    private static final int HEIGHT = 15;

    public static BufferedImage spieler1, spieler2, spieler3, spieler4, grass, trunk, leaves, stone, pig, select, inv, flight, air, dirt, mud_bricks, sand, worldBackground, menuBackground, button1, button2, button3, button4, button5, button6, button7, button8, wallOverlay;

    public static void init()
    {
        SpriteSheet background = new SpriteSheet(Texture.load("/textures/sprite_background.png"));
        SpriteSheet button = new SpriteSheet(Texture.load("/textures/sprite_button.png"));
        SpriteSheet block = new SpriteSheet(Texture.load("/textures/sprite_block.png"));
        SpriteSheet entity = new SpriteSheet(Texture.load("/textures/sprite_entity.png"));
        SpriteSheet item = new SpriteSheet(Texture.load("/textures/sprite_item.png"));
        SpriteSheet player = new SpriteSheet(Texture.load("/textures/sprite_player.png"));
        SpriteSheet ui = new SpriteSheet(Texture.load("/textures/sprite_ui.png"));

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

        pig = entity.pacman(1, 1, WIDTH, HEIGHT);

        select = ui.pacman(1, 1, WIDTH, HEIGHT);
        inv = ui.pacman(WIDTH + 2, 1, WIDTH, HEIGHT);
        flight = ui.pacman(WIDTH*2+3, 1, WIDTH, HEIGHT);

        air = item.pacman(1, 1, WIDTH, HEIGHT);
        wallOverlay = item.pacman(WIDTH + 2, 1, WIDTH, HEIGHT);
    }
}
