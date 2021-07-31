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
        graphics.drawImage(null, posx, posy, 60, 60, null);
    }

    @Override
    public void tick()
    {

    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
