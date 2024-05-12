package database.queries.get;

import database.Database;
import database.DatabaseExtensions;
import database.queries.QueryObject;

public class GetAllPictureQuery extends QueryObject
{
    @Override
    public String getStatement()
    {
        return "SELECT * " +
                "FROM quackstagram.picture";
    }

    public static void main(String[] args)
    {
        var result = Database.executeStatement(new GetAllPictureQuery());
        DatabaseExtensions.printAllData(result);
    }
}