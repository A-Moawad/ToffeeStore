package model.users;

import model.payment.Payment;
import model.tool.Cart;
import model.users.details.Order;
import model.users.details.ShippingInformation;
import utilities.Utility;
import java.util.ArrayList;

public class Customer extends User {

    /* static variables related to class only */
    /* static variables related to class only */
    private static int s_totalCountOfCustomers;  // Total count of customers created in the system

    /* Instance attributes */
    private final String customerId;                  /* Auto */  // Unique identifier for each customer
    private Cart cart;                                 // Shopping cart associated with the customer
    private ArrayList<Order> orders;                   // List of orders placed by the customer
    private ShippingInformation shippingInfo;          // Shipping information associated with the customer
    private Payment paymentMethods;                    // Payment methods associated with the customer

    /**
     * Default constructor for the Customer class.
     * Initializes the customer with default values and generates a unique customer ID.
     */
    public Customer() {
        super();
        customerId = Utility.generateId("CUSTOMER", ++s_totalCountOfCustomers);
        cart = new Cart();
        shippingInfo = new ShippingInformation();
        paymentMethods = new Payment();
        orders = new ArrayList<>();
    }

    /**
     * Constructor for the Customer class with specified values.
     * Initializes the customer with the given parameters and generates a unique customer ID.
     *
     * @param t_name         The name of the customer.
     * @param t_password     The password of the customer.
     * @param t_email        The email address of the customer.
     * @param t_phoneNumber  The phone number of the customer.
     * @param t_cart         The cart associated with the customer.
     * @param t_payment      The payment methods of the customer.
     * @param t_info         The shipping information of the customer.
     */
    public Customer(String t_name, String t_password, String t_email, String t_phoneNumber, Cart t_cart,
                    Payment t_payment, ShippingInformation t_info) {
        super(t_name, t_password, t_phoneNumber, t_email);
        customerId = Utility.generateId("CUSTOMER", ++s_totalCountOfCustomers);
        cart = t_cart;
        shippingInfo = t_info;
        paymentMethods = t_payment;
        orders = new ArrayList<>();
    }

    /**
     * Sets the cart associated with the customer.
     *
     * @param t_cart The cart to be set.
     */
    public void setCart(Cart t_cart) {
        cart = t_cart;
    }

    /**
     * Sets the payment methods of the customer.
     *
     * @param t_payment The payment methods to be set.
     */
    public void setPayment(Payment t_payment) {
        paymentMethods = t_payment;
    }

    /**
     * Sets the shipping information of the customer.
     *
     * @param t_info The shipping information to be set.
     */
    public void setShippingInfo(ShippingInformation t_info) {
        shippingInfo = t_info;
    }

    /**
     * Returns the customer ID.
     *
     * @return The customer ID.
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Returns the cart associated with the customer.
     *
     * @return The cart associated with the customer.
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * Returns the payment methods of the customer.
     *
     * @return The payment methods of the customer.
     */
    public Payment getPayment() {
        return paymentMethods;
    }

    /**
     * Returns the shipping information of the customer.
     *
     * @return The shipping information of the customer.
     */
    public ShippingInformation getShippingInformation() {
        return shippingInfo;
    }

    /**
     * Returns the orders placed by the customer.
     *
     * @return The orders placed by the customer.
     */
    public ArrayList<Order> getCustomerOrders() {
        return orders;
    }

    /**
     * Places an order for the customer.
     *
     * @param t_order The order to be placed.
     * @return True if the order is successfully placed, false otherwise.
     */
    public boolean placeAnOrder(Order t_order) {
        // Place order
        orders.add(t_order);
        return true;
    }

    /**
     * Displays the information of the customer, including ID, user details, payment information,
     * shipping information, and orders placed by the customer.
     */
    public void displayInfo() {
        // print id
        System.out.println("----------------------------------------- " + Utility.convertToTitleCase(getUserName()) + " Information ----------------------------------------");
        System.out.println("Id: " + getCustomerId());

        // print User details
        super.displayInfo();

        // payment info
        paymentMethods.print();
        System.out.println();

        // print shipping information
        this.shippingInfo.print();
        System.out.println();

        // print orders
        for (Order order : orders) {
            order.print();
        }
    }

    /**
     * Retrieves the number of customers created in this class.
     *
     * @return The number of customers created.
     */
    public static int GetNumberCustomerThisClassMade() {
        return s_totalCountOfCustomers;
    }
}
