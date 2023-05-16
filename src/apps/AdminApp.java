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

public class AdminApp extends App
{
    /* static attributes */
    private final Scanner scanner = new Scanner(System.in);

    /* Instance attributes */
    private Admin admin;

    private ArrayList<Customer> customers;

    private ArrayList<Order> orders;
    private CategoriesManger catManger;


    /* Constructors */
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

    /* Setters & Getters (encapsulation) */

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void setCatManger(CategoriesManger catManger) {
        this.catManger = catManger;
    }

    public Admin getAdmin() {
        return admin;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public CategoriesManger getCatManger() {
        return catManger;
    }

    /* Instance Methods */
    @Override
    public void run()
    {
        // welcome message
        Utility.printWelcomingMessage("!! Welcome " + this.admin.getUserName() + " !!");

        // display menu and take input from user
        displayMenuAndTakeInputFromUser("Main");

        // main loop
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

            // separator
            System.out.println(Utility.sep);

            // display menu and take input from user
            displayMenuAndTakeInputFromUser("Main");
        }
    }


    private void manageCategories()
    {
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

    public void manageProducts()
    {
        // display sep
        System.out.println(Utility.sep);

        // display menu
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

                // take description
                System.out.print("Product description (in one line): ");
                description = scanner.nextLine();

                // take price per unit
                pricePerUnit = Utility.getValidPrice(false);

                // take amount
                System.out.print("Available amount: ");
                availableAmount = (int) Utility.getValidAmountOfNumberFromUser();
                scanner.nextLine();

                // create product
                category = scanner.nextLine();
                Product product = new Product(productName, category, description, pricePerUnit, availableAmount);

                // category to add new product to
                System.out.print("Category ( if category doesn't exist it will be created implicitly ): ");


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


    void manageCustomers()
    {
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

            // sep
            System.out.println(Utility.sep);

            // display proper prompt and take choice from user
            displayMenuAndTakeInputFromUser("PersonalInfo");
        }
    }


    ///////////////////////////////// Helper Methods ////////////////////////////////
    private void updateProduct()
    {
        // category name
        String categoryName;

        // get valid category name from user
        categoryName = Utility.getValidCategoryNameFromUser(false);

        // flag
        boolean updated = false;

        if (catManger.exists(categoryName))
        {
            // get category
            Category fetchedCat = catManger.getCategoryByName(categoryName);

            // print category
            fetchedCat.displayAllProducts();

            // sep
            System.out.println(Utility.sep);

            // take product name
            String productName = Utility.getValidProductNameFromUser(false);

            if (fetchedCat.exists(productName))
            {
                // fetch product to update it
                Product fetchedProduct = fetchedCat.getProductByName(productName);

                // print product
                fetchedProduct.print();


                // call method update product
                updateValidatedProduct(fetchedProduct);

            }
            else
            {
                // print product doesn't exists
                Utility.printFormatedMessage("!! Doesn't Exist !!", false);
            }
        }
        else
        {
            Utility.printFormatedMessage(" !! Doesn't Exist !!", false);

        }

    }


    private void updateValidatedProduct(Product product)
    {
        // sep
        System.out.println(Utility.sep);

        // display menu
        displayMenuAndTakeInputFromUser("ProductUpdates");

        // bool flag updated
        boolean updated = false;

        // sep before each
        while (true)
        {
            if (getUserChoice().equals("1"))
            {
                /* update product name */

                // sep
                System.out.println(Utility.sep);

                // take new name from user
                String newName = Utility.getValidProductNameFromUser(true);

                // update product name
                product.setM_productName(newName);

                // set flag
                updated = true;
            }
            else if (getUserChoice().equals("2"))
            {
                // update product description
                String desc = scanner.nextLine();

                // update product description
                product.setM_productDescription(desc);

                // set flag
                updated = true;
            }
            else if (getUserChoice().equals("3"))
            {
                // update a product available quantity
                double amount = Utility.getValidAmountOfNumberFromUser();

                // update it
                product.setM_availableAmount((int)amount);

                // set flag
                updated = true;
            }
            else if (getUserChoice().equals("4"))
            {
                // update product unit price
                double newPrice = Utility.getValidPrice(true);

                // set it
                product.setM_productPrice(newPrice);

                // set flag
                updated = true;
            }
            else if (getUserChoice().equals("5"))
            {
                // back to previous menu
                break;
            }
            else
            {
                // print invalid choice
                Utility.printFormatedMessage("!! Invalid !!", false);
            }


            if (updated)
            {
                // print convfirming message
                Utility.printFormatedMessage("!! Product Updated Successfully !!", false);

                // break
                break;
            }
            else
            {
                // call function again
                updateValidatedProduct(product);
            }
        }
    }


    private void updatePersonalInformation()
    {
        // sep
        System.out.println(Utility.sep);

        // display proper prompt and take option from user
        displayMenuAndTakeInputFromUser("UpdateAdminInformation");

        // value which data has been updated
        boolean isUpdated = false;

        while (true)
        {
            // sep
            System.out.println(Utility.sep);

            if (getUserChoice().equals("1"))
            {
                // update name
                String newName = Utility.getValidNameFromUser(true);
                this.admin.setUserName(newName);

                // set updated flag
                isUpdated = true;
            }
            else if (getUserChoice().equals("2"))
            {
                // phone number
                String newPhoneNumber = Utility.getValidPhoneNumber(true);
                this.admin.setPhoneNumber(newPhoneNumber);

                // set updated flag
                isUpdated = true;
            }
            else if (getUserChoice().equals("3"))
            {
                // update email
                String newEmail = Utility.getValidEmailFromUser(true);
                this.admin.setEmail(newEmail);

                // set updated flag
                isUpdated = true;
            }
            else if (getUserChoice().equals("4"))
            {
                // back to prev menu
                break;
            }
            else
            {
                System.out.println("!! Invalid !!");
            }

            if(isUpdated)
            {
                // confirming message
                Utility.printFormatedMessage("!! Updated Successfully !!", false);

                // back to pre menu
                break;
            }
            else
            {
                // rerun the function
                updatePersonalInformation();
            }
        }
    }



}
