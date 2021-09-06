package de.matthi.blocked.entity;

import java.awt.*;

public abstract class Entity
{
    protected double posx, posy;
    protected int width, height;

    public Entity(double posx, double posy, int width, int height)
    {
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
    }

    public abstract void render(Graphics graphics);

    public abstract void tick();
}
