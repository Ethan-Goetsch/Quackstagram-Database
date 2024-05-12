package database.queries.insert;

import database.Database;
import database.queries.QueryObject;
import entities.Picture;
import entities.Post;
import entities.User;

public class InsertPostRelationshipQuery extends QueryObject
{
    @Override
    public String getStatement()
    {
        return "INSERT INTO quackstagram.post_relationship (user_id, post_id) " +
                "VALUES (?, ?)";
    }

    public static void main(String[] args)
    {
        var user =  new User(0, "", "", "", new Picture(0, ""));
        var post =  new Post(0, user, "", new Picture(0, ""));
        Database.insertPostRelationship(user, post);
    }
}