package de.matthi.blocked.menu;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.main.Language;
import de.matthi.blocked.utils.ConfigHandler;

import javax.swing.*;
import java.awt.*;

public class OptionsMenu {

    private final int buttonWidth = 310;
    private final int buttonHeight = 150;
    int posx;
    double mposx, mposy;
    private final MenuButton lang = new MenuButton();
    private final MenuButton back = new MenuButton();
    private MenuSlider fpsSlider;

    public OptionsMenu() {

    }

    public void init() {
        posx = (int) ((Game.WIDTH/2)-(0.5* buttonWidth));
        back.setSpecialButton();
        fpsSlider = new MenuSlider(10, 140, 1, posx, 230);
        fpsSlider.setDefaultValue(Game.fps);
    }

    public void render(Graphics graphics) {
        graphics.drawImage(Assets.menuBackground, 0,0, Game.getWindow().getWidth(), Game.getWindow().getHeight(), null);
        back.render(graphics, 30, (Game.getWindow().getHeight()) / 2 - buttonHeight / 2, buttonWidth, buttonHeight, Language.back);
        lang.render(graphics, posx, 30, buttonWidth, buttonHeight, Language.langSelect);
        fpsSlider.render(graphics, "FPS");
    }

    public void tick(JFrame fenster) {
        Point p = fenster.getMousePosition();
        if (p != null)
        {
            mposx = p.getX();
            mposy = p.getY();
        }

        lang.tick(mposx, mposy, fenster);
        back.tick(mposx, mposy, fenster);
        fpsSlider.tick(mposx, mposy, fenster);
        if (lang.isclicked()) {
            Language.setLanguage();
        }
        if (back.isclicked()) {
            Game.fps = (int) fpsSlider.getCurrentValue();
            Game.nsperframe = 1000000000/Game.fps;
            Game.gameState = 1;
            ConfigHandler.write();
        }
        if (!back.hover && !lang.hover && !fpsSlider.hover) {
            fenster.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }
}
