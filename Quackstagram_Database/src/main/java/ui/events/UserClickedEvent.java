package ui.events;

import entities.User;

public record UserClickedEvent(User user) implements IUIEvent
{
}
