import apps.ToffeeStore;
import utilities.Utility;

public class Main
{
    public static void main(String[] args)
    {
        Utility.printFormatedMessage("!! Test System As Admin Using Credentials: fady kamal, 20210282", false);
        ToffeeStore store = new ToffeeStore();
        store.run();
    }
}