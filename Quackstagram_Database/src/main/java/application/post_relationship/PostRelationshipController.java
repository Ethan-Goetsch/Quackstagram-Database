package application.post_relationship;

import database.Database;
import entities.Post;
import entities.User;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class PostRelationshipController
{
    private static final Subject<IPostRelationshipEvent> onEvent = PublishSubject.create();

    public static void likePost(User user, Post post)
    {
        // Ignore if already liked
        if (post.likes().contains(user)) return;

        post.likes().add(user);
        Database.insertPostRelationship(user, post);
        //Database.saveDataToFile();
        onEvent.onNext(new LikePostEvent(user, post));
    }

    public static void unlikePost(User user, Post post)
    {
        // Ignore if not liked
        if (!post.likes().contains(user)) return;

        post.likes().remove(user);
        Database.deletePostRelationship(user, post);
        //Database.saveDataToFile();
        onEvent.onNext(new UnlikePostEvent(user, post));
    }

    public static <T extends IPostRelationshipEvent> Observable<T> onEvent(Class<T> type)
    {
        return onEvent
                .filter(type::isInstance)
                .map(type::cast);
    }
}