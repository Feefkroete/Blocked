package de.matthi.blocked.main;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.Item;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Hotbar {
    public static int selectedSlot = 0;
    private static final BufferedImage[] textures = new BufferedImage[8];
    private static final BufferedImage renderedTexture = new BufferedImage(84, 466, 2);

    public static void init() {
        for (int i = 0; i<8; i++) {
            BufferedImage texture = new BufferedImage(42, 233, 2);
            Graphics graphics = texture.createGraphics();
            graphics.drawImage(Assets.hotbar, 0, 0, 42, 233, null);
            graphics.drawImage(Assets.inventorySelectBox, 10, 11+ i*27, 22, 22, null);
            graphics.dispose();
            textures[i] = texture;
        }
    }

    public static void up() {
        if (selectedSlot < 7) {
            selectedSlot ++;
        }
        else {
            selectedSlot = 0;
        }
        update();
    }
    public static void down() {
        if (selectedSlot > 0) {
            selectedSlot --;
        }
        else {
            selectedSlot = 7;
        }
        update();
    }

    public static void tick() {

    }

    public static void update() {
        Graphics g = renderedTexture.createGraphics();
        g.drawImage(textures[selectedSlot], 0, 0, 84, 466, null);
        for (int i = 0; i<8; i++) {
            if (Inventory.commonSlotItem[i] != null) {
                g.setColor(Color.BLUE);
                g.setFont(new Font("Arial", Font.BOLD, 18));
                Inventory.commonSlotItem[i].render(g, 28, i*54 + 30, 28, 28, Inventory.commonSlotCount[i]);
            }
        }
        g.dispose();
    }

    public static void render(Graphics graphics) {
        double scale = 2.5 * (Game.getWindow().getWidth()/1400D);
        graphics.drawImage(renderedTexture, Game.getWindow().getWidth()/100, Game.getWindow().getHeight()/2-(int)(233*scale/1.8), (int)(42*scale), (int)(233*scale), null);
    }

    public static Item getSelectedItem() {
        return Inventory.commonSlotItem[selectedSlot];
    }
}
