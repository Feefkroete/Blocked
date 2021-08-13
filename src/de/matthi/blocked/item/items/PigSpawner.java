package de.matthi.blocked.item.items;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.Item;

import java.awt.*;

public class PigSpawner extends Item {

    public PigSpawner(int id) {
        super(Assets.pigSpawner, id);
    }

    @Override
    public void render(Graphics graphics, int posx, int posy, int width, int height) {
        graphics.drawImage(Assets.pigSpawner, posx, posy, width, height, null);
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
        return 1;
    }
}
