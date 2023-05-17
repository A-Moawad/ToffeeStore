package apps;

import model.categoriesmanger.CategoriesManger;
import model.categoriesmanger.Category;
import model.payment.EWallet;
import model.payment.Payment;
import model.tool.Cart;
import model.users.Admin;
import model.users.Customer;
import model.users.details.Order;
import model.users.details.ShippingInformation;
import services.OTP.otpSystem;
import utilities.Utility;
import java.util.ArrayList;

/**
 * This class simulates ToffeeStore properties and methods<br>Extends App class.
 */
public class ToffeeStore extends  App
{

    /* Instance Attributes */

    /**
     * List of administrators associated with the store.
     */
    private ArrayList<Admin> storeAdmins;

    /**
     * List of customers associated with the store.
     */
    private ArrayList<Customer> storeCustomers;

    /**
     * Manager for managing categories of products in the store.
     */
    private CategoriesManger storeCatManger;

    /**
     * List of orders placed in the store.
     */
    private ArrayList<Order> storeOrders;


    /**
     * Default constructor of ToffeeStore<br>note: It initializes instance attributes via initialization function.
     */
    public ToffeeStore()
    {
        super(true, Utility.TOFFEE_STORE_PROMPTS);
        Initialization();
    }

    /**
     * Overrides the run() method from the superclass App.
     * This method runs the main loop of the program, displaying menus, (loginAsAdmin, loginAsCustomer, SignUp).
     * taking user input, and performing corresponding actions based on the user's choice.
     * @see App
     */
    @Override
    public void run()
    {
        // Display separator
        System.out.println(Utility.sep);

        // Display main menu prompt and take input from the user
        displayMenuAndTakeInputFromUser("Main");

        // Main loop
        while (getAppStatus())
        {
            // Check user's choice and perform corresponding action
            if (getUserChoice().equals("1"))
            {
                loginAsAdmin();
            }
            else if (getUserChoice().equals("2"))
            {
                loginAsCustomer();
            }
            else if (getUserChoice().equals("3"))
            {
                signUp();
            }
            else if (getUserChoice().equals("4"))
            {
                // Set app status to false and exit loop
                setAppStatus(false);
                break;
            }
            else
            {
                // Display error message for invalid input
                Utility.printFormatedMessage("!! Invalid !!", false);
            }

            // Display separator
            System.out.println(Utility.sep);

            // Display main menu prompt and take input from the user
            displayMenuAndTakeInputFromUser("Main");
        }
    }

    /**
     * Initializes the store with hard-coded values as a replacement for a database.
     * This method is called in the constructor of the ToffeeStore class.
     *
     * @see ToffeeStore
     */
    private void Initialization()
    {
        // init attributes
        this.storeAdmins = new ArrayList<Admin>();
        this.storeCustomers = new ArrayList<Customer>();
        this.storeOrders = new ArrayList<Order>();
        this.storeCatManger = new CategoriesManger();

        // customers
        Customer c = new Customer("fady kamal", "20210282", "popfadykamal151617@gmail.com", "01277157747", new Cart(), new Payment(10, new EWallet(500)), new ShippingInformation("fady kamal", "01220680646", "popfadykamal151617@gmail.com", "Cairo", ""));
        Customer c1 = new Customer("adham mahmoud", "657941", "popfadykamal151617@gmail.com", "01277157747", new Cart(), new Payment(), new ShippingInformation());
        Customer c2 = new Customer("mohamed kamal", "234", "popfadykamal151617@gmail.com", "01277157747", new Cart(), new Payment(), new ShippingInformation());
        Customer c3 = new Customer("ahmed kamal", "2341", "popfadykamal151617@gmail.com", "01277157747", new Cart(), new Payment(), new ShippingInformation());

        this.storeCustomers.add(c);
        this.storeCustomers.add(c1);
        this.storeCustomers.add(c2);
        this.storeCustomers.add(c3);

        // init addmins
        Admin admin = new Admin("fady kamal", "20210282", "01277157747", "popfadykamal151617@gmail.com", this.storeCatManger);
        this.storeAdmins.add(admin);

        // add init categories
        Category cake = new Category("Cake");
        cake.addProduct("Sponge Cake", "", 300, 20);
        cake.addProduct("Cheese Cake", "", 250,  30);

        // add init categories
        Category chocolate = new Category("Chocolate");
        chocolate.addProduct("Milk Chocolate", "", 60, 15);
        chocolate.addProduct("White Chocolate", "", 70, 10);
        chocolate.addProduct("Dark Chocolate", "", 50, 40);

        // add them to catmanger
        ArrayList<Category> categories = new ArrayList<Category>(2);
        categories.add(cake);
        categories.add(chocolate);

        // init manger
        this.storeCatManger = new CategoriesManger(categories);
    }

    /**
     *  This function takes login credentials (username, password) from user and logs him in as admin.
     *  User won't be able to log in unless he enters a valid name and password, and exists in system admins.
     *  When user got validated as an admin it instantiates an instance from admin app and runs it with.
     * @see Admin
     * @see AdminApp
     */
    private void loginAsAdmin()
    {
        // sep
        System.out.println(Utility.sep);

        // take valid user name
        String userName = Utility.getValidNameFromUser(false);

        // sep
        System.out.println(Utility.sep);

        // take password
        String password = Utility.getPasswordFromUser();

        // if admin: create admin app else print not an admin
        if (inAdmins(userName, password))
        {
            // get him and create app for him
            AdminApp app = new AdminApp(getAdminByName(userName, password), storeCustomers, storeOrders, storeCatManger);
            app.run();
        }
        else
        {
            Utility.printFormatedMessage("!! Not An Admin !!", false);
        }
    }


    /**
     *  This function takes login credentials (username, password) from user and logs him in as customer.
     *  User won't be able to log in unless he enters a valid name and password, and exists in system customers.
     *  When user got validated as a customers it fetches that customer from the system and instantiates an instance from Customer app, thereafter runs the system.
     * @see Customer
     * @see CustomerApp
     */
    private void loginAsCustomer()
    {
        // sep
        System.out.println(Utility.sep);

        // take valid user name
        String userName = Utility.getValidNameFromUser(false);

        // sep
        System.out.println(Utility.sep);

        // take password
        String password = Utility.getPasswordFromUser();

        // if in customers: create admin app else print not an admin
        if (inCustomers(userName, password))
        {
            // fetch him and start CustomerApp
            CustomerApp app = new CustomerApp(getCustomerByName(userName, password), storeOrders, storeCatManger);
            app.run();
        }
        else
        {
            Utility.printFormatedMessage("!! Not An Customer !!", false);
        }
    }

    /**
     * This function add new customers to the system.
     * As soon as user enters his username and password, system checks whether he already exists or not, unless user exists system sends him otp using preEntered email<br>
     * After user confirms OTP, system register him into system customers.
     * @see otpSystem
     * @see Customer
     */
    private void signUp()
    {
        // sep
        System.out.println(Utility.sep);

        // take valid user name
        String userName = Utility.getValidNameFromUser(false);

        // sep
        System.out.println(Utility.sep);

        // take password
        String password = Utility.getPasswordFromUser();

        /******* Check if already exits in customers *********/
        if (inCustomers(userName, password))
        {
            Utility.printFormatedMessage("!! Already Exists !!", false);
            return;
        }

        // sep
        System.out.println(Utility.sep);

        // take email
        String email = Utility.getValidEmailFromUser(false);

        // sep
        System.out.println(Utility.sep);

        // take phoneNumber
        String phoneNumber = Utility.getValidPhoneNumber(false);

        // sep
        System.out.println(Utility.sep);

        // take address
        String address = Utility.getValidAddressFromUser(false);

        // sep
        System.out.println(Utility.sep);

        // take city
        String city = Utility.getValidCityFromUser();

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        otpSystem otpSystem = new otpSystem(email, userName);
        otpSystem.run();
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

        // create customer and add it to customers
        Customer c = new Customer(userName, password, email, phoneNumber, new Cart(), new Payment(), new ShippingInformation(userName, phoneNumber, email, city, address));

        // add to customers
        this.storeCustomers.add(c);

        // mes
        Utility.printFormatedMessage("!!  You have successfully signed up !! ", false);
    }

    /**
     * This function takes an admin credentials (username, password) and check whether he exists in system admins or not
     * @param userName Logger username
     * @param password Logger password
     * @return true if admin in database, else return false.
     */
    private boolean inAdmins(String userName, String password)
    {
        // flag
        boolean isAdmin = false;

        // search for it
        for(Admin admin: this.storeAdmins)
        {
            if (admin.getUserName().equals(userName) && admin.getPassword().equals(password))
            {
                return true;
            }
        }

        return isAdmin;
    }

    /**
     * Retrieves an Admin object from the storeAdmins list based on the given username and password.
     * If a matching admin is found, it is returned; otherwise, a new empty Customer object is returned.
     *
     * @param userName The username to search for.
     * @param password The password to search for.
     * @return The Admin object that matches the given username and password, or a new empty Admin object if no match is found.
     */
    private Admin getAdminByName(String userName, String password)
    {
        for(Admin admin: this.storeAdmins)
        {
            if (admin.getUserName().equals(userName) && admin.getPassword().equals(password))
            {
                return admin;
            }
        }

        return new Admin();
    }

    /**
     * This function takes a Customer credentials (username, password) and check whether he exists in system customers or not.
     * @param userName Logger username
     * @param password Logger password
     * @return true if customer exists in database, else return false.
     */
    private boolean inCustomers(String userName, String password)
    {
        // flag
        boolean isCustomer = false;

        // search for it
        for(Customer customer: this.storeCustomers)
        {
            if (customer.getUserName().equals(userName) && customer.getPassword().equals(password))
            {
                return true;
            }
        }

        return isCustomer;
    }

    /**
     * Retrieves a Customer object from the storeCustomers list based on the given username and password.
     * If a matching customer is found, it is returned; otherwise, a new empty Customer object is returned.
     *
     * @param userName The username to search for.
     * @param password The password to search for.
     * @return The Customer object that matches the given username and password, or a new empty Customer object if no match is found.
     */
    private Customer getCustomerByName(String userName, String password)
    {
        // Search for the customer in the storeCustomers list
        for (Customer customer : this.storeCustomers)
        {
            // Check if the customer's username and password match the given values
            if (customer.getUserName().equals(userName) && customer.getPassword().equals(password))
            {
                // Return the matching customer
                return customer;
            }
        }

        // If no match is found, return a new empty Customer object
        return new Customer();
    }
}
