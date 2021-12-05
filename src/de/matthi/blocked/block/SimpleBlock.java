package de.matthi.blocked.block;

import de.matthi.blocked.gfx.Assets;

import java.awt.*;

public class SimpleBlock extends Block {

    private final String id;
    private final boolean isSolid;
    private final boolean isWallBlock;

    public SimpleBlock(String id, String texture, boolean isSolid, boolean isWallBlock, boolean hasWallBlock) {
        super(Assets.loadBlockTexture(texture), id, isWallBlock);
        this.id = id;
        this.isSolid = isSolid;
        this.isWallBlock = isWallBlock;
        if (hasWallBlock) {
            BlockRegistry.blocks.add(new SimpleBlock(id + "_wall", texture, false, true, false));
        }
    }

    @Override
    public void render(Graphics graphics, int posx, int posy) {
        graphics.drawImage(texture, posx, posy, 60, 60, null);
    }

    @Override
    public boolean isSolid() {
        return isSolid;
    }

    @Override
    public boolean isWallBlock() {
        return isWallBlock;
    }

    @Override
    public String getName() {
        return id;
    }
}
