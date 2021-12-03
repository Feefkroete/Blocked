package de.matthi.blocked.utils;

import de.matthi.blocked.main.Game;
import de.matthi.blocked.main.Hotbar;
import de.matthi.blocked.main.Overlay;
import de.matthi.blocked.menu.SelectWorldsMenu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseInput implements MouseListener, MouseWheelListener {

    public static boolean leftMousePressed, middleMouseClicked, rightMouseClicked;

    public MouseInput()
    {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            leftMousePressed = true;
        }
        if (e.getButton() == MouseEvent.BUTTON2) {
            middleMouseClicked = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightMouseClicked = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            leftMousePressed = false;
        }
        if (e.getButton() == MouseEvent.BUTTON2) {
            middleMouseClicked = false;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightMouseClicked = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        if(e.getWheelRotation() == 1)
        {
            if (Game.gameState == 0)
            {
                Overlay.up();
                Hotbar.up();
            }
            if(Game.gameState == 2)
            {
                SelectWorldsMenu.up();
            }
        }
        if(e.getWheelRotation() == -1)
        {
            if (Game.gameState == 0)
            {
                Overlay.down();
                Hotbar.down();
            }
            if(Game.gameState == 2)
            {
                SelectWorldsMenu.down();
            }
        }
    }
}
