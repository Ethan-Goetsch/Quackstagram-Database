package entities;

import java.util.Objects;

public class Picture
{
    private final int id;

    private final String pathFile;

    public Picture(int id, String pathFile)
    {
        this.id = id;
        this.pathFile = pathFile;
    }

    public int id()
    {
        return id;
    }

    public String pathFile()
    {
        return pathFile;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Picture) obj;
        return this.id == that.id &&
                Objects.equals(this.pathFile, that.pathFile);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, pathFile);
    }

}