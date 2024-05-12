package database.queries.delete;

import database.Database;
import database.queries.QueryObject;
import entities.Picture;
import entities.Post;
import entities.User;

public class DeletePostRelationshipQuery extends QueryObject
{
    @Override
    public String getStatement()
    {
        return "DELETE FROM quackstagram.post_relationship " +
                "WHERE user_id = ? AND post_id = ?";
    }

    public static void main(String[] args)
    {
        var user =  new User(0, "", "", "", new Picture(0, ""));
        var post =  new Post(0, user, "", new Picture(0, ""));
        Database.deletePostRelationship(user, post);
    }
}