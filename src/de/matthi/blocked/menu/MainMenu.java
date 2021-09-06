package de.matthi.blocked.menu;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.main.Language;

import javax.swing.*;
import java.awt.*;

public class MainMenu
{
    MenuButton newWorldButton = new MenuButton();
    MenuButton selectWorldButton = new MenuButton();
    MenuButton optionsButton = new MenuButton();
    MenuButton quitGameButton = new MenuButton();
    private final int buttonWidth = 310;
    private final int buttonHeight = 150;
    private int posx;
    private double mposx, mposy;

    public MainMenu() {
        init();
    }

    public void init() {
        posx = (int) ((Game.WIDTH/2)-(0.5* buttonWidth));
    }

    public void tick(JFrame fenster) {
        Point p = fenster.getMousePosition();
        if (p != null)
        {
            mposx = p.getX();
            mposy = p.getY();
        }

        newWorldButton.tick(mposx, mposy, fenster);
        selectWorldButton.tick(mposx, mposy, fenster);
        optionsButton.tick(mposx, mposy, fenster);
        quitGameButton.tick(mposx, mposy, fenster);

        if (!newWorldButton.hover && !selectWorldButton.hover && !quitGameButton.hover && !optionsButton.hover)
        {
            fenster.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

        if (quitGameButton.isclicked())
        {
            System.exit(0);
        }
        if (selectWorldButton.isclicked())
        {
            Game.gameState = 2;
        }
        if (newWorldButton.isclicked())
        {
            Game.gameState = 3;
        }
        if (optionsButton.isclicked()) {
            Game.gameState = 5;
        }
    }

    public void render(Graphics graphics)
    {
        graphics.drawImage(Assets.menuBackground, 0,0, Game.getFenster().getWidth(), Game.getFenster().getHeight(), null);
        newWorldButton.render(graphics, posx, 50, buttonWidth, buttonHeight, Language.createNewWorld);
        selectWorldButton.render(graphics, posx, 230, buttonWidth, buttonHeight, Language.selectWorld);
        optionsButton.render(graphics, posx, 410, buttonWidth, buttonHeight, Language.options);
        quitGameButton.render(graphics, posx, 590, buttonWidth, buttonHeight, Language.quitGame);
    }
}
