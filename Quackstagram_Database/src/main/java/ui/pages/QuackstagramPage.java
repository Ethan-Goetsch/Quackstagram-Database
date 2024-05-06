package ui.pages;

import ui.UIController;
import ui.factory.QuackstagramUIFactory;
import ui.factory.UIFactory;

public abstract class QuackstagramPage extends UIPage
{
    protected UIFactory factory;

    public QuackstagramPage(UIController manager, UIFactory factory, String title)
    {
        super(manager, factory.createFrame(title));
        this.factory = factory;
    }
}