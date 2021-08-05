package de.matthi.blocked.main;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.Item;
import de.matthi.blocked.utils.KeyInput;

import java.awt.*;

public class Overlay
{
    public static int pos = 0;


    public Overlay()
    {

    }

    public static void up()
    {
        if(pos < Item.items.length-1)
        {
            pos++;
        }
        else
        {
            pos = 0;
        }
    }
    public static void down()
    {
        if (pos > 0)
        {
            pos--;
        }
        else
        {
            pos = Item.items.length-1;
        }
    }

    public void render(Graphics graphics)
    {
        if (KeyInput.fly) {
            graphics.drawImage(Assets.flight, Game.WIDTH - 70, -7, 65, 65, null);
        }
        graphics.drawImage(Assets.inv, ((Game.WIDTH)/2)-32, (Game.HEIGHT)-80, 56, 56, null);
        Item.items[pos].render(graphics, ((Game.WIDTH)/2)-27, (Game.HEIGHT)-75, 46, 46);
        if (Item.items[pos].isWallItem()) {
            graphics.drawImage(Assets.wallOverlay, ((Game.WIDTH)/2)-27, (Game.HEIGHT)-75, 46, 46, null);
        }
    }
}
