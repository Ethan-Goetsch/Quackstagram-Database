package ui.pages;

import entities.User;
import ui.UIController;
import ui.events.NavigationEvent;
import ui.events.ProfileClickedEvent;
import ui.factory.QuackstagramUIFactory;
import ui.factory.UIFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProfilePage extends QuackstagramPage
{
    private JPanel contentPanel; // Panel to display the image grid or the clicked image
    private final User profileOwner;
    private final User currentUser; // User object to store the current user's information

    public ProfilePage(UIController manager, UIFactory factory, User profileOwner, User currentUser)
    {
        super(manager, factory, "Profile");
        this.profileOwner = profileOwner;
        this.currentUser = currentUser;
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
        frame.setLayout(new BorderLayout());
        contentPanel = new JPanel();
        // Panel for the header
        JPanel headerPanel = createHeaderPanel();       // Initialize header panel
        // Panel for the navigation
        JPanel navigationPanel = factory.createNavigationPanel(arg -> onEvent.onNext(new NavigationEvent(arg))); // Initialize navigation panel

        frame.getContentPane().removeAll(); // Clear existing components

        // Re-add the header and navigation panels
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(navigationPanel, BorderLayout.SOUTH);

        // Initialize the image grid
        initializePostGrid();

        frame.revalidate();
        frame.repaint();
    }

    private JPanel createHeaderPanel()
    {
        int postCount = profileOwner.posts().size();
        int followersCount = profileOwner.followers().size();
        int followingCount = profileOwner.followings().size();

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.GRAY);

        // Top Part of the Header (Profile Image, Stats, Follow Button)
        JPanel topHeaderPanel = new JPanel(new BorderLayout(10, 0));
        topHeaderPanel.setBackground(new Color(249, 249, 249));

        // Profile image
        JLabel profileImage = factory.createProfileIcon(profileOwner.profilePicture());
        topHeaderPanel.add(profileImage, BorderLayout.WEST);

        // Stats Panel
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        statsPanel.setBackground(new Color(249, 249, 249));

        statsPanel.add(factory.createStatLabel(Integer.toString(postCount) , "Posts"));
        statsPanel.add(factory.createStatLabel(Integer.toString(followersCount), "Followers"));
        statsPanel.add(factory.createStatLabel(Integer.toString(followingCount), "Following"));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 10, 0)); // Add some vertical padding

        // Profile Button
        var profileButtonText = currentUser == profileOwner
                ? "Edit Profile"
                : currentUser.isFollowing(profileOwner)
                    ? "Unfollow"
                    : "Follow";
        JButton profileButton = new JButton(profileButtonText);
        profileButton.addActionListener(e -> onProfileButtonClicked());

        profileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileButton.setFont(new Font("Arial", Font.BOLD, 12));
        profileButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, profileButton.getMinimumSize().height)); // Make the button fill the horizontal space
        profileButton.setBackground(new Color(225, 228, 232)); // A soft, appealing color that complements the UI
        profileButton.setForeground(Color.BLACK);
        profileButton.setOpaque(true);
        profileButton.setBorderPainted(false);
        profileButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add some vertical padding

        // Add Stats and Profile Button to a combined Panel
        JPanel statsFollowPanel = new JPanel();
        statsFollowPanel.setLayout(new BoxLayout(statsFollowPanel, BoxLayout.Y_AXIS));
        statsFollowPanel.add(statsPanel);
        statsFollowPanel.add(profileButton);
        topHeaderPanel.add(statsFollowPanel, BorderLayout.CENTER);

        headerPanel.add(topHeaderPanel);

        // Profile Name and Bio Panel
        JPanel profileNameAndBioPanel = new JPanel();
        profileNameAndBioPanel.setLayout(new BorderLayout());
        profileNameAndBioPanel.setBackground(new Color(249, 249, 249));

        JLabel profileNameLabel = new JLabel(profileOwner.username());
        profileNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        profileNameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Padding on the sides

        JTextArea profileBio = new JTextArea(profileOwner.bio());

        profileBio.setEditable(false);
        profileBio.setFont(new Font("Arial", Font.PLAIN, 12));
        profileBio.setBackground(new Color(249, 249, 249));
        profileBio.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Padding on the sides

        profileNameAndBioPanel.add(profileNameLabel, BorderLayout.NORTH);
        profileNameAndBioPanel.add(profileBio, BorderLayout.CENTER);

        headerPanel.add(profileNameAndBioPanel);
        return headerPanel;
    }

    private void initializePostGrid()
    {
        contentPanel.removeAll(); // Clear existing content
        contentPanel.setLayout(new GridLayout(0, 3, 5, 5)); // Grid layout for image grid

        profileOwner.posts().forEach(post ->
                {
                    ImageIcon imageIcon = factory.createGridImage(post.content());
                    JLabel imageLabel = new JLabel(imageIcon);
                    imageLabel.addMouseListener(new MouseAdapter()
                    {
                        @Override
                        public void mouseClicked(MouseEvent e)
                        {
                            displayImage(imageIcon); // Call method to display the clicked image
                        }
                    });
                    contentPanel.add(imageLabel);
                });

        JScrollPane scrollPane = factory.createScrollPanel(contentPanel);
        frame.add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center

        frame.revalidate();
        frame.repaint();
    }

    private void displayImage(ImageIcon imageIcon)
    {
        contentPanel.removeAll(); // Remove existing content
        contentPanel.setLayout(new BorderLayout()); // Change layout for image display

        JLabel fullSizeImageLabel = new JLabel(imageIcon);
        fullSizeImageLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPanel.add(fullSizeImageLabel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> onBackClicked());
        contentPanel.add(backButton, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private void onBackClicked()
    {
        frame.getContentPane().removeAll(); // Remove all components from the frame
        initializeUI(); // Re-initialize the UI
    }

    private void onProfileButtonClicked()
    {
        onEvent.onNext(new ProfileClickedEvent(profileOwner));
    }
}