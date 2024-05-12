package database.queries.get;

import database.Database;
import database.DatabaseExtensions;
import database.queries.QueryObject;

public class GetAllPostQuery extends QueryObject
{
    @Override
    public String getStatement()
    {
        return "SELECT * " +
                "FROM quackstagram.post";
    }

    public static void main(String[] args)
    {
        var result = Database.executeStatement(new GetAllPostQuery());
        DatabaseExtensions.printAllData(result);
    }
}