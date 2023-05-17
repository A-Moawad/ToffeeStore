package apps;

import model.categoriesmanger.CategoriesManger;
import model.categoriesmanger.Category;
import model.categoriesmanger.Product;
import model.users.Admin;
import model.users.Customer;
import model.users.details.Order;
import utilities.Utility;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * The AdminApp class represents an administrative application that extends the App class.
 * It provides functionality specific to the admin role, such as managing categories, customers, and orders.
 */
public class AdminApp extends App
{
    /* Instance attributes */

    /**
     * The admin associated with the application.
     */
    private Admin admin;

    /**
     * The list of customers in the application.
     */
    private ArrayList<Customer> customers;

    /**
     * The list of orders in the application.
     */
    private ArrayList<Order> orders;

    /**
     * The category manager for managing categories.
     */
    private CategoriesManger catManger;


    /* Constructors */

    /**
     * Default constructor for the AdminApp class.
     * Initializes the attributes with default values.
     */
    public AdminApp()
    {
        // init parent
        super(true, Utility.ADMIN_APP_PROMPTS);

        // attributes
        this.admin = new Admin();
        this.catManger = new CategoriesManger();
        this.customers = new ArrayList<Customer>();
        this.orders = new ArrayList<Order>();
    }

    /**
     * Parameterized constructor for the AdminApp class.
     * Initializes the attributes with the provided values.
     *
     * @param admin     The admin associated with the application.
     * @param customers The list of customers in the application.
     * @param orders    The list of orders in the application.
     * @param categoriesManger The category manager for managing categories.
     */
    public AdminApp(Admin admin, ArrayList<Customer> customers, ArrayList<Order> orders, CategoriesManger categoriesManger)
    {
        // init attributes
        super(true, Utility.ADMIN_APP_PROMPTS);
        this.admin = admin;
        this.customers = customers;
        this.catManger = categoriesManger;
        this.orders = orders;
    }

    /* Setters & Getters (encapsulation) */

    /**
     * Sets the admin for the application.
     *
     * @param admin The admin to set.
     */
    public void setAdmin(Admin admin)
    {
        this.admin = admin;
    }

    /**
     * Sets the customers for the application.
     *
     * @param customers The list of customers to set.
     */
    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    /**
     * Sets the orders for the application.
     *
     * @param orders The list of orders to set.
     */
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    /**
     * Sets the category manager for the application.
     *
     * @param catManger The category manager to set.
     */
    public void setCatManger(CategoriesManger catManger) {
        this.catManger = catManger;
    }

    /**
     * Returns the admin associated with the application.
     *
     * @return The admin.
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * Returns the list of customers in the application.
     *
     * @return The list of customers.
     */
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    /**
     * Returns the list of orders in the application.
     *
     * @return The list of orders.
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Returns the category manager for the application.
     *
     * @return The category manager.
     */
    public CategoriesManger getCatManger() {
        return catManger;
    }

    /* Instance Methods */

    /**
     * Runs the admin application.
     * Displays a welcome message, displays the menu, and takes input from the user.
     * Executes different actions based on the user's input until the application is stopped.
     */
    @Override
    public void run()
    {
        // Scanner
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        Utility.printWelcomingMessage("!! Welcome " + this.admin.getUserName() + " !!");

        // Display menu and take input from user
        displayMenuAndTakeInputFromUser("Main");

        // Main loop
        while (getAppStatus())
        {
            if (getUserChoice().equals("1"))
            {
                manageCategories();
            }

            else if (getUserChoice().equals("2"))
            {
                manageProducts();
            }

            else if (getUserChoice().equals("3"))
            {
                manageCustomers();
            }

            else if (getUserChoice().equals("4"))
            {
                manageOrders();
            }

            else if (getUserChoice().equals("5"))
            {
                personalInformation();
            }

            else if (getUserChoice().equals("6"))
            {
                setAppStatus(false);
                break;
            }
            else
            {
                Utility.printFormatedMessage("!! Invalid !!", false);
            }

            // Separator
            System.out.println(Utility.sep);

            // Display menu and take input from user
            displayMenuAndTakeInputFromUser("Main");
        }
    }

    /**
     * Manages the categories in the application.
     * Displays the menu for modifying categories and executes different actions based on user input.
     * Continues the loop until the user chooses to exit.
     */
    private void manageCategories()
    {
        // Scanner
        Scanner scanner = new Scanner(System.in);

        // Separator
        System.out.println(Utility.sep);

        // display menu and take input from user
        displayMenuAndTakeInputFromUser("ModifyCategories");

        // main loop
        while (true)
        {
            if (getUserChoice().equals("1"))
            {
                if (!catManger.isEmpty())
                {
                    // display all categories in category manager
                    catManger.displayAllCategories();
                }
                else
                {
                    // print message
                    Utility.printFormatedMessage("!! No Categories !!", false);
                }
            }
            else if (getUserChoice().equals("2")) {
                // add new category
                // separator
                System.out.println(Utility.sep);

                // take category name from user
                String catName;
                System.out.print("Category name: ");
                catName = scanner.nextLine();

                // check if already exists
                if (catManger.exists(catName))
                {
                    // print complaining message
                    Utility.printFormatedMessage("!! Already Exists !!", false);
                }
                else
                {
                    // create it
                    catManger.addCategory(catName);

                    // confirming message
                    Utility.printFormatedMessage("!! Created Successfully !!", false);
                }
            }
            else if (getUserChoice().equals("3"))
            {
                // delete category
                // separator
                System.out.println(Utility.sep);

                // take category name to delete
                String catName;
                System.out.print("Category name: ");
                catName = scanner.nextLine();

                // check if exists
                if (catManger.exists(catName))
                {
                    // remove it
                    catManger.removeCategoryByName(catName);

                    // print confirming message
                    Utility.printFormatedMessage("!! Removed Successfully !!",false);
                }
                else
                {
                    // complaining message
                    Utility.printFormatedMessage("!! Doesn't Exist !!", false);
                }
            }
            else if (getUserChoice().equals("4"))
            {
                // clear
                if (!catManger.isEmpty())
                {
                    // display all categories in category manager
                    catManger.Clear();

                    // cleared message
                    Utility.printFormatedMessage("!! Cleared Successfully !!", false);
                }
                else
                {
                    // print message
                    Utility.printFormatedMessage("!! Already Empty !!", false);
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

            // separator
            System.out.println(Utility.sep);

            // display proper prompt and take choice from user
            displayMenuAndTakeInputFromUser("ModifyCategories");
        }

    }

    /**
     * Manages the products in the application.
     * Displays the menu for modifying products and executes different actions based on user input.
     * Continues the loop until the user chooses to exit.
     */
    public void manageProducts()
    {
        // Scanner
        Scanner scanner = new Scanner(System.in);

        // Display separator
        System.out.println(Utility.sep);

        // Display menu
        displayMenuAndTakeInputFromUser("ModifyProducts");

        // main loop
        while (true)
        {

            if (getUserChoice().equals("1"))
            {
                System.out.println(Utility.sep);
                String productName, category, description;
                double pricePerUnit;
                int availableAmount;


                // take name
                productName = Utility.getValidProductNameFromUser(false);

                //sep
                System.out.println(Utility.sep);

                // take price per unit
                pricePerUnit = Utility.getValidPrice(false);

                //sep
                System.out.println(Utility.sep);

                // take amount
                availableAmount = (int) Utility.getValidAmountOfNumberFromUser();

                //sep
                System.out.println(Utility.sep);

                // category to add new product to
                System.out.print("Category ( if category doesn't exist it will be created implicitly ): ");

                // create product
                category = scanner.nextLine();
                Product product = new Product(productName, category, "", pricePerUnit, availableAmount);


                // check if cat exists
                if (catManger.exists(category))
                {
                    // check if there is already such product
                    if (catManger.getCategoryByName(category).exists(productName)) {
                        Utility.printFormatedMessage("!! Already Exists !!", false);
                    }
                    else
                    {
                        // added to category
                        catManger.getCategoryByName(category).addProduct(product);

                        // confirming message
                        Utility.printFormatedMessage("!! Added Successfully !!", false);
                    }
                }
                else
                {
                    // create product and add it to this category
                    ArrayList<Product> products = new ArrayList<>();
                    products.add(product);

                    // added to products
                    catManger.addCategory(category, products);

                    Utility.printFormatedMessage("!! Created New Category: (" + category + ") And Added " + productName + " Successfully !!", false);
                }
            }
            else if (getUserChoice().equals("2"))
            {

                // delete product
                // take cat name to delete
                // sep
                System.out.println(Utility.sep);
                String productName;
                System.out.print("Product name: ");
                productName = scanner.nextLine();


                // get it
                if (catManger.GetProductByNameThroughAllCat(productName).getKey())
                {
                    // remove it
                    catManger.getCategoryByName(catManger.GetProductByNameThroughAllCat(productName).getValue().getM_productCategory()).removeProduct(productName);

                    // confirming message
                    Utility.printFormatedMessage("!! Removed Successfully !!", false);
                }
                else
                {
                    Utility.printFormatedMessage("!! Doesn't Exist !!", false);
                }
            }
            else if (getUserChoice().equals("3"))
            {
                // sep
                System.out.println(Utility.sep);
                updateProduct();
            }
            else if (getUserChoice().equals("4"))
            {
                // clear
                System.out.println(Utility.sep);

                // clear each cat from products
                for (Category cat : catManger.getT_categories())
                {
                    cat.clear();
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

            // display proper prompt and take choice from user
            displayMenuAndTakeInputFromUser("ModifyProducts");
        }
    }

    /**
     * Manages the customers in the application.
     * Displays the menu for modifying customers and executes different actions based on user input.
     * Continues the loop until the user chooses to exit.
     */
    void manageCustomers()
    {
        // Scanner
        Scanner scanner = new Scanner(System.in);

        // display sep
        System.out.println(Utility.sep);

        // display menu
        displayMenuAndTakeInputFromUser("ModifyCustomers");

        // main loop
        while (true) {
            if (getUserChoice().equals("1"))
            {
                // check if empty
                if (customers.isEmpty())
                {
                    Utility.printFormatedMessage("!! No Customers !!", false);
                }
                else
                {
                    // display all customers
                    for (Customer c : customers)
                    {
                        c.displayInfo();
                    }
                }
            }
            else if (getUserChoice().equals("2"))
            {
                // get customer id you want to remove
                String customerId;
                Utility.printFormatedMessage("Customer id (e.g, FCAI-CUSTOMER-1 ): ", true);
                customerId = scanner.next();

                // if valid customer id
                if (Utility.isValidCustomerId(customerId))
                {
                    // check if exists
                    Optional<Customer> customerToRemove = customers.stream()
                            .filter(c -> c.getCustomerId().equals(customerId))
                            .findFirst();

                    if (customerToRemove.isPresent())
                    {
                        // remove him
                        customers.remove(customerToRemove.get());
                        Utility.printFormatedMessage("!! Customer With Id (" + customerId + ") Was Removed Successfully !!", false);
                    }
                    else
                    {
                        Utility.printFormatedMessage("!! Doesn't Exist !!", false);
                    }
                }
                else
                {
                    Utility.printFormatedMessage("!! Not A Valid CustomerId !!", false);
                }
            }
            else if (getUserChoice().equals("3"))
            {
                // clear all cutomers
                if (customers.isEmpty())
                {
                    Utility.printFormatedMessage("!! Already Empty !!", false);

                }
                else
                {
                    customers.clear();
                    Utility.printFormatedMessage("!! Cleared Successfully !!", false);
                }

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

            // display proper prompt and take choice from user
            displayMenuAndTakeInputFromUser("ModifyCustomers");
        }
    }

    /**
     * Manages the orders in the application.
     * Displays the menu for modifying orders and executes different actions based on user input.
     * Continues the loop until the user chooses to exit.
     */
    public void manageOrders()
    {
        // Scanner
        Scanner scanner = new Scanner(System.in);

        // Separator
        System.out.println(Utility.sep);

        // Display proper prompt and take choice from user
        displayMenuAndTakeInputFromUser("ModifyOrders");

        // Main loop
        while (true)
        {
            if (getUserChoice().equals("1"))
            {
                // Check if empty
                if (orders.isEmpty())
                {
                    Utility.printFormatedMessage("!! No Orders !!", false);
                }
                else
                {
                    // Display all orders
                    for (Order order : orders)
                    {
                        order.print();
                    }
                }
            }
//            else if (getUserChoice().equals("2"))
//            {
//
//            }
            else if (getUserChoice().equals("2"))
            {
                // Get Order ID to remove
                String orderId;
                Utility.printFormatedMessage("Order ID (e.g., FCAI-ORDER-1): ", true);
                orderId = scanner.next();

                // If valid Order ID
                if (Utility.isValidOrderId(orderId))
                {
                    // Check if exists
                    Optional<Order> orderToRemove = orders.stream()
                            .filter(o -> o.getOrderId().equals(orderId))
                            .findFirst();

                    if (orderToRemove.isPresent())
                    {
                        // Remove the order
                        orders.remove(orderToRemove.get());
                        Utility.printFormatedMessage("!! Order With ID (" + orderId + ") Was Removed Successfully !!", false);
                    }
                    else
                    {
                        Utility.printFormatedMessage("!! Doesn't Exist !!", false);
                    }
                }
                else
                {
                    Utility.printFormatedMessage("!! Not A Valid Order ID !!", false);
                }
            }
            else if (getUserChoice().equals("3"))
            {
                // Clear all orders
                if (orders.isEmpty())
                {
                    Utility.printFormatedMessage("!! Already Empty !!", false);
                }
                else
                {
                    orders.clear();
                    Utility.printFormatedMessage("!! Cleared Successfully !!", false);
                }
            }
            else if (getUserChoice().equals("4"))
            {
                break;
            }
            else
            {
                Utility.printFormatedMessage("!! Invalid !!", false);
            }

            // Separator
            System.out.println(Utility.sep);

            // Display proper prompt and take choice from user
            displayMenuAndTakeInputFromUser("ModifyOrders");
        }
    }

    /**
     * Displays and manages personal information for the admin.
     * Displays the menu for personal information and executes different actions based on user input.
     * Continues the loop until the user chooses to exit.
     */
    private void personalInformation()
    {
        // Scanner
        Scanner scanner = new Scanner(System.in);

        // Separator
        System.out.println(Utility.sep);

        // Display proper prompt and take choice from user
        displayMenuAndTakeInputFromUser("PersonalInfo");

        // Main loop
        while (true) {
            if (getUserChoice().equals("1"))
            {
                admin.displayInfo();
            }
            else if (getUserChoice().equals("2"))
            {
                updatePersonalInformation();
            }
            else if (getUserChoice().equals("3"))
            {
                break;
            }
            else
            {
                Utility.printFormatedMessage("!! Invalid !!", false);
            }

            // Separator
            System.out.println(Utility.sep);

            // Display proper prompt and take choice from user
            displayMenuAndTakeInputFromUser("PersonalInfo");
        }
    }


    ///////////////////////////////// Helper Methods ////////////////////////////////
    /**
     * Updates a product in the inventory.
     * Prompts the user to enter a valid category name and product name.
     * If the category and product exist, it calls the updateValidatedProduct() method to perform the actual update.
     */
    private void updateProduct()
    {
        // Category name
        String categoryName;

        // Get valid category name from user
        categoryName = Utility.getValidCategoryNameFromUser(false);

        // Flag
        boolean updated = false;

        if (catManger.exists(categoryName)) {
            // Get category
            Category fetchedCat = catManger.getCategoryByName(categoryName);

            // Print category
            fetchedCat.displayAllProducts();

            // Separator
            System.out.println(Utility.sep);

            // Take product name
            String productName = Utility.getValidProductNameFromUser(false);

            if (fetchedCat.exists(productName)) {
                // Fetch product to update it
                Product fetchedProduct = fetchedCat.getProductByName(productName);

                // Print product
                fetchedProduct.print();

                // Call method updateValidatedProduct
                updateValidatedProduct(fetchedProduct);
            } else {
                // Print product doesn't exist
                Utility.printFormatedMessage("!! Doesn't Exist !!", false);
            }
        } else {
            Utility.printFormatedMessage(" !! Doesn't Exist !!", false);
        }
    }

    /**
     * Updates a validated product based on user input.
     * Displays a menu for updating product details and performs the requested updates on the given product.
     * Continues the loop until the user chooses to go back to the previous menu.
     *
     * @param product The product to be updated.
     */
    private void updateValidatedProduct(Product product)
    {
        // Scanner
        Scanner scanner = new Scanner(System.in);

        // Separator
        System.out.println(Utility.sep);

        // Display menu
        displayMenuAndTakeInputFromUser("ProductUpdates");

        // Flag indicating if the product has been updated
        boolean updated = false;

        while (true) {
            if (getUserChoice().equals("1"))
            {
                /* Update product name */

                // Separator
                System.out.println(Utility.sep);

                // Take new name from user
                String newName = Utility.getValidProductNameFromUser(true);

                // Update product name
                product.setM_productName(newName);

                // Set flag
                updated = true;
            }
            else if (getUserChoice().equals("2"))
            {
                // Update product description
                String desc = scanner.nextLine();

                // Update product description
                product.setM_productDescription(desc);

                // Set flag
                updated = true;
            }
            else if (getUserChoice().equals("3"))
            {
                // Update product available quantity
                double amount = Utility.getValidAmountOfNumberFromUser();

                // Update it
                product.setM_availableAmount((int) amount);

                // Set flag
                updated = true;
            }
            else if (getUserChoice().equals("4"))
            {
                // Update product unit price
                double newPrice = Utility.getValidPrice(true);

                // Set it
                product.setM_productPrice(newPrice);

                // Set flag
                updated = true;
            }
            else if (getUserChoice().equals("5"))
            {
                // Go back to the previous menu
                break;
            }
            else
            {
                // Print invalid choice
                Utility.printFormatedMessage("!! Invalid !!", false);
            }

            if (updated)
            {
                // Print confirming message
                Utility.printFormatedMessage("!! Product Updated Successfully !!", false);

                // Break the loop
                break;
            }
            else
            {
                // Call the function again
                updateValidatedProduct(product);
            }
        }
    }

    /**
     * Updates the personal information of the admin.
     * Displays the menu for updating admin information and performs the requested updates based on user input.
     * Continues the loop until the user chooses to go back to the previous menu.
     */
    private void updatePersonalInformation() {
        // Separator
        System.out.println(Utility.sep);

        // Display proper prompt and take option from user
        displayMenuAndTakeInputFromUser("UpdateAdminInformation");

        // Flag indicating whether data has been updated
        boolean isUpdated = false;

        while (true)
        {
            // Separator
            System.out.println(Utility.sep);

            if (getUserChoice().equals("1"))
            {
                // Update name
                String newName = Utility.getValidNameFromUser(true);
                this.admin.setUserName(newName);

                // Set updated flag
                isUpdated = true;
            }
            else if (getUserChoice().equals("2"))
            {
                // Update phone number
                String newPhoneNumber = Utility.getValidPhoneNumber(true);
                this.admin.setPhoneNumber(newPhoneNumber);

                // Set updated flag
                isUpdated = true;
            }
            else if (getUserChoice().equals("3"))
            {
                // Update email
                String newEmail = Utility.getValidEmailFromUser(true);
                this.admin.setEmail(newEmail);

                // Set updated flag
                isUpdated = true;
            }
            else if (getUserChoice().equals("4"))
            {
                // Go back to the previous menu
                break;
            }
            else
            {
                System.out.println("!! Invalid !!");
            }

            if (isUpdated)
            {
                // Confirming message
                Utility.printFormatedMessage("!! Updated Successfully !!", false);

                // Go back to the previous menu
                break;
            }
            else
            {
                // Re-run the function
                updatePersonalInformation();
            }
        }
    }

}
