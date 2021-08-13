package de.matthi.blocked.item.items;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.Item;

import java.awt.*;

public class GlassItem extends Item {

    public GlassItem(int id) {
        super(Assets.glass, id);
    }

    @Override
    public void render(Graphics graphics, int posx, int posy, int width, int height) {
        graphics.drawImage(Assets.glass, posx, posy, width, height, null);
    }

    @Override
    public void tick() {

    }

    @Override
    public boolean isWallItem() {
        return false;
    }

    @Override
    public short itemType() {
        return 0;
    }
}
