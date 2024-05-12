package ui.pages;

import entities.Post;
import ui.UIController;
import ui.events.LikePostEvent;
import ui.events.NavigationEvent;
import ui.factory.UIFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class HomePage extends QuackstagramPage
{
    private final List<Post> posts;

    public HomePage(UIController manager, UIFactory factory, List<Post> posts)
    {
        super(manager, factory, "Home");
        this.posts = posts;
        initializeUI();
    }

    @Override
    public void refresh()
    {
        super.refresh();
        initializeUI();
    }

    private void initializeUI()
    {
        // Content Scroll Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Vertical box layout

        JScrollPane contentScrollPane = new JScrollPane(contentPanel);
        contentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // Never allow horizontal scrolling

        contentPanel.removeAll();
        posts.forEach(post -> displayPost(contentPanel, post));

        frame.setLayout(new BorderLayout());
        frame.add(contentScrollPane, BorderLayout.CENTER);

        JPanel headerPanel = factory.createHeaderPanel("🐥 Quackstagram 🐥");
        frame.add(headerPanel, BorderLayout.NORTH);

        // Navigation Bar
        JPanel navigationPanel = factory.createNavigationPanel(arg -> onEvent.onNext(new NavigationEvent(arg)));
        frame.add(navigationPanel, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void displayPost(JPanel panel, Post post)
    {
        var author = post.author().username();
        var description = post.title();
        var likes = String.valueOf(post.likes().size());

        JPanel itemPanel = factory.createItemPanel();
        JLabel nameLabel = factory.createTextLabel(author);
        JLabel imageLabel = factory.createImageLabel();

        try
        {
            ImageIcon imageIcon = factory.createImage(post);
            imageLabel.setIcon(imageIcon);
        }
        catch (IOException ex)
        {
            // Handle exception: Image file not found or reading error
            imageLabel.setText("Post not found");
        }

        JLabel descriptionLabel = factory.createTextLabel(description);
        JLabel likesLabel = factory.createTextLabel(likes);

        JButton likeButton = factory.createLikeButton();

        itemPanel.add(nameLabel);
        itemPanel.add(imageLabel);
        itemPanel.add(descriptionLabel);
        itemPanel.add(likesLabel);
        itemPanel.add(likeButton);

        likeButton.addActionListener(e ->
        {
            likeButtonClicked(post);
            likesLabel.setText(String.valueOf(post.likes().size()));
        });

        panel.add(itemPanel);

        // Grey spacing panel
        JPanel spacingPanel = factory.createSpacingPanel();
        panel.add(spacingPanel);

        panel.revalidate();
        panel.repaint();
    }

    private void likeButtonClicked(Post post)
    {
        onEvent.onNext(new LikePostEvent(post));
    }
}