package de.matthi.blocked.menu;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.main.Language;
import de.matthi.blocked.main.Launcher;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class UpdateMenu {
    MenuButton back = new MenuButton();
    MenuButton download = new MenuButton();
    private final int buttonWidth = 310;
    private final int buttonHeight = 150;
    private double mposx, mposy;
    private final int posx;

    private boolean downloadComplete = false;

    public UpdateMenu() {
        back.setSpecialButton();
        posx = (int) ((Game.WIDTH/2)-(0.5* buttonWidth));
    }
    public void tick(JFrame fenster) {
        Point p = fenster.getMousePosition();
        if (p != null) {
            mposx = p.getX();
            mposy = p.getY();
        }
        back.tick(mposx, mposy, fenster);
        download.tick(mposx, mposy, fenster);
        if (back.isclicked()) {
            Game.gameState = 1;
        }
        if (download.isclicked()) {
            File oldFile = new File(Game.thisFile+"OLD");
            Game.thisFile.renameTo(oldFile);
            try {
                BufferedInputStream in = new BufferedInputStream(new URL("https://github.com/Feefkroete/Blocked/releases/download/" + Launcher.newVersion + "/Blocked.jar").openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(Game.decodedPath + "/Blocked.jar");
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
                System.out.println("Downloading new Version!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!back.hover && !download.hover) {
                fenster.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            downloadComplete = true;
            try {
                oldFile.delete();
                Game.getFenster().setVisible(false);
                Runtime.getRuntime().exec("java -jar "+Game.decodedPath+"/Blocked.jar");
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.menuBackground, 0,0, Game.getFenster().getWidth(), Game.getFenster().getHeight(), null);
        back.render(graphics, 30, (Game.getFenster().getHeight()) / 2 - buttonHeight / 2, buttonWidth, buttonHeight, Language.back);
        if (!downloadComplete) {
            graphics.drawString(Language.downloadUpdate, (int) ((posx + Game.WIDTH / 2) - ((Language.downloadUpdate.length() * 21) / 1.5)), 100);
            download.render(graphics, posx, (Game.getFenster().getHeight()) / 2 - buttonHeight / 2, buttonWidth, buttonHeight, Language.download);
        }
        else {
            graphics.drawString(Language.downloadComplete, (int) ((posx + Game.WIDTH / 2) - ((Language.downloadComplete.length() * 21) / 1.7)), 100);
            download.render(graphics, posx, (Game.getFenster().getHeight()) / 2 - buttonHeight / 2, buttonWidth, buttonHeight, Language.download);
        }
    }
}
