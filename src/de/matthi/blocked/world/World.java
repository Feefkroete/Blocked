package de.matthi.blocked.world;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.block.BlockRegistry;
import de.matthi.blocked.entity.creature.Creature;
import de.matthi.blocked.entity.creature.Pig;
import de.matthi.blocked.entity.itemEntity.ItemEntity;
import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.main.Inventory;
import de.matthi.blocked.main.Overlay;
import de.matthi.blocked.player.Player;
import de.matthi.blocked.structure.Tree;
import de.matthi.blocked.utils.FileHandler;
import de.matthi.blocked.utils.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class World
{
    private int width, height;
    private int[][] worldData;
    private final List<Creature> creatureData = new ArrayList<>();
    private final List<ItemEntity> itemData = new ArrayList<>();
    private double mposx, mposy;
    public static int pposx, pposy;
    private String path;

    public World()
    {

    }

    public void loadWorld(String path)
    {
        this.path = Game.worldsPath + path;
        String welt = FileHandler.loadFileAsString(this.path);       //ruft die Methode "loadFileAsString" im filehandler auf => returnt einen String (ach nee)
        String[] data = welt.split("\\s+");               //Spaltet den String bei jedem Leerzeichen und packt die Bruchstücke in ein Stringarray
        width = FileHandler.parseInt(data[0]);                  //ließt die ersten sieben Daten aus der Weltdatei (erste sieben Werte im Array)
        height = FileHandler.parseInt(data[1]);
        pposx = FileHandler.parseInt(data[2]);
        pposy = FileHandler.parseInt(data[3]);
        Game.getPlayer().setHp(FileHandler.parseInt(data[4]));
        Game.getPlayer().setFoodLevel(FileHandler.parseInt(data[5]));
        Game.getPlayer().setWaterLevel(FileHandler.parseInt(data[6]));
        worldData = new int[width][height];                     //worldData als neues multidimensionales Integerarray

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                    worldData[x][y] = FileHandler.parseInt(data[(x + y * width) + 7]);      //Weltdaten werden gelesen und ins multidimensionale Array geschrieben
            }
        }
        for (int i = height*width+7; i < data.length; i++) {
            String[] entitySplit = data[i].split(":");
            if (entitySplit[0].equals("0")) {
                creatureData.add(new Pig(FileHandler.parseInt(entitySplit[1]), FileHandler.parseInt(entitySplit[2]), FileHandler.parseInt(entitySplit[3])));
            }
        }
        Game.getPlayer().teleport(pposx, pposy);                 //Spieler wird an die gespeicherte Position teleportiert
        Game.gameState = 0;                                      //GameState auf 0 gesetzt => Spiel wird angezeigt & läuft
    }

    public void saveWorld(Player player)
    {
        String[] saveData = new String[width*height + 7 + creatureData.size()];         //Neues Stringarray mit der Länge breite*höhe+4 für weltdata + 4 Werte für Spielerpos und Weltgröße
        saveData[0] = String.valueOf(width);                      //Setzen der ersten vier Werte
        saveData[1] = String.valueOf(height);
        saveData[2] = String.valueOf((int)(player.getXPosition()));
        saveData[3] = String.valueOf((int)(player.getYPosition()));
        saveData[4] = String.valueOf(player.getHealth());
        saveData[5] = String.valueOf(player.getFoodLevel());
        saveData[6] = String.valueOf(player.getWaterLevel());
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                saveData[(x+y*width) + 7] = String.valueOf(worldData[x][y]);        //Stringarray wird mit der Weltdata gefüllt
            }
        }
        for (int i = 0; i< creatureData.size(); i++) {
            saveData[(height*width) + 7 + i] = creatureData.get(i).getType() + ":" + (int)creatureData.get(i).getPosX() + ":" + (int)creatureData.get(i).getPosY() + ":" + creatureData.get(i).getHp();
        }
        FileHandler.writeStringAsFile(path, saveData, 0);                               //saveData wird vom FileWriter in eine Textdatei gespeichert
        creatureData.clear();
        player.setHp(20);
        player.setWaterLevel(10);
        player.setFoodLevel(10);
    }

    public void createWorld(int width, int height)
    {
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
                    if (worldData[x][y-1] == 0)     //Oberfläche (=Gras) wird generiert
                    {
                        worldData[x][y] = 2;
                    }
                    else {
                        if (worldData[x][y - 1] == 2 || worldData[x][y - 2] == 2 || worldData[x][y - 3] == 2) {     //Generierung von Dirt bis 4 Blöcke unter dem Gras
                            worldData[x][y] = 3;
                        } else {
                            worldData[x][y] = 1;        //Rest ist Stein
                        }
                    }
                }
                else
                {
                    worldData[x][y] = 0;    //Wenn überhalb der Oberfläche => Luft
                }
            }
        }

        int prevx = 0;      //x-koordinate des vorherigen Baumes für Mindestabstand
        for (int x = 0; x<width-4; x++) {
            if (Math.random() < 0.2 && prevx+2 < x) {       //Wenn der Zufall es gut meint und der Mindestabstand eingehalten wird
                for (int y = 0; y<height; y++) {
                    if (worldData[x][y] == 2) {
                        int[][] treeData = Tree.generate();     //Einen Baum generieren
                        if (y - Tree.HEIGHT -1 >= 0) {       //Darauf achten, dass die Bäume in die Welt passen
                            for (int ax = 0; ax < Tree.WIDTH; ax++) {
                                for (int ay = 0; ay < Tree.HEIGHT; ay++) {
                                    if (treeData[ax][ay] != 0 && worldData[(x - ax)+(Tree.WIDTH/2)][y - ay - 1] == 0) {
                                        prevx = x;
                                        worldData[(x - ax)+(Tree.WIDTH/2)][y - ay - 1] = treeData[ax][ay];
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

        String[] saveData = new String[width * height + 7];


        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                saveData[(x+y*width) + 7] = String.valueOf(worldData[x][y]);        //Stringarray wird mit der Weltdata gefüllt
            }
        }

        saveData[0] = String.valueOf(width);                        //Wie üblich erste sieben Werte schreiben
        saveData[1] = String.valueOf(height);
        saveData[2] = String.valueOf(300);
        saveData[3] = String.valueOf(0);
        saveData[4] = String.valueOf(20);
        saveData[5] = String.valueOf(10);
        saveData[6] = String.valueOf(10);

        int newPposx = (int)(Math.random()*width+1);
        for (int i = 0; i<height-1; i++) {
            if (worldData[newPposx][i+1] == 2) {
                saveData[2] = String.valueOf(newPposx*60);
                saveData[3] = String.valueOf(i*60);
            }
        }

        FileHandler.writeStringAsFile(Game.worldsPath + "/world" + nummer + ".txt", saveData, 0);       //saveData wird in die Textdatei geschrieben
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
            if (Game.gameState != 4) {
                if (MouseInput.leftMousePressed) {
                    Overlay.selectedItem.leftClickAction();
                }
                if (MouseInput.middleMouseClicked) {
                    Overlay.selectedItem.middleClickAction();
                }
                if (MouseInput.rightMouseClicked) {
                    Overlay.selectedItem.rightClickAction();
                }
            }
        }
        if (Math.random() < 0.05) {                 //--- Gras wird zu Erde, wenn solider Block darüber ---//
            for (int y = 1; y < height-1; y++) {
                for (int x = 0; x < width-1; x++) {
                    if (Math.random() < 0.5) {
                        if (worldData[x][y] == 2 && BlockRegistry.blocks.get(worldData[x][y - 1]).isSolid() && Math.random() < 0.05) {
                            worldData[x][y] = 3;
                        }
                    }
                }
            }
        }
        for (Creature creatureDatum : creatureData) {
            creatureDatum.tick();
        }
        int s = itemData.size();
        for (int i = 0; i<s; i++) {
            itemData.get(i).tick();
            //Add to Inventory
            if (Math.abs(Game.getPlayer().getXPosition() - itemData.get(i).getPosX()-10) < 35 && Math.abs(Game.getPlayer().getYPosition() - itemData.get(i).getPosY()-5) < 35 && Inventory.addItem(itemData.get(i).getType())) {
                itemData.remove(itemData.get(i));
                s-=1;
            }
        }
    }

    public void render(Graphics graphics)
    {
        int XStart = (int) Math.max(0,Game.poffx/60);                               //Ermittelt die Blöcke , die sich innerhalb des Bildes befinden
        int XEnd = (int) Math.min(width, (Game.poffx + Game.WIDTH)/60 + 1);
        int YStart = (int) Math.max(0, Game.poffy/60);
        int YEnd = (int) Math.min(height, (Game.poffy + Game.HEIGHT)/60 + 1);

        graphics.drawImage(Assets.worldBackground, 0,0, Game.getWindow().getWidth(), Game.getWindow().getHeight(), null);     //Hintergrund abbilden
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
        for (Creature creatureDatum : creatureData) {
            creatureDatum.render(graphics);
        }
        for (ItemEntity itemEntityDatum : itemData) {
            itemEntityDatum.render(graphics);
        }
        //Select-Box bei der Maus abbilden
        if (Overlay.selectedItem.isBlockItem() && Game.gameState != 4) {
            graphics.drawImage(Assets.select, (int) (((mposx + Game.poffx) / 60)) * 60 - (int) Game.poffx, (int) (((mposy - 28 + Game.poffy) / 60)) * 60 - (int) Game.poffy, 60, 60, null);
        }
    }

    //-------------- GETTERS -----------------//

    public Block getBlock(int posx, int posy)
    {
        if (posx < width && posx >= 0 && posy < height && posy >= 0) {      //Stop this mf from returning null so ALL MY PROBLEMS ARE SOLVED
            return BlockRegistry.blocks.get(worldData[posx][posy]);
        }
        return BlockRegistry.blocks.get(1);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSelectedBlockX() {
        return (int) ((mposx + Game.poffx) / 60);
    }

    public int getSelectedBlockY() {
        return (int) ((mposy - 28 + Game.poffy) / 60);
    }

    public Block getSelectedBlock() {
        return BlockRegistry.blocks.get(worldData[(int) ((mposx + Game.poffx) / 60)][(int) ((mposy - 28 + Game.poffy) / 60)]);
    }

    public Creature getTargetedCreature() {
        for (Creature creatureDatum : creatureData) {
            if (mposx + Game.poffx > creatureDatum.getPosX() && mposx + Game.poffx < creatureDatum.getPosX() + creatureDatum.getWidth() && mposy - 28 + Game.poffy > creatureDatum.getPosY() && mposy - 28 + Game.poffy < creatureDatum.getPosY() + creatureDatum.getHeight()) {
                return creatureDatum;
            }
        }
        return null;
    }

    public double getMposx() {
        return mposx;
    }

    public double getMposy() {
        return mposy;
    }

    public List<Creature> getCreatureData() {
        return creatureData;
    }

    public List<ItemEntity> getItemData() {
        return itemData;
    }

    //----------------- SETTERS ----------------//

    public void setWorldDataAtPosition(int posX, int posY, int value) {
        worldData[posX][posY] = value;
    }
}
