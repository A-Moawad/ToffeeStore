package apps;

import enums.OrderStatus;
import enums.PaymentMethods;
import model.categoriesmanger.CategoriesManger;
import model.categoriesmanger.Category;
import model.categoriesmanger.Product;
import model.users.Customer;
import model.users.details.Order;
import model.users.details.OrderDetails;
import model.users.details.ShippingInformation;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class CustomerApp extends App
{
    private Customer customer;
    private CategoriesManger catManger;

    private ArrayList<Order> orders;

    public CustomerApp()
    {
        // init parent with true status as init and proper prompts
        super(true, Utility.CUSTOMER_APP_PROMPTS);

        // init attributes with default value
        this.customer = new Customer();
        this.catManger = new CategoriesManger();
    }

    public CustomerApp(Customer customer, CategoriesManger catManger, ArrayList<Order> orders)
    {
        super(true, Utility.CUSTOMER_APP_PROMPTS);
        this.customer = customer;
        this.orders = orders;
        this.catManger = catManger;
    }

    @Override
    public void run()
    {
        // welcome message
        Utility.printWelcomingMessage("!! Welcome " + this.customer.getUserName() + " !!");

        // display market
        catManger.displayAllCategories();

        // display menu and take input from user
        displayMenuAndTakeInputFromUser("Main");

        // main loop
        while (getAppStatus())
        {
            switch (getUserChoice())
            {
                case "1":
                    selectProduct();
                    break;

                case "2":
                    orders();
                    break;

                case "3":
                    cart();
                    break;

                case "4":
                    profile();
                    break;

                case "5":
                    setAppStatus(false);
                    break;

                default:
                    System.out.println("!! Invalid !!");
                    break;
            }

            // separator
            System.out.println(Utility.sep);

            // display market
            catManger.displayAllCategories();

            // display menu and take input from user
            displayMenuAndTakeInputFromUser("Main");

        }

    }

    private void displayCustomerInformation()
    {
        this.customer.displayInfo();
    }

    public void selectProduct()
    {
        // new scanner
        Scanner scanner = new Scanner(System.in);

        // take category name
        Utility.printFormatedMessage("Category name: ", true);
        String categoryName = scanner.nextLine();

        // if exists display selected menu and complete, else print not exists and return
        if (catManger.exists(categoryName)) {
            // get it
            Category tmp = catManger.getCategoryByName(categoryName);

            // display it to user
            tmp.displayAllProducts();

            // take product name and check if exists
            Utility.printFormatedMessage("Product name: ", true);
            String productName = scanner.nextLine();

            // check if product exists
            if (tmp.exists(productName)) {
                // get product
                Product p = tmp.getProductByName(productName);

                // print the product
                p.print();

                // take quantity
                Utility.printFormatedMessage("Quantity you intend to buy: ", true);
                int quantity = scanner.nextInt();

                // check if there is enough quantity in inventory
                if ((p.getM_availableAmount() - quantity) >= 0)
                {
                    // display prompt add product to cart
                    this.customer.getCart().addToCart(p, quantity);

                    // confirming message
                    Utility.printFormatedMessage("!! Product Added Successfully to your cart !!", false);

                }
                else
                {
                    // not enough
                    Utility.printFormatedMessage("!! Not Enough " + productName + "s In Inventory !!", false);
                }
            }
            else
            {
                Utility.printFormatedMessage("!! Doesn't Exist !!", false);
            }
        }
        else
        {
            Utility.printFormatedMessage("!! Invalid Category !!", false);
        }
    }



    private void orders()
    {
        // code goes here
    }

    private void cart()
    {
        // check if cart empty
        if (this.customer.getCart().isEmpty()) {
            Utility.printFormatedMessage("!! Cart Is Empty !!", false);
            new Scanner(System.in).nextLine();
            return;
        }

        // sep
        System.out.println(Utility.sep);

        // display prompt
        displayMenuAndTakeInputFromUser("Cart");

        // menu loop
        while (true) {
            if (getUserChoice().equals("1"))
            {
                // display cart
                this.customer.getCart().print();

            }
            else if (getUserChoice().equals("2"))
            {
                // take product name and remove it
                String productName;
                Utility.printFormatedMessage("product you want to remove: ", true);
                productName = new Scanner(System.in).nextLine();

                // check if product in cart
                if (this.customer.getCart().exists(productName).getKey())
                {
                    this.customer.getCart().removeFromCart(productName);
                    Utility.printFormatedMessage("!! Product Removed Successfully !!", false);

                }
                else
                {
                    Utility.printFormatedMessage("!! Not In Cart !!", false);
                }
            }

            else if (getUserChoice().equals("3"))
            {
                pay();
            }

            else if (getUserChoice().equals("4"))
            {
                // clear cart
                if (!this.customer.getCart().isEmpty())
                {
                    this.customer.getCart().clear();
                    Utility.printFormatedMessage("!! Cart Cleared Successfully !!", false);
                }
            }
            else if (getUserChoice().equals("5"))
            {
                break;
            }
            else
            {
                Utility.printFormatedMessage("!! Invalid !!", false);
            }

            // sep
            System.out.println(Utility.sep);

            // display prompt
            displayMenuAndTakeInputFromUser("Cart");
        }

    }

    private void pay()
    {
        // sep
        System.out.println(Utility.sep);

        // display prompt
        displayMenuAndTakeInputFromUser("Payment");

        // menu loop
        while (true)
        {
            if (getUserChoice().equals("1"))
            {
                if (customer.getPayment().getLoyaltyPoints() >= customer.getCart().totalPrice())
                {
                    placeOrder("LOYALTY_POINTS");
                    Utility.printFormatedMessage("!! Orders Placed Successfully !!", false);

                }
                else
                {
                    // not enough points
                    Utility.printFormatedMessage("!! Not Enough Points To Purchase !!", false);
                }
            }
            else if (getUserChoice().equals("2"))
            {
                // e wallet
                if (customer.getPayment().getEwallet().getBalance() >= customer.getCart().totalPrice())
                {
                    // place order for each, and add orders
                    placeOrder("ELECTRONIC_WALLET");
                    Utility.printFormatedMessage("!! Orders Placed Successfully !!", false);

                }
                else
                {
                    Utility.printFormatedMessage("!! Not Enough Balance In EWallet To Purchase !!", false);
                }
            }
            else if (getUserChoice().equals("3"))
            {
                // place order for each, and add orders
                placeOrder("ON_DELIVERY");
                Utility.printFormatedMessage("!! Orders Placed Successfully !!", false);
                break;

            }
            else if (getUserChoice().equals("4"))
            {
                break;
            }
            else
            {
                Utility.printFormatedMessage("!! Invalid !!", false);
            }

            // sep
            System.out.println(Utility.sep);

            // display prompt
            displayMenuAndTakeInputFromUser("Payment");
        }
    }

    private void placeOrder(String paymentMethod)
    {
        // place order for each, and add orders
        for(Product product: this.customer.getCart().getGoods())
        {
            // create order and place it
            OrderDetails orderDetails = new OrderDetails("", product.getM_productId(), product.getM_productName(), product.getM_availableAmount(), product.getM_productPrice(), (product.getM_productPrice()) * product.getM_availableAmount());
            ShippingInformation sh = new ShippingInformation(this.customer.getShippingInformation());
            Order o = new Order(this.customer.getCustomerId(),"PROCESSING", paymentMethod, orderDetails, sh);
            this.customer.placeAnOrder(o);
            this.orders.add(o);
        }
        // clear cart
        this.customer.getCart().clear();
    }
    private void displayPersonalInformation() {
        // code goes here
    }

    private void profile()
    {
        // sep
        System.out.println(Utility.sep);;

        // updated choice
        String updatedChoice;

        // display menu
        System.out.println("1- Update personal information");
        System.out.println("2- Update financials");
        System.out.println("3- Update shipping information");
        System.out.println("4- Back to previous menu");
        System.out.print("\nPlease enter a choice: ");

        // create scanner object
        Scanner scanner = new Scanner(System.in);

        // take value from user
        updatedChoice = scanner.nextLine();


        // main loop
        while (true)
        {
            if (updatedChoice.equals("1"))
            {
                // update
                updatePersonalInformation();
            }
            else if (updatedChoice.equals("2"))
            {
                // sep
                System.out.println(Utility.sep);;

                // display payemnt
                this.customer.getPayment().print();

                // update
                updateFinancials();
            }
            else if (updatedChoice.equals("3"))
            {
                // sep
                System.out.println(Utility.sep);;

                // display it first
                this.customer.getShippingInformation().print();
                updateShippingInformation();
            }
            else if (updatedChoice.equals("4"))
            {
               break;
            }
            else
            {
                Utility.printFormatedMessage("!! Invalid !!", false);
            }

            // sep
            System.out.println(Utility.sep);
            // display menu
            System.out.println("1- Update personal information");
            System.out.println("2- Update financials");
            System.out.println("3- Update shipping information");
            System.out.println("4- Back to previous menu ");
            System.out.print("\nPlease enter a choice: ");


            // take value from user
            updatedChoice = scanner.nextLine();
        }
    }

    public void updatePersonalInformation()
    {
        // scanner
        Scanner scanner = new Scanner(System.in);

        // display sep
        System.out.println(Utility.sep);

        // display menu
        System.out.println("1- User name");
        System.out.println("2- Phone number");
        System.out.println("3- Email");
        System.out.print("\nPlease select a choice to update: ");


        // take value from user
        String updateChoice = scanner.nextLine();

        // answer s
        String s;

        if (updateChoice.equals("1"))
        {
            // sep
            System.out.println(Utility.sep);

            // take user name
            s = Utility.getValidNameFromUser();

            // update it in user
            this.customer.setUserName(s);

            Utility.printFormatedMessage("!! Updated Username successfully !!", false);
        }
        else if (updateChoice.equals("2"))
        {
            // sep
            System.out.println(Utility.sep);

            // take phone number
            s = Utility.getValidPhoneNumber();

            // update it in user
            this.customer.setPhoneNumber(s);

            Utility.printFormatedMessage("!! Updated Phone Number successfully !!", false);
        }
        else if (updateChoice.equals("3"))
        {
            // sep
            System.out.println(Utility.sep);

            // take email
            s = Utility.getValidEmailFromUser();

            // update it in user
            this.customer.setEmail(s);

            Utility.printFormatedMessage("!! Updated Email successfully !!", false);
        }
        else
        {
            Utility.printFormatedMessage("!! Invalid !!", false);
        }
    }

    public void updateFinancials()
    {
        // scanner
        Scanner scanner = new Scanner(System.in);

        // display sep
        System.out.println(Utility.sep);

        // display menu
        System.out.println("1- Add points to Loyalty points");
        System.out.println("2- Add money to your Ewallet");
        System.out.print("\nPlease enter a choice: ");


        // take value from user
        String updateChoice = scanner.nextLine();

        // answer s
        double amount;

        if (updateChoice.equals("1"))
        {
            //sep
            System.out.println(Utility.sep);

            // take points
            amount = Utility.getValidAmountOfNumberFromUser();
            int points = (int) amount;

            // update it in user
            this.customer.getPayment().addPoints(points);

            Utility.printFormatedMessage("!! Points Added Successfully !!", false);
        }
        else if (updateChoice.equals("2"))
        {
            amount = Utility.getValidAmountOfNumberFromUser();

            // update it in user
            this.customer.getPayment().getEwallet().deposit(amount);

            Utility.printFormatedMessage("!! Money Added To Your EWallet Successfully !!", false);
        }
        else
        {
            Utility.printFormatedMessage("!! Invalid !!",false);
        }
    }

    public void updateShippingInformation()
    {
        // scanner
        Scanner scanner = new Scanner(System.in);
        // display sep
        System.out.println(Utility.sep);

        System.out.println("1- Recipient name");
        // display menu
        System.out.println("2- Recipient phoneNumber");

        System.out.println("3- Recipient email");
        // display menu
        System.out.println("4- Recipient address");

        System.out.println("5- Recipient city");

        System.out.print("\nPlease enter a choice to update: ");

        // take value from user
        String updateChoice = scanner.nextLine();

        // answer s
        String s;


        if (updateChoice.equals("1"))
        {
            // sep
            System.out.println(Utility.sep);

            // take name from user
            s = Utility.getValidNameFromUser();

            // update it in user
            this.customer.getShippingInformation().setRecipientName(s);

        }
        else if (updateChoice.equals("2"))
        {
            // sep
            System.out.println(Utility.sep);

            // take phoneNumber from user
            s = Utility.getValidPhoneNumber();

            // update it in user
            this.customer.getShippingInformation().setRecipientPhoneNumber(s);

        }
        else if (updateChoice.equals("3"))
        {
            // sep
            System.out.println(Utility.sep);

            // take email from user
            s = Utility.getValidEmailFromUser();

            // update it in user
            this.customer.getShippingInformation().setEmail(s);

        }
        else if (updateChoice.equals("4"))
        {
            // sep
            System.out.println(Utility.sep);

            // take address from user
            s = Utility.getValidAddressFromUser();

            // update it in user
            this.customer.getShippingInformation().setShippingAddress(s);

        }
        else if (updateChoice.equals("5"))
        {
            // sep
            System.out.println(Utility.sep);

            // take city from user
            s = Utility.getValidCityFromUser();

            // update it in user
            this.customer.getShippingInformation().setCity(s);
        }
        else
        {
            Utility.printFormatedMessage("!! Invalid !!", false);
        }

        Utility.printFormatedMessage("!! Updated Shipping Information successfully !!", false);
    }
}

