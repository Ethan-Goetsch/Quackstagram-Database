package database;

import entities.Picture;
import entities.Post;
import entities.User;
import entities.Notification;

import java.util.ArrayList;
import java.util.List;

public class DatabaseModel
{
    public final List<User> users;
    public final List<Post> posts;
    public final List<Picture> pictures;
    public final List<Notification> notifications;

    public DatabaseModel()
    {
        users = new ArrayList<>();
        posts = new ArrayList<>();
        pictures = new ArrayList<>();
        notifications = new ArrayList<>();
    }

    public DatabaseModel(List<User> users, List<Post> posts, List<Picture> pictures, List<Notification> notifications)
    {
        this.users = users;
        this.posts = posts;
        this.pictures = pictures;
        this.notifications = notifications;
    }
}