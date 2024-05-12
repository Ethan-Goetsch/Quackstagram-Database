package database.queries.insert;

import database.Database;
import database.queries.QueryObject;
import entities.Picture;

public class InsertPictureQuery extends QueryObject
{
    @Override
    public String getStatement()
    {
            return "INSERT INTO quackstagram.picture (picture_id, path_file) " +
                "VALUES (?, ?)";
    }

    public static void main(String[] args)
    {
        var picture = new Picture(0, "src\\main\\resources\\img\\storage\\profile\\Chad Baby.jpg");
        Database.insertPicture(picture);
    }
}