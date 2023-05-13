package model.tool;
import model.categoriesmanger.Product;
import utilities.Utility;

import java.util.*;

public class Cart {

    /* Static Attributes (related to class only and has nothing to do with instances) */
    private static int s_TotalCartCount;
    private String cartId;
    private double subTotal;
    private ArrayList<Product> goods;

    public Cart() {
        // Default constructor
        this.cartId = Utility.generateId("CART", ++s_TotalCartCount);
        this.subTotal = 0;
        this.goods = new ArrayList<>();
    }


    /* Setters and Getters */
    public String getId() {
        return cartId;
    }

    public ArrayList<Product> getGoods() {
        return goods;
    }

    /* Instance Methods */
    public Product addToCart(Product t_product, int quantity)
    {
        // set product quantity as quantity he wants to buy
        t_product.setM_availableAmount(quantity);

        // add to total price
        subTotal += (t_product.getM_productPrice() * quantity);

        // append to cart
        goods.add(t_product);
        return t_product;
    }

    public Product removeFromCart(String t_productName)
    {
        if (goods.isEmpty())
        {
            throw new RuntimeException("!! Cart is Empty !!");
        }
        else
        {
            // find product
            for (Product p : goods) {
                if (p.getM_productName().equals(t_productName))
                {
                    // remove from cart
                    Product tmp = new Product(p);  // create a copy to return

                    // decrement subTotal
                    subTotal -= (p.getM_productPrice() * p.getM_availableAmount());

                    // remove item from cart
                    goods.remove(p);

                    return tmp;
                }
            }

            System.out.println("!! No such product");
            return new Product();
        }
    }


    public boolean increaseProductAmount(String t_productName, int t_amount)
    {
        // check if valid amount
        if (t_amount >= 0)
        {
            // get entry
            Map.Entry<Boolean, Product> existsAndProduct = exists(t_productName);

            if (existsAndProduct.getKey())
            {
                // increase product amount
                existsAndProduct.getValue().increaseAmount(t_amount);

                // update total price
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

    public boolean decreaseProductAmount(String productName, int amount) {
        // flag
        boolean hasDecreased = false;

        // check if valid amount
        if (amount >= 0) {
            // get Map.Entry
            Map.Entry<Boolean, Product> existsAndProduct = exists(productName);

            if (existsAndProduct.getKey())
            {
                // decrease product amount
                if (existsAndProduct.getValue().decreaseAmount(amount))
                {
                    // update total price
                    subTotal -= (existsAndProduct.getValue().getM_productPrice() * amount);

                    // switch flag
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

        // return flag
        return hasDecreased;
    }


    public Map.Entry<Boolean, Product> exists(String t_productName) {
        for (Product p : goods) {
            if (p.getM_productName().equals(t_productName)) {
                return new AbstractMap.SimpleEntry<Boolean, Product>(true, p);
            }
        }

        return new AbstractMap.SimpleEntry<Boolean, Product>(false, null);
    }

    public double totalPrice()
    {
        return this.subTotal;
    }

    public void clear()
    {
        if (isEmpty()) {
            System.out.println("!! Cart Is Empty !!");
        }
        else
        {
            goods.clear();
        }
    }


    public void print() {
        if (isEmpty()) {
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


    public boolean isEmpty() {
       return this.goods.isEmpty();
    }

    public int size() {
       return this.goods.size();
    }

}
