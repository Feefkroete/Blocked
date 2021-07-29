package de.matthi.blocked.utils;

import java.io.*;

public class FileHandler
{
    static String line;

    public static String loadFileAsString(String path)
    {
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("/home/matthi/Dokumente/Spiel/res" + path));
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static int parseInt(String nummer)
    {
        try
        {
            return Integer.parseInt(nummer);
        }
        catch (NumberFormatException n)
        {
            n.printStackTrace();
            return 0;
        }
    }

    public static void writeWorldAsFile(String path, String[] worldData)
    {
        BufferedWriter bufferedWriter;
        try
        {
            bufferedWriter = new BufferedWriter(new FileWriter("/home/matthi/Dokumente/Spiel/res" + path));
            for(int i = 0; i< worldData.length; i++)
            {
                bufferedWriter.write(worldData[i] + " ");
                if (i == 1 || i == 3)
                {
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
