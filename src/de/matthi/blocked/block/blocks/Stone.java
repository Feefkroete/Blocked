package de.matthi.blocked.block.blocks;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.gfx.Assets;

import java.awt.*;

public class Stone extends Block
{

    public Stone(int id)
    {
        super(Assets.stone, id);
    }

    @Override
    public void render(Graphics graphics, int posx, int posy)
    {
        graphics.drawImage(textur, posx, posy, 60, 60, null);
    }

    @Override
    public void tick()
    {

    }
}
