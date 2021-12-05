package de.matthi.blocked.entity;

import de.matthi.blocked.main.Game;
import de.matthi.blocked.utils.KeyInput;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity
{
    protected double posx, posy;
    protected int width, height;
    protected BufferedImage textur;

    public Entity(double posx, double posy, int width, int height, BufferedImage textur)
    {
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
        this.textur = textur;
    }

    public void render(Graphics graphics) {
        if (KeyInput.hitBox) {
            graphics.setColor(Color.red);
            graphics.fillRect((int) (posx - Game.poffx), (int) (posy + height - height - Game.poffy), width, height);
        }
        graphics.drawImage(textur, (int)(posx - Game.poffx), (int)(posy - Game.poffy), width, this.height, null);
    }

    public abstract void tick();

    public double getPosX() {
        return posx;
    }
    public double getPosY() {
        return posy;
    }
}
