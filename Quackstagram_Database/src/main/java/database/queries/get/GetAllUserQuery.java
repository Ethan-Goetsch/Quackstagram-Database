package database.queries.get;

import database.Database;
import database.DatabaseExtensions;
import database.queries.ResultQueryObject;
import entities.data.UserData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllUserQuery extends ResultQueryObject<List<UserData>>
{
    @Override
    public String getStatement()
    {
        return "SELECT * " +
                "FROM quackstagram.user";
    }

    @Override
    public List<UserData> readResult(ResultSet resultSet)
    {
        try
        {
            var users = new ArrayList<UserData>();
            while (resultSet.next())
            {
                users.add(new UserData(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)));
            }
            return users;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void main(String[] args)
    {
        var result = Database.executeQuery(new GetAllUserQuery());
        DatabaseExtensions.printAllData(result);
        System.out.println(Database.executeQueryAndReadResult(new GetAllUserQuery()));
    }
}