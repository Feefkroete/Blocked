package de.matthi.blocked.block;

import de.matthi.blocked.item.BlockItem;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block
{
    protected final int id;
    public BlockItem item;
    protected BufferedImage textur;

    public Block(BufferedImage textur, int id)
    {
        this.textur = textur;
        this.id = id;
    }

    public abstract void render(Graphics graphics, int posx, int posy);

    public abstract boolean isSolid();

    public abstract boolean isWallBlock();

    public abstract String getName();
}
