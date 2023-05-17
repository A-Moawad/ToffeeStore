package model.categoriesmanger;
import java.util.*;
/**
 * The Category class represents a category of products. It stores information about the category's name, the number of products it contains, and a list of products in the category.
 */
public class Category {
    /* Static attributes (related to class only, and have nothing to do with an instance) */
    private static int s_categoriesCount;

    /* Instance attributes */
    private String m_name;
    private int n_products;
    private ArrayList<Product> t_products;

    /* Constructors */

    /**
     * Creates a new Category object with default values.
     */
    public Category() {
        this.m_name = "None";
        this.n_products = 0;
        this.t_products = new ArrayList<Product>(200);
        Category.s_categoriesCount++;
    }

    /**
     * Creates a new Category object with the specified name.
     *
     * @param t_name The name of the category.
     */
    public Category(String t_name) {
        this.m_name = t_name;
        this.n_products = 0;
        this.t_products = new ArrayList<Product>(0);
        Category.s_categoriesCount++;
    }

    /**
     * Creates a new Category object with the specified name and list of products.
     *
     * @param t_name     The name of the category.
     * @param t_products The list of products in the category.
     */
    public Category(String t_name, ArrayList<Product> t_products) {
        this.m_name = t_name;
        this.n_products = t_products.size();
        this.t_products = t_products;
        Category.s_categoriesCount++;
    }

    //////////////////////////////////////////// Setters And Getters (Encapsulation) /////////////////////////////////////////////////////////////////

    /**
     * Sets the name of the category.
     *
     * @param t_name The new name of the category.
     */
    public void setName(String t_name) {
        this.m_name = t_name;
    }

    /**
     * Returns the current name of the category.
     *
     * @return The current name of the category.
     */
    public String getName()
    {
        return this.m_name;
    }

    /**
     * Sets the number of products in the category.
     *
     * @param n The number of products in the category.
     */
    public void setNumberOfProducts(int n) {
        this.n_products = n;
    }

    /**
     * Returns the current number of products in the category.
     *
     * @return The current number of products in the category.
     */
    public int getNumberOfProducts() {
        return this.n_products;
    }

    /**
     * Returns all the products in the category.
     *
     * @return A list of all the products in the category.
     */
    public ArrayList<Product> getAllProducts() {
        return this.t_products;
    }

    /**
     * Adds a new product to the category.
     *
     * @param t_name   The name of the new product.
     * @param t_descr  The description of the new product.
     * @param t_price  The initial price of the new product.
     * @param t_amount The available amount of the new product.
     * @return The newly created Product object.
     */
    public Product addProduct(String t_name, String t_descr, double t_price, int t_amount) {
        Product p = new Product(t_name, this.m_name, t_descr, t_price, t_amount);
        this.t_products.add(p);
        return p;
    }

    /**
     * Adds a new product to the category.
     *
     * @param p The Product object to add.
     * @return The added Product object.
     */
    public Product addProduct(Product p) {
        this.t_products.add(p);
        this.n_products++;
        return p;
    }

    /**
     * Removes a product from the category based on its name.
     *
     * @param t_name The name of the product to remove.
     * @return The removed Product object, or a new empty Product object if the product was not found.
     */
    public Product removeProduct(String t_name) {
        ListIterator<Product> it = t_products.listIterator();
        while (it.hasNext()) {
            Product p = it.next();
            if (compareByName(p, t_name)) {
                Product removedProduct = p;
                it.remove();
                this.n_products--;
                return removedProduct;
            }
        }
        System.out.println("Couldn't Find Product with name: " + t_name + " To Erase............!");
        return new Product();
    }

    /**
     * Searches for a product in the category based on its name.
     *
     * @param t_productName The name of the product to search for.
     * @return The index of the product if found, or -1 if not found.
     */
    public int searchProductByName(String t_productName) {
        if (exists(t_productName)) {
            ListIterator<Product> it = t_products.listIterator();
            while (it.hasNext()) {
                if (compareByName(it.next(), t_productName)) {
                    return it.previousIndex();
                }
            }
        }
        System.out.println("Couldn't Find Product with name: " + t_productName + "............!");
        return -1;
    }

    /**
     * Returns a product from the category based on its name.
     *
     * @param t_productName The name of the product to retrieve.
     * @return The Product object with the specified name, or null if not found.
     */
    public Product getProductByName(String t_productName) {
        return t_products.get(searchProductByName(t_productName));
    }

    /**
     * Displays all products in the category.
     */
    public void displayAllProducts() {
        if (isEmpty()) {
            System.out.println("------------------------------------ " + this.m_name + " Department Is Empty ------------------------------------");
        } else {
            System.out.println("------------------------------------ " + this.m_name + " ------------------------------------");
            for (Product p : t_products) {
                p.print();
            }
        }
    }

    /**
     * Checks if a product with the given name exists in the category.
     *
     * @param t_productName The name of the product to check.
     * @return true if the product exists in the category, false otherwise.
     */
    public boolean exists(String t_productName) {
        for (Product tProduct : t_products) {
            if (compareByName(tProduct, t_productName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a product exists in the category.
     *
     * @param t_product The Product object to check.
     * @return true if the product exists in the category, false otherwise.
     */
    public boolean exists(Product t_product) {
        return t_products.contains(t_product);
    }

    /**
     * Clears the list of products in the category.
     */
    public void clear() {
        this.t_products.clear();
    }

    /**
     * Checks whether the category is empty or not.
     *
     * @return true if the category is empty, false otherwise.
     */
    public boolean isEmpty() {
        return t_products.isEmpty();
    }

    /**
     * Compares a product's name with a given name.
     *
     * @param p       The Product object to compare.
     * @param t_name  The name to compare with the product's name.
     * @return true if the product's name is equal to the given name, false otherwise.
     */
    public boolean compareByName(Product p, String t_name) {
        return p.getM_productName().equals(t_name);
    }
}


