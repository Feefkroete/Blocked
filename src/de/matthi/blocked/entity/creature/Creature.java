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

    public abstract void move(double x, double y);
    public abstract void teleport(double posx, double posy);

    public int getType() {
        return 0;
    }
    public double getPosX() {
        return posx;
    }
    public double getPosY() {
        return posy;
    }
    public int getHp() {
        return health;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
