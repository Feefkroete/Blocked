package de.matthi.blocked.entity.creature;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.block.BlockRegistry;
import de.matthi.blocked.entity.Entity;
import de.matthi.blocked.main.Game;

import java.awt.image.BufferedImage;

public abstract class Creature extends Entity
{
    protected int health;
    protected int type;
    protected int speed;
    protected int fallSpeed = 0;
    protected BufferedImage textur1, textur2;

    private int runningStatus;
    private int runningStatusDuration;

    public Creature(double posx, double posy, int width, int heigth, int hp, int type, int speed, BufferedImage textur1, BufferedImage textur2)
    {
        super(posx, posy, width, heigth, textur1);
        health = hp;
        this.speed = speed;
        this.type=type;
        this.textur1 = textur1;
        this.textur2 = textur2;
    }

    @Override
    public void tick() {

        /*++COLLISION DETECTION++*/

        if (!isCollisionU() || fallSpeed<0) {
            fallSpeed +=1;
            move(0, fallSpeed);
        }
        else {
            fallSpeed = 0;
        }

        /*++PATHFINDING SYSTEM++*/

        // Zufallsgenerator
        double r = Math.random();
        if (r<0.2 && runningStatus == 0) {
            if (r<0.02) {
                runningStatus = -1;
                runningStatusDuration = (int) (Math.random()*120 + 30);
                textur = textur2;
            }
            if (r<0.18 && r>0.02) {
                runningStatusDuration = (int) (Math.random()*500 + 350);
            }
            if (r>0.18) {
                runningStatus = 1;
                runningStatusDuration = (int) (Math.random()*120 + 30);
                textur = textur1;
            }
        }

        // Blocküberprüfungen

        if (runningStatusDuration!=0) {
            Block checku1, checku2, checku3, checkv1, checkv2;
            int blockx = (int) (posx + (width / 2) + runningStatus) / 60;

            if ((int) (posy + height + 123) / 60< Game.getWorld().getHeight()) {
                checku1 = Game.getWorld().getBlock(blockx, (int) (posy + height + 3) / 60);
                checku2 = Game.getWorld().getBlock(blockx, (int) (posy + height + 63) / 60);
                checku3 = Game.getWorld().getBlock(blockx, (int) (posy + height + 123) / 60);
            }
            else {
                checku1 = BlockRegistry.blocks.get(3);
                checku2 = BlockRegistry.blocks.get(3);
                checku3 = BlockRegistry.blocks.get(3);
            }
            if ((posx + (width/2D) + (runningStatus*width)/2D) / 60 > 0 && (posx + (width/2D) + (runningStatus*width)/2D) / 60 < Game.getWorld().getWidth() && posy-7>0) {
                checkv1 = Game.getWorld().getBlock((int) (posx + (width/2) + (runningStatus*(width+2))/2) / 60, (int) (posy+7)/60);
                checkv2 = Game.getWorld().getBlock((int) (posx + (width/2) + (runningStatus*(width+2))/2) / 60, (int) (posy-67)/60);
            }
            else {
                checkv1 = BlockRegistry.blocks.get(3);
                checkv2 = BlockRegistry.blocks.get(3);
            }

            if (checkv1.isSolid() && !checkv2.isSolid()) {  //Jump wenn sich vor und vor/über der Creature die Möglichkeit anbietet
                fallSpeed = -10;
            }
            if (checku1.isSolid() || checku2.isSolid() || checku3.isSolid()) {  //Laufen, wenn kein 3+ Block tiefes Loch vor Creature
                move(runningStatus * speed, 0);
            }
            runningStatusDuration--;
        }
        else {
            runningStatus = 0;
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
