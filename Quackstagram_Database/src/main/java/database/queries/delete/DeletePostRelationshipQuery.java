package database.queries.delete;

import database.Database;
import database.queries.QueryObject;
import entities.Picture;
import entities.Post;
import entities.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePostRelationshipQuery extends QueryObject
{
    private final User user;
    private final Post post;

    public DeletePostRelationshipQuery(User user, Post post)
    {
        this.user = user;
        this.post = post;
    }

    @Override
    public String getStatement()
    {
        return "DELETE FROM quackstagram.post_relationship " +
                "WHERE user_id = ? AND post_id = ?";
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
        Database.deletePostRelationship(user, post);
    }
}