package model.users.details;

/**
 * The ShippingInformation class represents the shipping details of an order.
 * It stores information such as the recipient's name, phone number, email,
 * city, and shipping address.
 */
public class ShippingInformation
{
    private String recipientName;           // Stores the name of the recipient for the shipping information.
    private String recipientPhoneNumber;    // Stores the phone number of the recipient for the shipping information.
    private String email;                   // Stores the email address of the recipient for the shipping information.
    private String city;                    // Stores the city where the order will be shipped.
    private String shippingAddress;         // Stores the shipping address for the order.


    /**
     * Default constructor for the ShippingInformation class.
     * Initializes all instance variables with default values.
     */
    public ShippingInformation()
    {
        recipientName = "None";
        recipientPhoneNumber = "None";
        email = "None";
        city = "None";
        shippingAddress = "None";
    }

    /**
     * Parameterized constructor for the ShippingInformation class.
     * Initializes instance variables with the provided values.
     *
     * @param recipientName         the recipient's name
     * @param recipientPhoneNumber  the recipient's phone number
     * @param email                 the recipient's email
     * @param city                  the city for shipping
     * @param shippingAddress       the shipping address
     */
    public ShippingInformation(String recipientName, String recipientPhoneNumber, String email, String city, String shippingAddress)
    {
        setRecipientName(recipientName);
        setRecipientPhoneNumber(recipientPhoneNumber);
        setEmail(email);
        setCity(city);
        setShippingAddress(shippingAddress);
    }

    /**
     * Copy constructor for the ShippingInformation class.
     * Creates a new instance by copying the values from another ShippingInformation object.
     *
     * @param information the ShippingInformation object to copy
     */
    public ShippingInformation(ShippingInformation information)
    {
        setRecipientName(information.getRecipientName());
        setRecipientPhoneNumber(information.getRecipientPhoneNumber());
        setEmail(information.getEmail());
        setCity(information.getCity());
        setShippingAddress(information.getShippingAddress());
    }

    /**
     * Returns the recipient's name.
     *
     * @return the recipient's name
     */
    public String getRecipientName()
    {
        return recipientName;
    }

    /**
     * Sets the recipient's name.
     *
     * @param recipientName the recipient's name to set
     */
    public void setRecipientName(String recipientName)
    {
        this.recipientName = recipientName;
    }

    /**
     * Returns the recipient's phone number.
     *
     * @return the recipient's phone number
     */
    public String getRecipientPhoneNumber()
    {
        return recipientPhoneNumber;
    }

    /**
     * Sets the recipient's phone number.
     *
     * @param recipientPhoneNumber the recipient's phone number to set
     */
    public void setRecipientPhoneNumber(String recipientPhoneNumber)
    {
        this.recipientPhoneNumber = recipientPhoneNumber;
    }

    /**
     * Returns the recipient's email.
     *
     * @return the recipient's email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets the recipient's email.
     *
     * @param email the recipient's email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Returns the city for shipping.
     *
     * @return the city for shipping
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Sets the city for shipping.
     *
     * @param city the city for shipping to set
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Returns the shipping address.
     *
     * @return the shipping address
     */
    public String getShippingAddress()
    {
        return shippingAddress;
    }

    /**
     * Sets the shipping address.
     *
     * @param shippingAddress the shipping address to set
     */
    public void setShippingAddress(String shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }

    /**
     * Prints the shipping details to the console.
     */
    public void print()
    {
        System.out.println("************* Shipping Details ************");
        System.out.println("RecipientName: " + recipientName + "\n" + "RecipientPhoneNumber: " + recipientPhoneNumber);
        System.out.println("RecipientEmail: " + email + "\n" + "City: " + city);
        System.out.println("Address: " + shippingAddress);
    }
}
