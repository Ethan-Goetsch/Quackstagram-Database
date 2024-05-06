package ui.factory;

import entities.Notification;
import entities.Picture;
import entities.Post;
import entities.User;
import ui.UINavigationType;
import util.IAction;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public abstract class UIFactory
{
    public abstract JFrame createFrame(String title);
    public abstract JPanel createHeaderPanel(String header);

    public abstract JPanel createNavigationPanel(IAction<UINavigationType> action);

    public abstract JButton createIconButton(String iconPath, UINavigationType navigationType, IAction<UINavigationType> action);

    public abstract JLabel createStatLabel(String number, String text);

    public abstract JButton createLikeButton();

    public abstract JPanel createPostInformationPanel(Post post);

    public abstract JPanel createUserInformationPanel(User user);

    public abstract ImageIcon createCroppedImage(Post post) throws IOException;

    public abstract ImageIcon createImage(Post post) throws IOException;

    public abstract JPanel createSpacingPanel();

    public abstract JLabel createTextLabel(String text);

    public abstract JPanel createItemPanel();

    public abstract JLabel createImageLabel();

    public abstract JLabel createImageLabel(String pathFile);

    public abstract JLabel createImagePreviewLabel();

    public abstract JScrollPane createScrollPanel(JPanel panel);

    public abstract JPanel createNotification(Notification notification);

    public abstract JLabel createProfileIcon(Picture picture);

    public abstract ImageIcon createGridImage(Picture picture);

    public abstract JTextArea createTitleTextArea(String defaultText);

    public abstract JScrollPane createTitleScrollPane(JTextArea titleTextArea);

    public abstract JButton createBackButton();
}