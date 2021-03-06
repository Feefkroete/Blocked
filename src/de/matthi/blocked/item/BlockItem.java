package de.matthi.blocked.item;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.block.BlockRegistry;
import de.matthi.blocked.entity.itemEntity.ItemEntity;
import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.gui.Hotbar;
import de.matthi.blocked.gui.Inventory;
import de.matthi.blocked.gui.Overlay;
import de.matthi.blocked.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BlockItem extends Item {

    private final int blockId;

    public BlockItem(String textureName, boolean isWallItem, boolean hasWallItem, int blockId) {
        super(Assets.loadBlockTexture(textureName), true, 20);
        this.blockId = blockId;
        BlockRegistry.blocks.get(blockId).item = this;
        if (isWallItem) {
            BufferedImage newTexture = new BufferedImage(15, 15, 2);
            Graphics2D graphics = newTexture.createGraphics();
            graphics.drawImage(texture, 0, 0, newTexture.getWidth(), newTexture.getHeight(), null);
            graphics.drawImage(Assets.wallItemOverlay, 0, 0, newTexture.getWidth(), newTexture.getHeight(), null);
            this.texture = newTexture;
        }
        if (hasWallItem) {
            ItemRegistry.items.add(new BlockItem(textureName, true, false, blockId-1));
        }
    }

    @Override
    public void leftClickAction() {
        if (Game.getWorld().setWorldDataAtPosition(Game.getWorld().getSelectedBlockX(), Game.getWorld().getSelectedBlockY(), blockId)) {
            Inventory.consumeItem(Hotbar.selectedSlot);
        }
    }

    @Override
    public void middleClickAction() {
        Overlay.selectedItem = Game.getWorld().getSelectedBlock().item;
    }

    @Override
    public void rightClickAction() {
        World world = Game.getWorld();
        Block selB = world.getSelectedBlock();
        if (selB != BlockRegistry.blocks.get(0)) {
            world.setWorldDataAtPosition(world.getSelectedBlockX(), world.getSelectedBlockY(), 0);
            world.getItemData().add(new ItemEntity(world.getSelectedBlockX() * 60 + 20 + Math.random()*20, world.getSelectedBlockY() * 60 + 30, selB.item));
        }
    }
}
