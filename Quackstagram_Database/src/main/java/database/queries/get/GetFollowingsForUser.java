package database.queries.get;

import database.Database;
import database.queries.ResultQueryObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetFollowingsForUser extends ResultQueryObject<List<Integer>>
{
    private final int id;

    public GetFollowingsForUser(int id)
    {
        this.id = id;
    }

    @Override
    public String getStatement()
    {
        return "SELECT destination_id " +
                "FROM quackstagram.user_relationship " +
                "WHERE source_id = ?";
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
            var followings = new ArrayList<Integer>();
            while (resultSet.next())
                followings.add(resultSet.getInt(1));
            return followings;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void main(String[] args)
    {
        System.out.println(Database.executeQueryAndReadResult(new GetFollowingsForUser(0)));
    }
}