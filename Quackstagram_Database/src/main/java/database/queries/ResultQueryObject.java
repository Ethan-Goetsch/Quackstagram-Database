package database.queries;

import java.sql.ResultSet;

public abstract class ResultQueryObject<T> extends QueryObject
{
    public abstract T readResult(ResultSet resultSet);
}