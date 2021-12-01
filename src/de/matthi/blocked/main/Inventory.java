package de.matthi.blocked.main;

import de.matthi.blocked.item.Item;
import de.matthi.blocked.utils.MouseInput;

import java.awt.*;

public class Inventory {
    public static int[] commonSlotCount = new int[88];
    public static Item[] commonSlotItem = new Item[88];
    public static int[] armorSlotCount = new int[12];
    public static Item[] armorSlotItem = new Item[12];
    public static int mouseSlotCount;
    public static Item mouseSlotItem;

    public static boolean addItem(Item type) {
        for (int i = 0; i< commonSlotItem.length; i++) {
            if (commonSlotItem[i]!=null) {    //Wenn in dem Slot ein Item ist und es das gedropte ist auf den Stack eines dazurechnen
                if (commonSlotItem[i].equals(type) && commonSlotCount[i] < type.maxStackSize) {
                    commonSlotCount[i] += 1;
                    return true;
                }
            }
        }
        for (int i = 0; i< commonSlotItem.length; i++) {     //sonst neuen Stack im inv erstellen
            if (commonSlotItem[i] == null) {
                commonSlotItem[i] = type;
                commonSlotCount[i] = 1;
                return true;
            }
        }
        return false;       //Inventar voll
    }

    public static void tick() {
        if (Overlay.targetedSlot!=-1) {
            if (MouseInput.leftMousePressed) {
                if (mouseSlotItem == null) {
                    mouseSlotCount = commonSlotCount[Overlay.targetedSlot];
                    mouseSlotItem = commonSlotItem[Overlay.targetedSlot];
                    commonSlotItem[Overlay.targetedSlot] = null;
                    commonSlotCount[Overlay.targetedSlot] = 0;
                } else {
                    if (commonSlotItem[Overlay.targetedSlot] == null) {
                        commonSlotCount[Overlay.targetedSlot] = mouseSlotCount;
                        commonSlotItem[Overlay.targetedSlot] = mouseSlotItem;
                        mouseSlotCount = 0;
                        mouseSlotItem = null;
                    } else {
                        if (mouseSlotItem == commonSlotItem[Overlay.targetedSlot] && commonSlotCount[Overlay.targetedSlot]+mouseSlotCount < commonSlotItem[Overlay.targetedSlot].maxStackSize) {
                            commonSlotCount[Overlay.targetedSlot] += mouseSlotCount;
                            mouseSlotItem = null;
                            mouseSlotCount = 0;
                        }
                        else {
                            int tempCount = commonSlotCount[Overlay.targetedSlot];
                            Item tempItem = commonSlotItem[Overlay.targetedSlot];
                            commonSlotItem[Overlay.targetedSlot] = mouseSlotItem;
                            commonSlotCount[Overlay.targetedSlot] = mouseSlotCount;
                            mouseSlotCount = tempCount;
                            mouseSlotItem = tempItem;
                        }
                    }
                }
                MouseInput.leftMousePressed = false;
            }
            if (MouseInput.rightMouseClicked) {
                if (commonSlotItem[Overlay.targetedSlot] != null) {
                    if (mouseSlotItem == null) {
                        mouseSlotItem = commonSlotItem[Overlay.targetedSlot];
                        if (commonSlotCount[Overlay.targetedSlot] > 1) {
                            mouseSlotCount = commonSlotCount[Overlay.targetedSlot] / 2;
                            commonSlotCount[Overlay.targetedSlot] -= mouseSlotCount;
                        } else {
                            commonSlotItem[Overlay.targetedSlot] = null;
                            commonSlotCount[Overlay.targetedSlot] = 0;
                            mouseSlotCount = 1;
                        }
                    }
                    else if(commonSlotItem[Overlay.targetedSlot] == mouseSlotItem) {
                        if (commonSlotCount[Overlay.targetedSlot] < commonSlotItem[Overlay.targetedSlot].maxStackSize) {
                            commonSlotCount[Overlay.targetedSlot] += 1;
                            mouseSlotCount -= 1;
                        }
                    }
                }
                else {
                    commonSlotItem[Overlay.targetedSlot] = mouseSlotItem;
                    commonSlotCount[Overlay.targetedSlot] = 1;
                    mouseSlotCount -= 1;
                }
                MouseInput.rightMouseClicked = false;
                if (mouseSlotCount == 0) {
                    mouseSlotItem = null;
                }
            }
        }
    }

    public static void render(Graphics graphics) {
        if (mouseSlotItem != null) {
            graphics.drawImage(mouseSlotItem.texture, (int) Game.getWindow().getMousePosition().getX() + 8, (int) Game.getWindow().getMousePosition().getY(), 20, 20, null);
            graphics.setColor(Color.BLUE);
            graphics.drawString(String.valueOf(mouseSlotCount), (int)Game.getWindow().getMousePosition().getX() + 28- (String.valueOf(mouseSlotCount).length()*11), (int) Game.getWindow().getMousePosition().getY() + 32);
            graphics.setColor(Color.BLACK);
        }
    }
}