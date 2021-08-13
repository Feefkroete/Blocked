package de.matthi.blocked.entity.creature;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;

import java.awt.*;

public class Pig extends Creature
{

    public Pig(double posx, double posy, int width, int heigth, int hp)
    {
        super(posx, posy, width, heigth, hp);
    }
    public Pig(double posx, double posy, int hp)
    {
        super(posx, posy, 60, 60, hp);
    }

    @Override
    public void render(Graphics graphics)
    {
        graphics.drawImage(Assets.pig, (int)(posx - Game.poffx), (int)(posy - Game.poffy), width, heigth, null);
    }

    @Override
    public void tick() {
    }

    @Override
    public void move(double x, double y) {

    }

    @Override
    public void teleport(double posx, double posy) {

    }
}
