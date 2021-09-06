package de.matthi.blocked.entity.creature;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.utils.KeyInput;

import java.awt.*;

public class Pig extends Creature
{
    int fallSpeed = 0;

    public Pig(double posx, double posy, int scale, int hp)
    {
        super(posx, posy, 60*scale, 36*scale, hp);
    }
    public Pig(double posx, double posy, int hp)
    {
        super(posx, posy, 60, 36, hp);
    }

    @Override
    public void render(Graphics graphics)
    {
        if (KeyInput.hitBox) {
            graphics.setColor(Color.red);
            graphics.fillRect((int) (posx - Game.poffx), (int) (posy + height - height - Game.poffy), width, height);
        }
        graphics.drawImage(Assets.pig, (int)(posx - Game.poffx), (int)(posy - Game.poffy), width, this.height, null);
    }

    @Override
    public void tick() {
        if (!isCollisionU()) {
            fallSpeed +=1;
            move(0, fallSpeed);
        }
        else {
            fallSpeed = 0;
        }
    }

    @Override
    public void move(double x, double y) {
        if (x<0) {      //Wenn Bewegung nach LINKS
            for (int i = 0; i>x; i--) {
                if (posx-1 > 0) {
                    Block lo = Game.getWorld().getBlock((int) (posx - 1) / 60, (int) (posy) / 60);
                    Block lu = Game.getWorld().getBlock((int) (posx - 1) / 60, (int) (posy + height) / 60);
                    if (!lo.isSolid() && !lu.isSolid()) {
                        posx -= 1;
                    } else {
                        break;
                    }
                }
            }
        }
        else {      //Wenn Bewegung nach RECHTS
            for (int i = 0; i<x; i++) {
                if (posx+width+1 < Game.getWorld().getWidth()*60) {
                    Block ro = Game.getWorld().getBlock((int) (posx + width + 1) / 60, (int) (posy) / 60);
                    Block ru = Game.getWorld().getBlock((int) (posx + width + 1) / 60, (int) (posy + height) / 60);
                    if (!ro.isSolid() && !ru.isSolid()) {
                        posx += 1;
                    } else {
                        break;
                    }
                }
            }
        }
        if (y<0) {      //Wenn Bewegung nach OBEN
            for (int i = 0; i>y; i--) {
                if (posy-1 > 0) {
                    Block lo = Game.getWorld().getBlock((int) (posx) / 60, (int) (posy-1) / 60);
                    Block ro = Game.getWorld().getBlock((int) (posx + width) / 60, (int) (posy-1) / 60);
                    if (!lo.isSolid() && !ro.isSolid()) {
                        posy -= 1;
                    } else {
                        break;
                    }
                }
            }
        }
        else {      //Wenn Bewegung nach UNTEN
            for (int i = 0; i<y; i++) {
                if (posy+1+height < Game.getWorld().getHeight()*60) {
                    Block lu = Game.getWorld().getBlock((int) (posx) / 60, (int) (posy+height+1) / 60);
                    Block ru = Game.getWorld().getBlock((int) (posx + width) / 60, (int) (posy+height+1) / 60);
                    if (!lu.isSolid() && !ru.isSolid()) {
                        posy += 1;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public boolean isCollisionU() {
        if (posy+1+height < Game.getWorld().getHeight()*60) {
            Block lu = Game.getWorld().getBlock((int) (posx) / 60, (int) (posy+height+1) / 60);
            Block ru = Game.getWorld().getBlock((int) (posx + width) / 60, (int) (posy+height+1) / 60);
            return lu.isSolid() || ru.isSolid();
        }
        return true;
    }

    @Override
    public void teleport(double posx, double posy) {

    }
}
