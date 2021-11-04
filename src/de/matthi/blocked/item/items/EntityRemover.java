package de.matthi.blocked.item.items;

import de.matthi.blocked.entity.creature.Creature;
import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.Item;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.utils.MouseInput;

public class EntityRemover extends Item {

    public EntityRemover() {
        super(Assets.entityRemover, false);
    }

    @Override
    public void leftClickAction() {
        Creature target = Game.getWorld().getTargetedCreature();
        if (target != null) {
            Game.getWorld().getCreatureData().remove(Game.getWorld().getTargetedCreature());
        }
        MouseInput.leftMousePressed = false;
    }

    @Override
    public void middleClickAction() {

    }

    @Override
    public void rightClickAction() {
        Creature target = Game.getWorld().getTargetedCreature();
        if (target != null) {
            Game.getWorld().getCreatureData().remove(Game.getWorld().getTargetedCreature());
        }
        MouseInput.rightMouseClicked = false;
    }
}
