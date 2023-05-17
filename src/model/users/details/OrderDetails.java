package model.users.details;

/**
 * The OrderDetails class represents the details of an order.
 * It stores information such as the order ID, product ID, product name,
 * quantity, unit price, and total price.
 */
public class OrderDetails
{
    private String orderId;
    private String productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;

    /**
     * Default constructor for the OrderDetails class.
     * Initializes all instance variables with default values.
     */
    public OrderDetails() {
        this.orderId = "None";
        this.productId = "None";
        this.productName = "None";
        this.quantity = 0;
        this.unitPrice = 0;
        this.totalPrice = 0;
    }

    /**
     * Parameterized constructor for the OrderDetails class.
     * Initializes instance variables with the provided values.
     *
     * @param orderId     the order ID
     * @param productId   the product ID
     * @param productName the product name
     * @param quantity    the ordered quantity
     * @param unitPrice   the unit price
     * @param totalPrice  the total price
     */
    public OrderDetails(String orderId, String productId, String productName, int quantity, double unitPrice, double totalPrice) {
        setOrderId(orderId);
        setProductId(productId);
        setProductName(productName);
        setOrderedQuantity(quantity);
        setUnitPrice(unitPrice);
        setTotalPrice(totalPrice);
    }

    /**
     * Sets the order ID.
     *
     * @param orderId the order ID to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Returns the order ID.
     *
     * @return the order ID
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the product ID.
     *
     * @param productId the product ID to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Returns the product ID.
     *
     * @return the product ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets the product name.
     *
     * @param productName the product name to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Returns the product name.
     *
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the ordered quantity.
     *
     * @param quantity the ordered quantity to set
     */
    public void setOrderedQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the ordered quantity.
     *
     * @return the ordered quantity
     */
    public int getOrderedQuantity() {
        return quantity;
    }

    /**
     * Sets the unit price.
     *
     * @param unitPrice the unit price to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Returns the unit price.
     *
     * @return the unit price
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Sets the total price.
     *
     * @param totalPrice the total price to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Returns the total price.
     *
     * @return the total price
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Prints the order details to the console.
     */
    public void print() {
        System.out.println("************* Order Details ************");
        System.out.println("ProductId: " + getProductId());
        System.out.println("ProductName: " + getProductName());
        System.out.println("Quantity: " + getOrderedQuantity() + "\n" + "Price per unit: " + getUnitPrice() + "$");
        System.out.println("Total price: " + getTotalPrice() + "$");
    }
}
