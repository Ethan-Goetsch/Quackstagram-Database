package application;

import entities.User;

import java.io.File;

public record UploadPostRequest(User user, String title, File pictureFile)
{
}