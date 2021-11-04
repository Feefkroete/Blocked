package de.matthi.blocked.block.blocks;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.gfx.Assets;

import java.awt.*;

public class GlassWall extends Block {

    public GlassWall(int id) {
        super(Assets.glass, id);
    }

    @Override
    public void render(Graphics graphics, int posx, int posy) {
        graphics.drawImage(Assets.glass, posx, posy, 60, 60, null);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isWallBlock() {
        return true;
    }

    @Override
    public String getName() {
        return "GlassWall";
    }
}
