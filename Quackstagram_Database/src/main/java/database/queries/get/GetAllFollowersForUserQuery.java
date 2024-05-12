package database.queries.get;

import database.Database;
import database.DatabaseExtensions;
import database.queries.QueryObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetAllFollowersForUserQuery extends QueryObject
{
    private final int id;

    public GetAllFollowersForUserQuery(int id)
    {
        this.id = id;
    }

    @Override
    public String getStatement()
    {
        return "SELECT source_id " +
                "FROM quackstagram.user" +
                "WHERE destination_id = ?";
    }

    @Override
    public void applyParameters(PreparedStatement statement) throws SQLException
    {
        statement.setInt(1, id);
    }

    public static void main(String[] args)
    {
        var result = Database.executeQuery(new GetAllFollowersForUserQuery(0));
        DatabaseExtensions.printAllData(result);
    }
}