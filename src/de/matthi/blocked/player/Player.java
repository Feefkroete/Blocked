package de.matthi.blocked.player;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.utils.KeyInput;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player {

    BufferedImage textur;
    int posx, posy;
    int width, height;
    int hitboxWidth = 48, hitboxHeight = 52;    //Hitboxen - WOW!
    protected int hp, foodLevel, waterLevel;
    boolean collisionU, collisionO, collisionL, collisionR;
    int fallSpeed = 0;
    int speed;

    public Player(double posx, double posy, int width, int heigth, int hp, int foodLevel, int waterLevel, BufferedImage textur) {
        this.width = width;
        this.height = heigth;
        this.textur = textur;
        this.hp = hp;
        this.foodLevel = foodLevel;
        this.waterLevel = waterLevel;
        Game.poffx = posx + (Game.getWindow().getWidth()/2.0);
        Game.poffy = posy + (Game.getWindow().getHeight()/2.0);
    }

    public void move(double x, double y)
    {
        this.posx += x;
        this.posy += y;
        Game.poffx = this.posx - (Game.getWindow().getWidth()/2.0);
        Game.poffy = this.posy - (Game.getWindow().getHeight()/2.0);
    }

    public void teleport(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
        Game.poffx = this.posx - (Game.getWindow().getWidth()/2.0);
        Game.poffy = this.posy - (Game.getWindow().getHeight()/2.0);
    }

    public void render(Graphics graphics) {
        if (KeyInput.hitBox) {                  //Hitbox nur auf Tastendruck togglen
            graphics.setColor(Color.red);
            graphics.fillRect(Game.getWindow().getWidth()/2 - 26, Game.getWindow().getHeight()/2 -22, hitboxWidth, hitboxHeight);
        }
        graphics.drawImage(textur, Game.getWindow().getWidth()/2 - 30, Game.getWindow().getHeight()/2 -30, width, height, null);
        //Item.items[Overlay.selectedBlock].render(graphics, Game.getFenster().getWidth()/2 + 12, Game.getFenster().getHeight()/2 -24, 15, 15);
    }

    public void tick() {
        speed = 5;
        if (KeyInput.sprint) {
            speed *= 2;             //Wenn sprint -> 2-fache Geschwindigkeit
        }

        if (!KeyInput.fly) {        //Wenn Flugmodus inaktiv
            if (isCollisionU()) {   //Wenn spieler auf einem Block steht
                if (KeyInput.up) {
                    fallSpeed = -11;    //Quasi nach oben fliegen... ja halt negative Fallgeschwindigkeit = nach-oben-flieg-geschwindigkeit
                }
                else {
                    if (fallSpeed>30) {
                        if (hp-((fallSpeed-30)/1.5) >= 0) {
                            hp = (int) (hp - ((fallSpeed - 30) / 1.5));
                        }
                        else {
                            hp = 0;
                        }
                    }
                    fallSpeed = 0;
                }
            }
            if (!isCollisionU()) {      //Wenn Spieler in der Luft
                fallSpeed++;
                if (fallSpeed > 0) {
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

        if (KeyInput.up && KeyInput.fly) {
            moveUp(speed);
        }
        if (KeyInput.down && KeyInput.fly) {
            moveDown(speed);
        }
        if (KeyInput.left) {
            moveLeft(speed);
        }
        if (KeyInput.right) {
            moveRight(speed);
        }
    }

    public void healTick() {
        if (hp < 20 && foodLevel > 4 && waterLevel > 5) {
            hp += 1;
            foodLevel -= 1;
            waterLevel -= 1;
        }
    }

    public void foodWaterRandomTick() {
        Random r = new Random();
        if (waterLevel>0 && r.nextFloat()<0.005*speed) {
            waterLevel-=1;
        }
        if (foodLevel>0 && r.nextFloat()<0.002*speed) {
            foodLevel-=1;
        }
    }

    public void moveUp(int dist) {
        for(int i = 0; i <= dist && (posy - (hitboxHeight / 2)+3) >= 0; i++) {
            Block lo = Game.getWorld().getBlock((posx - (hitboxWidth / 2)) / 60, (posy - (hitboxHeight / 2)+3) / 60);
            Block ro = Game.getWorld().getBlock((posx + (hitboxWidth / 2) - 3) / 60, (posy - (hitboxHeight / 2)+3) / 60);
            if (!lo.isSolid() && !ro.isSolid()) {
                this.move(0, -1);
                collisionO = false;
            } else {
                collisionO = true;
                break;
            }
        }
    }
    public void moveDown(int dist) {
        for (int i = 0; i <= dist; i++) {
            Block lu = Game.getWorld().getBlock((posx - (hitboxWidth / 2)) / 60, (posy + (hitboxHeight / 2)+4) / 60);
            Block ru = Game.getWorld().getBlock((posx + (hitboxWidth / 2) - 3) / 60, (posy + (hitboxHeight / 2)+4) / 60);
            if (!lu.isSolid() && !ru.isSolid()) {
                this.move(0, 1);
                collisionU = false;
            } else {
                collisionU = true;
                break;
            }
        }
    }
    public void moveLeft(int dist) {
        for (int i = 0; i<=dist && (posx - (hitboxWidth/2)-3)>=0; i++) {    //wenn die Position nicht weit im Minus ist wird auf null aufgerundet, desshalb die Absicherung...
            Block lo = Game.getWorld().getBlock((posx - (hitboxWidth/2)-3) / 60, (posy - (hitboxHeight/2) +4) / 60);
            Block lu = Game.getWorld().getBlock((posx - (hitboxWidth/2)-3) / 60, (posy + (hitboxHeight/2)) / 60);
            if (!lo.isSolid() && !lu.isSolid()) {
                this.move(-1, 0);
                collisionL = false;
            } else {
                collisionL = true;
                break;
            }
        }
    }
    public void moveRight(int dist) {
        for(int i = 0; i<=dist; i++) {
            Block ro = Game.getWorld().getBlock((posx + (hitboxWidth / 2) -2) / 60, (posy - (hitboxHeight / 2) + 4) / 60);
            Block ru = Game.getWorld().getBlock((posx + (hitboxWidth / 2) -2) / 60, (posy + (hitboxHeight / 2)) / 60);
            if (!ro.isSolid() && !ru.isSolid()) {
                this.move(1, 0);
                collisionR = false;
            } else {
                collisionR = true;
                break;
            }
        }
    }
    public boolean isCollisionU()
    {
        Block lu = Game.getWorld().getBlock((posx - (hitboxWidth / 2)) / 60, (posy + (hitboxHeight / 2)+4) / 60);
        Block ru = Game.getWorld().getBlock((posx + (hitboxWidth / 2) - 3) / 60, (posy + (hitboxHeight / 2)+4) / 60);
        if (!lu.isSolid()) {
            return ru.isSolid();
        }
        else {
            return true;
        }
    }

    //-------- GETTERS --------//

    public double getXPosition()
    {
        return posx;
    }
    public double getYPosition()
    {
        return posy;
    }
    public int getHealth() {
        return hp;
    }
    public int getWaterLevel() {
        return waterLevel;
    }
    public int getFoodLevel() {
        return foodLevel;
    }

    //-------- SETTERS --------//


    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    //-------- ADDERS --------//

    //TODO: ADDER UMSCHREIBEN, DASS MAN NICHT AU MINUS KOMMT

    public boolean addFood(int amt) {
        if (foodLevel+amt <= 10) {
            foodLevel += amt;
        }
        else {
            if (foodLevel == 10) {
                return false;
            }
            foodLevel = 10;
        }
        return true;
    }
    public boolean addWater(int amt) {
        if (waterLevel+amt <= 10) {
            waterLevel += amt;
        }
        else {
            if (waterLevel == 10) {
                return false;
            }
            waterLevel = 10;
        }
        return true;
    }
}
