package de.matthi.blocked.block.blocks;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.gfx.Assets;

import java.awt.*;

public class Grass extends Block
{
    public Grass(int id)
    {
        super(Assets.grass, id);
    }

    @Override
    public void render(Graphics graphics, int posx, int posy)
    {
        graphics.drawImage(textur, posx, posy, 60, 60, null);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public boolean isWallBlock() {
        return false;
    }

    @Override
    public String getName() {
        return "Grass";
    }
}
