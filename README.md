# Blocked - Ein seltsames 2d-Spiel

---

Controls:

* w / Pfeiltaste nach oben = hoch
* s / Pfeiltaste nach unten = runter
* a / Pfeiltaste nach links = links
* d / Pfeiltaste nach rechts = rechts
* f = Flugmodus
* h = Hitboxen anzeigen
* p = speichern
* esc = speichern und ins Hauptmenu
* mausrad = Blöcke auswählen
* linke maustaste = block platzieren

---

# "Installation"

#### ACHTUNG: Für Blocked wird Java mit der Version 15+ benötigt!
* Am besten hierfür die richtige Version auf https://www.azul.com/downloads/?package=jdk herunterladen und installieren!

    - NUR FÜR LINUX-NUTZER:
    - Da bei den meisten Distros Java bereits vorinstalliert ist, muss nach der Installation der neuen Javaversion noch die Standard-Javaversion gewählt werden:
    ```
    sudo update-alternatives --config java
    java --version
    ```
    - Beim Ersten die zulu-Version wählen
    - Beim Zweiten kontrollieren, ob nun die richtige Version ausgewählt ist

---

* Blocked.jar aus den Releases herunterladen und in einen beliebigen Ordner verschieben (z.B. in einen eigenen Ordner auf dem Desktop)
* -> Beim ersten Start wird ein seperater Weltenordner neben der jar-Datei erstellt!


# Nice-to-know's:
- Weltdateien können umbenannt werden, dürfen aber keine Leer- und Sonderzeichen enthalten!
- Der Weltenordner darf NICHT umbenannt werden, da das Spiel sonst einen neuen generiert.
- Bei Spielcrashes das Spiel mit dem Terminal/cmd ausführen und die Ausgabe als neues Issue schreiben; dann schau ich mir das mal an^^
```
java -jar Blocked.jar
```
