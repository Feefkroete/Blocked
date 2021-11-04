package de.matthi.blocked.main;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.Item;
import de.matthi.blocked.item.ItemRegistry;
import de.matthi.blocked.utils.KeyInput;
import de.matthi.blocked.utils.MouseInput;

import java.awt.*;

public class Overlay
{
    public static Item selectedItem;
    private int mposx = 0, mposy = 0;
    private static int scrollPos = 0;

    public Overlay()
    {
        selectedItem = ItemRegistry.items.get(0);
    }

    public static void up()
    {
        if(scrollPos < ItemRegistry.items.size()-1) {
            scrollPos++;
        }
        else {
            scrollPos = 0;
        }
        selectedItem = ItemRegistry.items.get(scrollPos);
    }
    public static void down()
    {
        if (scrollPos > 0)
        {
            scrollPos--;
        }
        else
        {
            scrollPos = ItemRegistry.items.size()-1;
        }
        selectedItem = ItemRegistry.items.get(scrollPos);
    }

    public void render(Graphics graphics)
    {
        if (Game.showTPSFPS) {
            graphics.drawString("TPS: " + Game.currentTPS + " | FPS: " + Game.currentFPS, 3, 21);
        }
        if (KeyInput.fly) {
            graphics.drawImage(Assets.flight, Game.WIDTH - 70, -7, 65, 65, null);
        }
        graphics.drawImage(Assets.inv, ((Game.WIDTH)/2)-32, (Game.HEIGHT)-80, 56, 56, null);
        selectedItem.render(graphics, ((Game.WIDTH)/2)-27, (Game.HEIGHT)-75, 46, 46);
        if (Game.gameState == 4) {      //Wenn Inventar geöffnet
            int yoffset = 0;
            int xoffset = 0;
            for (int i = 0; i<ItemRegistry.items.size(); i++) {     //Für jedes Item im Spiel
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
                        scrollPos = i;
                        selectedItem = ItemRegistry.items.get(scrollPos);
                        selectedItem.render(graphics, ((Game.WIDTH)/2)-27, (Game.HEIGHT)-75, 46, 46);
                    }
                }
                else {
                    graphics.drawImage(Assets.inv, (((Game.WIDTH) / 2) - 200) + xoffset * 56, 70 + yoffset, 56, 56, null);
                }
                ItemRegistry.items.get(i).render(graphics, (((Game.WIDTH) / 2) - 195) + xoffset * 56, 75 + yoffset, 46, 46);
            }
        }
    }
}
