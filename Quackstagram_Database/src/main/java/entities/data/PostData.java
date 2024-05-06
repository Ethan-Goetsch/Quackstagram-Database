package entities.data;

import java.util.List;

public record PostData(int id, int author, String title, int content, List<Integer> likes)
{
}
