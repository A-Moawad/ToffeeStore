package apps;

import java.util.Map;
import java.util.Scanner;
/**
 * The abstract class representing an application.
 * This class provides common functionality and attributes for different types of applications.
 */
public abstract class App {

    /**
     * Scanner object to read user input from the console.
     */
    protected final Scanner scanner = new Scanner(System.in);

    /**
     * The status of the application.
     * If true, the application is running; otherwise, it is not running.
     */
    protected boolean appRunning;

    /**
     * The user's choice input for the application.
     */
    protected String choice;

    /**
     * A map containing prompts for various menus in the application.
     * The keys represent the menu names, and the values represent the corresponding prompts.
     */
    protected Map<String, String> prompts;

    /**
     * Constructs an App object with the default app running status as true.
     */
    public App() {
        this.appRunning = true;
    }

    /**
     * Constructs an App object with the specified app running status and prompts.
     *
     * @param appStatus The initial status of the app.
     * @param prompts   The map containing prompts for various menus.
     */
    public App(boolean appStatus, Map<String, String> prompts) {
        this.appRunning = appStatus;
        this.prompts = prompts;
    }

    /**
     * Sets the status of the app.
     *
     * @param status The status of the app.
     */
    public void setAppStatus(boolean status) {
        this.appRunning = status;
    }

    /**
     * Sets the user's choice.
     *
     * @param choice The user's choice.
     */
    public void setChoice(String choice) {
        this.choice = choice;
    }

    /**
     * Sets the prompts map.
     *
     * @param prompts The map containing prompts for various menus.
     */
    public void setPrompts(Map<String, String> prompts) {
        this.prompts = prompts;
    }

    /**
     * Returns the current status of the app.
     *
     * @return The current status of the app.
     */
    public boolean getAppStatus() {
        return this.appRunning;
    }

    /**
     * Returns the user's choice.
     *
     * @return The user's choice.
     */
    public String getUserChoice() {
        return this.choice;
    }

    /**
     * Returns the prompts map.
     *
     * @return The map containing prompts for various menus.
     */
    public Map<String, String> getPrompts() {
        return this.prompts;
    }

    /**
     * The main method to run the application.
     * This method needs to be implemented in the subclasses.
     */
    public abstract void run();

    /**
     * Displays the menu and takes user input for the specified menu.
     *
     * @param menu The name of the menu.
     */
    public void displayMenuAndTakeInputFromUser(String menu) {
        // Create a new scanner
        Scanner scan = new Scanner(System.in);

        // Display the menu
        System.out.print(getPrompts().get(menu));

        // Take user input
        String s = scan.nextLine();
        setChoice(s);
    }
}
