package de.matthi.blocked.menu;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;

import javax.swing.*;
import java.awt.*;

public class MainMenu
{
    MenuButton menuButton1 = new MenuButton();
    MenuButton menuButton2 = new MenuButton();
    MenuButton menuButton3 = new MenuButton();
    private final int buttonWidth = 310;
    private final int buttonHeight = 150;
    private int posx;
    private double mposx, mposy;

    public MainMenu()
    {
        init();
    }

    public void init()
    {
        posx = (int) ((Game.WIDTH/2)-(0.5* buttonWidth));
    }

    public void tick(JFrame fenster)
    {
        Point p = fenster.getMousePosition();
        if (p != null)
        {
            mposx = p.getX();
            mposy = p.getY();
        }

        menuButton1.tick(mposx, mposy, fenster);
        menuButton2.tick(mposx, mposy, fenster);
        menuButton3.tick(mposx, mposy, fenster);

        if (!MenuButton.hover)
        {
            fenster.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        MenuButton.hover = false;

        if (menuButton3.isclicked())
        {
            System.exit(0);
        }
        if (menuButton2.isclicked())
        {
            Game.gameState = 2;
        }
        if (menuButton1.isclicked())
        {
            Game.gameState = 3;
        }
    }

    public void render(Graphics graphics)
    {
        graphics.drawImage(Assets.menuBackground, 0,0, Game.getFenster().getWidth(), Game.getFenster().getHeight(), null);
        menuButton1.render(graphics, posx, 60, buttonWidth, buttonHeight, "Neue Welt erstellen");
        menuButton2.render(graphics, posx, 250, buttonWidth, buttonHeight, "Welt laden");
        menuButton3.render(graphics, posx, 440, buttonWidth, buttonHeight, "Spiel beenden");
    }
}
