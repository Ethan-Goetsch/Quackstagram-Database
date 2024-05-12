package database.queries.insert;

import database.Database;
import database.queries.QueryObject;
import entities.Picture;
import entities.User;

public class InsertUserQuery extends QueryObject
{
    @Override
    public String getStatement()
    {
        return "INSERT INTO quackstagram.user (user_id, username, password, bio, picture_id) " +
                "VALUES (?, ?, ?, ?, ?)";
    }

    public static void main(String[] args)
    {
        var user = new User(0, "Test Username", "Test Password", "Test Bio", new Picture(0, "src\\main\\resources\\img\\storage\\profile\\Chad Baby.jpg"));
        Database.insertUser(user);
    }
}