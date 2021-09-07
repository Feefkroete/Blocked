package de.matthi.blocked.menu;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.utils.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuSlider
{
    BufferedImage slider = new BufferedImage(310, 240, 2);
    BufferedImage box, handleOff, handleOn, name;

    Graphics2D graphics2D;

    double minValue, maxValue, scale;
    int posx, posy, width, height;
    int posxHandlePX, posyHandlePX, posxHandle, xplus;
    public boolean hover = false;
    private final double valueScale;
    private double currentValue;

    public MenuSlider(double minValue, double maxValue, double scale, int posx, int posy)
    {
        this.posx = posx;
        this.posy = posy;
        this.scale = scale;
        posxHandle = 40;
        posxHandlePX = (int) (posx+posxHandle*scale);
        posyHandlePX = (int) (posy+140*scale);
        box = Assets.sliderBox;
        handleOff = Assets.sliderHandleOff;
        handleOn = Assets.sliderHandleOn;
        name = Assets.sliderName;
        this.minValue = minValue;
        this.maxValue = maxValue;
        valueScale = (maxValue-minValue)/(190*scale);
        width = (int)(310*scale);
        height = (int)(170*scale);
        graphics2D = slider.createGraphics();
        graphics2D.drawImage(this.name, 0, 0, 300, 70, null);
        graphics2D.drawImage(box, 0, 70, 300, 170, null);
    }

    public void render(Graphics graphics, String name)
    {
        graphics.drawImage(slider, posx, posy, width, height, null);
        graphics.drawString(name, (int) ((posx + width /2) - ((name.length()*21)/2.5)), (int)(posy + height/5.5));
        int value = (int)(currentValue);
        graphics.drawString(String.valueOf(value), (int) ((posx + width /2) - (String.valueOf(value).length()*21)/2.5), (int)(posy + height/2.1));
    }

    public void tick(double mposx, double mposy, JFrame fenster) {
        graphics2D.drawImage(this.name, 0, 0, 300, 70, null);
        graphics2D.drawImage(box, 0, 70, 300, 170, null);
        posxHandlePX = (int) (posx+posxHandle*scale);
        if (mposx>posxHandlePX-xplus && mposx<posxHandlePX+30*scale+xplus && mposy+25> posyHandlePX && mposy+25< posyHandlePX +50*scale){
            graphics2D.drawImage(handleOn, posxHandle, 140, 30, 50, null);
            fenster.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            hover = true;
            if (MouseInput.leftMousePressed) {
                if (mposx-(posx+15*scale)>=40 && mposx-(posx+15*scale)<=230) {
                    posxHandle = (int) (mposx - (posx + 15 * scale));
                }
                if (mposx-(posx+15*scale)<40) {
                    posxHandle = 40;
                }
                if (mposx-(posx+15*scale)>230) {
                    posxHandle = 230;
                }
                xplus=200;
            }
            else {
                xplus=0;
            }
        }
        else {
            graphics2D.drawImage(handleOff, posxHandle, 140, 30, 50, null);
            hover = false;
            xplus=0;
        }
        currentValue = (posxHandle-40)*valueScale+minValue;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setDefaultValue(double value) {
        //FIXME: Ziemlich beschissene Lösung -.-
        posxHandle = (int) ((value/valueScale-minValue)+36);
    }
}
