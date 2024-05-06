package application.user_relationship;

import entities.User;

public interface IUserRelationshipEvent
{
    public User getSource();
    public User getDestination();
}