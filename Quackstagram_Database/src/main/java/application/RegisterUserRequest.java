package application;

import java.io.File;

public record RegisterUserRequest(String username, String password, String bio, File pictureFile)
{
}
