package database.queries.insert;

import database.Database;
import database.queries.QueryObject;
import entities.Picture;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertPictureQuery extends QueryObject
{
    private final Picture picture;

    public InsertPictureQuery(Picture picture)
    {
        this.picture = picture;
    }

    @Override
    public String getStatement()
    {
            return "INSERT INTO quackstagram.picture (picture_id, path_file) " +
                "VALUES (?, ?)";
    }

    @Override
    public void applyParameters(PreparedStatement statement) throws SQLException
    {
        statement.setInt(1, picture.id());
        statement.setString(2, picture.pathFile());
    }

    public static void main(String[] args)
    {
        var picture = new Picture(0, "src\\main\\resources\\img\\storage\\profile\\Chad Baby.jpg");
        Database.insertPicture(picture);
    }
}