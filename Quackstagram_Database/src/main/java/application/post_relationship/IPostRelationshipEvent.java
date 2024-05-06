package application.post_relationship;

import entities.Post;
import entities.User;

public interface IPostRelationshipEvent
{
    public User getUser();
    public Post getPost();
}