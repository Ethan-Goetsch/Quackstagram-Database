package database.queries.insert;

import database.Database;
import database.queries.QueryObject;
import entities.Picture;
import entities.Post;
import entities.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertPostQuery extends QueryObject
{
    private final Post post;

    public InsertPostQuery(Post post)
    {
        this.post = post;
    }

    @Override
    public String getStatement()
    {
        return "INSERT INTO quackstagram.post (post_id, author_id, picture_id, title) " +
                "VALUES (?, ?, ?, ?)";
    }

    @Override
    public void applyParameters(PreparedStatement statement) throws SQLException
    {
        statement.setInt(1, post.id());
        statement.setInt(2, post.author().id());
        statement.setInt(3, post.content().id());
        statement.setString(4, post.title());
    }

    public static void main(String[] args)
    {
        var post = new Post(0, new User(0, "", "", "", new Picture(0, "")), "Test Title", new Picture(0, ""));
        Database.insertPost(post);
    }
}