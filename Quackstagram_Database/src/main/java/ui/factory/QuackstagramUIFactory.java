package ui.factory;

import entities.Picture;
import entities.Post;
import entities.User;
import entities.Notification;
import ui.UINavigationType;
import util.IAction;
import util.PathFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Component.CENTER_ALIGNMENT;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class QuackstagramUIFactory extends UIFactory
{
    private static final int WIDTH = 300;
    private static final int HEIGHT = 500;
    private static final int NAV_ICON_SIZE = 20; // Corrected static size for bottom icons
    private static final int IMAGE_WIDTH = WIDTH - 100; // Width for the image posts
    private static final int IMAGE_HEIGHT = 150; // Height for the image posts
    private static final Color LIKE_BUTTON_COLOR = new Color(255, 90, 95); // Color for the like button
    private static final int PROFILE_IMAGE_SIZE = 80; // Adjusted size for the profile image to match UI
    private static final int GRID_IMAGE_SIZE = WIDTH / 3; // Static size for grid images
    private static final int IMAGE_SIZE = WIDTH / 3; // Size for each image in the grid

    @Override
    public JFrame createFrame(String title)
    {
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setSize(WIDTH, HEIGHT);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        return frame;
    }

    @Override
    public JPanel createHeaderPanel(String header)
    {
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(51, 51, 51)); // Set a darker background for the header
        JLabel lblRegister = new JLabel(header);
        lblRegister.setFont(new Font("Arial", Font.BOLD, 16));
        lblRegister.setForeground(Color.WHITE); // Set the text color to white
        headerPanel.add(lblRegister);
        headerPanel.setPreferredSize(new Dimension(WIDTH, 40)); // Give the header a fixed height
        return headerPanel;
    }

    @Override
    public JPanel createNavigationPanel(IAction<UINavigationType> action)
    {
        JPanel navigationPanel = new JPanel();
        navigationPanel.setBackground(new Color(249, 249, 249));
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.X_AXIS));
        navigationPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        navigationPanel.add(createIconButton(PathFile.HomeIcon, UINavigationType.HOME, action));
        navigationPanel.add(Box.createHorizontalGlue());
        navigationPanel.add(createIconButton(PathFile.ExploreIcon, UINavigationType.EXPLORE, action));
        navigationPanel.add(Box.createHorizontalGlue());
        navigationPanel.add(createIconButton(PathFile.UploadIcon, UINavigationType.UPLOAD, action));
        navigationPanel.add(Box.createHorizontalGlue());
        navigationPanel.add(createIconButton(PathFile.NotificationIcon, UINavigationType.NOTIFICATION, action));
        navigationPanel.add(Box.createHorizontalGlue());
        navigationPanel.add(createIconButton(PathFile.ProfileIcon, UINavigationType.PROFILE, action));
        return navigationPanel;
    }

    @Override
    public JButton createIconButton(String iconPath, UINavigationType navigationType, IAction<UINavigationType> action)
    {
        ImageIcon iconOriginal = new ImageIcon(iconPath);
        Image iconScaled = iconOriginal.getImage().getScaledInstance(NAV_ICON_SIZE, NAV_ICON_SIZE, Image.SCALE_SMOOTH);
        JButton button = new JButton(new ImageIcon(iconScaled));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.addActionListener(e -> action.execute(navigationType));
        return button;
    }

    @Override
    public JLabel createStatLabel(String number, String text)
    {
        JLabel label = new JLabel("<html><div style='text-align: center;'>" + number + "<br/>" + text + "</div></html>", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(Color.BLACK);
        return label;
    }

    @Override
    public JButton createLikeButton()
    {
        JButton likeButton = new JButton("❤");
        likeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        likeButton.setBackground(LIKE_BUTTON_COLOR); // Set the background color for the like button
        likeButton.setOpaque(true);
        likeButton.setBorderPainted(false); // Remove border
        return likeButton;
    }

    @Override
    public JPanel createPostInformationPanel(Post post)
    {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel(post.title())); // Description
        infoPanel.add(new JLabel(String.valueOf(post.likes().size()))); // Likes
        return infoPanel;
    }

    @Override
    public JPanel createUserInformationPanel(User user)
    {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel,BoxLayout.Y_AXIS));
        JLabel userName = new JLabel(user.username());
        userName.setFont(new Font("Arial", Font.BOLD, 18));
        userPanel.add(userName);
        return userPanel;
    }

    @Override
    public ImageIcon createCroppedImage(Post post) throws IOException
    {
        BufferedImage originalImage = ImageIO.read(new File(post.content().pathFile()));
        BufferedImage croppedImage = originalImage.getSubimage(0, 0, Math.min(originalImage.getWidth(), WIDTH-20), Math.min(originalImage.getHeight(), HEIGHT-40));
        return new ImageIcon(croppedImage);
    }

    @Override
    public ImageIcon createImage(Post post) throws IOException
    {
        BufferedImage originalImage = ImageIO.read(new File(post.content().pathFile()));
        BufferedImage croppedImage = originalImage.getSubimage(0, 0, Math.min(originalImage.getWidth(), IMAGE_WIDTH), Math.min(originalImage.getHeight(), IMAGE_HEIGHT));
        return new ImageIcon(croppedImage);
    }

    @Override
    public JPanel createSpacingPanel()
    {
        JPanel spacingPanel = new JPanel();
        spacingPanel.setPreferredSize(new Dimension(WIDTH-10, 5)); // Set the height for spacing
        spacingPanel.setBackground(new Color(230, 230, 230)); // Grey color for spacing
        return spacingPanel;
    }

    @Override
    public JLabel createTextLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    @Override
    public JPanel createItemPanel()
    {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(Color.WHITE); // Set the background color for the item panel
        itemPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        itemPanel.setAlignmentX(CENTER_ALIGNMENT);
        return itemPanel;
    }

    @Override
    public JLabel createImageLabel()
    {
        // Crop the image to the fixed size
        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        imageLabel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border to image label
        return imageLabel;
    }

    @Override
    public JLabel createImageLabel(String pathFile)
    {
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(pathFile).getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
        return new JLabel(imageIcon);
    }

    @Override
    public JLabel createImagePreviewLabel()
    {
        JLabel imagePreviewLabel = new JLabel();
        imagePreviewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePreviewLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 3));

        // Set an initial empty icon to the imagePreviewLabel
        ImageIcon emptyImageIcon = new ImageIcon();
        imagePreviewLabel.setIcon(emptyImageIcon);

        return imagePreviewLabel;
    }

    @Override
    public JScrollPane createScrollPanel(JPanel panel)
    {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    @Override
    public JPanel createNotification(Notification notification)
    {
        // Add the notification to the panel
        JPanel notificationPanel = new JPanel(new BorderLayout());
        notificationPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel notificationLabel = new JLabel(notification.message());
        notificationPanel.add(notificationLabel, BorderLayout.CENTER);
        return notificationPanel;
    }

    @Override
    public JLabel createProfileIcon(Picture picture)
    {
        ImageIcon profileIcon = new ImageIcon(new ImageIcon(picture.pathFile()).getImage().getScaledInstance(PROFILE_IMAGE_SIZE, PROFILE_IMAGE_SIZE, Image.SCALE_SMOOTH));
        JLabel profileImage = new JLabel(profileIcon);
        profileImage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return profileImage;
    }

    @Override
    public ImageIcon createGridImage(Picture picture)
    {
        return new ImageIcon(new ImageIcon(picture.pathFile()).getImage().getScaledInstance(GRID_IMAGE_SIZE, GRID_IMAGE_SIZE, Image.SCALE_SMOOTH));
    }

    @Override
    public JTextArea createTitleTextArea(String defaultText)
    {
        JTextArea titleTextArea = new JTextArea(defaultText);
        titleTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleTextArea.setLineWrap(true);
        titleTextArea.setWrapStyleWord(true);
        return titleTextArea;
    }

    @Override
    public JScrollPane createTitleScrollPane(JTextArea titleTextArea)
    {
        JScrollPane titleScrollPane = new JScrollPane(titleTextArea);
        titleScrollPane.setPreferredSize(new Dimension(WIDTH - 50, HEIGHT / 6));
        return titleScrollPane;
    }

    @Override
    public JButton createBackButton()
    {
        JButton backButton = new JButton("Back");
        // Make the button take up the full width
        backButton.setPreferredSize(new Dimension(WIDTH-20, backButton.getPreferredSize().height));
        return backButton;
    }
}