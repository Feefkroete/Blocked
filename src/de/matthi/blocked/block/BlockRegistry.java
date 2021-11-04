package de.matthi.blocked.block;

import de.matthi.blocked.block.blocks.*;

import java.util.ArrayList;
import java.util.List;

public class BlockRegistry {
    public static List<Block> blocks = new ArrayList<>();

    public static void init() {
        blocks.add(new Air(0));
        blocks.add(new Stone(1));
        blocks.add(new Grass(2));
        blocks.add(new Dirt(3));
        blocks.add(new DirtWall(4));
        blocks.add(new StoneBricks(5));
        blocks.add(new StoneBricksWall(6));
        blocks.add(new Sand(7));
        blocks.add(new MudBricks(8));
        blocks.add(new MudBricksWall(9));
        blocks.add(new Trunk(10));
        blocks.add(new Leaves(11));
        blocks.add(new Glass(12));
        blocks.add(new GlassWall(13));
    }
}
