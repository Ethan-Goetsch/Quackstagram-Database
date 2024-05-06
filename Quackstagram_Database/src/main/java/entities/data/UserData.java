package entities.data;

import java.util.List;

public record UserData(int id, String username, String password, String bio, int profilePicture, List<Integer> followers, List<Integer> followings, List<Integer> posts)
{
}
