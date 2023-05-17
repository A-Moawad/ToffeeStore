package model.categoriesmanger;
import java.util.StringTokenizer;

/**
 * The Product class represents a product with its details, such as name, category, description, price, and available amount.
 */
public class Product
{

    /* Static attributes */

    /**
     * The total number of products created.
     */
    static private int s_productCount;

    /**
     * The total number of alive products.
     */
    static private int s_aliveProductsCount;

    /* Instance attributes */

    /**
     * The unique ID of the product.
     */
    private String m_productId;

    /**
     * The name of the product.
     */
    private String m_productName;

    /**
     * The category of the product.
     */
    private String m_productCategory;

    /**
     * The description of the product.
     */
    private String m_productDescription;

    /**
     * The price of the product.
     */
    private double m_productPrice;

    /**
     * The available amount of the product.
     */
    private int m_availableAmount;

    /* Constructors */

    /**
     * Constructs a Product object with default values.
     * Initializes the product ID, increments the total number of products, and increments the total number of alive products.
     */
    public Product() {
        this.m_productName = "None";
        this.m_productCategory = "None";
        this.m_productDescription = "None";
        this.m_productPrice = 0;
        this.m_availableAmount = 0;

        // Increment total number of products and alive products and initialize the ID
        this.m_productId = ("FCAI-PRODUCT-" + ++s_productCount);
        s_aliveProductsCount++;
    }

    /**
     * Constructs a Product object with the specified values.
     * Initializes the product ID, increments the total number of products, and increments the total number of alive products.
     *
     * @param t_name        the name of the product
     * @param t_category    the category of the product
     * @param t_descr       the description of the product
     * @param t_price       the price of the product
     * @param t_amount      the available amount of the product
     */
    public Product(String t_name, String t_category, String t_descr, double t_price, int t_amount) {
        // Initialize the attributes
        this.m_productName = t_name;
        this.m_productCategory = t_category;
        this.m_productDescription = t_descr;
        this.m_productPrice = t_price;
        this.m_availableAmount = t_amount;

        // Increment total number of products and alive products and initialize the ID
        this.m_productId = ("FCAI-PRODUCT-" + ++s_productCount);
        s_aliveProductsCount++;
    }

    /**
     * Constructs a Product object by copying another Product object.
     * Initializes the attributes with the values from the specified Product object.
     * Decrements the total number of alive products.
     *
     * @param p  the Product object to copy
     */
    public Product(Product p)
    {
        // Initialize the attributes
        this.m_productName = p.getM_productName();
        this.m_productCategory = p.getM_productCategory();
        this.m_productDescription = p.getM_productDescription();
        this.m_productPrice = p.getM_productPrice();
        this.m_availableAmount = p.getM_availableAmount();

        // Set the ID and decrement the total number of alive products
        this.m_productId = p.getM_productId();
        s_aliveProductsCount--;
    }

    /* Setter and Getters */

    /**
     * Gets the product id.
     *
     * @return The product id.
     */
    public String getM_productId() {
        return m_productId;
    }

    /**
     * Sets the product name.
     *
     * @param m_productName The product name.
     */
    public void setM_productName(String m_productName) {
        this.m_productName = m_productName;
    }

    /**
     * Retrieves the product name.
     *
     * @return The product name.
     */
    public String getM_productName() {
        return m_productName;
    }

    /**
     * Sets the product category.
     *
     * @param m_productCategory The product category.
     */
    public void setM_productCategory(String m_productCategory) {
        this.m_productCategory = m_productCategory;
    }

    /**
     * Retrieves the product category.
     *
     * @return The product category.
     */
    public String getM_productCategory() {
        return m_productCategory;
    }

    /**
     * Sets the product description.
     *
     * @param m_productDescription The product description.
     */
    public void setM_productDescription(String m_productDescription) {
        this.m_productDescription = m_productDescription;
    }

    /**
     * Retrieves the product description.
     *
     * @return The product description.
     */
    public String getM_productDescription() {
        return m_productDescription;
    }

    /**
     * Sets the product price.
     *
     * @param m_productPrice The product price.
     */
    public void setM_productPrice(double m_productPrice) {
        this.m_productPrice = m_productPrice;
    }

    /**
     * Retrieves the product price.
     *
     * @return The product price.
     */
    public double getM_productPrice() {
        return m_productPrice;
    }

    /**
     * Sets the available amount of the product.
     *
     * @param m_availableAmount The available amount of the product.
     */
    public void setM_availableAmount(int m_availableAmount) {
        this.m_availableAmount = m_availableAmount;
    }

    /**
     * Retrieves the available amount of the product.
     *
     * @return The available amount of the product.
     */
    public int getM_availableAmount() {
        return m_availableAmount;
    }


    ////////////////////////////////////////// Instance Methods //////////////////////////////////////////

    /**
     * Increases the available amount of the product by a specified value.
     *
     * @param t_value  the value to increase the available amount by
     * @return the total available amount after the increase
     */
    public int increaseAmount(int t_value)
    {
        // Add new quantity
        this.m_availableAmount += t_value;

        // Return the total number after the addition
        return m_availableAmount;
    }

    /**
     * Prints the details of the product.
     *
     * @return the current Product object
     */
    public Product print()
    {
        // Box width
        int boxWidth = 30;
        int spaces;

        // Print the top border of the box
        System.out.println("+" + "-".repeat(boxWidth - 2) + "+");

        // Print the name of the product, centered within the box
        int nameLength = m_productName.length();
        spaces = (boxWidth - 2 - nameLength) / 2;
        System.out.println("|" + " ".repeat(spaces) + m_productName + " ".repeat(boxWidth - 2 - spaces - nameLength) + "|");

        System.out.println("+" + "-".repeat(boxWidth - 2) + "+");

        // Print the price of the product, centered within the box
        String priceStr = "$" + String.valueOf(m_productPrice);
        int priceLength = priceStr.length();
        spaces = (boxWidth - 9 - priceLength) / 2;
        System.out.println("|" + " ".repeat(spaces) + "Price: " + priceStr + " ".repeat(boxWidth - 9 - spaces - priceLength) + "|");
        System.out.println("+" + "-".repeat(boxWidth - 2) + "+");

        // Print the amount of the product, centered within the box
        String amountStr = String.valueOf(m_availableAmount);
        int amountLength = amountStr.length();
        spaces = (boxWidth - 10 - amountLength) / 2;
        System.out.println("|" + " ".repeat(spaces) + "Amount: " + amountStr + " ".repeat(boxWidth - 10 - spaces - amountLength) + "|");
        System.out.println("+" + "-".repeat(boxWidth - 2) + "+");

        // Product description
        if (m_productDescription.length() > 0 && !m_productDescription.equals("None")) {
            System.out.println("|" + " ".repeat(spaces) + "About product: " + " ".repeat(boxWidth - 16 - spaces - amountLength) + "|");
            parseDescription(m_productDescription);

            // Print the bottom border of the box
            System.out.println("+" + "-".repeat(boxWidth - 2) + "+");
        }

        // Return the current Product object
        return this;
    }

    /**
     * Decreases the available amount of the product by a specified value.
     *
     * @param t_value  the value to decrease the available amount by
     * @return true if the amount was successfully decreased, false otherwise
     */
    public boolean decreaseAmount(int t_value)
    {
        boolean decreased = true;

        if (m_availableAmount == 0)
        {
            decreased = false;
        }
        else
        {
            m_availableAmount -= t_value;
        }

        return decreased;
    }

    /**
     * Compares the product's name with another product's name to determine if it is less than the other product's name.
     *
     * @param t_product  the other product to compare with
     * @return true if the product's name is less than the other product's name, false otherwise
     */
    public boolean isLessThan(Product t_product)
    {
        return this.m_productName.compareTo(t_product.getM_productName()) < 0;
    }

    /**
     * Compares the product's name with another product's name to determine if it is greater than the other product's name.
     *
     * @param t_product  the other product to compare with
     * @return true if the product's name is greater than the other product's name, false otherwise
     */
    public boolean isGreaterThan(Product t_product)
    {
        return this.m_productName.compareTo(t_product.getM_productName()) > 0;
    }

    /**
     * Checks if the current product is equal to another product based on their names.
     *
     * @param other  the other product to compare with
     * @return true if the products have the same name, false otherwise
     */
    public boolean isEqualTo(Product other)
    {
        return this.m_productName.equals(other.getM_productName());
    }

    ///////////////////////////////////////////////// Static Methods /////////////////////////////////////////////////

    /**
     * Returns the total number of alive products.
     *
     * @return the total number of alive products
     */
    public static int GetTotalNumberAliveProducts()
    {
        // Return the total count
        return s_aliveProductsCount;
    }

    /**
     * Returns the total number of products created by this class.
     *
     * @return the total number of products created by this class
     */
    public static int GetTotalNumberProductsThisClassMade()
    {
        // Return the total count
        return s_productCount;
    }

    ////////////////////////////////////////////// Helper Functions ////////////////////////////////////////////////

    private void parseDescription(final String m_descr)
    {
        final StringTokenizer st = new StringTokenizer(m_descr);
        int wordCount = 0;
        String word;
        StringBuilder line = new StringBuilder();
        StringBuilder so = new StringBuilder();

        // Add to each stream lines so that each comprises 4 words at most
        while (st.hasMoreTokens()) {
            word = st.nextToken();

            if (wordCount == 4) {
                so.append(line).append("\n");
                line = new StringBuilder();
                wordCount = 0;
            }

            line.append(word).append(" ");
            wordCount++;
        }

        // Print each line
        // Center the sentence within the field and add pipe characters at the beginning and end
        for (final String str : so.toString().split("\n")) {
            System.out.println("| " + str + " |");
        }
    }
}