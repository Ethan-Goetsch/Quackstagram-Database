package application.user_relationship;

import entities.User;

public record UnfollowEvent(User source, User destination) implements IUserRelationshipEvent
{
    @Override
    public User getSource()
    {
        return source;
    }

    @Override
    public User getDestination()
    {
        return destination;
    }
}