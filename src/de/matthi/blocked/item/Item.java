package de.matthi.blocked.item;

import de.matthi.blocked.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item {
    public BufferedImage texture;
    protected boolean isBlockItem;
    public int maxStackSize;

    public Item(BufferedImage texture, boolean isBlockItem, int maxStackSize) {
        this.texture = texture;
        this.isBlockItem = isBlockItem;
        this.maxStackSize = maxStackSize;
    }

    public void render(Graphics graphics, int posx, int posy, int width, int height) {
        graphics.drawImage(texture, posx, posy, width, height, null);
    }

    public boolean isBlockItem() {
        return isBlockItem;
    }

    public abstract void leftClickAction();
    public abstract void middleClickAction();
    public abstract void rightClickAction();
}
