package ui.events;

import application.SignInRequest;

public record LogInEvent(SignInRequest request) implements IUIEvent
{
}
