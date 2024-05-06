package ui.events;

import ui.UINavigationType;

public record NavigationEvent(UINavigationType type) implements IUIEvent
{
}
