package de.matthi.blocked.item.items;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.item.Item;
import de.matthi.blocked.main.Game;
import de.matthi.blocked.utils.MouseInput;

public class TestFood extends Item {

    public TestFood() {
        super(Assets.testFood, false, 40);
    }

    @Override
    public void leftClickAction() {
        Game.getPlayer().addFood(1);
        MouseInput.leftMousePressed = false;
    }

    @Override
    public void middleClickAction() {

    }

    @Override
    public void rightClickAction() {

    }
}
