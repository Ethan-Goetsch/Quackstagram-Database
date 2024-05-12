package database.queries.delete;

import database.Database;
import database.queries.QueryObject;
import entities.Picture;
import entities.Post;
import entities.User;

public class DeleteUserRelationshipQuery extends QueryObject
{
    @Override
    public String getStatement()
    {
        return "DELETE FROM quackstagram.user_relationship " +
                "WHERE source_id = ? AND destination_id = ?";
    }

    public static void main(String[] args)
    {
        var source =  new User(0, "", "", "", new Picture(0, ""));
        var destination =  new User(1, "", "", "", new Picture(0, ""));
        Database.deleteUserRelationship(source, destination);
    }
}