package application;

import application.post_relationship.LikePostEvent;
import application.post_relationship.PostRelationshipController;
import application.user_relationship.FollowEvent;
import application.user_relationship.UserRelationshipController;
import database.Database;
import entities.Notification;
import entities.Picture;
import entities.Post;
import entities.User;
import entities.factories.NotificationFactory;
import entities.factories.PictureFactory;
import entities.factories.PostFactory;
import entities.factories.UserFactory;
import util.PathFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

public class ApplicationManager
{
    private final CredentialsValidator credentialsValidator;

    private User currentUser;

    public ApplicationManager()
    {
        this.credentialsValidator = new CredentialsValidator();
        createObservers();
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public List<User> getUsers() { return Database.getUsers(); }
    public List<Post> getPosts() { return Database.getPosts(); }
    public List<Picture> getPictures() { return Database.getPictures(); }
    public List<Notification> getNotifications() { return Database.getNotifications(); }

    public Optional<User> getUser(String username)
    {
        return Database.getUser(username);
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }

    public RequestResult signInUser(SignInRequest request)
    {
        var user = getUser(request.username());

        if (!credentialsValidator.isCredentialsValid(request.username(), request.password())) return new RequestResult(false, "Invalid Credentials");
        if (user.isEmpty()) return new RequestResult(false, "User does not exist"); // Technically checked in isCredentialsValid but this was so the compiler doesn't shout about a direct get to an Optional<User>

        setCurrentUser(user.get());
        return new RequestResult(true, "Success");
    }

    public RequestResult registerUser(RegisterUserRequest request)
    {
        if (!credentialsValidator.isRequestValid(request)) return new RequestResult(false, "Invalid Details");
        if (!credentialsValidator.isUsernameValid(request.username())) return new RequestResult(false, "Username already exists");

        var picture = uploadPicture(request.pictureFile(), PathFile.ProfilePictureFolder);
        var user = UserFactory.create(request.username(), EncryptionController.encrypt(request.password()), request.bio(), picture);
        Database.putUser(user);
        return new RequestResult(true, "Success");
    }

    public RequestResult uploadPost(UploadPostRequest request)
    {
        var picture = uploadPicture(request.pictureFile(), PathFile.UploadPictureFolder);
        var post = PostFactory.create(request.user(), request.title(), picture);
        request.user().addPost(post);
        Database.putPost(post);
        return new RequestResult(true, "Success");
    }

    public void likePost(User user, Post post)
    {
        PostRelationshipController.likePost(user, post);
    }

    public void unlikePost(User user, Post post)
    {
        PostRelationshipController.unlikePost(user, post);
    }

    public void followUser(User source, User destination)
    {
        UserRelationshipController.followUser(source, destination);
    }

    public void unfollowUser(User source, User destination)
    {
        UserRelationshipController.unfollowUser(source, destination);
    }

    private Picture uploadPicture(File pictureFile, String destinationFolder)
    {
        var savedFile = saveFile(pictureFile, Path.of(destinationFolder + pictureFile.getName()));
        var picture = PictureFactory.create(savedFile);
        Database.putPicture(picture);
        return picture;
    }

    private String saveFile(File pictureFile, Path destination)
    {
        try
        {
            var path = Files.copy(pictureFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            return path.toString();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void uploadNotification(User target, String message)
    {
        var notification = NotificationFactory.createNotification(target, message);
        Database.putNotification(notification);
    }

    private void createObservers()
    {
        UserRelationshipController
                .onEvent(FollowEvent.class)
                .subscribe(evt -> uploadNotification(evt.destination(), evt.getSource().username() + " Followed You!" ));
        PostRelationshipController
                .onEvent(LikePostEvent.class)
                .subscribe(evt -> uploadNotification(evt.post().author(), evt.user().username() + " Liked Your Post!" ));
    }
}