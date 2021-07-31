package de.matthi.blocked.structure;

public class Tree {

    public static int WIDTH;
    public static int HEIGHT;

    public static int[][] generate()
    {
        double r = (Math.random());
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
        HEIGHT = WIDTH+(int)(Math.random()*5)-1;
        int split = (HEIGHT/2)+(int)(Math.random()*HEIGHT/2);

        int[][] treeBlocks = new int[WIDTH][HEIGHT];

        for (int x = 0; x<WIDTH; x++)
        {
            for (int y = 0; y<HEIGHT; y++)
            {
                if(y<split)
                {
                    if(x == ((int)(WIDTH/2))+1)
                    {
                        treeBlocks[x][y] = 2;
                    }
                }
                else
                {
                    treeBlocks[x][y] = 4;
                }
            }
        }

        return treeBlocks;
    }
    public static int[][] generate(int width, int height)
    {
        WIDTH = width;
        HEIGHT = height;
        int[][] treeBlocks = new int[width][height];
        return treeBlocks;
    }
}
