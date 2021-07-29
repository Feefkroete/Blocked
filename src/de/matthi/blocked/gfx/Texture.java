package de.matthi.blocked.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Texture
{
    public static BufferedImage load (String path)
    {
        try {
            return ImageIO.read(Texture.class.getResource(path));
        } catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
