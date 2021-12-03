package de.matthi.blocked.main;

import de.matthi.blocked.gfx.Assets;
import de.matthi.blocked.menu.*;
import de.matthi.blocked.player.Player;
import de.matthi.blocked.utils.ConfigHandler;
import de.matthi.blocked.utils.KeyInput;
import de.matthi.blocked.utils.MouseInput;
import de.matthi.blocked.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.CodeSource;

public class Game extends Canvas implements Runnable
{
    public static final double VERSION = 1.51;

    public static final String name = "Blocked";
    public static final int WIDTH = 1400;
    public static final int HEIGHT = WIDTH/16*9;
    public static int fps = 70;
    public static final int tps = 40;
    public static JFrame window;
    public static String worldsPath;
    public static String decodedPath;
    public static File thisFile;

    public static double poffx, poffy;

    public static int gameState;
    public static long currentFPS, currentTPS;
    public static int nsperframe = 1000000000/fps;
    public static boolean showTPSFPS = false;

    private static World world;
    private static final Overlay overlay = new Overlay();
    private static Player player;
    private static final MainMenu mainMenu = new MainMenu();
    private static final SelectWorldsMenu worldsMenu = new SelectWorldsMenu();
    private static final NewWorldMenu newWorldMenu = new NewWorldMenu();
    private static final OptionsMenu optionsMenu = new OptionsMenu();
    private static final UpdateMenu updateMenu = new UpdateMenu();
    private static Font font;

    public void init() throws URISyntaxException {
        new Thread(this).start();
        font = new Font("Arial", Font.BOLD, 21);
        player = new Player(World.pposx, World.pposy, 60, 60, 15, 10, 10, Assets.spieler2);
        world = new World();
        CodeSource path = Game.class.getProtectionDomain().getCodeSource();
        thisFile = new File(path.getLocation().toURI().getPath());
        String parentDir = thisFile.getParentFile().getPath();
        decodedPath = URLDecoder.decode(parentDir, StandardCharsets.UTF_8);
        worldsPath = decodedPath + "/BLOCKED_WELTEN";
        File worlds = new File(worldsPath);
        if (worlds.mkdir()) {
            System.out.println("Created World Folder at: " + worldsPath);
        }
        else {
            System.out.println("Found existing World Folder at: " + worldsPath);
        }
        worldsMenu.init();
        ConfigHandler.init();
        optionsMenu.init();
        Hotbar.init();
    }

    public Game()
    {
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(false);
        MouseInput mouseInput = new MouseInput();
        addMouseListener(mouseInput);

        gameState = 1;

        window = new JFrame(name);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setIconImage(Assets.spieler3);
        window.add(this, BorderLayout.CENTER);

        KeyInput keyInput = new KeyInput();
        window.addKeyListener(keyInput);
        window.addMouseWheelListener(mouseInput);

        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    public void run()
    {
        long lasttimeTick = System.nanoTime();      //  <-  müssen 2 verschiedene Variablen sein,
        long lasttimeFrame = System.nanoTime();     //  <-  weil sie unten jeweils um 1 reduziert werden und ticks und frames unterschiedlich schnell sind

        long lastSecond = 0;
        long tick = 0;
        long frame = 0;

        int nspertick = 1000000000/tps;     //umrechnung tps und fps in nanosekunden pro tick/frame

        double deltaTick = 0;       //zeitliche Differenz zwischen dem letzten run-loop und dem jetzigen
        double deltaFrame = 0;

        while (true)
        {
            long now = System.nanoTime();
            deltaTick += (double) (now-lasttimeTick) / nspertick;       // delta wird hier berechnet und durch die nanosekunden pro tick/frame geteilt
            lasttimeTick = now;
            if (deltaTick >= 1)
            {
                tick();
                tick++;
                deltaTick--;
            }

            deltaFrame += (double) (now-lasttimeFrame) / nsperframe;
            lasttimeFrame = now;
            if (deltaFrame >=1)
            {
                render();
                frame++;
                deltaFrame--;
            }

            if (System.currentTimeMillis()-lastSecond >= 1000) {
                lastSecond=System.currentTimeMillis();
                currentTPS = tick;
                currentFPS = frame;
                tick = 0;
                frame = 0;
                if (player != null) {
                    player.healTick();
                    player.foodWaterRandomTick();
                }
            }
        }
    }

    public void tick()
    {
        switch (gameState) {
            case 0 -> {
                world.tick(window);
                player.tick();
                KeyInput.tick();
                if (KeyInput.save) {
                    world.saveWorld(player);
                    System.out.println("Welt gespeichert");
                }
                if (KeyInput.inv) {
                    gameState = 4;
                }
            }
            case 1 -> mainMenu.tick(window);
            case 2 -> worldsMenu.tick(window);
            case 3 -> newWorldMenu.tick();
            case 4 -> {
                world.tick(window);
                player.tick();
                Inventory.tick();
                if (!KeyInput.inv) {
                    gameState = 0;
                }
            }
            case 5 -> optionsMenu.tick(window);
            case 6 -> updateMenu.tick(window);
        }
    }

    public void render()
    {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null)
        {
            createBufferStrategy(3);    //aktiviert trippleBuffering, wenn keine Bufferstrategy gesetzt ist
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.setFont(font);
        graphics.clearRect(0,0,getWidth(), getHeight());

        if(gameState == 0 || gameState == 4)  //Welt rendern
        {
            world.render(graphics);
            player.render(graphics);
            overlay.render(graphics);
        }
        if(gameState == 1)  //MainMenu rendern
        {
            mainMenu.render(graphics);
        }
        if(gameState == 2) //WorldSelectMenu rendern
        {
            worldsMenu.render(graphics);
        }
        if(gameState == 3)
        {
            newWorldMenu.render(graphics);
        }
        if (gameState == 4) {
            Inventory.render(graphics);
        }
        if (gameState == 5) {
            optionsMenu.render(graphics);
        }
        if (gameState == 6) {
            updateMenu.render(graphics);
        }

        graphics.dispose();         //Leert den cache?
        bufferStrategy.show();      //Zeigt das Bild an
    }

        /*----------------GETTERS------------------*/

    public static World getWorld() {
        return world;
    }

    public static Player getPlayer() {
        return player;
    }

    public static SelectWorldsMenu getWorldsMenu() {
        return worldsMenu;
    }

    public static JFrame getWindow()
    {
        return window;
    }
}
