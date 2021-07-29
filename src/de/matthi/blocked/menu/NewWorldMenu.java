package de.matthi.blocked.menu;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;

import java.awt.*;

public class NewWorldMenu
{
    private final int buttonWidth = 310;
    private final int buttonHeight = 150;
    private int posx, size = 0;
    private double mposx, mposy;
    private final MenuButton back = new MenuButton();
    private final MenuButton up = new MenuButton(Assets.button5, Assets.button6);
    private final MenuButton value = new MenuButton();
    private final MenuButton down = new MenuButton(Assets.button7, Assets.button8);
    private final MenuButton confirm = new MenuButton();

    public NewWorldMenu()
    {
        init();
    }

    public void init()
    {
        posx = (int) ((Game.WIDTH/2)-(0.5* buttonWidth));
        back.setSpecialButton();
        confirm.setSpecialButton();
    }

    public void tick()
    {
        Point p = Game.getFenster().getMousePosition();
        if (p != null)
        {
            mposx = p.getX();
            mposy = p.getY();
        }
        back.tick(mposx, mposy, Game.getFenster());
        if (back.isclicked())
        {
            Game.gameState = 1;
            size = 0;
        }
        up.tick(mposx, mposy, Game.getFenster());
        if (up.isclicked())
        {
            size += 10;
        }
        down.tick(mposx, mposy, Game.getFenster());
        if (down.isclicked())
        {
            if (size>=10)
            {
                size -= 10;
            }
        }
        confirm.tick(mposx, mposy, Game.getFenster());
        if (confirm.isclicked())
        {
            Game.getWorld().createWorld(size);
        }
    }

    public void render(Graphics graphics)
    {
        graphics.drawImage(Assets.menuBackground, 0,0, Game.getFenster().getWidth(), Game.getFenster().getHeight(), null);
        graphics.drawString("Weltgröße auswählen", Game.getFenster().getWidth()/2-120, 100);
        back.render(graphics, 30, (Game.getFenster().getHeight()) / 2 - buttonHeight / 2, buttonWidth, buttonHeight, "<- Zurück");
        up.render(graphics, posx, 180, buttonWidth, buttonHeight, "");
        value.render(graphics, posx, 330, buttonWidth, buttonHeight, String.valueOf(size));
        down.render(graphics, posx, 480, buttonWidth, buttonHeight, "");
        confirm.render(graphics, Game.getFenster().getWidth() - 340, (Game.getFenster().getHeight()) / 2 - buttonHeight / 2, buttonWidth, buttonHeight, "Welt erstellen");
    }
}
