package de.matthi.blocked.item.items;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.Item;

import java.awt.*;

public class StoneBricksWallItem extends Item
{
    public StoneBricksWallItem(int id)
    {
        super(Assets.stone_bricks, id);
    }

    @Override
    public void render(Graphics graphics, int posx, int posy, int width, int height) {
        graphics.drawImage(textur, posx, posy, width, height, null);
    }

    @Override
    public void tick() {

    }

    @Override
    public boolean isWallItem() {
        return true;
    }

    @Override
    public short itemType() {
        return 0;
    }
}
