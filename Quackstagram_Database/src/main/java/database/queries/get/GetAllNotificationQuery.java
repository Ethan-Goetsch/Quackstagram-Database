package database.queries.get;

import database.Database;
import database.DatabaseExtensions;
import database.queries.QueryObject;

public class GetAllNotificationQuery extends QueryObject
{
    @Override
    public String getStatement()
    {
        return "SELECT * " +
                "FROM quackstagram.notification";
    }

    public static void main(String[] args)
    {
        var result = Database.executeStatement(new GetAllNotificationQuery());
        DatabaseExtensions.printAllData(result);
    }
}