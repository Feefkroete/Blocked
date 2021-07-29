package de.matthi.blocked.gfx;

import java.awt.image.BufferedImage;

public class Assets
{
    private static final int WIDTH = 15;
    private static final int HEIGHT = 15;

    public static BufferedImage spieler1, spieler2, spieler3, spieler4, grass, trunk, leaves, stone, pig, select, inv, air, dirt, worldBackground, menuBackground, button1, button2, button3, button4, button5, button6, button7, button8;

    public static void init()
    {
        SpriteSheet sheet = new SpriteSheet(Texture.load("/textures/sprite.png"));
        SpriteSheet background = new SpriteSheet(Texture.load("/textures/sprite_background.png"));
        SpriteSheet button = new SpriteSheet(Texture.load("/textures/sprite_button.png"));

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

        spieler1 = sheet.pacman(1,1,WIDTH, HEIGHT);
        spieler2 = sheet.pacman(WIDTH + 2, 1, WIDTH, HEIGHT);
        spieler3 = sheet.pacman(WIDTH*2 + 3, 1, WIDTH, HEIGHT);
        spieler4 = sheet.pacman(WIDTH*3 + 4, 1, WIDTH, HEIGHT);
        grass = sheet.pacman(1, HEIGHT+2, WIDTH, HEIGHT);
        trunk = sheet.pacman(WIDTH+2, HEIGHT+2, WIDTH, HEIGHT);
        leaves = sheet.pacman(WIDTH*2+3, HEIGHT+2, WIDTH, HEIGHT);
        stone = sheet.pacman(WIDTH*3+4, HEIGHT+2, WIDTH, HEIGHT);
        pig = sheet.pacman(1, HEIGHT*2+3, WIDTH, HEIGHT);
        select = sheet.pacman(WIDTH + 2, HEIGHT*2+3, WIDTH, HEIGHT);
        inv = sheet.pacman(WIDTH*2+3, HEIGHT*2+3, WIDTH, HEIGHT);
        air = sheet.pacman(WIDTH*3+4, HEIGHT*2+3, WIDTH, HEIGHT);
        dirt = sheet.pacman(1, HEIGHT*3+4, WIDTH, HEIGHT);
    }
}
