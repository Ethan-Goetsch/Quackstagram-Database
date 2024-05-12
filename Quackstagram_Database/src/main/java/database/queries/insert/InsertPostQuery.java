package database.queries.insert;

import database.Database;
import database.queries.QueryObject;
import entities.Picture;
import entities.Post;
import entities.User;

public class InsertPostQuery extends QueryObject
{
    @Override
    public String getStatement()
    {
        return "INSERT INTO quackstagram.post (post_id, author_id, picture_id, title) " +
                "VALUES (?, ?, ?, ?)";
    }

    public static void main(String[] args)
    {
        var post = new Post(0, new User(0, "", "", "", new Picture(0, "")), "Test Title", new Picture(0, ""));
        Database.insertPost(post);
    }
}