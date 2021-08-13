package de.matthi.blocked.world;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.entity.creature.Player;
import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.main.Overlay;
import de.matthi.blocked.structure.Tree;
import de.matthi.blocked.utils.FileHandler;
import de.matthi.blocked.utils.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
        final int height = 60;
        int nummer = 0;
        try
        {
            while (true)
            {
                File worldFile = new File(Game.worldsPath + "/world" + nummer + ".txt");
                if (!worldFile.createNewFile()) {                   //Versucht, eine Datei mit diesem Namen zu erstellen; wenns nicht geht, wird die Zahl im Weltnamen erhöht
                    nummer++;
                }
                else
                {
                    System.out.println("Welt " + "world" + nummer + " gespeichert.");
                    break;                                          //Aus dem while-loop gehen
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        int[][] worldData = new int[width][height];

        for (int x = 0; x<width; x++)       //Terraingeneration
        {
            double noise = ((0.2*Math.sin(0.3*x)+4)/3)*0.3*Math.sin(0.15*x)+Math.sin(0.2*x*3)+18-(1*Math.sin(0.65*x)+2)-(2*Math.sin(0.3*x)+7)*((2.5*Math.sin(0.25*x)+4)/100)*19;
            for (int y = 0; y< height; y++)
            {
                if(y > noise)
                {
                    if (worldData[x][y-1] == 4)     //Oberfläche (=Gras) wird generiert
                    {
                        worldData[x][y] = 0;
                    }
                    else {
                        if (worldData[x][y - 1] == 0 || worldData[x][y - 2] == 0 || worldData[x][y - 3] == 0) {     //Generierung von Dirt bis 4 Blöcke unter dem Gras
                            worldData[x][y] = 5;
                        } else {
                            worldData[x][y] = 1;        //Rest ist Stein
                        }
                    }
                }
                else
                {
                    worldData[x][y] = 4;    //Wenn überhalb der Oberfläche => Luft
                }
            }
        }

        int prevx = 0;      //x-koordinate des vorherigen Baumes für Mindestabstand
        for (int x = 0; x<width-4; x++) {
            if (Math.random() < 0.2 && prevx+2 < x) {       //Wenn der Zufall es gut meint und der Mindestabstand eingehalten wird
                for (int y = 0; y<height; y++) {
                    if (worldData[x][y] == 0) {
                        int[][] treeData = Tree.generate();     //Einen Baum generieren
                        if (y - Tree.HEIGHT -1>= 0) {       //Darauf achten, dass die Bäume in die Welt passen
                            System.out.println();
                            for (int ax = 0; ax < Tree.WIDTH; ax++) {
                                for (int ay = 0; ay < Tree.HEIGHT; ay++) {
                                    if (treeData[ax][ay] != 4 && worldData[(x - ax)+(Tree.WIDTH/2)][y - ay - 1] == 4) {
                                        prevx = x;
                                        worldData[(x - ax)+(Tree.WIDTH/2)][y - ay - 1] = treeData[ax][ay];
                                    }
                                }
                            }
                        }
                        else {
                            System.out.println(" -> Canceled!");
                        }
                    }
                }

            }
        }
        String[] saveData = new String[width * height + 4];


        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                saveData[(x+y*width) + 4] = String.valueOf(worldData[x][y]);        //Stringarray wird mit der Weltdata gefüllt
            }
        }

        saveData[0] = String.valueOf(width);                        //Wie üblich erste vier Werte schreiben
        saveData[1] = String.valueOf(height);
        saveData[2] = String.valueOf(0);
        saveData[3] = String.valueOf(0);

        FileHandler.writeWorldAsFile("/world" + nummer + ".txt", saveData);       //saveData wird in die Textdatei geschrieben
        Game.getWorldsMenu().init();        //World-select-menu wird neu initialisiert, damit während der runtime erstellte Dateien angezeigt werden

        loadWorld("/world" + nummer + ".txt");
    }

    public void tick(JFrame fenster)
    {
        Point p = fenster.getMousePosition();

        if (p != null)
        {
            mposx = p.getX();           //Holt sich x und y der Mausposition
            mposy = p.getY();
        }
        if((int)((mposx+Game.poffx)/60) < width && (int)((mposy-28+Game.poffy)/60) < height && (int)((mposx+Game.poffx)/60)>=0 && (int)((mposy-28+Game.poffy)/60)>=0) {
            if (MouseInput.leftMousePressed) {
                worldData[(int) ((mposx + Game.poffx) / 60)][(int) ((mposy - 28 + Game.poffy) / 60)] = Overlay.selectedBlock;     //Wenn die Maus innerhalb der Welt gelinksklickt wird => Block platzieren
            }
            if (MouseInput.middleMouseClicked) {
                Overlay.selectedBlock = worldData[(int) ((mposx + Game.poffx) / 60)][(int) ((mposy - 28 + Game.poffy) / 60)];
            }
            if (MouseInput.rightMouseClicked) {
                worldData[(int) ((mposx + Game.poffx) / 60)][(int) ((mposy - 28 + Game.poffy) / 60)] = 4;
            }
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
                if (getBlock(x, y).isWallBlock()) {
                    graphics.drawImage(Assets.wallBlockOverlay, (int) (x*60 - (Game.poffx)), (int) (y*60 - (Game.poffy)), 60, 60, null);
                }
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
