package model.users;
import model.payment.Payment;
import model.tool.Cart;
import model.users.details.Order;
import model.users.details.ShippingInformation;
import utilities.Utility;
import java.util.ArrayList;
public class Customer extends User {
    /* static variables related to class only */
    private static int s_totalCountOfCusotmers;

    /* Instance attributes */
    private final String customerId;                  /* Auto */
    private Cart cart;
    private ArrayList<Order> orders;
    private ShippingInformation shippingInfo;
    private Payment paymentMethods;

    public Customer() {
        super();
        customerId = Utility.generateId("CUSTOMER", ++s_totalCountOfCusotmers);
        cart = new Cart();
        shippingInfo = new ShippingInformation();
        paymentMethods = new Payment();
        orders = new ArrayList<>();
    }


    public Customer(String t_name, String t_password, String t_email, String t_phoneNumber, Cart t_cart,
                    Payment t_payment, ShippingInformation t_info)
    {
        super(t_name, t_password, t_email, t_phoneNumber);

        customerId = Utility.generateId("CUSTOMER", ++s_totalCountOfCusotmers);
        cart = t_cart;
        shippingInfo = t_info;
        paymentMethods = t_payment;
        orders = new ArrayList<>();
    }

    public void setCart(Cart t_cart) {
        cart = t_cart;
    }

    public void setPayment(Payment t_payment) {
        paymentMethods = t_payment;
    }

    public void setShippingInfo(ShippingInformation t_info) {
        shippingInfo = t_info;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Cart getCart() {
        return cart;
    }

    public Payment getPayment() {
        return paymentMethods;
    }

    public ShippingInformation getShippingInformation() {
        return shippingInfo;
    }

    public ArrayList<Order> getCustomerOrders() {
        return orders;
    }

    public boolean placeAnOrder(Order t_order)
    {
        // Place order
        orders.add(t_order);
        return true;
    }

    public void displayInfo() {
        // print id
        System.out.println("-----------------------------------------" + getUserName() + " Information ----------------------------------------");
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

    /* static methods */
    public static int GetNumberCustomerThisClassMade() {
        return s_totalCountOfCusotmers;
    }

}
