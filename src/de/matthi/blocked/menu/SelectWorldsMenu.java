package de.matthi.blocked.menu;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.main.Language;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SelectWorldsMenu
{
    private static File list;
    public static String[] files;
    private final MenuButton back = new MenuButton();
    private static int menupos;
    List<MenuButton> menuButtonList;

    private double mposx, mposy;
    private final int buttonWidth = 310;
    private final int buttonHeight = 150;
    private int posx;

    public SelectWorldsMenu()
    {

    }

    public void init()
    {
        list = new File(Game.worldsPath);
        files = list.list();
        back.setSpecialButton();
        menuButtonList = new ArrayList<>();
        for (int i = 0; i< files.length; i++)
        {
            menuButtonList.add(new MenuButton());
        }
        posx = (int) ((Game.WIDTH/2)-(0.5* buttonWidth));
    }

    public void render(Graphics graphics)
    {
        graphics.drawImage(Assets.menuBackground, 0,0, Game.getFenster().getWidth(), Game.getFenster().getHeight(), null);
        back.render(graphics, 30, (Game.getFenster().getHeight()) / 2 - buttonHeight / 2, buttonWidth, buttonHeight, Language.back);
        for (int i = 0; i < files.length; i++) {
            int posy = buttonHeight * i + 30 * i + menupos + 30;
            menuButtonList.get(i).render(graphics, posx, posy, buttonWidth, buttonHeight, (files[i].substring(0, files[i].length() - 4)));
        }
    }

    public void tick(JFrame fenster) {
        Point p = fenster.getMousePosition();
        if (p != null) {
            mposx = p.getX();
            mposy = p.getY();
        }

        back.tick(mposx, mposy, fenster);
        boolean hover = false;
        for (int i = 0; i < files.length; i++) {
            menuButtonList.get(i).tick(mposx, mposy, fenster);
            if (menuButtonList.get(i).isclicked()) {
                Game.getWorld().loadWorld("/" + files[i]);
            }
            if (back.isclicked()) {
                Game.gameState = 1;
                menupos = 0;
            }
            if (menuButtonList.get(i).hover || back.hover) {
                hover = true;
            }
        }
        if (!hover) {
            fenster.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public static void up()
    {
        menupos = menupos-25;
    }
    public static void down()
    {
        menupos = menupos+25;
    }
}
