package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.queries.*;
import database.queries.delete.DeletePostRelationshipQuery;
import database.queries.delete.DeleteUserRelationshipQuery;
import database.queries.insert.*;
import entities.*;
import util.PathFile;

import java.io.*;
import java.sql.*;
import java.util.List;
import java.util.Optional;

import static util.PathFile.DataFile;

public class Database
{
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static DatabaseModel model;
    private static Connection connection;

    private static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                var userConfig = readData(PathFile.CREDENTIALS_FILE, UserConfig.class);
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(userConfig.url(), userConfig.username(), userConfig.password());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return connection;
    }

    public static DatabaseModel getModel()
    {
        if (model == null)
            model = loadDataFromDatabase();
        return model;
    }

    public static boolean execute(QueryObject query)
    {
        return query.execute(getConnection());
    }

    public static boolean execute(String query)
    {
        try
        {
            var preparedStatement = getConnection().prepareStatement(query);
            return preparedStatement.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeQuery(QueryObject query)
    {
        return query.executeQuery(getConnection());
    }

    public static ResultSet executeQuery(String query)
    {
        try
        {
            var preparedStatement = getConnection().prepareStatement(query);
            return preparedStatement.executeQuery();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
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
        insertUser(user);
        // saveDataToFile();
    }

    public static void putPost(Post post)
    {
        getModel().posts.add(post);
        insertPost(post);
        // saveDataToFile();
    }

    public static void putPicture(Picture picture)
    {
        getModel().pictures.add(picture);
        insertPicture(picture);
        // saveDataToFile();
    }

    public static void putNotification(Notification notification)
    {
        getModel().notifications.add(notification);
        insertNotification(notification);
        // saveDataToFile();
    }

    public static void insertUser(User user)
    {
        execute(new InsertUserQuery(user));
    }

    public static void insertPost(Post post)
    {
        execute(new InsertPostQuery(post));
    }

    public static void insertPicture(Picture picture)
    {
        execute(new InsertPictureQuery(picture));
    }

    public static void insertNotification(Notification notification)
    {
        execute(new InsertNotificationQuery(notification));
    }

    public static void insertUserRelationship(User source, User destination)
    {
        execute(new InsertUserRelationshipQuery(source, destination));
    }

    public static void deleteUserRelationship(User source, User destination)
    {
        execute(new DeleteUserRelationshipQuery(source, destination));
    }

    public static void insertPostRelationship(User user, Post post)
    {
        execute(new InsertPostRelationshipQuery(user, post));
    }

    public static void deletePostRelationship(User user, Post post)
    {
        execute(new DeletePostRelationshipQuery(user, post));
    }

    public static void saveDataToFile()
    {
        var data = DataAdapter.toData(getModel());
        var json = new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(data);
        storeData(json, DataFile);
    }

    public static <T> T readData(String path, Class<T> type)
    {
        try
        {
            var json = readFile(path);
            return new Gson().fromJson(json, type);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseModel loadDataFromDatabase()
    {
        return DataAdapter.toModel(EntityDataLoader.loadDataFromDatabase());
    }

    public static DatabaseModel loadDataFromFile()
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