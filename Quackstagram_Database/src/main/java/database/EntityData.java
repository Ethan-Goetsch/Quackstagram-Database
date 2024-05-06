package database;

import entities.data.NotificationData;
import entities.data.PictureData;
import entities.data.PostData;
import entities.data.UserData;

import java.util.ArrayList;
import java.util.List;

public class EntityData
{
    public final List<UserData> users;
    public final List<PostData> posts;
    public final List<PictureData> pictures;
    public final List<NotificationData> notifications;

    public EntityData()
    {
        this.users = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.pictures = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public EntityData(List<UserData> users, List<PostData> posts, List<PictureData> pictures, List<NotificationData> notifications)
    {
        this.users = users;
        this.posts = posts;
        this.pictures = pictures;
        this.notifications = notifications;
    }
}