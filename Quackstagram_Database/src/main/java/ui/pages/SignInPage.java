package ui.pages;

import application.SignInRequest;
import ui.UIController;
import ui.events.LogInEvent;
import ui.events.SignUpEvent;
import ui.factory.QuackstagramUIFactory;
import ui.factory.UIFactory;
import util.PathFile;

import javax.swing.*;
import java.awt.*;

public class SignInPage extends QuackstagramPage
{
    private JTextField txtUsername;
    private JTextField txtPassword;

    public SignInPage(UIController manager, UIFactory factory)
    {
        super(manager, factory, "Log In");
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
        lblPhoto.setIcon(new ImageIcon(new ImageIcon(PathFile.DACSLogo).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        JPanel photoPanel = new JPanel(); // Use a panel to center the photo label
        photoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        photoPanel.add(lblPhoto);

        // Text fields panel
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        txtUsername = new JTextField("Username");
        txtPassword = new JTextField("Password");
        txtUsername.setForeground(Color.GRAY);
        txtPassword.setForeground(Color.GRAY);

        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(photoPanel);
        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(txtUsername);
        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(txtPassword);
        fieldsPanel.add(Box.createVerticalStrut(10));

        // Register button with black text
        JButton btnSignIn = new JButton("Sign-In");
        btnSignIn.addActionListener(e -> onSignInClicked());
        btnSignIn.setBackground(new Color(255, 90, 95)); // Use a red color that matches the mockup
        btnSignIn.setForeground(Color.BLACK); // Set the text color to black
        btnSignIn.setFocusPainted(false);
        btnSignIn.setBorderPainted(false);
        btnSignIn.setFont(new Font("Arial", Font.BOLD, 14));
        JPanel registerPanel = new JPanel(new BorderLayout()); // Panel to contain the register button
        registerPanel.setBackground(Color.WHITE); // Background for the panel
        registerPanel.add(btnSignIn, BorderLayout.CENTER);

        // Adding components to the frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(fieldsPanel, BorderLayout.CENTER);
        frame.add(registerPanel, BorderLayout.SOUTH);

        // New button for navigating to SignUpUI
        JButton btnRegisterNow = new JButton("No Account? Register Now");
        btnRegisterNow.addActionListener(e -> onRegisterNowClicked());
        btnRegisterNow.setBackground(Color.WHITE); // Set a different color for distinction
        btnRegisterNow.setForeground(Color.BLACK);
        btnRegisterNow.setFocusPainted(false);
        btnRegisterNow.setBorderPainted(false);

        // Panel to hold both buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10)); // Grid layout with 1 row, 2 columns
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(btnSignIn);
        buttonPanel.add(btnRegisterNow);

        // Adding the button panel to the frame
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void onSignInClicked()
    {
        onEvent.onNext(new LogInEvent(new SignInRequest(txtUsername.getText(), txtPassword.getText())));
    }

    private void onRegisterNowClicked()
    {
        onEvent.onNext(new SignUpEvent());
    }
}