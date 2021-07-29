package de.matthi.blocked.item.items;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.Item;

import java.awt.*;

public class GrassItem extends Item
{

    public GrassItem(int id)
    {
        super(Assets.grass, id);
    }

    @Override
    public void render(Graphics graphics, int posx, int posy, int width, int height)
    {
        graphics.drawImage(textur, posx, posy, width, height, null);
    }

    @Override
    public void tick() {

    }
}