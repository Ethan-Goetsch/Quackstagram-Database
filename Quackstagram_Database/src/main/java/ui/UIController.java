package ui;

import application.ApplicationManager;
import application.UploadPostRequest;
import entities.User;
import ui.events.*;
import ui.factory.UIFactory;
import ui.pages.*;

import javax.swing.*;

public class UIController
{
    private final ApplicationManager applicationManager;
    private final UIFactory factory;

    private SignInPage signInPage;
    private SignUpPage signUpPage;

    private HomePage homePage;
    private ExplorePage explorePage;
    private UploadPage uploadPage;
    private NotificationsPage notificationsPage;
    private ProfilePage profilePage;

    private UIPage currentPage;

    public UIController(ApplicationManager applicationManager, UIFactory factory)
    {
        this.applicationManager = applicationManager;
        this.factory = factory;
    }

    public void openSignIn()
    {
        signInPage = new SignInPage(this, factory);
        signInPage
                .onEvent(LogInEvent.class)
                .subscribe(this::handleLogin);
        signInPage
                .onEvent(SignUpEvent.class)
                .subscribe(this::handleSignUp);
        setCurrentPage(signInPage);
    }

    public void openSignUp()
    {
        signUpPage = new SignUpPage(this, factory);
        signUpPage
                .onEvent(RegisterEvent.class)
                .subscribe(this::handleRegister);
        signUpPage
                .onEvent(SignInEvent.class)
                .subscribe(this::handleSignIn);
        setCurrentPage(signUpPage);
    }

    public void openHome()
    {
        var posts = applicationManager.getPosts().stream()
                .filter(post -> post.author() != applicationManager.getCurrentUser())
                .toList();
        homePage = new HomePage(this, factory, posts);
        homePage
                .onEvent(NavigationEvent.class)
                .subscribe(this::handleNavigation);
        homePage
                .onEvent(LikePostEvent.class)
                .subscribe(this::handleLike);
        setCurrentPage(homePage);
    }

    public void openExplore()
    {
        var posts = applicationManager
                .getPosts()
                .stream()
                .filter(post -> post.author() != applicationManager.getCurrentUser())
                .toList();
        explorePage = new ExplorePage(this, factory, posts);
        explorePage
                .onEvent(NavigationEvent.class)
                .subscribe(this::handleNavigation);
        explorePage
                .onEvent(UserClickedEvent.class)
                .subscribe(this::handleUserClicked);
        setCurrentPage(explorePage);
    }

    public void openUpload()
    {
        uploadPage = new UploadPage(this, factory);
        uploadPage
                .onEvent(NavigationEvent.class)
                .subscribe(this::handleNavigation);
        uploadPage
                .onEvent(UploadPostEvent.class)
                .subscribe(this::uploadPost);
        setCurrentPage(uploadPage);
    }

    public void openNotifications()
    {
        var user = applicationManager.getCurrentUser();
        var notifications = applicationManager.getNotifications().stream()
                .filter(notification -> notification.target() == user)
                .toList();
        notificationsPage = new NotificationsPage(this, factory, notifications);
        notificationsPage.onEvent(NavigationEvent.class)
                .subscribe(this::handleNavigation);
        setCurrentPage(notificationsPage);
    }

    public void openProfile()
    {
        openProfile(applicationManager.getCurrentUser());
    }

    public void openProfile(User profileOwner)
    {
        profilePage = new ProfilePage(this, factory, profileOwner, applicationManager.getCurrentUser());
        profilePage.onEvent(NavigationEvent.class)
                .subscribe(this::handleNavigation);
        profilePage.onEvent(ProfileClickedEvent.class)
                .subscribe(this::handleProfile);
        setCurrentPage(profilePage);
    }

    private void setCurrentPage(UIPage page)
    {
        if (currentPage != null)
            currentPage.close();
        currentPage = page;
        currentPage.open();
    }

    private void handleLogin(LogInEvent args)
    {
        var result = applicationManager.signInUser(args.request());
        if (result.status())
            openHome();
        else
            signInPage.displayMessage("Error", result.message(), JOptionPane.ERROR_MESSAGE);
    }

    private void handleSignIn(SignInEvent args)
    {
        openSignIn();
    }

    private void handleSignUp(SignUpEvent args)
    {
        openSignUp();
    }

    private void handleRegister(RegisterEvent args)
    {
        var result = applicationManager.registerUser(args.request());
        if (result.status())
            openSignIn();
        else
            signUpPage.displayMessage("Error", result.message(), JOptionPane.ERROR_MESSAGE);
    }

    private void handleNavigation(NavigationEvent args)
    {
        switch (args.type())
        {
            case HOME -> openHome();
            case EXPLORE -> openExplore();
            case UPLOAD -> openUpload();
            case NOTIFICATION -> openNotifications();
            case PROFILE -> openProfile();
            default -> throw new IllegalStateException("Unexpected value: " + args.type());
        }
    }

    private void handleLike(LikePostEvent args)
    {
        var currentUser = applicationManager.getCurrentUser();

        // If user already liked the post
        if (args.post().likes().contains(currentUser))
            applicationManager.unlikePost(currentUser, args.post());
        else
            applicationManager.likePost(currentUser, args.post());
        homePage.refresh();
    }

    private void handleUserClicked(UserClickedEvent args)
    {
        openProfile(args.user());
    }

    private void uploadPost(UploadPostEvent args)
    {
        var result = applicationManager.uploadPost(new UploadPostRequest(applicationManager.getCurrentUser(), args.title(), args.file()));
        if (result.status())
            uploadPage.displayMessage(result.message(), "Image uploaded", JOptionPane.INFORMATION_MESSAGE);
        else
            uploadPage.displayMessage(result.message(), "Error uploading image", JOptionPane.ERROR_MESSAGE);
    }

    private void handleProfile(ProfileClickedEvent args)
    {
        var currentUser = applicationManager.getCurrentUser();
        // If user clicked on their profile button
        if (currentUser == args.user())
        {
            profilePage.displayMessage("Error", "Sorry, editing your profile is down due to server issues", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // If already following the user
            if (currentUser.followings().contains(args.user()))
                applicationManager.unfollowUser(currentUser, args.user());
            else
                applicationManager.followUser(currentUser, args.user());

            profilePage.refresh();
        }
    }
}