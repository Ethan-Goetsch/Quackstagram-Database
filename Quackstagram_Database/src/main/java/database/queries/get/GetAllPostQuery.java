package database.queries.get;

import database.Database;
import database.DatabaseExtensions;
import database.queries.ResultQueryObject;
import entities.data.PostData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllPostQuery extends ResultQueryObject<List<PostData>>
{
    @Override
    public String getStatement()
    {
        return "SELECT * " +
                "FROM quackstagram.post";
    }

    @Override
    public List<PostData> readResult(ResultSet resultSet)
    {
        try
        {
            var posts = new ArrayList<PostData>();
            while (resultSet.next())
            {
                posts.add(new PostData(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4)));
            }
            return posts;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void main(String[] args)
    {
        var result = Database.executeQuery(new GetAllPostQuery());
        DatabaseExtensions.printAllData(result);
        System.out.println(Database.executeQueryAndReadResult(new GetAllPostQuery()));
    }
}