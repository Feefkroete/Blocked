DE
---
# Blocked - Ein seltsames 2d-Spiel

---

Controls:

* w / Pfeiltaste nach oben = hoch /springen
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

    ----

    - NUR FÜR WINDOWS-NUTZER:
    - Auf der website den msi-installer für die **x86 64-bit** Version herunterladen.
    - Auf der 2. Seite des installers auf das Kästchen links neben Azul Zulu JDK[...] klicken und anschließend die untere Option wählen
![Click](https://cdn.discordapp.com/attachments/851116039223509062/874948214598545449/unknown.png)
    - Danach die Installation abschließen und die neue Java-Version sollte installiert sein.
   
   ----
   
   - Falls hier irgendwer Mac-Nutzer ist:
   - Sorry, aber ich habe keine Ahnung, wie das da ist mit der Installation ¯\\_(ツ)_/¯
    
----

* Blocked.jar aus den Releases herunterladen und in einen beliebigen Ordner verschieben (z.B. in einen eigenen Ordner auf dem Desktop)
![Release](https://cdn.discordapp.com/attachments/851116039223509062/874771196376403988/Screenshot_20210810_233340.png)
* -> Beim ersten Start wird ein seperater Weltenordner neben der jar-Datei erstellt!


# Nice-to-know's:
- Weltdateien können umbenannt werden, dürfen aber keine Leer- und Sonderzeichen enthalten!
- Der Weltenordner darf NICHT umbenannt werden, da das Spiel sonst einen neuen generiert.
- Bei Spielcrashes das Spiel mit dem Terminal/cmd ausführen und die Ausgabe als neues Issue schreiben; dann schau ich mir das mal an^^
```
java -jar Blocked.jar
```
-----

EN
---
# Blocked - A weird 2d Game

---

Controls:

* w / Arrow up = move upwards / jump
* s / Arrow down = move downwards
* a / Arrow left = move left
* d / Arrow right = move right
* f = Flight mode
* h = Show hitboxes
* p = Save game
* esc = Save Game and back to main menu
* mouse wheel = Select Blocks
* left mouse button = Place selected block

---

# "Installation"

#### Attention: You have to have Java version 15+ installed on your device to play Blocked
* The easyest solution would be downloading the correct version from https://www.azul.com/downloads/?package=jdk and installing it!

    - ONLY FOR LINUX USERS:
    - Because of many distros having Java preinstalled you'll have to choose the right Version after installing the new one:
    ```
    sudo update-alternatives --config java
    java --version
    ```
    - Choose the zulu-version after running the first command
    - Check if it was succesfully changed with the second one

    ----

    - ONLY FOR WINDOWS USERS:
    - Download the msi installer for the **x86 64-bit** version from the website.
    - On the second page of the installer click on the box to the left of Azul Zulu JDK[...] and choose the second option.
![Click](https://cdn.discordapp.com/attachments/851116039223509062/874948214598545449/unknown.png)
    - Complete the installation and the new java version should be up and running.
   
   ----
   
   - If someone here is a Mac-user:
   - Sorry but I have no idea how the installation process works there... ¯\\_(ツ)_/¯

---

* Download the Blocked.jar from the Releases and save it in a seperate folder (e.g. on the Desktop)
![Release](https://cdn.discordapp.com/attachments/851116039223509062/874771196376403988/Screenshot_20210810_233340.png)
* -> Blocked will create a seperate world folder in the same directory next to the jar!


# Nice-to-know's:
- World files can be renamed but MAY NOT include spaces or special characters!
- The world folder MAY NOT be renamed due to a new one being generated afterwards!
- If the game crashes unexpectedly just run the game with the following command in the terminal/cmd and write a new Issue in Github or write to me via discord and - post the output there. I'll have a look at it... maybe^^
```
java -jar Blocked.jar
```
