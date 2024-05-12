package database.queries.get;

import database.Database;
import database.queries.ResultQueryObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetPostsForUser extends ResultQueryObject<List<Integer>>
{
    private final int id;

    public GetPostsForUser(int id)
    {
        this.id = id;
    }

    @Override
    public String getStatement()
    {
        return "SELECT post_id " +
                "FROM quackstagram.post " +
                "WHERE author_id = ?";
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
            var posts = new ArrayList<Integer>();
            while (resultSet.next())
                posts.add(resultSet.getInt(1));
            return posts;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)
    {
        System.out.println(Database.executeQueryAndReadResult(new GetPostsForUser(0)));
    }
}