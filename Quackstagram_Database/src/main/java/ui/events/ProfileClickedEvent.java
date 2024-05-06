package ui.events;

import entities.User;

public record ProfileClickedEvent(User user) implements IUIEvent
{
}