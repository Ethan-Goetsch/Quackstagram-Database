package entities.factories;

import database.Database;
import entities.Picture;

public class PictureFactory
{
    public static Picture create(String pathFile)
    {
        return new Picture(getNextID(), pathFile);
    }

    private static int getNextID()
    {
        return Database
                .getPictures()
                .size() + 1;
    }
}