package database.queries.insert;

import database.Database;
import database.queries.QueryObject;
import entities.Notification;
import entities.Picture;
import entities.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertNotificationQuery extends QueryObject
{
    private final Notification notification;

    public InsertNotificationQuery(Notification notification)
    {
        this.notification = notification;
    }

    @Override
    public String getStatement()
    {
        return "INSERT INTO quackstagram.notification (notification_id, target_id, message) " +
                "VALUES (?, ?, ?)";
    }

    @Override
    public void applyParameters(PreparedStatement statement) throws SQLException
    {
        statement.setInt(1, notification.id());
        statement.setInt(2, notification.target().id());
        statement.setString(3, notification.message());
    }

    public static void main(String[] args)
    {
        var notification = new Notification(0, new User(0, "", "", "", new Picture(0, "")), "Test Message");
        Database.insertNotification(notification);
    }
}