package de.matthi.blocked.block;

import de.matthi.blocked.block.blocks.*;

import java.util.ArrayList;
import java.util.List;

public class BlockRegistry {
    public static List<Block> blocks = new ArrayList<>();

    public static void init() {

        //Reihenfolge in der List nicht ändern! Ids und shit sind scheiß egal; Listposition aber nicht!!11!1!11!

        blocks.add(new Air());
        blocks.add(new SimpleBlock("grass", "grass", true, false, true));
        blocks.add(new SimpleBlock("dirt", "dirt", true, false, true));
        blocks.add(new SimpleBlock("sand", "sand", true, false, true));
        blocks.add(new SimpleBlock("gravel", "gravel", true, false, true));
        blocks.add(new SimpleBlock("clay", "clay", true, false, true));
        blocks.add(new SimpleBlock("stone", "stone", true, false, true));
        blocks.add(new SimpleBlock("marble", "marble", true, false, true));
        blocks.add(new SimpleBlock("basalt", "basalt", true, false, true));
        blocks.add(new SimpleBlock("andesite", "andesite", true, false, true));
        blocks.add(new SimpleBlock("trunk", "trunk", false, false, false));
        blocks.add(new SimpleBlock("leaves", "leaves", false, false, true));
        blocks.add(new SimpleBlock("glass", "glass", true, false, true));
        blocks.add(new SimpleBlock("stone_bricks", "stone_bricks", true, false, true));
        blocks.add(new SimpleBlock("mud_bricks", "mud_bricks", true, false, true));
    }
}
