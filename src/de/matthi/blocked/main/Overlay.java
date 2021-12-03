package de.matthi.blocked.main;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.Item;
import de.matthi.blocked.item.ItemRegistry;
import de.matthi.blocked.utils.KeyInput;

import java.awt.*;

public class Overlay
{
    public static Item selectedItem;
    private int mposx = 0, mposy = 0;
    private static int scrollPos = 0;
    public static int targetedSlot;

    public Overlay()
    {
        selectedItem = ItemRegistry.items.get(0);
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
        //Draw hearts
        for (int i = 0; i < Game.getPlayer().getHealth(); i++) {
            graphics.drawImage(Assets.heart, Game.window.getWidth()-300+(i*15), 5, 13, 13, null);
        }
        for (int i = 0; i < 20-Game.getPlayer().getHealth() && i < 20; i++) {
            graphics.drawImage(Assets.heart_inactive, Game.window.getWidth()-15-(i*15), 5, 13, 13, null);
        }
        //Draw Food Level
        for (int i = 0; i < Game.getPlayer().getFoodLevel(); i++) {
            graphics.drawImage(Assets.food, Game.window.getWidth()-300+(i*15), 25, 13, 13, null);
        }
        for (int i = 0; i < 10-Game.getPlayer().getFoodLevel() && i < 10; i++) {
            graphics.drawImage(Assets.foodEmpty, Game.window.getWidth()-165-(i*15), 25, 13, 13, null);
        }
        //Draw Water Level
        for (int i = 0; i < Game.getPlayer().getWaterLevel(); i++) {
            graphics.drawImage(Assets.water, Game.window.getWidth()-150+(i*15), 25, 13, 13, null);
        }
        for (int i = 0; i < 10-Game.getPlayer().getWaterLevel() && i < 10; i++) {
            graphics.drawImage(Assets.waterEmpty, Game.window.getWidth()-15-(i*15), 25, 13, 13, null);
        }
        if (Game.gameState == 4) {      //Wenn Inventar geöffnet
            int posx = Game.WIDTH/2-503;
            int posy = Game.HEIGHT/2-292;

            Point p = Game.getWindow().getMousePosition();
            if (p != null) {
                mposx = (int) p.getX();
                mposy = (int) p.getY();
            }

            graphics.drawImage(Assets.inventory, posx, posy, 1005, 583, null);
            if (mposy-32>posy+15 && mposy-32<posy+553 && mposx>posx+30 && mposx < posx+85) {
                graphics.drawImage(Assets.inventorySelectBox, posx+25, (int)(((int)((mposy-10)/67.5))*67.5-8), 55, 55, null);
                targetedSlot = (int)((mposy-10)/67.5)-2;
            }
            else {
                if (mposy-32>posy+20 && mposy-32<posy+553 && mposx>posx+112 && mposx<posx+753) {
                    graphics.drawImage(Assets.inventorySelectBox, ((mposx+15)/65)*65-15, (int)(((int)((mposy-15)/67.5))*67.5-8), 55, 55, null);
                    targetedSlot = (((mposx+15)/65)-4)*8+(int)(((mposy-15))/67.5)-2;
                }
                else {
                    if (mposy-32>posy+300 && mposy-32<posy+553) {
                        if (mposx>posx+785 && mposx<posx+840) {
                            graphics.drawImage(Assets.inventorySelectBox, posx + 785, (int) (((int) ((mposy - 10) / 67.5)) * 67.5 - 8), 55, 55, null);
                        }
                        if (mposx>posx+855 && mposx<posx+980) {
                            graphics.drawImage(Assets.inventorySelectBox, ((mposx-10)/65)*65+17, (int)(((int)((mposy-15)/67.5))*67.5-8), 55, 55, null);
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