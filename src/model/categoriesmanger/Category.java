package model.categoriesmanger;
import java.util.*;

public class Category
{
    /* Static attributes (related to class only, and have nothing to do with instance) */
    private static int s_categoriesCount;

    /* Instance attributes */
    private String m_name;
    private int n_products;
    private ArrayList<Product> t_products;



    /* Constructors and Destructor */

    public Category()
    {
        this.m_name = "None";
        this.n_products = 0;

        // allocate arrayList with 200 products as a start
        this.t_products = new ArrayList<Product>(200);

        // increment number of created Categories (class entire program level)
        Category.s_categoriesCount++;
    }

    /**
     * Construct a new Category object
     *
     * @param t_name Name of to be created Category
     */
    public Category(String t_name)
    {
        // Set instance attributes
        this.m_name = t_name;
        this.n_products = 0;
        this.t_products = new ArrayList<Product>(0);

        // Increment number of created Categories (class entire program level)
        Category.s_categoriesCount++;
    }

    /**
     * Constructor to create a new category with a given name and a vector of products.
     *
     * @param t_name     Name of the category to be created
     * @param t_products Vector of products for the category
     */
    public Category(String t_name, ArrayList<Product> t_products)
    {
        this.m_name = t_name;
        this.n_products = t_products.size();
        this.t_products = t_products;

        // increment number of created Categories (class entire program level)
        s_categoriesCount++;
    }

    //////////////////////////////////////////// Setters And Getters (Encapsulation) /////////////////////////////////////////////////////////////////

    /**
     * Set Category's name
     *
     * @param t_name CategoryName - newName
     */
    public void setName(String t_name) {
        // update current name
        this.m_name = t_name;
    }


    /**
     * Returns current of category
     *
     * @return String Categories current name
     */
    public String getName() {
        return this.m_name;
    }


    /**
     * Sets Current Number of Categories
     *
     * @param n Number of products in Categories
     */
    public void setNumberOfProducts(int n) {
        this.n_products = n;
    }


    /**
     * Returns current number of products
     *
     * @return int Current number of products
     */
    public int getNumberOfProducts() {
        return this.n_products;
    }


    /**
     * Returns all products in the category
     *
     * @return Vector<Product> All products in the category
     */
    public ArrayList<Product> getAllProducts() {
        return this.t_products;
    }

    /**
     * Adds new product to Category.
     *
     * @param t_name   newProduct's name
     * @param t_descr  newProduct's description
     * @param t_price  newProduct's initial price
     * @param t_amount newProduct's available amount
     * @return Product newProduct
     */
    public Product addProduct(String t_name, String t_descr, double t_price, int t_amount) {
        // create new product
        Product p = new Product(t_name, this.m_name, t_descr, t_price, t_amount);
        this.t_products.add(p);
        return p;
    }


    /**
     * Adds new product to Category
     *
     * @param p Product object to add
     * @return Product  newProduct
     */
    public Product addProduct(Product p)
    {
       this.t_products.add(p);
       this.n_products++;

        return p;
    }

    /**
     *  Removes a product by name
     *
     * @param t_name Name of prodcut that will be removed
     * @return Product Removed product
     */
    public Product RemoveProduct(String t_name)
    {
        // check if exists
        ListIterator<Product> it = t_products.listIterator();
        while (it.hasNext()) {
            Product p = it.next();
            if (compareByName(p, t_name)) {
                // if product found store it in temp, then remove it
                Product removedProduct = p;
                it.remove();

                // reduce product count
                this.n_products--;

                // return the erased one
                return removedProduct;
            }
        }

        System.out.println("Couldn't Find Product with name: " + t_name + " To Erase............!");
        return new Product();
    }

    /**
     * @brief Takes product name and returns its index
     *
     * @param t_productName Name of product
     * @return int Index of product
     */
    public int searchProductByName(String t_productName) {
        // check if product exists
        if (exists(t_productName)) {
            ListIterator<Product> it = t_products.listIterator();
            while (it.hasNext()) {
                if (compareByName(it.next(), t_productName)) {
                    // return index
                    return it.previousIndex();
                }
            }
        }

        System.out.println("Couldn't Find Product with name: " + t_productName + "............!");
        return -1;
    }

    /**
     * @brief Takes productName and returns reference to it, so as user be able to update it
     *
     * @param t_productName Products'Name
     * @return Product& The product
     */
    public Product getProductByName(String t_productName) {
        return t_products.get(searchProductByName(t_productName));
    }

    public void displayAllProducts() {
        if (IsEmpty()) {
            System.out.println("------------------------------------ " + this.m_name + " Department Is Empty ------------------------------------");
        } else {
            // header
            System.out.println("------------------------------------ " + this.m_name + " ------------------------------------");

            // print each product
            for (Product p : t_products) {
                p.print();
            }

        }
    }

    /**
     * &#064;brief
     *
     * @param t_productName
     * @return true
     * @return false
     */
    public boolean exists(String t_productName) {
        for (Product tProduct : t_products) {
            if (compareByName(tProduct, t_productName)) {
                // it not pointing the element after the last, then it found something
                return true;
            }
        }

        // if it hasn't found something then return false
        return false;
    }

    public boolean exists(Product t_product) {
        return t_products.contains(t_product);
    }

    public void clear()
    {
        this.t_products.clear();
    }
    /**
     *  Checks whether category is empty or not
     *
     * @return true if category is empty
     *  false if category isn't empty
     */
    public boolean IsEmpty() {
        return t_products.isEmpty();
    }

    /**
     * Takse product and t_name, and compare product.name with the passed name (string)
     * main purpose of this function is work as lamabda function in find_if
     *
     * @param p     Product whose name will be compared
     * @param t_name Which will be compared with Product's name
     * @return true if p.GetName() = t_name
     * false if p.GetName() != t_name
     */
    public boolean compareByName(Product p, String t_name) {
        return p.getM_productName().equals(t_name);
    }
}

