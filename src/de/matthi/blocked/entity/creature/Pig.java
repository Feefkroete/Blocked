package de.matthi.blocked.entity.creature;

import de.matthi.blocked.gfx.Assets;

public class Pig extends Creature
{
    public Pig(double posx, double posy, int scale, int hp)
    {
        super(posx, posy, 60*scale, 36*scale, hp, 0, 3, Assets.pig, Assets.pig2);
    }
    public Pig(double posx, double posy, int hp)
    {
        super(posx, posy, 60, 36, hp, 0, 1, Assets.pig, Assets.pig2);
    }
}
