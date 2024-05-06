package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Notification;
import entities.Picture;
import entities.Post;
import entities.User;

import java.io.*;
import java.util.List;
import java.util.Optional;

import static util.PathFile.DataFile;

public class Database
{
    private static DatabaseModel model;

    public static DatabaseModel getModel()
    {
        if (model == null)
            model = loadData();
        return model;
    }

    public static List<User> getUsers() { return getModel().users.stream().toList(); }

    public static Optional<User> getUser(String username)
    {
        return getUsers().stream()
                .filter(user -> user.username().equals(username))
                .findAny();
    }

    public static List<Post> getPosts() { return getModel().posts.stream().toList(); }
    public static List<Picture> getPictures() { return getModel().pictures.stream().toList(); }
    public static List<Notification> getNotifications() { return getModel().notifications.stream().toList(); }

    public static void putUser(User user)
    {
        getModel().users.add(user);
        saveData();
    }

    public static void putPost(Post post)
    {
        getModel().posts.add(post);
        saveData();
    }

    public static void putPicture(Picture picture)
    {
        getModel().pictures.add(picture);
        saveData();
    }

    public static void putNotification(Notification notification)
    {
        getModel().notifications.add(notification);
        saveData();
    }

    public static void saveData()
    {
        var data = DataAdapter.toData(getModel());
        var json = new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(data);
        storeData(json, DataFile);
    }

    public static DatabaseModel loadData()
    {
        try
        {
            var json = readFile(DataFile);
            var data = new Gson().fromJson(json, EntityData.class);
            if (data != null)
                return DataAdapter.toModel(data);
            else
                return new DatabaseModel();
        }
        catch (IOException e)
        {
            return new DatabaseModel();
        }
    }

    private static void storeData(String data, String filePath)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(data);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static String readFile(String filePath) throws IOException
    {
        StringBuilder data = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null)
            data.append(line);

        return data.toString();
    }
}