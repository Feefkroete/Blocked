package de.matthi.blocked.main;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.Item;
import de.matthi.blocked.utils.KeyInput;
import de.matthi.blocked.utils.MouseInput;

import java.awt.*;

public class Overlay
{
    public static int selectedBlock = 0;
    private int mposx = 0, mposy = 0;

    public Overlay()
    {

    }

    public static void up()
    {
        if(selectedBlock < Item.items.length-1)
        {
            selectedBlock++;
        }
        else
        {
            selectedBlock = 0;
        }
    }
    public static void down()
    {
        if (selectedBlock > 0)
        {
            selectedBlock--;
        }
        else
        {
            selectedBlock = Item.items.length-1;
        }
    }

    public void render(Graphics graphics)
    {
        if (KeyInput.fly) {
            graphics.drawImage(Assets.flight, Game.WIDTH - 70, -7, 65, 65, null);
        }
        graphics.drawImage(Assets.inv, ((Game.WIDTH)/2)-32, (Game.HEIGHT)-80, 56, 56, null);
        Item.items[selectedBlock].render(graphics, ((Game.WIDTH)/2)-27, (Game.HEIGHT)-75, 46, 46);
        if (Item.items[selectedBlock].isWallItem()) {
            graphics.drawImage(Assets.wallOverlay, ((Game.WIDTH)/2)-27, (Game.HEIGHT)-75, 46, 46, null);
        }
        if (Game.gameState == 4) {      //Wenn Inventar geöffnet
            int yoffset = 0;
            int xoffset = 0;
            for (int i = 0; i<Item.items.length; i++) {     //Für jedes Item im Spiel
                if (i % 7 == 0) {       //Alle sieben Items nächste Zeile Items
                    yoffset = yoffset+56;
                    xoffset = 0;
                }
                else {
                    xoffset++;
                }

                Point p = Game.getFenster().getMousePosition();
                if (p != null) {
                    mposx = (int) p.getX();
                    mposy = (int) p.getY();
                }

                //Wenn Maus über dem Item hovert
                if (mposx > (((Game.WIDTH)/2.0)-195) + xoffset*56-4 && mposx < (((Game.WIDTH)/2.0)-195) + xoffset*56 + 56-4 && mposy < 75 + yoffset + 82 && mposy > 75 + yoffset + 26) {
                    graphics.drawImage(Assets.selInv, (((Game.WIDTH) / 2) - 200) + xoffset * 56, 70 + yoffset, 56, 56, null);
                    if (MouseInput.leftMousePressed) {      //Wenn gehovertes Item angeklickt wird
                        selectedBlock = i;
                        Item.items[selectedBlock].render(graphics, ((Game.WIDTH)/2)-27, (Game.HEIGHT)-75, 46, 46);
                        if (Item.items[selectedBlock].isWallItem()) {
                            graphics.drawImage(Assets.wallOverlay, ((Game.WIDTH)/2)-27, (Game.HEIGHT)-75, 46, 46, null);
                        }
                    }
                }
                else {
                    graphics.drawImage(Assets.inv, (((Game.WIDTH) / 2) - 200) + xoffset * 56, 70 + yoffset, 56, 56, null);
                }
                    Item.items[i].render(graphics, (((Game.WIDTH) / 2) - 195) + xoffset * 56, 75 + yoffset, 46, 46);
                if (Item.items[i].isWallItem()) {
                    graphics.drawImage(Assets.wallOverlay, (((Game.WIDTH)/2)-195) + xoffset*56, 75 + yoffset, 46, 46, null);
                }
            }
        }
    }
}
