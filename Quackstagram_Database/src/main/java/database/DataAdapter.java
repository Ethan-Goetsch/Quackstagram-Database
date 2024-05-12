package database;

import entities.Notification;
import entities.Picture;
import entities.Post;
import entities.User;
import entities.data.NotificationData;
import entities.data.PictureData;
import entities.data.PostData;
import entities.data.UserData;

import java.util.stream.Collectors;

public class DataAdapter
{
    public static EntityData toData(DatabaseModel model)
    {
        var users = model.users.stream()
                .map(DataAdapter::toData)
                .toList();
        var posts = model.posts.stream()
                .map(DataAdapter::toData)
                .toList();
        var pictures = model.pictures.stream()
                .map(DataAdapter::toData)
                .toList();
        var notifications = model.notifications.stream()
                .map(DataAdapter::toData)
                .toList();
        return new EntityData(users, posts, pictures, notifications);
    }

    public static DatabaseModel toModel(EntityData entityData)
    {
        var users = entityData.users.stream()
                .map(data -> new User(data.id(), data.username(), data.password(), data.bio(), null, null, null, null))
                .collect(Collectors.toList());
        var posts = entityData.posts.stream()
                .map(data -> new Post(data.id(), null, data.title(), null))
                .collect(Collectors.toList());
        var pictures = entityData.pictures.stream()
                .map(data -> new Picture(data.id(), data.filePath()))
                .collect(Collectors.toList());
        var notifications  = entityData.notifications.stream()
                .map(data -> new Notification(data.id(), null, data.message()))
                .collect(Collectors.toList());

        for (var i = users.size() - 1; i >= 0; i--)
        {
            var user = users.get(i);
            var data = entityData.users.stream()
                    .filter(userData -> user.id() == userData.id())
                    .findFirst()
                    .orElseThrow();

            var profilePicture = pictures.stream()
                    .filter(picture -> data.profilePicture() == picture.id())
                    .findFirst()
                    .orElseThrow();
            var followers = users.stream()
                    .filter(follower -> data.followers().contains(follower.id()))
                    .collect(Collectors.toList());
            var followings = users.stream()
                    .filter(following -> data.followings().contains(following.id()))
                    .collect(Collectors.toList());
            var userPosts = posts.stream()
                    .filter(post -> data.posts().contains(post.id()))
                    .collect(Collectors.toList());

            user.setProfilePicture(profilePicture);
            user.setFollowers(followers);
            user.setFollowings(followings);
            user.setPosts(userPosts);
        }

        for (var i = posts.size() - 1; i >= 0; i--)
        {
            var post = posts.get(i);
            var data = entityData.posts.stream()
                    .filter(postData -> post.id() == postData.id())
                    .findFirst()
                    .orElseThrow();

            var author = users.stream()
                    .filter(user -> data.author() == user.id())
                    .findFirst()
                    .orElseThrow();
            var content = pictures.stream()
                    .filter(picture -> data.content() == picture.id())
                    .findFirst()
                    .orElseThrow();
            var likes = users.stream()
                    .filter(user -> data.likes().contains(user.id()))
                    .collect(Collectors.toList());

            post.setAuthor(author);
            post.setContent(content);
            post.setLikes(likes);
        }

        for (var i = notifications.size() - 1; i >= 0; i--)
        {
            var notification = notifications.get(i);
            var data = entityData.notifications.stream()
                    .filter(notificationData -> notification.id() == notificationData.id())
                    .findFirst()
                    .orElseThrow();

            var target = users.stream()
                    .filter(user -> data.target() == user.id())
                    .findFirst()
                    .orElseThrow();

            notification.setTarget(target);
        }

        return new DatabaseModel(users, posts, pictures, notifications);
    }

    private static UserData toData(User user)
    {
        var followers = user.followers().stream()
                .map(User::id)
                .toList();
        var followings = user.followings().stream()
                .map(User::id)
                .toList();
        var posts = user.posts().stream()
                .map(Post::id)
                .toList();
        return new UserData(user.id(), user.username(), user.password(), user.bio(), user.profilePicture().id(), followers, followings, posts);
    }

    private static PostData toData(Post post)
    {
        var likes = post.likes().stream()
                .map(User::id)
                .toList();
        return new PostData(post.id(), post.author().id(), post.content().id(), post.title(), likes);
    }

    private static PictureData toData(Picture picture)
    {
        return new PictureData(picture.id(), picture.pathFile());
    }

    private static NotificationData toData(Notification notification)
    {
        return new NotificationData(notification.id(), notification.target().id(), notification.message());
    }
}