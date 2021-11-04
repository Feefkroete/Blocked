package de.matthi.blocked.item.items;

import de.matthi.blocked.entity.creature.Pig;
import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.Item;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.utils.MouseInput;
import de.matthi.blocked.world.World;

public class PigSpawner extends Item {
    public PigSpawner() {
        super(Assets.pigSpawner,false);
    }

    @Override
    public void leftClickAction() {
        World world = Game.getWorld();
        world.getCreatureData().add(new Pig(world.getMposx()+Game.poffx-30, world.getMposy()+Game.poffy-45, 20));
        MouseInput.leftMousePressed = false;
    }

    @Override
    public void middleClickAction() {

    }

    @Override
    public void rightClickAction() {

    }
}
