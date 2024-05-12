package database.queries.get;

import database.Database;
import database.DatabaseExtensions;
import database.queries.QueryObject;

public class GetFollowersForUserQuery extends QueryObject
{
    @Override
    public String getStatement()
    {
        return "SELECT * " +
                "FROM quackstagram.user";
    }

    public static void main(String[] args)
    {
        var result = Database.executeStatement(new GetAllUserQuery());
        DatabaseExtensions.printAllData(result);
    }
}