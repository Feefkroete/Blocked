package de.matthi.blocked.item.items;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.Item;

import java.awt.*;

public class MudBricksItem extends Item
{
    public MudBricksItem(int id)
    {
        super(Assets.mud_bricks, id);
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
        return false;
    }
}
