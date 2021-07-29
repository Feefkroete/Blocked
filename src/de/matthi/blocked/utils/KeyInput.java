package de.matthi.blocked.utils;

import de.matthi.blocked.main.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener
{
    private static boolean[] keys;
    public static boolean up, down, right, left, sprint, set, save;

    public KeyInput()
    {
        keys = new boolean[300];
    }

    public static void tick()
    {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        sprint = keys[KeyEvent.VK_SHIFT];
        set = keys[KeyEvent.VK_SPACE];
        save = keys[KeyEvent.VK_P];
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            if (Game.gameState == 0)
            {
                Game.gameState = 1;
                (Game.getWorld()).saveWorld(Game.getPlayer());
                System.out.println("Welt gespeichert");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
            keys[e.getKeyCode()] = false;
    }
}
