package de.matthi.blocked.entity.creature;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.utils.KeyInput;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature
{
    BufferedImage textur;
    boolean colisionO, colisionU, colisionL, colisionR;
    int test = 0;

    public Player(double posx, double posy, int width, int height, int hp, BufferedImage textur)
    {
        super(posx, posy, width, height, hp);
        this.textur = textur;
        Game.poffx = posx + (Game.getFenster().getWidth()/2);
        Game.poffy = posy + (Game.getFenster().getHeight()/2);
    }

    public void move(double posx, double posy)
    {
        this.posx += posx;
        this.posy += posy;
        Game.poffx = this.posx - (Game.getFenster().getWidth()/2);
        Game.poffy = this.posy - (Game.getFenster().getHeight()/2);
    }

    public void teleport(double posx, double posy)
    {
        this.posx = posx;
        this.posy = posy;
        Game.poffx = this.posx - (Game.getFenster().getWidth()/2);
        Game.poffy = this.posy - (Game.getFenster().getHeight()/2);
    }

    @Override
    public void render(Graphics graphics)
    {
        graphics.setColor(Color.red);
        graphics.fillRect(Game.getFenster().getWidth()/2 - 30, Game.getFenster().getHeight()/2 -30, width, heigth);
        graphics.drawImage(textur, Game.getFenster().getWidth()/2 - 30, Game.getFenster().getHeight()/2 -30, width, heigth, null);
    }

    @Override
    public void tick()
    {
        int geschw = 5;
        if (KeyInput.sprint) {
            geschw *= 2;
        }

        if (KeyInput.up && posy-30 >= 0) {
            Block blo = Game.getWorld().getBlock((int) (posx - (width/2) + 1) / 60, (int) (posy - (heigth/2) - geschw + 1) / 60);
            Block bro = Game.getWorld().getBlock((int) (posx + (width/2) - 1) / 60, (int) (posy - (heigth/2) - geschw + 1) / 60);
            boolean lo = blo == Block.blocks[4] || blo == null;
            boolean ro = bro == Block.blocks[4] || bro == null;
                if (lo && ro) {
                    this.move(0, -1 * geschw);
                    colisionO = false;
                }
                else {
                    colisionO = true;
                }
        }
        if (KeyInput.down && posy+30 < Game.getWorld().getHeight()*60) {
            Block lu = Game.getWorld().getBlock((int) (posx - (width/2) + 1) / 60, (int) (posy + (heigth/2) + geschw - 1) / 60);
            Block ru = Game.getWorld().getBlock((int) (posx + (width/2) - 1) / 60, (int) (posy + (heigth/2) + geschw - 1) / 60);
            if (lu == Block.blocks[4] || lu == null) {
                if (ru == Block.blocks[4] || ru == null) {
                    this.move(0, geschw);
                    colisionU = false;
                }
                else {
                    colisionU = true;
                }
            }
        }
        if (KeyInput.left && posx-30 >= 0) {
            Block lo = Game.getWorld().getBlock((int) (posx - (width/2) - geschw + 1) / 60, (int) (posy - (heigth/2) + 2) / 60);
            Block lu = Game.getWorld().getBlock((int) (posx - (width/2) - geschw + 1) / 60, (int) (posy + (heigth/2) - 2) / 60);
            if (lo == Block.blocks[4] || lo == null) {
                if (lu == Block.blocks[4] || lu == null) {
                    this.move(-1 * geschw, 0);
                    colisionL = false;
                }
                else {
                    colisionL = true;
                }
            }
        }
        if (KeyInput.right && posx+30 < Game.getWorld().getWidth()*60) {
            Block ro = Game.getWorld().getBlock((int) (posx + (width/2) + geschw - 1) / 60, (int) (posy - (heigth/2) + 2) / 60);
            Block ru = Game.getWorld().getBlock((int) (posx + (width/2) + geschw - 1) / 60, (int) (posy + (heigth/2) - 2) / 60);
            if (ro == Block.blocks[4] || ro == null) {
                if (ru == Block.blocks[4] || ru == null) {
                    this.move(geschw, 0);
                    colisionR = false;
                }
                else  {
                    colisionR = true;
                }
            }
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
