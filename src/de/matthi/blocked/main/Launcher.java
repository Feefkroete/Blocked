package de.matthi.blocked.main;

import de.matthi.blocked.gfx.Assets;

public class Launcher
{
    public static void main(String[] args)
    {
        Assets.init();
        new Game().init();
    }
}


