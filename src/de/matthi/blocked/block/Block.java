package de.matthi.blocked.block;

import de.matthi.blocked.block.blocks.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block
{
    protected final int id;

    public static Block[] blocks = new Block[100];
    public static Block grass_block = new Grass(0);
    public static Block stone_block = new Stone(1);
    public static Block trunk_block = new Trunk(2);
    public static Block leaves_block = new Leaves(3);
    public static Block air_block = new Air(4);
    public static Block dirt_block = new Dirt(5);
    public static Block dirt_wall_block = new DirtWall(6);
    public static Block mud_bricks_block = new MudBricks(7);
    public static Block mud_bricks_wall_block = new MudBricksWall(8);
    public static Block sand_block = new Sand(9);
    public static Block glass_block = new Glass(10);
    public static Block glass_wall_block = new GlassWall(11);
    public static Block stone_bricks_block = new StoneBricks(12);
    public static Block stone_bricks_wall_block = new StoneBricksWall(13);

    protected BufferedImage textur;

    public Block(BufferedImage textur, int id)
    {
        this.textur = textur;
        this.id = id;

        blocks[id] = this;
    }

    public abstract void render(Graphics graphics, int posx, int posy);

    public abstract void tick();

    public abstract boolean isSolid();

    public abstract boolean isWallBlock();

    public abstract String getName();
}
