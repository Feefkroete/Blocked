package de.matthi.blocked.world;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.entity.creature.Player;
import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.main.Overlay;
import de.matthi.blocked.utils.FileHandler;
import de.matthi.blocked.utils.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class World
{
    private int width, height;
    private int[][] worldData;
    private double mposx, mposy;
    public static double pposx, pposy;
    private String path;

    public World()
    {

    }

    public void loadWorld(String path)
    {
        this.path = path;
        String welt = FileHandler.loadFileAsString(path);       //ruft die Methode "loadFileAsString" im filehandler auf => returnt einen String (ach nee)
        String[] data = welt.split("\\s+");               //Spaltet den String bei jedem Leerzeichen und packt die Bruchstücke in ein Stringarray
        width = FileHandler.parseInt(data[0]);                  //ließt die ersten vier Daten aus der Weltdatei (erste vier Werte im Array)
        height = FileHandler.parseInt(data[1]);
        pposx = FileHandler.parseInt(data[2]);
        pposy = FileHandler.parseInt(data[3]);
        worldData = new int[width][height];                     //worldData als neues multidimensionales Integerarray

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                    worldData[x][y] = FileHandler.parseInt(data[(x + y * width) + 4]);      //Weltdaten werden gelesen und ins multidimensionale Array geschrieben
            }
        }
        Game.getPlayer().teleport(pposx, pposy);                 //Spieler wird an die gespeicherte Position teleportiert
        Game.gameState = 0;                                      //GameState auf 0 gesetzt => Spiel wird angezeigt & läuft
    }

    public void saveWorld(Player player)
    {
        String[] saveData = new String[width*height + 4];         //Neues Stringarray mit der Länge breite*höhe+4 für weltdata + 4 Werte für Spielerpos und Weltgröße
        saveData[0] = String.valueOf(width);                      //Setzen der ersten vier Werte
        saveData[1] = String.valueOf(height);
        saveData[2] = String.valueOf((int)(player.getXPosition()));
        saveData[3] = String.valueOf((int)(player.getYPosition()));
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                saveData[(x+y*width) + 4] = String.valueOf(worldData[x][y]);        //Stringarray wird mit der Weltdata gefüllt
            }
        }
        FileHandler.writeWorldAsFile(path, saveData);                               //saveData wird vom FileWriter in eine Textdatei gespeichert
    }

    public void createWorld(int width)
    {
        int x = 0;
        try
        {
            while (true)
            {
                File worldFile = new File("res/worlds/world" + x + ".txt");
                if (!worldFile.createNewFile()) {                   //Versucht, eine Datei mit diesem Namen zu erstellen; wenns nicht geht, wird die Zahl im Weltnamen erhöht
                    x++;
                }
                else
                {
                    System.out.println("Welt " + "world" + x + " gespeichert.");
                    break;                                          //Aus dem while-loop gehen
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String[] saveData = new String[width * 30 + 4];             //Wird zukünftig die Daten der generierten Welt enthalten

        Arrays.fill(saveData, "1");     //TEMPORÄR!!!!!!!!

        saveData[0] = String.valueOf(width);                        //Wie üblich erste vier Werte schreiben
        saveData[1] = String.valueOf(30);
        saveData[2] = String.valueOf(0);
        saveData[3] = String.valueOf(0);

        FileHandler.writeWorldAsFile("/worlds/world" + x + ".txt", saveData);       //saveData wird in die Textdatei geschrieben
        Game.getWorldsMenu().init();        //World-select-menu wird neu initialisiert, damit während der runtime erstellte Dateien angezeigt werden

        loadWorld("/worlds/world" + x + ".txt");
    }

    public void tick(JFrame fenster)
    {
        Point p = fenster.getMousePosition();

        if (p != null)
        {
            mposx = p.getX();           //Holt sich x und y der Mausposition
            mposy = p.getY();
        }
        if(MouseInput.leftMousePressed && (int)((mposx+Game.poffx)/60) < width && (int)((mposy-28+Game.poffy)/60) < height && (int)((mposx+Game.poffx)/60)>=0 && (int)((mposy-28+Game.poffy)/60)>=0)
        {
            worldData[(int)((mposx+Game.poffx)/60)][(int)((mposy-28+Game.poffy)/60)] = Overlay.pos;     //Wenn die Maus innerhalb der Welt gelinksklickt wird => Block platzieren
        }
    }

    public void render(Graphics graphics)
    {
        int XStart = (int) Math.max(0,Game.poffx/60);                               //Ermittelt die Blöcke , die sich innerhalb des Bildes befinden
        int XEnd = (int) Math.min(width, (Game.poffx + Game.WIDTH)/60 + 1);
        int YStart = (int) Math.max(0, Game.poffy/60);
        int YEnd = (int) Math.min(height, (Game.poffy + Game.HEIGHT)/60 + 1);

        graphics.drawImage(Assets.worldBackground, 0,0, Game.getFenster().getWidth(), Game.getFenster().getHeight(), null);     //Hintergrund abbilden
        for (int y = YStart; y < YEnd; y++)
        {
            for (int x = XStart; x < XEnd; x++)
            {
                getBlock(x, y).render(graphics, (int) (x*60 - (Game.poffx)), (int) (y*60 - (Game.poffy)));      //NUR Blöcke im Bild rendern => Weniger CPU-Auslastung
            }
        }
        //Select-Box bei der Maus abbilden
        graphics.drawImage(Assets.select, (int)(((mposx+Game.poffx)/60))*60-(int)Game.poffx, (int)(((mposy-28+Game.poffy)/60))*60-(int)Game.poffy, 60, 60, null);
    }

    public Block getBlock(int posx, int posy)
    {
        Block block = Block.blocks[worldData[posx][posy]];
        if (block == null)
        {
            return Block.blocks[3];
        }
        return block;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
