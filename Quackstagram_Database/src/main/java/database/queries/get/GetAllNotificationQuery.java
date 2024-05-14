package database.queries.get;

import database.Database;
import database.DatabaseExtensions;
import database.queries.ResultQueryObject;
import entities.data.NotificationData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllNotificationQuery extends ResultQueryObject<List<NotificationData>>
{
    @Override
    public String getStatement()
    {
        return "SELECT * " +
                "FROM quackstagram.notification";
    }

    public static void main(String[] args)
    {
        var result = Database.executeQuery(new GetAllNotificationQuery());
        DatabaseExtensions.printAllData(result);
    }

    @Override
    public List<NotificationData> readResult(ResultSet resultSet)
    {
        try
        {
            var notifications = new ArrayList<NotificationData>();
            while (resultSet.next())
            {
                notifications.add(new NotificationData(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)));
            }
            return notifications;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}