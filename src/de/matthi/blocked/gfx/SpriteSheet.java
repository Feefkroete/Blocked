package de.matthi.blocked.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage bufferedImage;

    public SpriteSheet(BufferedImage bufferedImage)
    {
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage pacman(int x, int y, int width, int height)
    {
        return bufferedImage.getSubimage(x, y, width, height);
    }
}
