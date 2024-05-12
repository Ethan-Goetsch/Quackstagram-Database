package database.queries.insert;

import database.Database;
import database.queries.QueryObject;
import entities.Picture;
import entities.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertUserRelationshipQuery extends QueryObject
{
    private final User source;
    private final User destination;

    public InsertUserRelationshipQuery(User source, User destination)
    {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public String getStatement()
    {
        return "INSERT INTO quackstagram.user_relationship (source_id, destination_id) " +
                "VALUES (?, ?)";
    }

    public void applyParameters(PreparedStatement statement) throws SQLException
    {
        statement.setInt(1, source.id());
        statement.setInt(2, destination.id());
    }

    public static void main(String[] args)
    {
        var source =  new User(0, "", "", "", new Picture(0, ""));
        var destination =  new User(1, "", "", "", new Picture(0, ""));
        Database.insertUserRelationship(source, destination);
    }
}