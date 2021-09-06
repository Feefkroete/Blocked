package de.matthi.blocked.menu;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.utils.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton
{
    BufferedImage bufferedImage;

    int posx, posy, width, height;
    public boolean hover = false;
    private boolean pressed, hasCustomTexture;
    private boolean isSpecial = false;
    private BufferedImage textur, texturA;

    public MenuButton()
    {
        bufferedImage = Assets.button1;
    }

    public MenuButton(BufferedImage textur, BufferedImage texturA)
    {
        bufferedImage = textur;
        this.textur = textur;
        this.texturA = texturA;
        hasCustomTexture = true;
    }

    public void render(Graphics graphics, int posx, int posy, int width, int height, String content)
    {
        graphics.drawImage(bufferedImage, posx, posy, width, height, null);
        graphics.drawString(content, (int) ((posx + width/2) - ((content.length()*21)/3.4)), posy + height/2);
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
    }

    public void tick(double mposx, double mposy, JFrame fenster)
    {
        if(mposx >= posx && mposx <= posx+width)
        {
            if (mposy >= posy + 28 && mposy <= posy + height + 28)
            {
                setMenuButtonActive(true);
                fenster.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                hover = true;
                if (MouseInput.leftMousePressed)
                {
                    pressed = true;
                    MouseInput.leftMousePressed = false;
                }
                else
                {
                    pressed = false;
                }
            }
            else
            {
                setMenuButtonActive(false);
                hover = false;
            }
        }
        else
        {
            setMenuButtonActive(false);
            hover = false;
        }
    }

    public void setMenuButtonActive(boolean activated)
    {
        if (activated)
        {
            if (!isSpecial)
            {
                if(!hasCustomTexture) {
                    bufferedImage = Assets.button2;
                }
                else
                {
                    bufferedImage = texturA;
                }
            }
            else
            {
                bufferedImage = Assets.button4;
            }
        }
        else
        {
            if(!isSpecial)
            {
                if(!hasCustomTexture) {
                    bufferedImage = Assets.button1;
                }
                else
                {
                    bufferedImage = textur;
                }
            }
            else
            {
                bufferedImage = Assets.button3;
            }
        }
    }

    public void setSpecialButton()
    {
        bufferedImage = Assets.button3;
        isSpecial = true;
    }

    public boolean isclicked()
    {
        if(pressed)
        {
            pressed = false;
            return true;
        }
        return false;
    }
}
