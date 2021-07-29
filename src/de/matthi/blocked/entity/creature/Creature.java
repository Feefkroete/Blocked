package de.matthi.blocked.entity.creature;

import de.matthi.blocked.entity.Entity;

public abstract class Creature extends Entity
{
    protected int health;

    public Creature(double posx, double posy, int width, int heigth, int hp)
    {
        super(posx, posy, width, heigth);
        health = hp;
    }
}
