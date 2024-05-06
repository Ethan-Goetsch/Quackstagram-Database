package database;

import com.google.gson.*;
import entities.Picture;
import entities.Post;
import entities.User;
import entities.Notification;

import java.lang.reflect.Type;
import java.util.ArrayList;

//public class DatabaseModelAdapter implements JsonSerializer<DatabaseModel>, JsonDeserializer<DatabaseModel>
//{
//    @Override
//    public JsonElement serialize(DatabaseModel src, Type typeOfSrc, JsonSerializationContext context)
//    {
//        var modelArray = new JsonArray();
//        var usersArray = new JsonArray();
//        var postArray = new JsonArray();
//        var pictureArray = new JsonArray();
//        var notificationArray = new JsonArray();
//
//        for (User user : src.users)
//        {
//            var userObject = new JsonObject();
//            var followers = new JsonArray();
//            var followings = new JsonArray();
//            var posts = new JsonArray();
//
//            user.followers().forEach(follower -> followers.add(follower.id()));
//            user.followings().forEach(following -> followings.add(following.id()));
//            user.posts().forEach(post -> posts.add(post.id()));
//
//            userObject.addProperty("id", user.id());
//            userObject.addProperty("username", user.username());
//            userObject.addProperty("password", user.password());
//            userObject.addProperty("bio", user.bio());
//            userObject.addProperty("picture", user.profilePicture().id());
//            userObject.add("followers", followers);
//            userObject.add("followings", followings);
//            userObject.add("posts", posts);
//
//            usersArray.add(userObject);
//        }
//
//        for (Post post : src.posts)
//        {
//            var postObject = new JsonObject();
//            var likes = new JsonArray();
//
//            post.likes().forEach(user -> likes.add(user.id()));
//
//            postObject.addProperty("id", post.id());
//            postObject.addProperty("author", post.author().id());
//            postObject.addProperty("title", post.title());
//            postObject.addProperty("picture", post.content().id());
//            postObject.add("likes", likes);
//
//            postArray.add(postObject);
//        }
//
//        for (Picture picture : src.pictures)
//        {
//            var pictureObject = new JsonObject();
//            pictureObject.addProperty("id", picture.id());
//            pictureObject.addProperty("pathFile", picture.id());
//
//            pictureArray.add(pictureObject);
//        }
//
//        for (Notification notification : src.notifications)
//        {
//            var notificationObject = new JsonObject();
//
//            notificationObject.addProperty("id", notification.getId());
//            notificationObject.addProperty("target", notification.getTarget().id());
//            notificationObject.addProperty("description", notification.getDescription());
//
//            notificationArray.add(notificationObject);
//        }
//
//        modelArray.add(usersArray);
//        modelArray.add(postArray);
//        modelArray.add(pictureArray);
//        modelArray.add(notificationArray);
//        return modelArray;
//    }
//
//    @Override
//    public DatabaseModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
//    {
//        var users = new ArrayList<User>();
//        var posts = new ArrayList<Post>();
//        var pictures = new ArrayList<Picture>();
//        var notifications = new ArrayList<Notification>();
//
//        var userArray = json.getAsJsonArray().get(0).getAsJsonArray();
//        var postArray = json.getAsJsonArray().get(1).getAsJsonArray();
//        var pictureArray = json.getAsJsonArray().get(2).getAsJsonArray();
//        var notificationArray = json.getAsJsonArray().get(3).getAsJsonArray();
//
//        for (var i = 0; i < userArray.size(); i++)
//        {
//            var userObject = userArray.get(i);
//        }
//
//        return null;
//    }
//}
