package de.matthi.blocked.entity.creature;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.utils.KeyInput;

import java.awt.*;

public class Pig extends Creature
{
    private int hitboxWidth, hitboxHeight;
    private double collisionO, collisionU, collisionR, collisionL;

    public Pig(double posx, double posy, int width, int height, int hp)
    {
        super(posx, posy, width, height, hp);
        hitboxWidth = width;
        hitboxHeight = (int) (height - (height/2.5));
    }
    public Pig(double posx, double posy, int hp)
    {
        super(posx, posy, 60, 60, hp);
        hitboxWidth = width;
        hitboxHeight = (int) (60 - (60/2.5));
    }

    @Override
    public void render(Graphics graphics)
    {
        if (KeyInput.hitBox) {
            graphics.setColor(Color.red);
            graphics.fillRect((int) (posx - Game.poffx), (int) (posy + (heigth / 2.5) - Game.poffy), hitboxWidth, hitboxHeight);
        }
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

    public int getHitboxWidth() {
        return hitboxWidth;
    }

    public int getHitboxHeight() {
        return hitboxHeight;
    }
}
