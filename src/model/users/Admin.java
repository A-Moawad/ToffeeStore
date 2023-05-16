package model.users;

import model.categoriesmanger.CategoriesManger;
import utilities.Utility;

public class Admin extends User {

    /* Static attributes */
    private static int s_adminsTotalCount;

    /* Instance attributes */
    private String adminId;
    private CategoriesManger catManger;

    /* Constructor */
    public Admin()
    {
        super();
        adminId = Utility.generateId("admin", ++s_adminsTotalCount);

    }

    public Admin(String t_name, String t_password, String t_phoneNumber, String t_email, CategoriesManger t_catManger) {
        super(t_name, t_password, t_phoneNumber, t_email);
        adminId = Utility.generateId("admin", ++s_adminsTotalCount);
        catManger = t_catManger;
    }

    /* Instance methods */
    public String getId() {
        return adminId;
    }

    public CategoriesManger getCategoryManger() {
        return catManger;
    }

    public void displayInfo() {
        System.out.println("-----------------------------------------");
        System.out.println(getUserName() + " Information ");
        System.out.println("Id: " + getId());
        super.displayInfo();
    }

    /* Static methods */
    public static int getAdminsTotalCount() {
        return s_adminsTotalCount;
    }


    public void clearCategories()
    {
        this.catManger.getT_categories().clear();
    }
}
