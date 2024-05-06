package ui.pages;

import ui.UIController;
import ui.events.NavigationEvent;
import ui.events.UploadPostEvent;
import ui.factory.QuackstagramUIFactory;
import ui.factory.UIFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class UploadPage extends QuackstagramPage
{
    private JLabel imagePreviewLabel;
    private JTextArea titleTextArea;

    private File imageFile;

    public UploadPage(UIController manager, UIFactory factory)
    {
        super(manager, factory, "Post");
        initializeUI();
    }

    private void initializeUI()
    {
        JPanel headerPanel = factory.createHeaderPanel("Upload"); // Reuse the createHeaderPanel method
        JPanel navigationPanel = factory.createNavigationPanel(arg -> onEvent.onNext(new NavigationEvent(arg))); // Reuse the createNavigationPanel method

        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Image preview
        imagePreviewLabel = factory.createImagePreviewLabel();
        contentPanel.add(imagePreviewLabel);

        // Title text area
        titleTextArea = factory.createTitleTextArea("Enter a caption");
        JScrollPane bioScrollPane = factory.createTitleScrollPane(titleTextArea);
        contentPanel.add(bioScrollPane);

        JButton selectImageButton = new JButton("Select Image");
        selectImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectImageButton.addActionListener(e -> onSelectImageClicked());
        contentPanel.add(selectImageButton);

        JButton uploadButton = new JButton("Upload");
        uploadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadButton.addActionListener(e -> onUploadClicked());
        contentPanel.add(uploadButton);

        // Add panels to frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.add(navigationPanel, BorderLayout.SOUTH);
    }

    private void previewImage(ImageIcon icon)
    {
        imagePreviewLabel.setIcon(icon);

        // Check if imagePreviewLabel has a valid size
        if (imagePreviewLabel.getWidth() <= 0 || imagePreviewLabel.getHeight() <= 0) return;
        Image image = icon.getImage();

        // Calculate the dimensions for the image preview
        int previewWidth = imagePreviewLabel.getWidth();
        int previewHeight = imagePreviewLabel.getHeight();
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double widthRatio = (double) previewWidth / imageWidth;
        double heightRatio = (double) previewHeight / imageHeight;
        double scale = Math.min(widthRatio, heightRatio);
        int scaledWidth = (int) (scale * imageWidth);
        int scaledHeight = (int) (scale * imageHeight);

        // Set the image icon with the scaled image
        icon.setImage(image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH));
    }

    private void onSelectImageClicked()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an image file");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg");
        fileChooser.addChoosableFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            imageFile = fileChooser.getSelectedFile();
            previewImage(new ImageIcon(imageFile.getPath()));
        }
    }

    private void onUploadClicked()
    {
        onEvent.onNext(new UploadPostEvent(titleTextArea.getText(), imageFile));
    }
}