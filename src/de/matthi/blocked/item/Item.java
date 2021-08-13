package de.matthi.blocked.item;

import de.matthi.blocked.item.items.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item
{
    protected final int id;

    public static Item[] items = new Item[10];
    public static Item grass_item = new GrassItem(0);
    public static Item stone_item = new StoneItem(1);
    public static Item trunk_item = new TrunkItem(2);
    public static Item leaves_item = new LeavesItem(3);
    public static Item air_item = new AirItem(4);
    public static Item dirt_item = new DirtItem(5);
    public static Item dirt_wall_item = new DirtWallItem(6);
    public static Item mud_bricks_item = new MudBricksItem(7);
    public static Item mud_bricks_wall_item = new MudBricksWallItem(8);
    public static Item sand_item = new SandItem(9);

    protected BufferedImage textur;

    public Item(BufferedImage textur, int id)
    {
        this.textur = textur;
        this.id = id;

        items[id] = this;
    }

    public abstract void render(Graphics graphics, int posx, int posy, int width, int height);

    public abstract void tick();

    public abstract boolean isWallItem();
}
