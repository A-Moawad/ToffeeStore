package model.categoriesmanger;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.*;

/**
 * The CategoriesManger class manages a collection of categories.
 */
public class CategoriesManger {
    ArrayList<Category> t_categories;

    /**
     * Default constructor. Creates a CategoriesManger object with an empty list of categories.
     */
    public CategoriesManger() {
        this.t_categories = new ArrayList<Category>(100);
    }

    /**
     * Constructor. Creates a CategoriesManger object with the given list of categories.
     *
     * @param t_categories The list of categories to initialize the manager with.
     */
    public CategoriesManger(ArrayList<Category> t_categories) {
        this.t_categories = t_categories;
    }

    /* Setters And Getters */

    /**
     * Sets the list of categories.
     *
     * @param t_categories The list of categories to set.
     */
    public void setT_categories(ArrayList<Category> t_categories) {
        this.t_categories = t_categories;
    }

    /**
     * Returns the list of categories.
     *
     * @return The list of categories.
     */
    public ArrayList<Category> getT_categories() {
        return t_categories;
    }

    /**
     * Returns the number of categories in the manager.
     *
     * @return The number of categories.
     */
    public int getNumberOfCategories() {
        return this.t_categories.size();
    }

    /**
     * Adds a new category to the manager with the given name and products.
     *
     * @param categoryName The name of the category to add.
     * @param products     The list of products to associate with the category.
     * @return The newly added Category object.
     */
    public Category addCategory(String categoryName, ArrayList<Product> products) {
        Category newCategory = new Category(categoryName, products);
        this.t_categories.add(newCategory);
        return newCategory;
    }

    /**
     * Adds a new category to the manager with the given name.
     *
     * @param categoryName The name of the category to add.
     * @return The newly added Category object.
     */
    public Category addCategory(String categoryName) {
        Category newCategory = new Category(categoryName);
        this.t_categories.add(newCategory);
        return newCategory;
    }

    /**
     * Removes a category from the manager based on its name.
     *
     * @param catName The name of the category to remove.
     * @return The removed Category object, or a new empty Category object if the category was not found.
     */
    public Category removeCategoryByName(String catName) {
        ListIterator<Category> it = t_categories.listIterator();
        while (it.hasNext()) {
            Category p = it.next();
            if (compareCategoriesByName(p, catName)) {
                it.remove();
                return p;
            }
        }
        System.out.println("Couldn't Find Category with name: " + catName + " To Erase............!");
        return new Category();
    }

    /**
     * Displays the details of all categories in the manager.
     */
    public void displayAllCategories() {
        if (isEmpty()) {
            System.out.println("!................................ Manger Is Empty ..................................!");
        } else {
            for (Category category : t_categories) {
                category.displayAllProducts();
            }
        }
    }

    /**
     * Searches for a category in the manager based on its name.
     *
     * @param t_catName The name of the category to search for.
     * @return The index of the category if found, or -1 if not found.
     */
    public int searchCategoryByName(String t_catName) {
        Optional<Category> result = t_categories.stream().filter(c -> compareCategoriesByName(c, t_catName)).findFirst();
        if (result.isPresent()) {
            return t_categories.indexOf(result.get());
        } else {
            System.out.printf("! Couldn't Find Category Named: %s !%n", t_catName);
            return -1;
        }
    }

    /**
     * Searches for a product with the given name in all categories.
     *
     * @param productName The name of the product to search for.
     * @return A SimpleEntry object containing a boolean indicating if the product was found and the found Product object.
     */
    public AbstractMap.SimpleEntry<Boolean, Product> GetProductByNameThroughAllCat(String productName) {
        for (Category category : t_categories) {
            for (Product product : category.getAllProducts()) {
                if (product.getM_productName().equals(productName)) {
                    return new AbstractMap.SimpleEntry<Boolean, Product>(true, product);
                }
            }
        }
        Product defaultProduct = new Product();
        return new AbstractMap.SimpleEntry<Boolean, Product>(false, defaultProduct);
    }

    /**
     * Retrieves a category from the manager based on its name.
     *
     * @param t_catName The name of the category to retrieve.
     * @return The Category object with the specified name.
     * @throws RuntimeException if the category is not found.
     */
    public Category getCategoryByName(String t_catName) {
        int index = searchCategoryByName(t_catName);
        if (index == -1) {
            throw new RuntimeException("!! Couldn't Find Category !!\n");
        } else {
            return this.t_categories.get(index);
        }
    }

    /**
     * Checks if a category with the given name exists in the manager.
     *
     * @param t_catName The name of the category to check.
     * @return true if the category exists, false otherwise.
     */
    public boolean exists(String t_catName) {
        Optional<Category> optionalCategory = t_categories.stream().filter(category -> compareCategoriesByName(category, t_catName)).findFirst();
        return optionalCategory.isPresent();
    }

    /**
     * Checks if the manager is empty.
     *
     * @return true if the manager is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.t_categories.isEmpty();
    }

    /**
     * Clears all categories from the manager.
     */
    public void Clear() {
        if (isEmpty()) {
            System.out.println("!................................ Manger Is Empty ..................................!");
            return;
        } else {
            t_categories.clear();
            System.out.println("!................................ Categories Have Been Cleared ..........................!");
        }
    }

    ////////////////////////////////////////////////////////////////// Helper Functions //////////////////////////////////////////////////////////

    /**
     * Compares a category's name with a given name.
     *
     * @param t_category The category to compare.
     * @param t_catName  The name to compare with.
     * @return true if the category's name is equal to the given name, false otherwise.
     */
    public boolean compareCategoriesByName(Category t_category, String t_catName) {
        return t_category.getName().equals(t_catName);
    }
}
