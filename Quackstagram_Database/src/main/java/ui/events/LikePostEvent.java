package ui.events;

import entities.Post;

public record LikePostEvent(Post post) implements IUIEvent
{
}
