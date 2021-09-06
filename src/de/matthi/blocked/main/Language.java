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
    public static String download = "Download";
    public static String downloadUpdate = "There's an Update available for Blocked!! Do you want to download it?";
    public static String downloadComplete = "The Download has completed! Delete the old Blocked.jar and run the new one!";

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
            downloadUpdate = "There's an Update available for Blocked!! Do you want to download it?";
            downloadComplete = "The Download has completed! Delete the old Blocked.jar and run the new one!";
        }
        if (activeLanguage == 1) {
            createNewWorld = "Neue Welt erstellen";
            createWorld = "Welt erstellen";
            selectWorldSize = "Weltgröße auswählen";
            selectWorld = "Welt laden";
            quitGame = "Spiel beenden";
            back = "<-Zurück";
            options = "Einstellungen";
            langSelect = "Sprache: DE";
            downloadUpdate = "Es gibt eine neue Version von Blocked!! Möchtest du sie herunterladen?";
            downloadComplete = "Der Download ist abgeschlossen. Lösche die alte Blocked.jar und starte die neue.";
        }
    }
}
