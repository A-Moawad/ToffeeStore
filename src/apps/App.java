package apps;

import java.util.Map;
import java.util.Scanner;

public abstract class App
{
    protected final Scanner scanner = new Scanner(System.in);

    protected boolean appRunning;
    protected String choice;
    protected Map<String, String> prompts;

    public App()
    {
        this.appRunning = true;
    }
    public App(boolean appStatus, Map<String, String> prompts) {
        this.appRunning = appStatus;
        this.prompts = prompts;
    }

    /* setters and getters */
    public void setAppStatus(boolean status) {
        this.appRunning = status;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void setPrompts(Map<String, String> prompts) {
        this.prompts = prompts;
    }

    public boolean getAppStatus() {
        return this.appRunning;
    }

    public String getUserChoice() {
        return this.choice;
    }

    public Map<String, String> getPrompts() {
        return this.prompts;
    }

    public abstract void run();

    public void displayMenuAndTakeInputFromUser(String menu)
    {
        // scanner
        Scanner scan = new Scanner(System.in);

        // display menu
        System.out.print((getPrompts().get(menu)));

        // take value
        String s = scan.nextLine();
        setChoice(s);

    }

}
