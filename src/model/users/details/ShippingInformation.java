package model.users.details;

public class ShippingInformation {
    private String recipientName;
    private String recipientPhoneNumber;
    private String email;
    private String city;
    private String shippingAddress;

    public ShippingInformation() {
        recipientName = "None";
        recipientPhoneNumber = "None";
        email = "None";
        city = "None";
        shippingAddress = "None";
    }

    public ShippingInformation(String recipientName, String recipientPhoneNumber, String email, String city, String shippingAddress) {
        setRecipientName(recipientName);
        setRecipientPhoneNumber(recipientPhoneNumber);
        setEmail(email);
        setCity(city);
        setShippingAddress(shippingAddress);
    }

    // copy constructor
    public ShippingInformation(ShippingInformation information)
    {
        setRecipientName(information.getRecipientName());
        setRecipientPhoneNumber(information.getRecipientPhoneNumber());
        setEmail(information.getEmail());
        setShippingAddress(getShippingAddress());
        setCity(information.getCity());
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public void setRecipientPhoneNumber(String recipientPhoneNumber) {
        this.recipientPhoneNumber = recipientPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void print() {
        System.out.println("************* Shipping Details ************");
        System.out.println("RecipientName: " + recipientName + "\n" + "RecipientPhoneNumber: " + this.recipientPhoneNumber);
        System.out.println("RecipientEmail: " + email + "\n" + "City: " + this.city);
        System.out.println("Address: " + this.shippingAddress);
    }

}
