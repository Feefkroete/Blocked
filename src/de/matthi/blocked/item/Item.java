package de.matthi.blocked.item;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item {
    protected BufferedImage texture;
    protected boolean isBlockItem;

    public Item(BufferedImage texture, boolean isBlockItem) {
        this.texture = texture;
        this.isBlockItem = isBlockItem;
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
