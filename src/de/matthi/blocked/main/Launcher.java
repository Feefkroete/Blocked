package de.matthi.blocked.main;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.utils.FileHandler;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class Launcher
{
    public static double newVersion = 0;

    public static void main(String[] args) throws URISyntaxException {
        Assets.init();
        new Game().init();
        versionCheck();
    }

    public static void versionCheck() {
        URL url;
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try
        {
            url = new URL("https://raw.githubusercontent.com/Feefkroete/Blocked/master/Version");
            URLConnection urlcon = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
            newVersion = FileHandler.parseDouble(stringBuilder.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        if (newVersion >= Game.VERSION) {
            Game.gameState = 6;
        }
    }
}


