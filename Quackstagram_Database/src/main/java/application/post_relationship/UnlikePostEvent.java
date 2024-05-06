package application.post_relationship;

import entities.Post;
import entities.User;

public record UnlikePostEvent(User user, Post post) implements IPostRelationshipEvent
{
    @Override
    public User getUser()
    {
        return user;
    }

    @Override
    public Post getPost()
    {
        return post;
    }
}