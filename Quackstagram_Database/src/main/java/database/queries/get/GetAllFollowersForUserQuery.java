package database.queries.get;

import database.Database;
import database.queries.ResultQueryObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllFollowersForUserQuery extends ResultQueryObject<List<Integer>>
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
                "FROM quackstagram.user_relationship " +
                "WHERE destination_id = ?";
    }

    @Override
    public void applyParameters(PreparedStatement statement) throws SQLException
    {
        statement.setInt(1, id);
    }

    @Override
    public List<Integer> readResult(ResultSet resultSet)
    {
        try
        {
            var followers = new ArrayList<Integer>();
            while (resultSet.next())
                followers.add(resultSet.getInt(1));
            return followers;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)
    {
        System.out.println(Database.executeQueryAndReadResult(new GetAllFollowersForUserQuery(1)));
    }
}