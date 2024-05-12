package database;

import database.queries.get.*;
import entities.data.PostData;
import entities.data.UserData;

public class EntityDataLoader
{
    public static EntityData loadDataFromDatabase()
    {
        /*
        Get User Data
            Get data from users
            Get all liked posts
            Get all followers
            Get all followings
         */

        var users = Database.executeQueryAndReadResult(new GetAllUserQuery());
        var posts = Database.executeQueryAndReadResult(new GetAllPostQuery());
        var pictures = Database.executeQueryAndReadResult(new GetAllPictureQuery());
        var notifications = Database.executeQueryAndReadResult(new GetAllNotificationQuery());

        for (UserData user : users)
        {
            user.followers().addAll(Database.executeQueryAndReadResult(new GetFollowersForUserQuery(user.id())));
            user.followings().addAll(Database.executeQueryAndReadResult(new GetFollowingsForUser(user.id())));
            user.posts().addAll(Database.executeQueryAndReadResult(new GetPostsForUser(user.id())));
        }

        for (PostData post : posts)
        {
            post.likes().addAll(Database.executeQueryAndReadResult(new GetLikesForPost(post.id())));
        }

        return new EntityData(users, posts, pictures, notifications);
    }
}