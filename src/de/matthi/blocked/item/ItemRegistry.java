package de.matthi.blocked.item;

import de.matthi.blocked.item.items.*;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {
    public static List<Item> items= new ArrayList<>();

    public static void init() {
        //----- BLOCKS -----//
        items.add(new BlockItem("grass", false, true, 2));
        items.add(new BlockItem("dirt", false, true, 4));
        items.add(new BlockItem("sand", false, true, 6));
        items.add(new BlockItem("gravel", false, true, 8));
        items.add(new BlockItem("clay", false, true, 10));
        items.add(new BlockItem("stone", false, true, 12));
        items.add(new BlockItem("marble", false, true, 14));
        items.add(new BlockItem("basalt", false, true, 16));
        items.add(new BlockItem("andesite", false, true, 18));
        items.add(new BlockItem("trunk", false, false, 19));
        items.add(new BlockItem("leaves", false, false, 21));
        items.add(new BlockItem("glass", false, true, 22));
        items.add(new BlockItem("stone_bricks", false, true, 24));
        items.add(new BlockItem("mud_bricks", false, true, 26));

        //----- OTHER ITEMS -----//
        items.add(new EntityRemover());
        items.add(new PigSpawner());
        items.add(new TestFood());
        items.add(new TestDrink());
    }
}
