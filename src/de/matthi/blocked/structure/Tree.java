package de.matthi.blocked.structure;

public class Tree {

    public static int WIDTH;
    public static int HEIGHT;

    public static int[][] generate()
    {
        double r = (Math.random());     //Generierung einer zufälligen Breite
        if (r<0.2)
        {
            WIDTH = 3;
        }
        else
        {
            if (r>0.8)
            {
                WIDTH = 7;
            }
            else
            {
                WIDTH = 5;
            }
        }
        HEIGHT = WIDTH+(int)(Math.random()*5)-1;    //Höhe zwar abhängig von Breite, allerdings auch zu gewissem Grad zufällig
        int split = (HEIGHT/2)+(int)(Math.random()*HEIGHT/3);   //Zufällige Unterteilung Stammhöhe/Blätterhöhe
        int[][] treeBlocks = new int[WIDTH][HEIGHT];

        for (int y = HEIGHT-1; y>-1; y--)
        {
            for (int x = 0; x<WIDTH; x++)
            {
                if(y<split)     //Wenn die Schleife gerade beim Stamm (unterhalb der Blätterlinie) ist => Generierung von Stamm und Luft um Stamm
                {
                    if(x == ((WIDTH/2)))
                    {
                        treeBlocks[x][y] = 19;
                    }
                    else {
                        treeBlocks[x][y] = 0;
                    }
                }
                else
                {
                    treeBlocks[x][y] = 21;       //Baumkrone mit Blättern gefüllt
                }
            }
        }
        treeBlocks[0][HEIGHT-1] = 0;
        treeBlocks[WIDTH-1][HEIGHT-1] = 0;
        //TODO: Mehr randomisation bei den Blättern! (Also die Blöcke neben und unter den oberen Ecken)
        return treeBlocks;
    }
}
