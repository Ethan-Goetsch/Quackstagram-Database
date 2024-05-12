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

    public static boolean executeQuery(QueryObject query)
    {
        return executeQuery(query.getStatement());
    }

    public static boolean executeQuery(String query)
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

    public static ResultSet executeStatement(QueryObject query)
    {
        return executeStatement(query.getStatement());
    }

    public static ResultSet executeStatement(String query)
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
        try
        {
            var preparedStatement = getConnection().prepareStatement(new InsertUserQuery().getStatement());
            preparedStatement.setInt(1, user.id());
            preparedStatement.setString(2, user.username());
            preparedStatement.setString(3, user.password());
            preparedStatement.setString(4, user.bio());
            preparedStatement.setInt(5, user.profilePicture().id());
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void insertPost(Post post)
    {
        try
        {
            var preparedStatement = getConnection().prepareStatement(new InsertPostQuery().getStatement());
            preparedStatement.setInt(1, post.id());
            preparedStatement.setInt(2, post.author().id());
            preparedStatement.setInt(3, post.content().id());
            preparedStatement.setString(4, post.title());
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void insertPicture(Picture picture)
    {
        try
        {
            var preparedStatement = getConnection().prepareStatement(new InsertPictureQuery().getStatement());
            preparedStatement.setInt(1, picture.id());
            preparedStatement.setString(2, picture.pathFile());
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void insertNotification(Notification notification)
    {
        try
        {
            var preparedStatement = getConnection().prepareStatement(new InsertNotificationQuery().getStatement());
            preparedStatement.setInt(1, notification.id());
            preparedStatement.setInt(2, notification.target().id());
            preparedStatement.setString(3, notification.message());
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void insertUserRelationship(User source, User destination)
    {
        try
        {
            var preparedStatement = getConnection().prepareStatement(new InsertUserRelationshipQuery().getStatement());
            preparedStatement.setInt(1, source.id());
            preparedStatement.setInt(2, destination.id());
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void deleteUserRelationship(User source, User destination)
    {
        try
        {
            var preparedStatement = getConnection().prepareStatement(new DeleteUserRelationshipQuery().getStatement());
            preparedStatement.setInt(1, source.id());
            preparedStatement.setInt(2, destination.id());
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void insertPostRelationship(User user, Post post)
    {
        try
        {
            var preparedStatement = getConnection().prepareStatement(new InsertPostRelationshipQuery().getStatement());
            preparedStatement.setInt(1, user.id());
            preparedStatement.setInt(2, post.id());
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void deletePostRelationship(User user, Post post)
    {
        try
        {
            var preparedStatement = getConnection().prepareStatement(new DeletePostRelationshipQuery().getStatement());
            preparedStatement.setInt(1, user.id());
            preparedStatement.setInt(2, post.id());
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
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
        var data = new EntityData();
        return DataAdapter.toModel(data);
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