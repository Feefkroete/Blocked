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
            BufferedReader reader = new BufferedReader(new FileReader(path));
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

    public static double parseDouble(String nummer) {
        try
        {
            return Double.parseDouble(nummer);
        }
        catch (NumberFormatException n)
        {
            n.printStackTrace();
            return 0;
        }
    }

    public static void writeStringAsFile(String path, String[] stringData, int type)
    {
        BufferedWriter bufferedWriter;
        try
        {
            System.out.println(path);
            bufferedWriter = new BufferedWriter(new FileWriter(path));
            for(int i = 0; i< stringData.length; i++)
            {
                bufferedWriter.write(stringData[i] + " ");
                if (type == 0) {
                    if (i == 1 || i == 3) {
                        bufferedWriter.newLine();
                    }
                }
                if (type == 1) {
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
