import application.ApplicationManager;
import database.Database;
import ui.UIController;
import ui.factory.DuckUIFactory;
import ui.factory.QuackstagramUIFactory;
import ui.factory.UIFactory;

public class Program
{
    public static void main(String[] args)
    {
        ApplicationManager controller = new ApplicationManager();
        UIFactory factory = new QuackstagramUIFactory();
        UIController uiController = new UIController(controller, factory);

        uiController.openSignIn();
    }
}