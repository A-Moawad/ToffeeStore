package model;

import java.util.ArrayList;

public class Store {
    private String name; // name of store
    private ArrayList<Customer> customers;
    private ArrayList<Admin> admins;
    private ArrayList<Products>products;

    private void initObject(){
        this.customers = new ArrayList<Customer>();
        this.admins = new ArrayList<Admin>();
        this.products = new ArrayList<Products>();

    }
    public Store(){
       this.initObject();
    }

    public Store(String name){
        this.name = name;
        this.initObject();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }


    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }
}
