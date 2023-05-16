package model.categoriesmanger;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.*;
public class CategoriesManger
{
    ArrayList<Category> t_categories;


    public CategoriesManger()
    {
        this.t_categories = new ArrayList<Category>(100);
    }
    public CategoriesManger(ArrayList<Category> t_categories)
    {
        this.t_categories = t_categories;
    }

    /* Setters And Getters */

    public void setT_categories(ArrayList<Category> t_categories) {
        this.t_categories = t_categories;
    }

    public ArrayList<Category> getT_categories() {
        return t_categories;
    }

    public int getNumberOfCategories()
    {
        return this.t_categories.size();
    }

    public Category addCategory(String categoryName, ArrayList<Product> products)
    {
        Category newCategory = new Category(categoryName, products);

        this.t_categories.add(newCategory);

        return newCategory;
    }

    public Category addCategory(String categoryName)
    {
        Category newCategory = new Category(categoryName);

        this.t_categories.add(newCategory);

        return newCategory;
    }

    public Category removeCategoryByName(String catName)
    {
        // check if exists
        ListIterator<Category> it = t_categories.listIterator();
        while (it.hasNext()) {
            Category p = it.next();
            if (compareCategoriesByName(p, catName))
            {
                // if product found store it in temp, then remove it
                it.remove();

                // return the erased one
                return p;
            }
        }

        System.out.println("Couldn't Find Product with name: " + catName + " To Erase............!");
        return new Category();
    }

    public void displayAllCategories() {
        if (isEmpty()) {
            System.out.println("!................................ Manger Is Empty ..................................!");
        } else
        {
            // iterate over them and print each
            for (Category category : t_categories)
            {
                category.displayAllProducts();
            }
        }
    }

    public int searchCategoryByName(String t_catName) {
        // check if exists and matches given name
        Optional<Category> result = t_categories.stream().filter(c -> compareCategoriesByName(c, t_catName)).findFirst();

        if (result.isPresent()) {
            // if found then return its index
            return t_categories.indexOf(result.get());
        } else {
            System.out.printf("! Couldn't Find Category Named: %s !%n", t_catName);
            // return empty cat
            return -1;
        }
    }

    public AbstractMap.SimpleEntry<Boolean, Product> GetProductByNameThroughAllCat(String productName) {
        for (Category category : t_categories) {
            for (Product product : category.getAllProducts()) {
                if (product.getM_productName().equals(productName)) {
                    return new AbstractMap.SimpleEntry<Boolean, Product>(true, product);
                }
            }
        }
        // Product not found, return a reference to default-constructed product
        Product defaultProduct = new Product();
        return new AbstractMap.SimpleEntry<Boolean, Product>(false, defaultProduct);
    }

    public Category getCategoryByName(String t_catName) {
        int index = searchCategoryByName(t_catName);

        if (index == -1)
        {
            throw new RuntimeException("!! Couldn't Find Category !!\n");
        }
        else
        {
            return this.t_categories.get(index);
        }
    }

    public boolean exists(String t_catName) {
        Optional<Category> optionalCategory = t_categories.stream().filter(category -> compareCategoriesByName(category, t_catName)).findFirst();
        return optionalCategory.isPresent();
    }

    public boolean isEmpty()
    {
        return this.t_categories.isEmpty();
    }
    public void Clear()
    {
        if (isEmpty())
        {
            System.out.println("!................................ Manger Is Empty ..................................!");
            return;
        } else
        {
            // clear it
            t_categories.clear();
            System.out.println("!................................ Categories Have Been Cleared ..........................!");
        }
    }

    ////////////////////////////////////////////////////////////////// Helper Functions //////////////////////////////////////////////////////////
    public boolean compareCategoriesByName(Category t_category, String t_catName) {
        return t_category.getName().equals(t_catName);
    }

}
