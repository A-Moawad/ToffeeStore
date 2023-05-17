import apps.ToffeeStore;
import utilities.Utility;

/**
 * The Main class represents the entry point of the program.
 * It is responsible for executing the ToffeeStore application.
 */
public class Main
{

    /**
     * The main method is the starting point of the program.
     * It creates an instance of the ToffeeStore class and runs it.
     *
     * @param args The command-line arguments passed to the program (not used in this case).
     */
    public static void main(String[] args) {
        // Print a formatted message for testing as an admin using specific credentials
        Utility.printFormatedMessage("!! Test System As Admin Using Credentials: fady kamal, 20210282", false);

        // Create an instance of ToffeeStore
        ToffeeStore store = new ToffeeStore();

        // Run the ToffeeStore application
        store.run();
    }
}





