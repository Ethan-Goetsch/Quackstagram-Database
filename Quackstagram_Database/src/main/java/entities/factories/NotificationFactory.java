package entities.factories;

import database.Database;
import entities.Notification;
import entities.User;

public class NotificationFactory
{
    public static Notification createNotification(User target, String message)
    {
        return new Notification(getNextId(), target, message);
    }

    private static int getNextId()
    {
        return Database
                .getNotifications()
                .size() + 1;
    }
}