package de.matthi.blocked.entity.creature;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.utils.KeyInput;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{

    BufferedImage textur;
    int hitboxWidth = 48, hitboxHeight = 52;    //Hitboxen - WOW!
    int hp;
    boolean collisionU, collisionO, collisionL, collisionR;
    int fallSpeed = 0;

    public Player(double posx, double posy, int width, int heigth, int hp, BufferedImage textur) {
        super(posx, posy, width, heigth, hp);
        this.textur = textur;
        this.hp = hp;
        Game.poffx = posx + (Game.getFenster().getWidth()/2.0);
        Game.poffy = posy + (Game.getFenster().getHeight()/2.0);
    }

    @Override
    public void move(double x, double y)
    {
        this.posx += x;
        this.posy += y;
        Game.poffx = this.posx - (Game.getFenster().getWidth()/2.0);
        Game.poffy = this.posy - (Game.getFenster().getHeight()/2.0);
    }

    public void teleport(double posx, double posy) {
        this.posx = posx;
        this.posy = posy;
        Game.poffx = this.posx - (Game.getFenster().getWidth()/2.0);
        Game.poffy = this.posy - (Game.getFenster().getHeight()/2.0);
    }

    @Override
    public void render(Graphics graphics) {
        if (KeyInput.hitBox) {                  //Hitbox nur auf Tastendruck togglen
            graphics.setColor(Color.red);
            graphics.fillRect(Game.getFenster().getWidth()/2 - 26, Game.getFenster().getHeight()/2 -22, hitboxWidth, hitboxHeight);
        }
        graphics.drawImage(textur, Game.getFenster().getWidth()/2 - 30, Game.getFenster().getHeight()/2 -30, width, height, null);
        //Item.items[Overlay.selectedBlock].render(graphics, Game.getFenster().getWidth()/2 + 12, Game.getFenster().getHeight()/2 -24, 15, 15);
    }

    @Override
    public void tick() {
        int speed = 5;
        if (KeyInput.sprint) {
            speed *= 2;             //Wenn sprint -> 2-fache Geschwindigkeit
        }

        if (!KeyInput.fly) {        //Wenn Flugmodus inaktiv
            if (isCollisionU()) {   //Wenn spieler auf einem Block steht
                if (KeyInput.up) {
                    fallSpeed = -11;    //Quasi nach oben fliegen... ja halt negative Fallgeschwindigkeit = nach-oben-flieg-geschwindigkeit
                }
                else {
                    fallSpeed = 0;      //Hier kann später dann auch falldamage rein
                }
            }
            if (!isCollisionU()) {      //Wenn Spieler in der Luft
                fallSpeed++;
                if (fallSpeed > 0 && posy+31 < Game.getWorld().getHeight()*60) {
                    moveDown(fallSpeed);    //Fallen mit der Aktuellen fallgeschwindigkeit
                }
            }
            if (fallSpeed < 0 && posy-30 >= 0) {
                moveUp(-1 * fallSpeed); //Nach oben fliegen mit der negativen Fallgeschwindigkeit
            }
        }
        else {
            fallSpeed = 0;      //Wenn Flugmodus im Fall angeschaltet wird -> Fallgeschwindigkeit auf null
        }

        if (KeyInput.up && posy-30 >= 0 && KeyInput.fly) {
            moveUp(speed);
        }
        if (KeyInput.down && posy+30 < Game.getWorld().getHeight()*60 && KeyInput.fly) {
            moveDown(speed);
        }
        if (KeyInput.left && posx-30 >= 0) {
            moveLeft(speed);
        }
        if (KeyInput.right && posx+30 < Game.getWorld().getWidth()*60) {
            moveRight(speed);
        }
    }

    public void moveUp(int dist) {
        for(int i = 0; i <= dist; i++) {
            Block lo = Game.getWorld().getBlock((int) (posx - (hitboxWidth / 2)) / 60, (int) (posy - (hitboxHeight / 2)+3) / 60);
            Block ro = Game.getWorld().getBlock((int) (posx + (hitboxWidth / 2) - 3) / 60, (int) (posy - (hitboxHeight / 2)+3) / 60);
            if (lo == null || !lo.isSolid()) {
                if (ro == null || !ro.isSolid()) {
                    this.move(0, -1);
                    collisionO = false;
                } else {
                    collisionO = true;
                }
            }
            else {
                collisionO = true;
            }
        }
    }
    public void moveDown(int dist) {
        for (int i = 0; i <= dist; i++) {
            Block lu = Game.getWorld().getBlock((int) (posx - (hitboxWidth / 2)) / 60, (int) (posy + (hitboxHeight / 2)+4) / 60);
            Block ru = Game.getWorld().getBlock((int) (posx + (hitboxWidth / 2) - 3) / 60, (int) (posy + (hitboxHeight / 2)+4) / 60);
            if (lu == null || !lu.isSolid()) {
                if (ru == null || !ru.isSolid()) {
                    if (posy + 31 < Game.getWorld().getHeight()*60) {
                        this.move(0, 1);
                        collisionU = false;
                    }
                    else {
                        collisionU = true;
                    }
                } else {
                    collisionU = true;
                }
            }
            else {
                collisionU = true;
            }
        }
    }
    public void moveLeft(int dist) {
        for (int i = 0; i<=dist; i++) {
            Block lo = Game.getWorld().getBlock((int) (posx - (hitboxWidth/2)-3) / 60, (int) (posy - (hitboxHeight/2) +4) / 60);
            Block lu = Game.getWorld().getBlock((int) (posx - (hitboxWidth/2)-3) / 60, (int) (posy + (hitboxHeight/2)) / 60);
            if (lo == null || !lo.isSolid()) {
                if (lu == null || !lu.isSolid()) {
                    this.move(-1, 0);
                    collisionL = false;
                } else {
                    collisionL = true;
                }
            }
        }
    }
    public void moveRight(int dist) {
        for(int i = 0; i<=dist; i++) {
            Block ro = Game.getWorld().getBlock((int) (posx + (hitboxWidth / 2) -2) / 60, (int) (posy - (hitboxHeight / 2) + 4) / 60);
            Block ru = Game.getWorld().getBlock((int) (posx + (hitboxWidth / 2) -2) / 60, (int) (posy + (hitboxHeight / 2)) / 60);
            if (ro == null || !ro.isSolid()) {
                if (ru == null || !ru.isSolid()) {
                    this.move(1, 0);
                    collisionR = false;
                } else {
                    collisionR = true;
                }
            }
        }
    }
    public boolean isCollisionU()
    {
        Block lu = Game.getWorld().getBlock((int) (posx - (hitboxWidth / 2)) / 60, (int) (posy + (hitboxHeight / 2)+4) / 60);
        Block ru = Game.getWorld().getBlock((int) (posx + (hitboxWidth / 2) - 3) / 60, (int) (posy + (hitboxHeight / 2)+4) / 60);
        if (lu == null || !lu.isSolid()) {
            return ru != null && ru.isSolid();
        }
        else {
            return true;
        }
    }

    public double getXPosition()
    {
        return posx;
    }
    public double getYPosition()
    {
        return posy;
    }

}
