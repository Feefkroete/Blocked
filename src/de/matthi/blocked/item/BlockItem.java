package de.matthi.blocked.item;

import de.matthi.blocked.block.BlockRegistry;
import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.main.Overlay;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BlockItem extends Item{

    private final int blockId;

    public BlockItem(BufferedImage texture, boolean isWallItem, int blockId) {
        super(texture, true);
        this.blockId = blockId;
        BlockRegistry.blocks.get(blockId).item = this;
        if (isWallItem) {
            BufferedImage newTexture = new BufferedImage(15, 15, 2);
            Graphics2D graphics = newTexture.createGraphics();
            graphics.drawImage(texture, 0, 0, texture.getWidth(), texture.getHeight(), null);
            graphics.drawImage(Assets.wallItemOverlay, 0, 0, texture.getWidth(), texture.getHeight(), null);
            this.texture = newTexture;
        }
    }

    @Override
    public void leftClickAction() {
        Game.getWorld().setWorldDataAtPosition(Game.getWorld().getSelectedBlockX(), Game.getWorld().getSelectedBlockY(), blockId);
    }

    @Override
    public void middleClickAction() {
        Overlay.selectedItem = Game.getWorld().getSelectedBlock().item;
    }

    @Override
    public void rightClickAction() {
        Game.getWorld().setWorldDataAtPosition(Game.getWorld().getSelectedBlockX(), Game.getWorld().getSelectedBlockY(), 0);
    }
}
