package ui.events;

import java.io.File;

public record UploadPostEvent(String title, File file) implements IUIEvent
{
}
