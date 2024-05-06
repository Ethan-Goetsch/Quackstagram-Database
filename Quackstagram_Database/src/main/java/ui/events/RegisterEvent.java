package ui.events;

import application.RegisterUserRequest;

public record RegisterEvent(RegisterUserRequest request) implements IUIEvent
{
}