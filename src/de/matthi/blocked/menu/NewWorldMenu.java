package de.matthi.blocked.menu;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;

import java.awt.*;

public class NewWorldMenu
{
    private final int buttonWidth = 310;
    private final int buttonHeight = 150;
    private int XButtonsPosx, YButtonsPosx, Xsize = 10, Ysize = 10;
    private double mposx, mposy;
    private final MenuButton back = new MenuButton();
    private final MenuButton Xup = new MenuButton(Assets.button5, Assets.button6);
    private final MenuButton Xvalue = new MenuButton();
    private final MenuButton Xdown = new MenuButton(Assets.button7, Assets.button8);
    private final MenuButton Yup = new MenuButton(Assets.button5, Assets.button6);
    private final MenuButton Yvalue = new MenuButton();
    private final MenuButton Ydown = new MenuButton(Assets.button7, Assets.button8);
    private final MenuButton confirm = new MenuButton();

    public NewWorldMenu()
    {
        init();
    }

    public void init()
    {
        //posx = (int) ((Game.WIDTH/2)-(0.5* buttonWidth));
        XButtonsPosx = buttonWidth + 60;
        YButtonsPosx = Game.WIDTH - 2*buttonWidth - 60;
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
            Xsize = 10;
        }
        Xup.tick(mposx, mposy, Game.getFenster());
        if (Xup.isclicked())
        {
            Xsize += 10;
        }
        Xdown.tick(mposx, mposy, Game.getFenster());
        if (Xdown.isclicked())
        {
            if (Xsize >10)
            {
                Xsize -= 10;
            }
        }
        Yup.tick(mposx, mposy, Game.getFenster());
        if (Yup.isclicked())
        {
            Ysize += 10;
        }
        Ydown.tick(mposx, mposy, Game.getFenster());
        if (Ydown.isclicked())
        {
            if (Ysize >10)
            {
                Ysize -= 10;
            }
        }
        confirm.tick(mposx, mposy, Game.getFenster());
        if (confirm.isclicked())
        {
            Game.getWorld().createWorld(Xsize, Ysize);
        }
    }

    public void render(Graphics graphics)
    {
        graphics.drawImage(Assets.menuBackground, 0,0, Game.getFenster().getWidth(), Game.getFenster().getHeight(), null);
        graphics.drawString("Weltgröße auswählen", Game.getFenster().getWidth()/2-120, 100);
        back.render(graphics, 30, (Game.getFenster().getHeight()) / 2 - buttonHeight / 2, buttonWidth, buttonHeight, "<- Zurück");
        Xup.render(graphics, XButtonsPosx, 180, buttonWidth, buttonHeight, "");
        Xvalue.render(graphics, XButtonsPosx, 330, buttonWidth, buttonHeight, "X: " + Xsize);
        Xdown.render(graphics, XButtonsPosx, 480, buttonWidth, buttonHeight, "");
        Yup.render(graphics, YButtonsPosx, 180, buttonWidth, buttonHeight, "");
        Yvalue.render(graphics, YButtonsPosx, 330, buttonWidth, buttonHeight, "Y: " + Ysize);
        Ydown.render(graphics, YButtonsPosx, 480, buttonWidth, buttonHeight, "");
        confirm.render(graphics, Game.getFenster().getWidth() - 340, (Game.getFenster().getHeight()) / 2 - buttonHeight / 2, buttonWidth, buttonHeight, "Welt erstellen");
    }
}
