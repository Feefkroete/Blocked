package de.matthi.blocked.block;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.BlockItem;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block
{
    protected final String id;
    public BlockItem item;
    protected BufferedImage texture = new BufferedImage(12, 12, 2);

    public Block(BufferedImage textur, String id, boolean isWallBlock)
    {
        if (isWallBlock) {
            Graphics g = texture.createGraphics();
            g.drawImage(textur, 0, 0, 12, 12, null);
            g.drawImage(Assets.wallBlockOverlay, 0, 0, 12, 12, null);
        }
        else {
            this.texture = textur;
        }
        this.id = id;
    }

    public abstract void render(Graphics graphics, int posx, int posy);

    public abstract boolean isSolid();

    public abstract boolean isWallBlock();

    public abstract String getName();
}
