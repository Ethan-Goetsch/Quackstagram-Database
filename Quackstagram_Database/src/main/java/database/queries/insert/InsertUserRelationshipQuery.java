package database.queries.insert;

import database.Database;
import database.queries.QueryObject;
import entities.Picture;
import entities.User;

public class InsertUserRelationshipQuery extends QueryObject
{
    @Override
    public String getStatement()
    {
        return "INSERT INTO quackstagram.user_relationship (source_id, destination_id) " +
                "VALUES (?, ?)";
    }

    public static void main(String[] args)
    {
        var source =  new User(0, "", "", "", new Picture(0, ""));
        var destination =  new User(1, "", "", "", new Picture(0, ""));
        Database.insertUserRelationship(source, destination);
    }
}