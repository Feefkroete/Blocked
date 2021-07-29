package de.matthi.blocked.entity.creature;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.utils.KeyInput;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature
{
    BufferedImage textur;
    int impulse = 0;
    boolean o = true;
    boolean u = true;
    boolean l = true;
    boolean r = true;

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
        int geschw = 6;

        /*
        if(impulse > -10)
        {
            this.move(0, -1*impulse);
            impulse--;
        }
         */

        if (KeyInput.sprint)
        {
            geschw *= 1.8;
        }
        if (KeyInput.up)
        {
            //impulse = 10;
            if (o) {
                this.move(0, -1 * geschw);
            }
        }
        if (KeyInput.down)
        {
            if (u) {
                this.move(0, geschw);
            }
        }
        if (KeyInput.left)
        {
            if (l) {
                this.move(-1 * geschw, 0);
            }
        }
        if (KeyInput.right)
        {
            if (r) {
                this.move(geschw, 0);
            }
        }

        //TESTCODE!! boolean t auch entfernen!
        /*
            eventuell mit vier booleans (colisiono/colisionu/colisionl/colisionr)
            und dann für jeden boolean jeweils 2 Ecken überprüfen (z.B. für links -> links oben + links unten)
        */

        Block testlo = null;
        Block testro = null;
        Block testlu = null;
        Block testru = null;
        if (posx < Game.getWorld().getWidth()*60 && posx >= 0 && posy < Game.getWorld().getHeight()*60 && posy >= 0)
        {
            testlo = Game.getWorld().getBlock((int) (posx - 30) / 60, (int) (posy - 27) / 60);
            testro = Game.getWorld().getBlock((int) (posx + 30) / 60, (int) (posy - 30) / 60);
            testlu = Game.getWorld().getBlock((int) (posx - 30) / 60, (int) (posy + 30) / 60);
            testru = Game.getWorld().getBlock((int) (posx + 30) / 60, (int) (posy + 30) / 60);
        }

        o = (testlo == Block.blocks[4] || testlo == null) && (testro == Block.blocks[4] || testro == null);
        u = (testlu == Block.blocks[4] || testlu == null) && (testru == Block.blocks[4] || testru == null);
        l = (testlu == Block.blocks[4] || testlu == null) && (testlo == Block.blocks[4] || testlo == null);
        r = (testru == Block.blocks[4] || testru == null) && (testro == Block.blocks[4] || testro == null);

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
