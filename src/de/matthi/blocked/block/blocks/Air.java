package de.matthi.blocked.block.blocks;

import de.matthi.blocked.block.Block;

import java.awt.*;

public class Air extends Block {

    public Air(int id)
    {
        super(null, id);
    }

    @Override
    public void render(Graphics graphics, int posx, int posy)
    {

    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isWallBlock() {
        return false;
    }

    @Override
    public String getName() {
        return "Air";
    }
}
