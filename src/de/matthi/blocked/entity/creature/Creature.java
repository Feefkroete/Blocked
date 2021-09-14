package de.matthi.blocked.entity.creature;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.entity.Entity;
import de.matthi.blocked.main.Game;

import java.awt.image.BufferedImage;

public abstract class Creature extends Entity
{
    protected int health;
    protected int type;
    protected int fallSpeed = 0;

    public Creature(double posx, double posy, int width, int heigth, int hp, int type, BufferedImage textur)
    {
        super(posx, posy, width, heigth, textur);
        health = hp;
        this.type=type;
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



    /*******GETTERS*******/

    public int getType() {
        return type;
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
