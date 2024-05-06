package ui.pages;

import entities.Notification;
import ui.UIController;
import ui.events.NavigationEvent;
import ui.factory.QuackstagramUIFactory;
import ui.factory.UIFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NotificationsPage extends QuackstagramPage
{
    private final List<Notification> notifications;

    public NotificationsPage(UIController manager, UIFactory factory, List<Notification> notifications)
    {
        super(manager, factory,"Notifications");
        this.notifications = notifications;
        initializeUI();
    }

    private void initializeUI()
    {
        frame.setLayout(new BorderLayout());

        // Reuse the header and navigation panel creation methods from the InstagramProfileUI class
        JPanel headerPanel = factory.createHeaderPanel(" Notifications 🐥");
        JPanel navigationPanel = factory.createNavigationPanel(arg -> onEvent.onNext(new NavigationEvent(arg)));

        // Content Panel for notifications
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        notifications.forEach(notification ->
        {
            JPanel notificationPanel = factory.createNotification(notification);
            contentPanel.add(notificationPanel);
        });

        // Add panels to frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(navigationPanel, BorderLayout.SOUTH);
    }
}