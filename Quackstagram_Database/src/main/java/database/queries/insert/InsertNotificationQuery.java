package database.queries.insert;

import database.Database;
import database.queries.QueryObject;
import entities.Notification;
import entities.Picture;
import entities.User;

public class InsertNotificationQuery extends QueryObject
{
    @Override
    public String getStatement()
    {
        return "INSERT INTO quackstagram.notification (notification_id, target_id, message) " +
                "VALUES (?, ?, ?)";
    }

    public static void main(String[] args)
    {
        var notification = new Notification(0, new User(0, "", "", "", new Picture(0, "")), "Test Message");
        Database.insertNotification(notification);
    }
}