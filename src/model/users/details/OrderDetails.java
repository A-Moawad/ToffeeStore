package model.users.details;

public class OrderDetails {
    private String orderId;
    private String productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;


    public OrderDetails()
    {
        this.orderId = "None";
        this.productId = "None";
        this.productName = "None";
        this.quantity = 0;
        this.unitPrice = 0;
        this.totalPrice = 0;
    }
    public OrderDetails(String orderId, String productId, String productName, int quantity, double unitPrice, double totalPrice) {
        // set all of them
        setOrderId(orderId);
        setProductId(productId);
        setProductName(productName);
        setOrderedQuantity(quantity);
        setUnitPrice(unitPrice);
        setTotalPrice(totalPrice);
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setOrderedQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderedQuantity() {
        return quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void print() {
        System.out.println("************* Order Details ************");
        System.out.println("ProductId: " + getProductId());
        System.out.println("ProductName: " + getProductName());
        System.out.println("Quantity: " + getOrderedQuantity() + "\n" + "Price per unit: " + getUnitPrice() + "$");
    }

}

