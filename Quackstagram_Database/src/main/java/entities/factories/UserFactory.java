package entities.factories;

import database.Database;
import entities.Picture;
import entities.User;

public class UserFactory
{
    public static User create(String username, String password, String bio, Picture profilePicture)
    {
        return new User(getNextID(), username, password, bio, profilePicture);
    }

    private static int getNextID()
    {
        return Database
                .getUsers()
                .size() + 1;
    }
}