package database.queries.insert;

import database.Database;
import database.queries.QueryObject;
import entities.Picture;
import entities.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertUserQuery extends QueryObject
{
    private final User user;

    public InsertUserQuery(User user)
    {
        this.user = user;
    }

    @Override
    public String getStatement()
    {
        return "INSERT INTO quackstagram.user (user_id, username, password, bio, picture_id) " +
                "VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public void applyParameters(PreparedStatement statement) throws SQLException
    {
        statement.setInt(1, user.id());
        statement.setString(2, user.username());
        statement.setString(3, user.password());
        statement.setString(4, user.bio());
        statement.setInt(5, user.profilePicture().id());
    }

    public static void main(String[] args)
    {
        var user = new User(0, "Test Username", "Test Password", "Test Bio", new Picture(0, "src\\main\\resources\\img\\storage\\profile\\Chad Baby.jpg"));
        Database.insertUser(user);
    }
}