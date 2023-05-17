package model.users;

import model.categoriesmanger.CategoriesManger;
import utilities.Utility;

/**
 * This class simulates the admin properties and methods<br>Extends from user.
 */
public class Admin extends User
{

    /* Static attributes */
    private static int s_adminsTotalCount;

    /* Instance attributes */
    private String adminId;
    private CategoriesManger catManger;

    /* Constructor */

    /**
     * Default constructor of admin class.
     */
    public Admin()
    {
        super();
        adminId = Utility.generateId("admin", ++s_adminsTotalCount);

    }

    /**
     * Parametrized constructor, initializes admin instance attributes.
     * @param t_name Admin name
     * @param t_password Admin password
     * @param t_phoneNumber Admin phone number
     * @param t_email   Admin email
     * @param t_catManger Category manger so that admin be able to modify store categories and products.
     * @see CategoriesManger
     */
    public Admin(String t_name, String t_password, String t_phoneNumber, String t_email, CategoriesManger t_catManger)
    {
        super(t_name, t_password, t_phoneNumber, t_email);
        adminId = Utility.generateId("admin", ++s_adminsTotalCount);
        catManger = t_catManger;
    }

    /* Instance methods */

    /**
     * Returns admin id
     * @return AutoGenerated id
     */
    public String getId() {
        return adminId;
    }

    /**
     * This returns category manger given to admin
     * @see CategoriesManger
     * @return Category manager
     */
    public CategoriesManger getCategoryManger() {
        return catManger;
    }

    /**
     * This function prints all admin information, including: id, username, phone number, email.
     */
    public void displayInfo() {
        System.out.println("-----------------------------------------");
        System.out.println(getUserName() + " Information ");
        System.out.println("Id: " + getId());
        super.displayInfo();
    }

    /* Static methods */

    /**
     * Returns total count of all created admins, this function is static, meaning it works on class level.
     * @return total count of all created customers
     */
    public static int getAdminsTotalCount() {
        return s_adminsTotalCount;
    }

    /**
     * Clears all existing categories provided by cateManger.
     */
    public void clearCategories()
    {
        this.catManger.getT_categories().clear();
    }
}
