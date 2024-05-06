package ui.pages;

import application.RegisterUserRequest;
import ui.UIController;
import ui.events.RegisterEvent;
import ui.events.SignInEvent;
import ui.factory.QuackstagramUIFactory;
import ui.factory.UIFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SignUpPage extends QuackstagramPage
{
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTextField txtBio;
    private File pictureFile;

    public SignUpPage(UIController manager, UIFactory factory)
    {
        super(manager, factory, "Register");
        initializeUI();
    }

    private void initializeUI()
    {
        frame.setLayout(new BorderLayout(10, 10));
        // Header with the Register label
        JPanel headerPanel = factory.createHeaderPanel("Quackstagram 🐥");

        // Profile picture placeholder without border
        JLabel lblPhoto = new JLabel();
        lblPhoto.setPreferredSize(new Dimension(80, 80));
        lblPhoto.setHorizontalAlignment(JLabel.CENTER);
        lblPhoto.setVerticalAlignment(JLabel.CENTER);
        lblPhoto.setIcon(new ImageIcon(new ImageIcon("img/logos/DACS.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        JPanel photoPanel = new JPanel(); // Use a panel to center the photo label
        photoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        photoPanel.add(lblPhoto);

        // Text fields panel
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        txtUsername = new JTextField("Username");
        txtPassword = new JTextField("Password");
        txtBio = new JTextField("Bio");
        txtBio.setForeground(Color.GRAY);
        txtUsername.setForeground(Color.GRAY);
        txtPassword.setForeground(Color.GRAY);

        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(photoPanel);
        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(txtUsername);
        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(txtPassword);
        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(txtBio);
        JButton btnUploadPhoto = new JButton("Upload Photo");

        btnUploadPhoto.addActionListener(this::onUploadClicked);
        JPanel photoUploadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        photoUploadPanel.add(btnUploadPhoto);
        fieldsPanel.add(photoUploadPanel);

        // Register button with black text
        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(e -> onRegisterClicked());
        btnRegister.setBackground(new Color(255, 90, 95)); // Use a red color that matches the mockup
        btnRegister.setForeground(Color.BLACK); // Set the text color to black
        btnRegister.setFocusPainted(false);
        btnRegister.setBorderPainted(false);
        btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
        JPanel registerPanel = new JPanel(new BorderLayout()); // Panel to contain the register button
        registerPanel.setBackground(Color.WHITE); // Background for the panel
        registerPanel.add(btnRegister, BorderLayout.CENTER);

        // Adding components to the frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(fieldsPanel, BorderLayout.CENTER);
        frame.add(registerPanel, BorderLayout.SOUTH);

        // Adding the sign in button to the register panel or another suitable panel
        JButton btnSignIn = new JButton("Already have an account? Sign In");
        btnSignIn.addActionListener(e -> onSignInClicked());
        registerPanel.add(btnSignIn, BorderLayout.SOUTH);
    }

    private void onUploadClicked(ActionEvent actionEvent)
    {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        fileChooser.setFileFilter(filter);

        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
            pictureFile = fileChooser.getSelectedFile();
    }

    private void onRegisterClicked()
    {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String bio = txtBio.getText();

        onEvent.onNext(new RegisterEvent(new RegisterUserRequest(username, password, bio, pictureFile)));
    }

    private void onSignInClicked()
    {
        onEvent.onNext(new SignInEvent());
    }
}