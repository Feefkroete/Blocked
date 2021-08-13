package de.matthi.blocked.main;

import de.matthi.blocked.gfx.Assets;

import java.net.URISyntaxException;

public class Launcher
{
    public static void main(String[] args) throws URISyntaxException {
        Assets.init();
        new Game().init();
    }
}


