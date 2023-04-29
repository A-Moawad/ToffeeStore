package model;

import java.util.ArrayList;

public class CartItem {
    private ArrayList<Products>product;
    private int quantity;
    private Customer customer;
    private double totalPrice;

    public CartItem(ArrayList<Products> product, int quantity, Customer customer, double totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    public ArrayList<Products> getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
