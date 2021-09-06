package de.matthi.blocked.utils;

import de.matthi.blocked.main.Game;
import de.matthi.blocked.main.Language;

import java.io.File;
import java.io.IOException;

public class ConfigHandler {
    private static final String configPath = Game.decodedPath + "/Blocked.conf";

    public static void init() {
        File configFile = new File(configPath);
        try {
            if (configFile.createNewFile()) {
                System.out.println("Created Config at: " + configPath);
                write();
            } else {
                System.out.println("Found Config at: " + configPath);
                read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        String configRawData = FileHandler.loadFileAsString(configPath);
        String[] data = configRawData.split("\\s+");

        String lang = data[0].substring(9);
        if (lang.equals("DE")) {
            Language.activeLanguage = 1;
        }
        if (lang.equals("EN")) {
            Language.activeLanguage = 0;
        }
        Language.langInit();
    }

    public static void write() {
        String[] data = new String[1];
        String langidentify = "";
        if (Language.activeLanguage == 0) {
            langidentify = "EN";
        }
        if (Language.activeLanguage == 1) {
            langidentify = "DE";
        }
        data[0] = "language:" + langidentify;
        FileHandler.writeStringAsFile(configPath, data);
    }
}
