package apps;

import model.categoriesmanger.CategoriesManger;
import model.payment.Payment;
import model.tool.Cart;
import model.users.Admin;
import model.users.Customer;
import model.users.details.Order;
import model.users.details.ShippingInformation;
import services.OTP.otpSystem;
import utilities.Utility;
import java.util.ArrayList;


public class ToffeeStore extends  App
{

    /* Instance Attributes */
    private ArrayList<Admin> storeAdmins;
    private ArrayList<Customer> storeCustomers;
    private CategoriesManger storeCatManger;
    private ArrayList<Order> storeOrders;

    public ToffeeStore()
    {
        super(true, Utility.TOFFEE_STORE_PROMPTS);
        Initialization();
    }

    @Override
    public void run()
    {
        // sep
        System.out.println(Utility.sep);

        // display proper prompt and take choice from user
        displayMenuAndTakeInputFromUser("Main");

        // main loop
        while (getAppStatus())
        {
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
            else if (getUserChoice().equals("4")){
                setAppStatus(false);
                break;
            }
            else
            {
                Utility.printFormatedMessage("!! Invalid !!", false);
            }

            // sep
            System.out.println(Utility.sep);

            // display proper prompt and take choice from user
            displayMenuAndTakeInputFromUser("Main");
        }
    }

    // init system with temp values
    private void Initialization()
    {
        this.storeAdmins = new ArrayList<Admin>();
        this.storeCustomers = new ArrayList<Customer>();
        this.storeOrders = new ArrayList<Order>();
        this.storeCatManger = new CategoriesManger();

        // testing login admin
        Admin admin = new Admin("fady kamal", "24221510", "01277157747", "popfadykamal151617@gmail.com", this.storeCatManger);
        this.storeAdmins.add(admin);
    }

    private void loginAsAdmin()
    {
        // sep
        System.out.println(Utility.sep);

        // take valid user name
        String userName = Utility.getValidNameFromUser();

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

    private void loginAsCustomer()
    {
        // sep
        System.out.println(Utility.sep);

        // take valid user name
        String userName = Utility.getValidNameFromUser();

        // sep
        System.out.println(Utility.sep);

        // take password
        String password = Utility.getPasswordFromUser();

        // if in customers: create admin app else print not an admin
        if (inCustomers(userName, password))
        {
            // fetch him and start CutomerApp

//            CustomerApp app = new CustomerApp(getCutomer(userName, password), storeCustomers, storeOrders, storeCatManger);
//            app.run();
        }
        else
        {
            Utility.printFormatedMessage("!! Not An Customer !!", false);
        }

    }

    private void signUp()
    {
        // sep
        System.out.println(Utility.sep);

        // take valid user name
        String userName = Utility.getValidNameFromUser();

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
        String email = Utility.getValidEmailFromUser();

        // sep
        System.out.println(Utility.sep);

        // take phoneNumber
        String phoneNumber = Utility.getValidPhoneNumber();

        // sep
        System.out.println(Utility.sep);

        // take address
        String address = Utility.getValidAddressFromUser();

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
    public Admin getAdminByName(String userName, String password)
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
    private Customer getCustomerByName(String userName, String password)
    {
        // search for it
        for(Customer customer: this.storeCustomers)
        {
            if (customer.getUserName().equals(userName) && customer.getPassword().equals(password))
            {
                return customer;
            }

        }

        return new Customer();
    }

}
