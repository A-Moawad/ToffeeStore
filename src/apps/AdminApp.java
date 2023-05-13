package apps;

import model.categoriesmanger.CategoriesManger;
import model.categoriesmanger.Category;
import model.categoriesmanger.Product;
import model.users.Admin;
import model.users.Customer;
import model.users.details.Order;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class AdminApp extends App
{
    private Admin admin;
    private CategoriesManger catManger;
    private ArrayList<Customer> customers;

    private ArrayList<Order> orders;

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

    public AdminApp(Admin admin, ArrayList<Customer> customers, ArrayList<Order> orders, CategoriesManger categoriesManger)
    {
        // init attributes
        super(true, Utility.ADMIN_APP_PROMPTS);
        this.admin = admin;
        this.customers = customers;
        this.catManger = categoriesManger;
        this.orders = orders;
    }

    @Override
    public void run()
    {
        // welcome message
        Utility.printWelcomingMessage("!! Welcome " + this.admin.getUserName() + " !!");

        // scanner
        Scanner scan = new Scanner(System.in);

        // display menu and take input from user
        displayMenuAndTakeInputFromUser("Main");

        // main loop
        while (getAppStatus())
        {
            switch (getUserChoice())
            {
                case "1":
                    manageCategories();
                    break;

                case "2":
                    manageProducts();
                    break;

                case "3":
                    manageCustomers();
                    break;

                case "4":
                    manageOrders();
                    break;

                case "5":
                    personalInformation();
                    break;

                case "6":
                    setAppStatus(false);
                    break;

                default:
                    System.out.println("!! Invalid !!");
                    break;
            }

            // separator
            System.out.println(Utility.sep);

            // display menu and take input from user
            displayMenuAndTakeInputFromUser("Main");

        }
    }


    private void manageCategories()
    {
        // scanner
        Scanner scan = new Scanner(System.in);

        // sep
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
            } else if (getUserChoice().equals("2")) {
                // add new category
                // separator
                System.out.println(Utility.sep);

                // take category name from user
                String catName;
                System.out.print("Category name: ");
                catName = scan.nextLine();

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
                catName = scan.nextLine();

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
                System.out.println(getUserChoice());
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

    public void manageProducts()
    {
        // scanner
        Scanner scanner = new Scanner(System.in);
        // display sep
        System.out.println(Utility.sep);

        // display menu
        displayMenuAndTakeInputFromUser("ModifyProducts");

        // main loop
        while (true) {
            if (getUserChoice().equals("1"))
            {
                // add new product
                String productName, category, description;
                double pricePerUnit;
                int availableAmount;

                // take name
                System.out.println(Utility.sep);
                System.out.print("Product name: ");
                productName = scanner.nextLine();

                // take description
                System.out.println(Utility.sep);
                System.out.print("Product description (in one line): ");
                description = scanner.nextLine();

                // take price per unit
                System.out.println(Utility.sep);
                System.out.print("Price per unit: ");
                pricePerUnit = scanner.nextDouble();

                // take amount
                System.out.println(Utility.sep);
                System.out.print("Available amount: ");
                availableAmount = scanner.nextInt();
                scanner.nextLine();

                // category to add new product to
                System.out.println(Utility.sep);
                System.out.print("Category ( if category doesn't exist it will be created implicitly ): ");
                category = scanner.nextLine();

                // create product
                Product product = new Product(productName, category, description, pricePerUnit, availableAmount);

                // check if cat exists
                if (catManger.exists(category)) {
                    // check if there is already such product
                    if (catManger.getCategoryByName(category).exists(productName)) {
                        Utility.printFormatedMessage("!! Already Exists !!", false);
                    } else {
                        // added to category
                        catManger.getCategoryByName(category).addProduct(product);

                        // confirming message
                        Utility.printFormatedMessage("!! Added Successfully !!", false);
                    }
                } else {
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
                // sep
                System.out.println(Utility.sep);

                // take cat name to delete
                String productName;
                System.out.print("Product name: ");
                productName = scanner.nextLine();



                // get it
                if (catManger.GetProductByNameThroughAllCat(productName).getKey())
                {
                    // remove it
                    catManger.getCategoryByName(catManger.GetProductByNameThroughAllCat(productName).getValue().getM_productCategory()).RemoveProduct(productName);

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


    void manageCustomers()
    {
        // scanner
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


    public void manageOrders()
    {
        // scanner
        Scanner scanner = new Scanner(System.in);

        // sep
        System.out.println(Utility.sep);

        // display proper prompt and take choice from user
        displayMenuAndTakeInputFromUser("ModifyOrders");

        // main loop
        while (true)
        {
            if (getUserChoice().equals("1"))
            {
                // check if empty
                if (orders.isEmpty())
                {
                    Utility.printFormatedMessage("!! No Orders !!", false);

                }
                else
                {
                    // display all orders
                    for (Order order : orders)
                    {
                        order.print();
                    }
                }
            }
            else if (getUserChoice().equals("2"))
            {

            }
            else if (getUserChoice().equals("3"))
            {
                // get Order id you want to remove
                String orderId;
                Utility.printFormatedMessage("Order id (e.g, FCAI-ORDER-1 ): ", false);
                orderId = scanner.next();

                // if valid Order id
                if (Utility.isValidOrderId(orderId))
                {
                    // check if exists
                    Optional<Customer> customerToRemove = customers.stream()
                            .filter(c -> c.getCustomerId().equals(orderId))
                            .findFirst();

                    if (customerToRemove.isPresent())
                    {
                        // remove him
                        customers.remove(customerToRemove.get());
                        Utility.printFormatedMessage("!! Order With Id (" + orderId + ") Was Removed Successfully !!", false);
                    }
                    else
                    {
                        Utility.printFormatedMessage("!! Doesn't Exist !!", false);
                    }
                }
                else
                {
                    Utility.printFormatedMessage("!! Not A Valid OrderId !!", false);
                }


            }
            else if (getUserChoice().equals("4"))
            {
                // clear all orders
                if (orders.isEmpty()){
                    Utility.printFormatedMessage("!! Already Empty !!", false);
                }
                else
                {
                    orders.clear();
                    Utility.printFormatedMessage("!! Cleared Successfully !1", false);
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
            displayMenuAndTakeInputFromUser("ModifyOrders");
        }
    }

    private void personalInformation()
    {
        // scanner
        Scanner scanner = new Scanner(System.in);

        // sep
        System.out.println(Utility.sep);

        // display proper prompt and take choice from user
        displayMenuAndTakeInputFromUser("PersonalInfo");

        // main loop
        while (true)
        {
            if (getUserChoice().equals("1"))
            {
               admin.displayInfo();
            }
            else if (getUserChoice().equals("2"))
            {

            }
            else if (getUserChoice().equals("3"))
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
            displayMenuAndTakeInputFromUser("PersonalInfo");
        }
    }


    ///////////////////////////////// Helper Methods ////////////////////////////////

}
