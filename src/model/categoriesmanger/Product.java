package model.categoriesmanger;
import java.util.*;

public class Product
{
    /* static attributes ( Related to class only and have nothing to do with instance) */
    static private int s_productCount;
    static private int s_aliveProductsCount;


    /* instance attributes ( instance properties ) */
    private String m_productId;
    private String m_productName;
    private String m_productCategory;
    private String m_productDescription;
    private double m_productPrice;
    private int m_availableAmount;


    /* (dConstructor, paraConstructor, defaultCopyConstructor, MoveConstructor, Destructor) */
    public Product()
    {
        this.m_productName = "None";
        this.m_productCategory = "None";
        this.m_productDescription = "None";
        this.m_productPrice = 0;
        this.m_availableAmount = 0;

        // incremant total n of Products and alive products and init  id
        this.m_productId = ("FCAI-PRODUCT-" + ++s_productCount);
        s_aliveProductsCount++;
    }

    public Product(String t_name, String t_category, String t_descr, double t_price, int t_amount)
    {
        // init
        this.m_productName = t_name;
        this.m_productCategory = t_category;
        this.m_productDescription = t_descr;
        this.m_productPrice = t_price;
        this.m_availableAmount = t_amount;

        // increment total n of Products and alive products and init  id
        this.m_productId = ("FCAI-PRODUCT-" + ++s_productCount);
        s_aliveProductsCount++;
    }

    public Product(Product p)
    {
        // init
        this.m_productName = p.getM_productName();
        this.m_productCategory = p.getM_productCategory();
        this.m_productDescription = p.getM_productDescription();
        this.m_productPrice = p.getM_productPrice();
        this.m_availableAmount = p.getM_availableAmount();

        // increment total n of Products and alive products and init  id
        this.m_productId = p.getM_productId();
        s_aliveProductsCount--;
    }

    /* Setter and Getters */

    public String getM_productId() {
        return m_productId;
    }

    public void setM_productName(String m_productName) {
        this.m_productName = m_productName;
    }

    public String getM_productName() {
        return m_productName;
    }

    public void setM_productCategory(String m_productCategory) {
        this.m_productCategory = m_productCategory;
    }

    public String getM_productCategory() {
        return m_productCategory;
    }

    public void setM_productDescription(String m_productDescription) {
        this.m_productDescription = m_productDescription;
    }

    public String getM_productDescription() {
        return m_productDescription;
    }

    public void setM_productPrice(double m_productPrice) {
        this.m_productPrice = m_productPrice;
    }

    public double getM_productPrice() {
        return m_productPrice;
    }

    public void setM_availableAmount(int m_availableAmount) {
        this.m_availableAmount = m_availableAmount;
    }

    public int getM_availableAmount() {
        return m_availableAmount;
    }

    ///////////////////////////////////////////////// Instance Methods  //////////////////////////////////////////////////
    public int increaseAmount(int t_value)
    {
        // add new quantity
        this.m_availableAmount += t_value;

        // return total number after addition
        return m_availableAmount;
    }

    public Product print() {
        // box width
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

        // product description
        if (m_productDescription.length() > 0 && !m_productDescription.equals("None")) {
            System.out.println("|" + " ".repeat(spaces) + "About product: " + " ".repeat(boxWidth - 16 - spaces - amountLength) + "|");
            ParseDescription(m_productDescription);

            // Print the bottom border of the box
            System.out.println("+" + "-".repeat(boxWidth - 2) + "+");
        }

        // return current product
        return this;
    }


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

    public boolean isLessThan(Product t_product)
    {
        return this.m_productName.compareTo(t_product.getM_productName()) < 0;
    }

    public boolean isGreaterThan(Product t_product)
    {
        return this.m_productName.compareTo(t_product.getM_productName()) > 0;
    }


    public boolean isEqualTo(Product other) {
        return this.m_productName.equals(other.getM_productName());
    }


    ///////////////////////////////////////////////// Static Methods (related to class only and have nothing to do with instance) //////////////////////////////////////////////////
    public static int GetTotalNumberAliveProducts()
    {
        // return total count
        return s_aliveProductsCount;
    }


    public static int GetTotalNumberProductsThisClassMade()
    {
        // return total count
        return s_productCount;
    }


    ////////////////////////////////////////////// Helper Functions ///////////////////////////////////////////////////////////////////////////////////////
    private void ParseDescription(final String m_descr)
    {
        final StringTokenizer st = new StringTokenizer(m_descr);
        int wordCount = 0;
        String word;
        StringBuilder line = new StringBuilder();
        StringBuilder so = new StringBuilder();

        // add to each stream lines so that each compriesed of 4 words at most
        while (st.hasMoreTokens())
        {
            word = st.nextToken();

            if (wordCount == 4)
            {
                so.append(line).append("\n");
                line = new StringBuilder();
                wordCount = 0;
            }

            line.append(word).append(" ");
            wordCount++;
        }

        // print each line
        // Center the sentence within the field and add pipe characters at the beginning and end
        for (final String str : so.toString().split("\n")) {
            System.out.println("| " + str + " |");
        }
    }
}
