package ui.pages;

import entities.Post;
import entities.User;
import ui.UIController;
import ui.events.NavigationEvent;
import ui.events.UserClickedEvent;
import ui.factory.QuackstagramUIFactory;
import ui.factory.UIFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExplorePage extends QuackstagramPage
{
    private final List<Post> posts;

    public ExplorePage(UIController manager, UIFactory factory, List<Post> posts)
    {
        super(manager, factory, "Explore");
        this.posts = posts;
        initializeUI();
    }

    private void initializeUI()
    {
        frame.getContentPane().removeAll(); // Clear existing components
        frame.setLayout(new BorderLayout()); // Reset the layout manager

        JPanel headerPanel = factory.createHeaderPanel("Explore"); // Method from your InstagramProfileUI class
        JPanel navigationPanel = factory.createNavigationPanel(arg -> onEvent.onNext(new NavigationEvent(arg))); // Method from your InstagramProfileUI class
        JPanel mainContentPanel = createMainContentPanel();

        // Add panels to the frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(mainContentPanel, BorderLayout.CENTER);
        frame.add(navigationPanel, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private JPanel createMainContentPanel()
    {
        // Create the main content panel with search and post grid
        // Search bar at the top
        JPanel searchPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField(" Search Users");
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchField.getPreferredSize().height)); // Limit the height

        // Posts
        JPanel postPanel = new JPanel(new GridLayout(0, 3, 2, 2)); // 3 columns, auto rows
        posts.forEach(post -> displayPosts(postPanel, post));

        JScrollPane scrollPane = new JScrollPane(postPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Main content panel that holds both the search bar and the image grid
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.add(searchPanel);
        mainContentPanel.add(scrollPane); // This will stretch to take up remaining space
        return mainContentPanel;
    }

    private void displayPosts(JPanel postPanel, Post post)
    {
        var imageFile = post.content().pathFile();
        JLabel imageLabel = factory.createImageLabel(imageFile);
        imageLabel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                displayFullscreenPost(post); // Call method to display the clicked image
            }
        });
        postPanel.add(imageLabel);
    }

    private void displayFullscreenPost(Post post)
    {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        // Add the header and navigation panels back
        frame.add(factory.createHeaderPanel("Explore"), BorderLayout.NORTH);
        frame.add(factory.createNavigationPanel(arg -> onEvent.onNext(new NavigationEvent(arg))), BorderLayout.SOUTH);

        JPanel imageViewerPanel = new JPanel(new BorderLayout());

        // Read image details
        String username = post.author().username();
        String title = post.getTitle();
        String likes = String.valueOf(post.likes().size());

        // Top panel for username and time since posting
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton usernameLabel = new JButton(username);
        topPanel.add(usernameLabel, BorderLayout.WEST);

        // Prepare the image for display
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        try
        {
            BufferedImage originalImage = ImageIO.read(new File(post.content().pathFile()));
            ImageIcon imageIcon = new ImageIcon(originalImage);
            imageLabel.setIcon(imageIcon);
        }
        catch (IOException ex)
        {
            imageLabel.setText("Image not found");
        }

        // Bottom panel for title and likes
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JTextArea bioTextArea = new JTextArea(title);
        bioTextArea.setEditable(false);
        JLabel likesLabel = new JLabel("Likes: " + likes);
        bottomPanel.add(bioTextArea, BorderLayout.CENTER);
        bottomPanel.add(likesLabel, BorderLayout.SOUTH);

        // Adding the components to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(imageLabel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Re-add the header and navigation panels
        frame.add(factory.createHeaderPanel("Explore"), BorderLayout.NORTH);
        frame.add(factory.createNavigationPanel(arg -> onEvent.onNext(new NavigationEvent(arg))), BorderLayout.SOUTH);

        // Panel for the back button
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = factory.createBackButton();

        backButtonPanel.add(backButton);
        backButton.addActionListener(e -> onBackClicked());

        usernameLabel.addActionListener(e -> onPostUsernameClicked(post.author()));

        // Container panel for image and details
        JPanel containerPanel = new JPanel(new BorderLayout());

        containerPanel.add(topPanel, BorderLayout.NORTH);
        containerPanel.add(imageLabel, BorderLayout.CENTER);
        containerPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add the container panel and back button panel to the frame
        frame.add(backButtonPanel, BorderLayout.NORTH);
        frame.add(containerPanel, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }

    private void onBackClicked()
    {
        initializeUI();
        frame.revalidate();
        frame.repaint();
    }

    private void onPostUsernameClicked(User user)
    {
        onEvent.onNext(new UserClickedEvent(user));
    }
}