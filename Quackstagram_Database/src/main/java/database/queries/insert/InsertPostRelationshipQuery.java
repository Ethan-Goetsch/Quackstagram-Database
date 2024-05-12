package database.queries.insert;

import database.Database;
import database.queries.QueryObject;
import entities.Picture;
import entities.Post;
import entities.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertPostRelationshipQuery extends QueryObject
{
    private final User user;
    private final Post post;           ;

    public InsertPostRelationshipQuery(User user, Post post)
    {
        this.user = user;
        this.post = post;
    }

    @Override
    public String getStatement()
    {
        return "INSERT INTO quackstagram.post_relationship (user_id, post_id) " +
                "VALUES (?, ?)";
    }

    @Override
    public void applyParameters(PreparedStatement statement) throws SQLException
    {
        statement.setInt(1, user.id());
        statement.setInt(2, post.id());
    }

    public static void main(String[] args)
    {
        var user =  new User(0, "", "", "", new Picture(0, ""));
        var post =  new Post(0, user, "", new Picture(0, ""));
        Database.insertPostRelationship(user, post);
    }
}