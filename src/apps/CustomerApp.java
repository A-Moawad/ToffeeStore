package apps;

import model.categoriesmanger.CategoriesManger;
import model.categoriesmanger.Category;
import model.categoriesmanger.Product;
import model.users.Customer;
import model.users.details.Order;
import model.users.details.OrderDetails;
import model.users.details.ShippingInformation;
import services.OTP.otpSystem;
import utilities.Utility;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;


/**
 * The CustomerApp class represents the customer application for the Toffee Store.
 * It allows customers to select products, place orders, manage their cart, and update their personal information.
 */
public class CustomerApp extends App
{
    private Customer customer;
    private CategoriesManger catManger;

    private ArrayList<Order> orders;

    /**
     * Initializes a new instance of the CustomerApp class with default values.
     * Sets the parent status to true and initializes customer and category manager objects.
     */
    public CustomerApp()
    {
        // init parent with true status as init and proper prompts
        super(true, Utility.CUSTOMER_APP_PROMPTS);

        // init attributes with default value
        this.customer = new Customer();
        this.catManger = new CategoriesManger();
    }

    /**
     * Initializes a new instance of the CustomerApp class with the provided parameters.
     * Sets the parent status to true and initializes customer, orders, and category manager objects.
     *
     * @param customer   The customer object.
     * @param orders     The list of customer orders.
     * @param catManger  The category manager object.
     */
    public CustomerApp(Customer customer, ArrayList<Order> orders, CategoriesManger catManger)
    {
        super(true, Utility.CUSTOMER_APP_PROMPTS);
        this.customer = customer;
        this.orders = orders;
        this.catManger = catManger;
    }

    /**
     * Runs the customer application.
     * Displays a welcome message, market categories, and menu options.
     * Handles user input and performs corresponding actions until the user chooses to exit.
     */
    @Override
    public void run()
    {
        // scanner
        Scanner scanner = new Scanner(System.in);

        // welcome message
        Utility.printWelcomingMessage("!! Welcome To Toffee Store !!");
        scanner.nextLine();

        // display market
        catManger.displayAllCategories();

        // display menu and take input from user
        displayMenuAndTakeInputFromUser("Main");

        // main loop
        while (getAppStatus())
        {
            if (getUserChoice().equals("1"))
            {
                selectProduct();
            }
            else if (getUserChoice().equals("2"))
            {
                orders();
            }
            else if (getUserChoice().equals("3"))
            {
                cart();
            }
            else if (getUserChoice().equals("4"))
            {
                profile();
            }
            else if (getUserChoice().equals("5"))
            {
                setAppStatus(false);
                break;
            }
            else
            {
                System.out.println("!! Invalid !!");
            }


            // separator
            System.out.println(Utility.sep);

            // display market
            catManger.displayAllCategories();

            // display menu and take input from user
            displayMenuAndTakeInputFromUser("Main");
        }

    }

    /**
     * Allows the customer to select a product from the available categories.
     * Checks if the selected category and product exist and updates the customer's cart accordingly.
     */
    /**
     * Allows the user to select a product from a specified category and add it to their cart.
     */
    private void selectProduct() {
        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a category name
        Utility.printFormatedMessage("Category name: ", true);
        String categoryName = scanner.nextLine();

        // Check if the category exists
        if (catManger.exists(categoryName)) {
            // Retrieve the specified category
            Category tmp = catManger.getCategoryByName(categoryName);

            // Display all products in the category to the user
            tmp.displayAllProducts();

            // Prompt the user to enter a product name
            Utility.printFormatedMessage("Product name: ", true);
            String productName = scanner.nextLine();

            // Check if the product exists in the category
            if (tmp.exists(productName)) {
                // Retrieve the specified product
                Product p = tmp.getProductByName(productName);

                // Print the details of the product
                p.print();

                // Prompt the user to enter a quantity
                Utility.printFormatedMessage("Quantity you intend to buy: ", true);
                int quantity = scanner.nextInt();

                // Check if there is enough quantity in the inventory
                if ((p.getM_availableAmount() - quantity) >= 0) {
                    // Add the product to the customer's cart
                    this.customer.getCart().addToCart(p, quantity);

                    // Display a confirmation message
                    Utility.printFormatedMessage("!! Product Added Successfully to your cart !!", false);
                } else {
                    // Not enough quantity in inventory
                    Utility.printFormatedMessage("!! Not Enough " + productName + "s In Inventory !!", false);
                }
            } else {
                // Product doesn't exist in the category
                Utility.printFormatedMessage("!! Doesn't Exist !!", false);
            }
        } else {
            // Invalid category
            Utility.printFormatedMessage("!! Invalid Category !!", false);
        }
    }

    /**
     * Allows the user to view and manage their orders.
     */
    private void orders() {
        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Check if the customer has placed any orders
        if (this.customer.getCustomerOrders().isEmpty()) {
            Utility.printFormatedMessage("!! You Haven't Placed Any Orders Yet !!", false);
            scanner.nextLine();
            return;
        }

        // Print a separator
        System.out.println(Utility.sep);

        // Display the menu prompt for orders
        displayMenuAndTakeInputFromUser("Orders");

        // Menu loop
        while (true) {
            if (getUserChoice().equals("1")) {
                // Display all customer orders
                if (this.customer.getCustomerOrders().isEmpty()) {
                    // If there are no orders, display a message
                    Utility.printFormatedMessage("!! No Orders To Display !!", false);
                } else {
                    // Print details of all orders
                    for (Order o : this.customer.getCustomerOrders()) {
                        o.print();
                    }
                }
            } else if (getUserChoice().equals("2")) {
                // Modify an order
                modifyOrder();
            } else if (getUserChoice().equals("3")) {
                // Clear all orders
                if (this.customer.getCustomerOrders().isEmpty()) {
                    Utility.printFormatedMessage(" Already Empty !!", false);
                } else {
                    // Clear all orders
                    this.customer.getCustomerOrders().clear();
                    Utility.printFormatedMessage(" Cleared Successfully !!", false);
                }
            } else if (getUserChoice().equals("4")) {
                // Exit the orders menu
                break;
            } else {
                // Invalid choice
                Utility.printFormatedMessage("!! Invalid !!", false);
            }

            // Print a separator
            System.out.println(Utility.sep);

            // Display the menu prompt for orders
            displayMenuAndTakeInputFromUser("Orders");
        }
    }

    public void modifyOrder()
    {
        // alias for real order
        Order order;
        Order inOrders;

        String orderId;
        Utility.printFormatedMessage("Order Id you want to modify (e.g, FCAI-ORDER-1 ): ", true);
        orderId = scanner.nextLine();

        // check if valid order id
        if (Utility.isValidOrderId(orderId))
        {
            // check if exists
            Optional<Order> orderOptional = this.customer.getCustomerOrders().stream()
                    .filter(o -> o.getOrderId().equals(orderId))
                    .findFirst();
            Optional<Order> orderInOrders = this.orders.stream()
                    .filter(o -> o.getOrderId().equals(orderId))
                    .findFirst();

            // if exists
            if (orderOptional.isPresent())
            {
                // alias
                order = orderOptional.get();
                inOrders =  orderInOrders.get();

                // sep
                System.out.println(Utility.sep);

                // display prompt
                displayMenuAndTakeInputFromUser("ModifyOrders");

                // menu loop
                while (true)
                {
                    if (getUserChoice().equals("1"))
                    {
                        // found then update its status
                        order.SetOrderStatus("CANCELED");
                        inOrders.SetOrderStatus("CANCELED");
                        Utility.printFormatedMessage("!! Order Canceled Successfully !!", false);

                    }
                    else if (getUserChoice().equals("2"))
                    {
                        // found then update its status
                        order.SetOrderStatus("RETURNED");
                        inOrders.SetOrderStatus("RETURNED");
                        Utility.printFormatedMessage("!! Done !!", false);

                    }
                    else if (getUserChoice().equals("3"))
                    {
                        // found then remove it
                        this.customer.getCustomerOrders().remove(order);
                        inOrders.SetOrderStatus("CANCELED");
                        Utility.printFormatedMessage("!! Order Removed Successfully !!", false);
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
                    displayMenuAndTakeInputFromUser("ModifyOrders");
                }
            }
            else
            {
                System.out.println("!! Not In Orders !!");
            }
        }
        else
        {
            Utility.printFormatedMessage("!! Not A Valid Order Id !!", false);
        }
    }


    private void cart()
    {
        // scanner
        Scanner scanner = new Scanner(System.in);

        // check if cart empty
        if (this.customer.getCart().isEmpty()) {
            Utility.printFormatedMessage("!! Cart Is Empty !!", false);
            scanner.nextLine();
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
                productName = scanner.nextLine();

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


    /**
     * Performs the payment process.
     * The method displays a payment menu and allows the user to choose a payment method.
     * It handles different payment options such as loyalty points, electronic wallet, and on delivery.
     * Depending on the user's choice, the method places an order and performs necessary payment actions.
     */
    private void pay() {

        // Print separator
        System.out.println(Utility.sep);

        // Display payment menu prompt
        displayMenuAndTakeInputFromUser("Payment");

        // Menu loop
        while (true) {
            // Check user's choice
            if (getUserChoice().equals("1")) {
                if (customer.getPayment().getLoyaltyPoints() >= customer.getCart().totalPrice()) {
                    // Place order using loyalty points
                    placeOrder("LOYALTY_POINTS");
                    Utility.printFormatedMessage("!! Orders Placed Successfully !!", false);

                    // Withdraw money from loyalty points
                    this.customer.getPayment().withDrawPoints((int) customer.getCart().totalPrice());
                } else {
                    // Not enough points
                    Utility.printFormatedMessage("!! Not Enough Points To Purchase !!", false);
                }
            } else if (getUserChoice().equals("2")) {
                // E-wallet payment
                if (customer.getPayment().getEwallet().getBalance() >= customer.getCart().totalPrice()) {
                    // Place order using electronic wallet
                    placeOrder("ELECTRONIC_WALLET");
                    Utility.printFormatedMessage("!! Orders Placed Successfully !!", false);

                    // Withdraw money from e-wallet
                    this.customer.getPayment().getEwallet().withdraw(customer.getCart().totalPrice());
                } else {
                    Utility.printFormatedMessage("!! Not Enough Balance In EWallet To Purchase !!", false);
                }
            } else if (getUserChoice().equals("3")) {
                // Place order for on delivery payment
                placeOrder("ON_DELIVERY");
                Utility.printFormatedMessage("!! Orders Placed Successfully !!", false);
                break;
            } else if (getUserChoice().equals("4")) {
                // Exit payment process
                break;
            } else {
                // Invalid choice
                Utility.printFormatedMessage("!! Invalid !!", false);
            }

            // Print separator
            System.out.println(Utility.sep);

            // Display payment menu prompt
            displayMenuAndTakeInputFromUser("Payment");
        }
    }

    /**
     * Places an order for each product in the customer's cart.
     * For each product in the cart, the method creates an order with the necessary details and places it.
     * The method also adds the order to the list of orders.
     * After placing the orders, it clears the customer's cart.
     *
     * @param paymentMethod The payment method to be used for the order.
     */
    private void placeOrder(String paymentMethod) {
        // Place an order for each product and add orders
        for (Product product : this.customer.getCart().getGoods()) {
            // Create order and place it
            OrderDetails orderDetails = new OrderDetails("", product.getM_productId(), product.getM_productName(), product.getM_availableAmount(), product.getM_productPrice(), (product.getM_productPrice()) * product.getM_availableAmount());
            ShippingInformation sh = new ShippingInformation(this.customer.getShippingInformation());
            Order o = new Order(this.customer.getCustomerId(), this.customer.getUserName(), "PROCESSING", paymentMethod, orderDetails, sh);
            this.customer.placeAnOrder(o);
            this.orders.add(o);
        }
        // Clear the cart
        this.customer.getCart().clear();
    }

    /**
     * Displays the personal information of the customer.
     * The method calls the `displayInfo()` method of the customer to display their personal information.
     */
    private void displayPersonalInformation() {
        this.customer.displayInfo();
    }


    /**
     * Displays and manages the user profile menu.
     * The method displays a menu with different options related to the user's personal information, and it allows the user to choose an option.
     * The available options are:
     * 1. Display personal information
     * 2. Update personal information
     * 3. Update financial information
     * 4. Update shipping information
     * 5. Exit profile menu
     *
     * The method uses utility methods to handle user input and calls corresponding methods based on the user's choice.
     * It loops until the user chooses to exit the profile menu.
     */
    private void profile() {
        // Display separator
        System.out.println(Utility.sep);

        // String to store the user's choice
        String userChoice;

        // Display menu for personal information
        displayMenuAndTakeInputFromUser("PersonalInfo");

        // Main loop
        while (true) {
            userChoice = getUserChoice();

            if (userChoice.equals("1")) {
                // Display personal information
                displayPersonalInformation();
            } else if (userChoice.equals("2")) {
                // Update personal information
                updatePersonalInformation();
            } else if (userChoice.equals("3")) {
                // To update financial information, OTP verification is required
                otpSystem otpSystem = new otpSystem(customer.getEmail(), customer.getUserName());
                otpSystem.run();

                // Display payment information
                this.customer.getPayment().print();

                // Update financial information
                updateFinancials();
            } else if (userChoice.equals("4")) {
                // Display separator
                System.out.println(Utility.sep);

                // Display current shipping information
                this.customer.getShippingInformation().print();

                // Update shipping information
                updateShippingInformation();
            } else if (userChoice.equals("5")) {
                // Exit profile menu
                break;
            } else {
                Utility.printFormatedMessage("!! Invalid !!", false);
            }

            // Display separator
            System.out.println(Utility.sep);

            // Display menu for personal information
            displayMenuAndTakeInputFromUser("PersonalInfo");
        }
    }



    /**
     * Updates the personal information of the customer.
     * The method prompts the user to choose an option for updating personal information and performs the corresponding update.
     * The available options are:
     * 1. Update username
     * 2. Update phone number
     * 3. Update email
     *
     * The method uses utility methods to validate and retrieve the updated information from the user.
     * Once the information is updated, the corresponding customer fields are updated, and a success message is displayed.
     * If an invalid option is chosen, an error message is displayed.
     */
    private void updatePersonalInformation() {
        // Display separator
        System.out.println(Utility.sep);

        // Display menu for personal updates
        displayMenuAndTakeInputFromUser("personalUpdates");

        // Initialize answer string
        String answer;

        // Process user choice
        if (getUserChoice().equals("1")) {
            // Display separator
            System.out.println(Utility.sep);

            // Prompt for updated username
            answer = Utility.getValidNameFromUser(true);

            // Update username in customer
            this.customer.setUserName(answer);

            Utility.printFormatedMessage("!! Updated Username successfully !!", false);
        } else if (getUserChoice().equals("2")) {
            // Display separator
            System.out.println(Utility.sep);

            // Prompt for updated phone number
            answer = Utility.getValidPhoneNumber(true);

            // Update phone number in customer
            this.customer.setPhoneNumber(answer);

            Utility.printFormatedMessage("!! Updated Phone Number successfully !!", false);
        } else if (getUserChoice().equals("3")) {
            // Display separator
            System.out.println(Utility.sep);

            // Prompt for updated email
            answer = Utility.getValidEmailFromUser(true);

            // Update email in customer
            this.customer.setEmail(answer);

            Utility.printFormatedMessage("!! Updated Email successfully !!", false);
        } else {
            Utility.printFormatedMessage("!! Invalid !!", false);
        }
    }

    /**
     * Updates the financial information of the customer.
     * The method prompts the user to choose an option for updating financials and performs the corresponding update.
     * The available options are:
     * 1. Add points to Loyalty points
     * 2. Add money to Ewallet
     *
     * The method uses utility methods to validate and retrieve the updated information from the user.
     * Once the information is updated, the corresponding customer fields are updated, and a success message is displayed.
     * If an invalid option is chosen, an error message is displayed.
     */
    private void updateFinancials() {
        // Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Display separator
        System.out.println(Utility.sep);

        // Display menu for financial updates
        System.out.println("1- Add points to Loyalty points");
        System.out.println("2- Add money to your Ewallet");
        System.out.print("\nPlease enter a choice: ");

        // Take user's choice
        String updateChoice = scanner.nextLine();

        // Initialize answer amount
        double amount;

        // Process user choice
        if (updateChoice.equals("1")) {
            // Display separator
            System.out.println(Utility.sep);

            // Prompt for loyalty points amount
            amount = Utility.getValidAmountOfNumberFromUser();
            int points = (int) amount;

            // Update loyalty points in customer payment
            this.customer.getPayment().addPoints(points);

            Utility.printFormatedMessage("!! Points Added Successfully !!", false);
        } else if (updateChoice.equals("2")) {
            // Display separator
            System.out.println(Utility.sep);

            // Prompt for amount to deposit to Ewallet
            amount = Utility.getValidAmountOfNumberFromUser();

            // Update Ewallet balance in customer payment
            this.customer.getPayment().getEwallet().deposit(amount);

            Utility.printFormatedMessage("!! Money Added To Your EWallet Successfully !!", false);
        } else {
            Utility.printFormatedMessage("!! Invalid !!", false);
        }
    }
    /**
     * Updates the shipping information of the customer.
     * The method prompts the user to choose an option for updating shipping information and performs the corresponding update.
     * The available options are:
     * 1. Recipient name
     * 2. Recipient phone number
     * 3. Recipient email
     * 4. Recipient address
     * 5. Recipient city
     *
     * The method uses utility methods to validate and retrieve the updated information from the user.
     * Once the information is updated, the corresponding customer fields are updated, and a success message is displayed.
     * If an invalid option is chosen, an error message is displayed.
     */
    private void updateShippingInformation() {
        // Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Display separator
        System.out.println(Utility.sep);

        // Display menu for shipping information updates
        System.out.println("1- Recipient name");
        System.out.println("2- Recipient phone number");
        System.out.println("3- Recipient email");
        System.out.println("4- Recipient address");
        System.out.println("5- Recipient city");
        System.out.print("\nPlease enter a choice to update: ");

        // Take user's choice
        String updateChoice = scanner.nextLine();

        // Initialize answer string
        String s;

        // Process user choice
        if (updateChoice.equals("1")) {
            // Display separator
            System.out.println(Utility.sep);

            // Prompt for recipient name
            s = Utility.getValidNameFromUser(true);

            // Update recipient name in customer's shipping information
            this.customer.getShippingInformation().setRecipientName(s);
        } else if (updateChoice.equals("2")) {
            // Display separator
            System.out.println(Utility.sep);

            // Prompt for recipient phone number
            s = Utility.getValidPhoneNumber(true);

            // Update recipient phone number in customer's shipping information
            this.customer.getShippingInformation().setRecipientPhoneNumber(s);
        } else if (updateChoice.equals("3")) {
            // Display separator
            System.out.println(Utility.sep);

            // Prompt for recipient email
            s = Utility.getValidEmailFromUser(true);

            // Update recipient email in customer's shipping information
            this.customer.getShippingInformation().setEmail(s);
        } else if (updateChoice.equals("4")) {
            // Display separator
            System.out.println(Utility.sep);

            // Prompt for recipient address
            s = Utility.getValidAddressFromUser(true);

            // Update recipient address in customer's shipping information
            this.customer.getShippingInformation().setShippingAddress(s);
        } else if (updateChoice.equals("5")) {
            // Display separator
            System.out.println(Utility.sep);

            // Prompt for recipient city
            s = Utility.getValidCityFromUser();

            // Update recipient city in customer's shipping information
            this.customer.getShippingInformation().setCity(s);
        } else {
            Utility.printFormatedMessage("!! Invalid !!", false);
        }

        Utility.printFormatedMessage("!! Updated Shipping Information successfully !!", false);
    }
}


