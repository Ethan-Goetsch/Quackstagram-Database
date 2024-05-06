package entities.factories;

import database.Database;
import entities.Picture;
import entities.Post;
import entities.User;

public class PostFactory
{
    public static Post create(User author, String title, Picture content)
    {
        return new Post(getNextID(), author, title, content);
    }

    private static int getNextID()
    {
        return Database
                .getPosts()
                .size() + 1;
    }
}