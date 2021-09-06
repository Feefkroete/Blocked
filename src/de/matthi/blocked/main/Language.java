package de.matthi.blocked.main;

public class Language {
    public static int activeLanguage;

    public static String createNewWorld = "Create new World";
    public static String createWorld = "Create World";
    public static String selectWorldSize = "Select World Size";
    public static String selectWorld = "Select World";
    public static String quitGame = "Quit Game";
    public static String back = "<-Back";
    public static String options = "Options";
    public static String langSelect = "Language: EN";

    public static void setLanguage() {
        if (activeLanguage<1) {
            activeLanguage += 1;
        }
        else {
            activeLanguage = 0;
        }
        langInit();
    }

    public static void langInit() {
        if (activeLanguage == 0) {
            createNewWorld = "Create new World";
            createWorld = "Create World";
            selectWorldSize = "Select World Size";
            selectWorld = "Select World";
            quitGame = "Quit Game";
            back = "<-Back";
            options = "Options";
            langSelect = "Language: EN";
        }
        if (activeLanguage == 1) {
            createNewWorld = "Neue Welt erstellen";
            createWorld = "Welt erstellen";
            selectWorldSize = "Weltgröße auswählen";
            selectWorld = "Welt laden";
            quitGame = "Spiel beenden";
            back = "<-Zurück";
            options = "Optionen";
            langSelect = "Sprache: DE";
        }
    }
}
