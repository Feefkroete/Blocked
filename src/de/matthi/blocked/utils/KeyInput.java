package de.matthi.blocked.utils;

import de.matthi.blocked.block.BlockRegistry;
import de.matthi.blocked.entity.itemEntity.ItemEntity;
import de.matthi.blocked.item.ItemRegistry;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.main.Inventory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener
{
    private static boolean[] keys;
    public static boolean up, down, right, left, sprint, save, fly = false, hitBox = false, inv = false;

    public KeyInput()
    {
        keys = new boolean[300];
    }

    public static void tick()
    {
        //Booleans werden auf true gesetzt, wenn Taste gedrückt
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        sprint = keys[KeyEvent.VK_SHIFT];
        save = keys[KeyEvent.VK_P];
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;                //Array auf true setzen bei der id der Taste
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            if (Game.gameState == 0)    //Wenn esc gedrückt und das Spiel läuft => Hauptmenu
            {
                Game.gameState = 1;
                (Game.getWorld()).saveWorld(Game.getPlayer());
                System.out.println("Welt gespeichert");
            }
            if (Game.gameState == 4) {
                inv = false;
                Game.gameState = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;   //Wenn Taste nicht mehr gedrückt => Array an der Stelle der Tastenid auf fals gesetzt
        if (e.getKeyCode() == KeyEvent.VK_F)
        {
            fly = !fly;
        }
        if (e.getKeyCode() == KeyEvent.VK_H)
        {
            hitBox = !hitBox;
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            inv = !inv;
            if (Inventory.mouseSlotItem != null) {
                for (int i = 0; i < Inventory.mouseSlotCount; i++) {
                    Game.getWorld().getItemData().add(new ItemEntity(Game.getPlayer().getXPosition() + (Math.random() - 0.8) * 20, Game.getPlayer().getYPosition(), Inventory.mouseSlotItem));
                }
                Inventory.mouseSlotItem = null;
                Inventory.mouseSlotCount = 0;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_F3) {
            Game.showTPSFPS = !Game.showTPSFPS;
        }
    }
}
