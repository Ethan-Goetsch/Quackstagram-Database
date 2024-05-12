package database;

import util.IAction;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseExtensions
{
    public static void printAllData(ResultSet resultSet)
    {
        try
        {
            while (resultSet.next())
            {
                var printData = "";
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
                {
                    printData += resultSet.getString(i) + " ";
                }
                System.out.println(printData);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}