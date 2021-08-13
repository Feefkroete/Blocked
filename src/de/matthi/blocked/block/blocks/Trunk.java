package de.matthi.blocked.block.blocks;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.gfx.Assets;

import java.awt.*;

public class Trunk extends Block
{

    public Trunk(int id)
    {
        super(Assets.trunk, id);
    }

    @Override
    public void render(Graphics graphics, int posx, int posy)
    {
        graphics.drawImage(textur, posx, posy, 60, 60, null);
    }

    @Override
    public void tick() {

    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isWallBlock() {
        return false;
    }
}
