package database.queries.get;

import database.Database;
import database.queries.ResultQueryObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetLikesForPost extends ResultQueryObject<List<Integer>>
{
    private final int id;

    public GetLikesForPost(int id)
    {
        this.id = id;
    }

    @Override
    public String getStatement()
    {
        return "SELECT user_id " +
                "FROM quackstagram.post_relationship " +
                "WHERE post_id = ?";
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
            var likes = new ArrayList<Integer>();
            while (resultSet.next())
                likes.add(resultSet.getInt(1));
            return likes;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void main(String[] args)
    {
        System.out.println(Database.executeQueryAndReadResult(new GetLikesForPost(0)));
    }
}