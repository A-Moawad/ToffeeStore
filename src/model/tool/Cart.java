package model.tool;
import model.categoriesmanger.Product;
import utilities.Utility;

import java.util.*;

/**
 * The Cart class represents a shopping cart. It allows adding, removing, and modifying products in the cart,
 * as well as calculating the total price and managing cart operations.
 */
public class Cart
{

    /* Static Attributes (related to class only and has nothing to do with instances) */

    /**
     * The total count of all carts created.
     */
    private static int s_TotalCartCount;

    /* Instance attributes */
    /**
     * The unique identifier of the cart.
     */
    private String cartId;

    /**
     * The subtotal of the cart, which is the total price of all products in the cart.
     */
    private double subTotal;

    /**
     * The list of products in the cart.
     */
    private ArrayList<Product> goods;

    /**
     * Default constructor that creates a new Cart instance.
     * It initializes the cart ID, subtotal, and the list of products to an empty state.
     */
    public Cart() {
        this.cartId = Utility.generateId("CART", ++s_TotalCartCount);
        this.subTotal = 0;
        this.goods = new ArrayList<>();
    }

    /* Setters and Getters */

    /**
     * Retrieves the ID of the cart.
     *
     * @return the cart ID
     */
    public String getId() {
        return cartId;
    }

    /**
     * Retrieves the list of products in the cart.
     *
     * @return the list of products
     */
    public ArrayList<Product> getGoods() {
        return goods;
    }

    /* Instance Methods */

    /**
     * Adds a product to the cart with the specified quantity.
     * It updates the subtotal and appends the product to the cart.
     *
     * @param t_product the product to add to the cart
     * @param quantity  the quantity of the product to add
     * @return the added product
     */
    public Product addToCart(Product t_product, int quantity)
    {
        t_product.setM_availableAmount(quantity);
        subTotal += (t_product.getM_productPrice() * quantity);
        goods.add(t_product);
        return t_product;
    }

    /**
     * Removes a product from the cart based on the product name.
     * It updates the subtotal and removes the product from the cart.
     *
     * @param t_productName the name of the product to remove
     * @return the removed product
     */
    public Product removeFromCart(String t_productName)
    {
        if (goods.isEmpty())
        {
            throw new RuntimeException("!! Cart is Empty !!");
        }
        else
        {
            for (Product p : goods)
            {
                if (p.getM_productName().equals(t_productName))
                {
                    Product tmp = new Product(p);  // create a copy to return
                    subTotal -= (p.getM_productPrice() * p.getM_availableAmount());
                    goods.remove(p);
                    return tmp;
                }
            }
            System.out.println("!! No such product");
            return new Product();
        }
    }

    /**
     * Increases the amount of a product in the cart based on the product name.
     * It updates the subtotal and returns whether the operation was successful.
     *
     * @param t_productName the name of the product to increase the amount
     * @param t_amount      the amount to increase
     * @return true if the increase operation was successful, false otherwise
     */
    public boolean increaseProductAmount(String t_productName, int t_amount)
    {
        if (t_amount >= 0)
        {
            Map.Entry<Boolean, Product> existsAndProduct = exists(t_productName);
            if (existsAndProduct.getKey())
            {
                existsAndProduct.getValue().increaseAmount(t_amount);
                subTotal += (existsAndProduct.getValue().getM_productPrice() * t_amount);
                return true;
            }
            else
            {
                System.out.println("!! No Such Product: " + t_productName + " Exists In Cart !!");
            }
        }
        else
        {
            System.out.println("!! Invalid Amount !!");
        }
        return false;
    }

    /**
     * Decreases the amount of a product in the cart based on the product name.
     * It updates the subtotal and returns whether the operation was successful.
     *
     * @param productName the name of the product to decrease the amount
     * @param amount      the amount to decrease
     * @return true if the decrease operation was successful, false otherwise
     */
    public boolean decreaseProductAmount(String productName, int amount) {
        boolean hasDecreased = false;
        if (amount >= 0)
        {
            Map.Entry<Boolean, Product> existsAndProduct = exists(productName);
            if (existsAndProduct.getKey())
            {
                if (existsAndProduct.getValue().decreaseAmount(amount))
                {
                    subTotal -= (existsAndProduct.getValue().getM_productPrice() * amount);
                    hasDecreased = true;
                }
            }
            else
            {
                System.out.println("!! No Such Product: " + productName + " Exists In Cart !!");
            }
        }
        else
        {
            System.out.println("!! Invalid Amount !!");
        }
        return hasDecreased;
    }

    /**
     * Checks if a product exists in the cart based on the product name.
     *
     * @param t_productName the name of the product to check
     * @return a Map.Entry where the key is true if the product exists, false otherwise,
     * and the value is the product object if it exists, null otherwise
     */
    public Map.Entry<Boolean, Product> exists(String t_productName)
    {
        for (Product p : goods)
        {
            if (p.getM_productName().equals(t_productName))
            {
                return new AbstractMap.SimpleEntry<Boolean, Product>(true, p);
            }
        }
        return new AbstractMap.SimpleEntry<Boolean, Product>(false, null);
    }

    /**
     * Calculates the total price of the products in the cart.
     *
     * @return the total price of the products in the cart
     */
    public double totalPrice()
    {
        return this.subTotal;
    }

    /**
     * Clears the cart by removing all the products.
     */
    public void clear()
    {
        if (isEmpty())
        {
            System.out.println("!! Cart Is Empty !!");
        }
        else
        {
            goods.clear();
        }
    }

    /**
     * Prints the details of the cart, including the cart ID, the products, and the total price.
     */
    public void print()
    {
        if (isEmpty())
        {
            System.out.println("--------------------------- Cart Is Empty -------------------");
            return;
        }
        else
        {
            System.out.println("-------------------------- Cart --------------------------");
            System.out.println("CartId: " + getId() + "\n");
            for (Product product : goods) {
                product.print();
            }
            System.out.println("\nTotalPrice: " + this.subTotal + "$");
        }
    }

    /**
     * Checks if the cart is empty.
     *
     * @return true if the cart is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return this.goods.isEmpty();
    }

    /**
     * Retrieves the size of the cart, indicating the number of products in it.
     *
     * @return the size of the cart
     */
    public int size()
    {
        return this.goods.size();
    }
}
