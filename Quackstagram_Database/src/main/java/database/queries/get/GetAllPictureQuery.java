package database.queries.get;

import database.Database;
import database.DatabaseExtensions;
import database.queries.ResultQueryObject;
import entities.data.PictureData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllPictureQuery extends ResultQueryObject<List<PictureData>>
{
    @Override
    public String getStatement()
    {
        return "SELECT * " +
                "FROM quackstagram.picture";
    }

    public static void main(String[] args)
    {
        var result = Database.executeQuery(new GetAllPictureQuery());
        DatabaseExtensions.printAllData(result);
    }

    @Override
    public List<PictureData> readResult(ResultSet resultSet)
    {
        try
        {
            var pictures = new ArrayList<PictureData>();
            while (resultSet.next())
            {
                pictures.add(new PictureData(resultSet.getInt(1), resultSet.getString(2)));
            }
            return pictures;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}