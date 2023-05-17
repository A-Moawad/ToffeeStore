package model.users.details;
import utilities.Utility;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * The `Order` class represents an order made by a customer.
 * It contains information such as the order ID, customer ID, customer name, order date, order status,
 * payment method, order details, and shipping information.
 * @see OrderDetails
 * @see ShippingInformation
 */
public class Order
{
    /* Static Attributes (related to class only and has nothing to do with instances) */
    private static int s_TotalOrdersCount;

    /* Data */

    /**
     * The auto-generated order ID.
     */
    private String orderId;

    /**
     * The ID of the customer placing the order.
     */
    private String customerId;

    /**
     * The name of the customer placing the order.
     */
    private String customerName;

    /**
     * The auto-generated order date.
     */
    private String orderDate;

    /**
     * The status of the order.
     */
    private String orderStatus;

    /**
     * The payment method used for the order.
     */
    private String paymentMethod;

    /**
     * The details of the order.
     */
    private OrderDetails details;

    /**
     * The shipping information for the order.
     */
    private ShippingInformation shippingInformation;

    /**
     * Default constructor for the Order class.
     * Initializes the order with default values and generates the order ID and order date.
     */
    public Order() {
        this.orderId = Utility.generateId("ORDER", ++s_TotalOrdersCount);
        this.customerId = "None";
        this.orderStatus = "None";
        this.orderDate = currentDateAndTime();
        this.paymentMethod = "None";
        this.details = new OrderDetails();
        this.shippingInformation = new ShippingInformation();
    }

    /**
     * Parameterized constructor for the Order class.
     * Initializes the order with the provided values and generates the order ID and order date.
     *
     * @param customerId       The ID of the customer placing the order.
     * @param customerName     The name of the customer placing the order.
     * @param orderStatus      The status of the order.
     * @param paymentMethod    The payment method used for the order.
     * @param details          The details of the order.
     * @param shippingInformation The shipping information for the order.
     */
    public Order(String customerId, String customerName, String orderStatus, String paymentMethod, OrderDetails details, ShippingInformation shippingInformation) {
        this.orderId = Utility.generateId("ORDER", ++s_TotalOrdersCount);
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderStatus = orderStatus;
        this.orderDate = currentDateAndTime();
        this.paymentMethod = paymentMethod;
        this.details = details;
        this.shippingInformation = shippingInformation;
    }

    /**
     * Sets the customer ID for the order.
     *
     * @param id The customer ID.
     */
    public void SetCustomerId(String id) {
        this.customerId = id;
    }

    /**
     * Sets the order status.
     *
     * @param status The order status.
     */
    public void SetOrderStatus(String status) {
        this.orderStatus = status;
    }

    /**
     * Sets the payment method for the order.
     *
     * @param method The payment method.
     */
    public void SetPaymentMethod(String method) {
        this.paymentMethod = method;
    }

    /**
     * Sets the shipping information for the order.
     *
     * @param info The shipping information.
     */
    public void SetShippingInfo(ShippingInformation info) {
        this.shippingInformation = info;
    }

    /**
     * Sets the order details.
     *
     * @param details The order details.
     */
    public void SetOrderDetails(OrderDetails details) {
        this.details = details;
    }

    /**
     * Retrieves the order ID.
     *
     * @return The order ID.
     */
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * Retrieves the customer ID.
     *
     * @return The customer ID.
     */
    public String getCustomerId() {
        return this.customerId;
    }

    /**
     * Retrieves the customer name.
     *
     * @return The customer name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Retrieves the order date.
     *
     * @return The order date.
     */
    public String getOrderingDate() {
        return this.orderDate;
    }

    /**
     * Retrieves the order status.
     *
     * @return The order status.
     */
    public String getOrderStatus() {
        return this.orderStatus;
    }

    /**
     * Retrieves the payment method.
     *
     * @return The payment method.
     */
    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    /**
     * Retrieves the shipping information.
     *
     * @return The shipping information.
     */
    public ShippingInformation getShippingInfo() {
        return this.shippingInformation;
    }

    /**
     * Retrieves the order details.
     *
     * @return The order details.
     */
    public OrderDetails getOrderDetails() {
        return this.details;
    }

    /**
     * Prints the order details to the console.
     */
    public void print() {
        System.out.println("---------------------------------------- Order " + getOrderNumber() + " ----------------------------------------");
        System.out.println("Order ID: " + getOrderId());
        System.out.println("Customer ID: " + getCustomerId());
        System.out.println("Customer Name: " + getCustomerName());
        System.out.println("Order Date: " + getOrderingDate());
        System.out.println("Order Status: " + getOrderStatus());
        System.out.println("Payment Method: " + getPaymentMethod());
        System.out.println();
        this.details.print();
        System.out.println();
        this.shippingInformation.print();
    }

    /**
     * Retrieves the last character of the order ID.
     *
     * @return The last character of the order ID.
     */
    private Character getOrderNumber() {
        return getOrderId().charAt(getOrderId().length() - 1);
    }

    /**
     * Retrieves the total number of orders made using this class.
     *
     * @return The total number of orders.
     */
    public static int getTotalNumberOfOrdersThisClassMade() {
        return s_TotalOrdersCount;
    }

    /**
     * Helper function to get the current date and time.
     *
     * @return The current date and time in the format "dd/MM/yyyy HH:mm:ss".
     */
    private String currentDateAndTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
