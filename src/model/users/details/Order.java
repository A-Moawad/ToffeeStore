package model.users.details;
import utilities.Utility;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Order {
    /* Static Attributes (related to class only and has nothing to do with instances) */
    private static int s_TotalOrdersCount;

    /* data */
    private String orderId;         // Auto
    private String customerId;
    private String customerName;
    private String orderDate;       // Auto
    private String orderStatus;
    private String paymentMethod;
    private OrderDetails details;
    private ShippingInformation shippingInformation;

    public Order() {
        this.orderId = Utility.generateId("ORDER", ++s_TotalOrdersCount);
        this.customerId = "None";
        this.orderStatus = "None";
        this.orderDate = currentDateAndTime();
        this.paymentMethod = "None";
        this.details = new OrderDetails();
        this.shippingInformation = new ShippingInformation();
    }

    public Order(String customerId, String customerName, String orderStatus, String paymentMethod, OrderDetails details, ShippingInformation info) {
        this.orderId = Utility.generateId("ORDER", ++s_TotalOrdersCount);
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderStatus = orderStatus;
        this.orderDate = currentDateAndTime();
        this.paymentMethod = paymentMethod;
        this.details = details;
        this.shippingInformation = info;
    }

    public void SetCustomerId(String id) {
        this.customerId = id;
    }

    public void SetOrderStatus(String status) {
        this.orderStatus = status;
    }

    public void SetPaymentMethod(String method) {
        this.paymentMethod = method;
    }

    public void SetShippingInfo(ShippingInformation info) {
        this.shippingInformation = info;
    }

    public void SetOrderDetails(OrderDetails details) {
        this.details = details;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOrderingDate() {
        return this.orderDate;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public ShippingInformation getShippingInfo() {
        return this.shippingInformation;
    }

    public OrderDetails getOrderDetails() {
        return this.details;
    }

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

    private Character getOrderNumber() {
        return getOrderId().charAt(getOrderId().length() - 1);
    }

    /* Static methods */
    public static int getTotalNumberOfOrdersThisClassMade() {
        return s_TotalOrdersCount;
    }


    /* Helper functions */
    private String currentDateAndTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
