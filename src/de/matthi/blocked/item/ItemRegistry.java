package de.matthi.blocked.item;

import de.matthi.blocked.item.items.*;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {
    public static List<Item> items= new ArrayList<>();

    public static void init() {
        //----- BLOCKS -----//
        items.add(new GrassItem());
        items.add(new DirtItem());
        items.add(new DirtWallItem());
        items.add(new StoneItem());
        items.add(new StoneBricksItem());
        items.add(new StoneBricksWallItem());
        items.add(new MudBricksItem());
        items.add(new MudBricksWallItem());
        items.add(new SandItem());
        items.add(new TrunkItem());
        items.add(new LeavesItem());
        items.add(new GlassItem());
        items.add(new GlassWallItem());
        items.add(new AirItem());

        //----- OTHER ITEMS -----//
        items.add(new EntityRemover());
        items.add(new PigSpawner());
        items.add(new TestFood());
        items.add(new TestDrink());
    }
}
