package entities;

import java.util.ArrayList;
import java.util.List;

public class User
{
    private final int id;
    private final String username;
    private final String password;
    private final String bio;

    private Picture profilePicture;
    private List<User> followers;
    private List<User> followings;
    private List<Post> posts;

    public User(int id, String username, String password, String bio, Picture profilePicture, List<User> followers, List<User> followings, List<Post> posts)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.profilePicture = profilePicture;
        this.followers = followers;
        this.followings = followings;
        this.posts = posts;
    }

    public User(int id, String username, String password, String bio, Picture profilePicture)
    {
        this(id, username, password, bio, profilePicture, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public void addFollower(User user)
    {
        followers.add(user);
    }

    public void removeFollower(User user)
    {
        followers.remove(user);
    }

    public void addFollowing(User user)
    {
        followings.add(user);
    }

    public void removeFollowing(User user)
    {
        followings.remove(user);
    }

    public void addPost(Post post)
    {
        posts.add(post);
    }

    public boolean isFollowing(User user)
    {
        return user.followers().contains(this);
    }

    public int id()
    {
        return id;
    }

    public String username()
    {
        return username;
    }

    public String password()
    {
        return password;
    }

    public String bio()
    {
        return bio;
    }

    public Picture profilePicture()
    {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture)
    {
        this.profilePicture = profilePicture;
    }

    public List<User> followers()
    {
        return followers;
    }

    public void setFollowers(List<User> followers)
    {
        this.followers = followers;
    }

    public List<User> followings()
    {
        return followings;
    }

    public void setFollowings(List<User> followings)
    {
        this.followings = followings;
    }

    public List<Post> posts()
    {
        return posts;
    }

    public void setPosts(List<Post> posts)
    {
        this.posts = posts;
    }
}