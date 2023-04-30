package service;

import model.Admin;
import model.Customer;
import model.Products;
import model.Store;

import java.util.Scanner;


public class StoreService {
    Store store;

    public StoreService(Store store){
        this.store = store;
    }

    public Store getStore(){
        return store;
    }

    public void registerCustomer() {
        Scanner scanner = new Scanner(System.in);
        String name,  email,  password;
        System.out.println("enter customer name");
        name = scanner.next();
        System.out.println("enter customer email");
        email = scanner.next();
        System.out.println("enter customer password");
        password = scanner.next();
        Customer newCustomer = new Customer(name, email, password);
        this.store.getCustomers().add(newCustomer);
    }

    public Customer loginCustomer(){
        Scanner scanner = new Scanner(System.in);
        String name, password;
        System.out.println("enter customer name");
        name = scanner.next();
        System.out.println("enter customer password");
        password = scanner.next();
        for(Customer customer : store.getCustomers()){
            if(customer.getUserName().equals(name) && customer.checkPassword(password)){
                return customer;
            }
        }
        return null;
    }
    public void checkLogInCustomer() {
        Customer customer = loginCustomer();
        if (customer != null) {
            System.out.println("Successful Login");
        } else {
            System.out.println("Invalid username or password");
        }
    }

    /////////////////////////////
    public void registerAdmin() {
        Scanner scanner = new Scanner(System.in);
        String name,  email,  password;
        System.out.println("enter admin name");
        name = scanner.next();
        System.out.println("enter admin email");
        email = scanner.next();
        System.out.println("enter admin password");
        password = scanner.next();
        Admin newAdmin = new Admin(name, email, password);
        this.store.getAdmins().add(newAdmin);
    }

    public Admin loginAdmin(){
        Scanner scanner = new Scanner(System.in);
        String name, password;
        System.out.println("enter admin name");
        name = scanner.next();
        System.out.println("enter admin password");
        password = scanner.next();
        for(Admin admin : store.getAdmins()){
            if(admin.getUserName().equals(name) && admin.checkPassword(password)){
                return admin;
            }
        }
        return null;
    }
    public void checkLogInAdmin() {
        Admin admin = loginAdmin();
        if (admin != null) {
            System.out.println("Successful Login");
        } else {
            System.out.println("Invalid username or password");
        }
    }




    public void addCustomer(Customer customer){
        this.store.getCustomers().add(customer);
    }

    public void addAdmin(Admin admin){
        this.store.getAdmins().add(admin);
    }

    public Customer getCustomerByName(String name){
        for(Customer customer : this.store.getCustomers()){
            if(customer.getUserName().toUpperCase().equals(name.toUpperCase())){
                return customer;
            }
        }
        return null;
    }

}

