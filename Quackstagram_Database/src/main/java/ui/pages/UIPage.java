package ui.pages;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import ui.events.IUIEvent;
import ui.UIController;

import javax.swing.*;

public class UIPage
{
    protected final UIController manager;
    protected final Subject<IUIEvent> onEvent;
    protected final JFrame frame;

    public UIPage(UIController manager, JFrame frame)
    {
        this.manager = manager;
        this.frame = frame;
        this.onEvent = PublishSubject.create();
    }

    public void open()
    {
        frame.setVisible(true);
    }

    public void close()
    {
        frame.dispose();
        onEvent.onComplete();
    }

    public void displayMessage(String title, String message, int type)
    {
        JOptionPane.showMessageDialog(frame, message, title, type);
    }

    public void refresh()
    {

    }

    public <T extends IUIEvent> Observable<T> onEvent(Class<T> type)
    {
        return onEvent
                .filter(type::isInstance)
                .map(type::cast);
    }
}