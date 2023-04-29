package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private ArrayList<Products>products;
    private int quantity; // num of all products
    private Customer customer;

    public Cart(){
        this.products = new ArrayList<Products>();
    }

    public Cart(ArrayList<Products> products, int quantity, Customer customer) {
        this.products = products;
        this.quantity = quantity;
        this.customer = customer;
    }

    public ArrayList<Products> getProducts(){
        return products;
    }

    public void addProduct(Products product){
        products.add(product);
    }

    public void removeProduct(Products product){
        products.remove(product);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    public void displayCustomerItems(Customer customer) {
//        Cart cart = customer.getCart();
//        List<>
//        for (Products item : customer.getCart().getProducts()) {
//            if (store.getCustomers() == customer) {
//                System.out.println("Item: " + item.getProducts().getName());
//                System.out.println("Quantity: " + item.getQuantity());
//                System.out.println("Total Price: " + item.getTotalPrice());
//                System.out.println();
//            }
//        }
//    }



    public double getTotalPrice(){
        double totalPrice = 0;
        for(Products product : products){
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

}
