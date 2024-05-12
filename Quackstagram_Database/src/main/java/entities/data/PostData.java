package entities.data;

import java.util.ArrayList;
import java.util.List;

public record PostData(int id, int author, int content, String title, List<Integer> likes)
{
    public PostData(int id, int author, int content, String title)
    {
        this(id, author, content, title, new ArrayList<>());
    }
}