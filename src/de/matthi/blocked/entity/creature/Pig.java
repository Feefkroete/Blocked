package de.matthi.blocked.entity.creature;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pig extends Creature
{
    BufferedImage textur;

    public Pig(double posx, double posy, int width, int heigth, int hp, BufferedImage textur)
    {
        super(posx, posy, width, heigth, hp);
        this.textur = textur;
    }

    @Override
    public void render(Graphics graphics)
    {
        graphics.drawImage(textur, (int)posx, (int)posy, width, heigth, null);
    }

    @Override
    public void tick() {

    }
}
