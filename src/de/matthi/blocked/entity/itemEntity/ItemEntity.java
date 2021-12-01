package de.matthi.blocked.entity.itemEntity;

import de.matthi.blocked.block.Block;
import de.matthi.blocked.entity.Entity;
import de.matthi.blocked.item.Item;
import de.matthi.blocked.main.Game;

public class ItemEntity extends Entity {

    protected Item type;
    protected int fallSpeed;

    public ItemEntity(double posx, double posy, int width, int height, Item type) {
        super(posx, posy, width, height, type.texture);
        this.type = type;
    }

    public ItemEntity(double posx, double posy, Item type) {
        super(posx, posy, 20, 20, type.texture);
        this.type = type;
    }

    public void tick() {
        if (!isCollisionU() || fallSpeed<0) {
            fallSpeed +=1;
            move(fallSpeed);
        }
        else {
            fallSpeed = 0;
        }
    }

    public void move (double y) {
        for (int i = 0; i<y; i++) {
            if (posy+1+height < Game.getWorld().getHeight()*60) {
                Block lu = Game.getWorld().getBlock((int) (posx) / 60, (int) (posy+height+1) / 60);
                Block ru = Game.getWorld().getBlock((int) (posx + width) / 60, (int) (posy+height+1) / 60);
                if (!lu.isSolid() && !ru.isSolid()) {
                    posy += 1;
                } else {
                    break;
                }
            }
        }
    }

    public boolean isCollisionU() {
        if (posy+1+height < Game.getWorld().getHeight()*60) {
            Block lu = Game.getWorld().getBlock((int) (posx) / 60, (int) (posy+height+1) / 60);
            Block ru = Game.getWorld().getBlock((int) (posx + width) / 60, (int) (posy+height+1) / 60);
            return lu.isSolid() || ru.isSolid();
        }
        return true;
    }

    public Item getType() {
        return type;
    }
}
