package application.user_relationship;

import database.Database;
import entities.User;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class UserRelationshipController
{
    private static final Subject<IUserRelationshipEvent> onEvent = PublishSubject.create();

    public static void followUser(User source, User destination)
    {
        // Ignore if already followed
        if (destination.followers().contains(source)) return;

        source.followings().add(destination);
        destination.followers().add(source);

        Database.insertUserRelationship(source, destination);
        //Database.saveDataToFile();
        onEvent.onNext(new FollowEvent(source, destination));
    }

    public static void unfollowUser(User source, User destination)
    {
        // Ignore if not following
        if (!destination.followers().contains(source)) return;

        source.followings().remove(destination);
        destination.followers().remove(source);

        Database.deleteUserRelationship(source, destination);
        //Database.saveDataToFile();
        onEvent.onNext(new UnfollowEvent(source, destination));
    }

    public static <T extends IUserRelationshipEvent> Observable<T> onEvent(Class<T> type)
    {
        return onEvent
                .filter(type::isInstance)
                .map(type::cast);
    }
}