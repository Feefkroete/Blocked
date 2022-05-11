package de.matthi.blocked.gui;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.Item;
import de.matthi.blocked.item.ItemRegistry;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.utils.KeyInput;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Overlay
{
    public static Item selectedItem;
    private int mposx = 0, mposy = 0;
    private static int scrollPos = 0;
    public static int targetedSlot;
    private static BufferedImage stats;
    public static int posy = 0, posx = 0, width = 0, height = 0;
    public static double scale;

    public Overlay()
    {
        selectedItem = ItemRegistry.items.get(0);
        stats = new BufferedImage(340, 33, 2);
    }

    public static void updateStats() {
        Graphics g = stats.createGraphics();
        for (int i = 0; i<20; i++) {
            if (Game.getPlayer().getHealth() > i) {
                g.drawImage(Assets.heart, i * 15, 0, 13, 13, null);
            }
            else {
                g.drawImage(Assets.heart_inactive, i * 15, 0, 13, 13, null);
            }
        }

        for (int i = 0; i<10; i++) {
            if (Game.getPlayer().getWaterLevel() > i) {
                g.drawImage(Assets.water, i * 15 + 150, 15, 13, 13, null);
            }
            else {
                g.drawImage(Assets.waterEmpty, i * 15 + 150, 15, 13, 13, null);
            }

            if (Game.getPlayer().getFoodLevel() > i) {
                g.drawImage(Assets.food, i * 15, 15, 13, 13, null);
            }
            else {
                g.drawImage(Assets.foodEmpty, i * 15, 15, 13, 13, null);
            }
        }
        g.dispose();
    }

    public static void up()
    {
        if(scrollPos < ItemRegistry.items.size()-1) {
            scrollPos++;
        }
        else {
            scrollPos = 0;
        }
        selectedItem = ItemRegistry.items.get(scrollPos);
    }
    public static void down()
    {
        if (scrollPos > 0)
        {
            scrollPos--;
        }
        else
        {
            scrollPos = ItemRegistry.items.size()-1;
        }
        selectedItem = ItemRegistry.items.get(scrollPos);
    }

    public void render(Graphics graphics)
    {
        if (Game.showTPSFPS) {
            graphics.drawString("TPS: " + Game.currentTPS + " | FPS: " + Game.currentFPS, 3, 21);
        }
        if (KeyInput.fly) {
            graphics.drawImage(Assets.flight, Game.WIDTH - 70, Game.HEIGHT-65, 65, 65, null);
        }
        graphics.drawImage(Assets.inv, ((Game.WIDTH)/2)-32, (Game.HEIGHT)-80, 56, 56, null);
        selectedItem.render(graphics, ((Game.WIDTH)/2)-27, (Game.HEIGHT)-75, 46, 46);

        //Draws the finished stats Image to the screen with the scaled size
        graphics.drawImage(stats, (int) (Game.WIDTH-Game.WIDTH/4.3), Game.HEIGHT/32, (int)(340*(Game.WIDTH/1400D)), (int)(33*Game.HEIGHT/787.5), null);

        if (Game.gameState == 4) {     //If Inventory is open

            Point p = Game.getWindow().getMousePosition();
            if (p != null) {
                mposx = (int) p.getX();
                mposy = (int) p.getY();
            }

            //TODO: Inventar nur so skalieren, dass eine gerade Zahl an pixeln erreicht werden kann.
            graphics.drawImage(Assets.inventory, posx, posy, width, height, null);
            if (mposy-32>posy+(15*scale) && mposy-32<posy+(553*scale) && mposx>posx+(30*scale) && mposx < posx+(85*scale)) {      //HOTBAR
                graphics.drawImage(Assets.inventorySelectBox, posx+(int)(25*scale), (int)((int)(mposy/(67.5*scale))*(67.5*scale)), (int)(scale*55), (int)(scale*55), null);
                targetedSlot = (int)((mposy-10)/67.5)-2;
            }
            else {
                if (mposy-32>posy+20 && mposy-32<posy+553 && mposx>posx+112 && mposx<posx+753) {
                    graphics.drawImage(Assets.inventorySelectBox, ((mposx+15)/65)*65-15, (int)(((int)((mposy-15)/67.5))*67.5-8), (int)(scale*55), (int)(scale*55), null);
                    targetedSlot = (((mposx+15)/65)-4)*8+(int)(((mposy-15))/67.5)-2;
                }
                else {
                    if (mposy-32>posy+300 && mposy-32<posy+553) {
                        if (mposx>posx+785 && mposx<posx+840) {
                            graphics.drawImage(Assets.inventorySelectBox, posx + 785, (int) (((int) ((mposy - 10) / 67.5)) * 67.5 - 8), (int)(scale*55), (int)(scale*55), null);
                        }
                        if (mposx>posx+855 && mposx<posx+980) {
                            graphics.drawImage(Assets.inventorySelectBox, ((mposx-10)/65)*65+17, (int)(((int)((mposy-15)/67.5))*67.5-8), (int)(scale*55), (int)(scale*55), null);
                        }
                    }
                    else {
                        targetedSlot = -1;
                    }
                }
            }
            int yOff = 0;
            int xOff = 0;
            for(int i = 0; i<88; i++) {
                if (Inventory.commonSlotItem[i]!=null) {
                    graphics.setColor(Color.blue);
                    if (i < 8) {
                        Inventory.commonSlotItem[i].render(graphics, posx+35, (int) (posy+38+(i*67.5)), 35, 35, Inventory.commonSlotCount[i]);
                    }
                    else {
                        Inventory.commonSlotItem[i].render(graphics, posx+57+(65*xOff), (int) (posy+38+yOff*67.5), 35, 35, Inventory.commonSlotCount[i]);
                    }
                    graphics.setColor(Color.black);
                }
                if (yOff<7) {
                    yOff++;
                }
                else {
                    yOff = 0;
                    xOff++;
                }
            }
        }
        else {
            Hotbar.render(graphics);
        }
    }
}