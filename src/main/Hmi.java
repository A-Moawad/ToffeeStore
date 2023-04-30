package main;

import enums.Actions;
import model.*;
import service.StoreService;

import java.util.Scanner;

public class Hmi { // to manage the system
    private static StoreService storeService;

    private static Store createStore(){
        Store store = new Store();
        store.setName("Toffee Store");
        Hmi.storeService = new StoreService(store);
        return store;
    }

    public static void start(){
        Scanner scanner = new Scanner(System.in);
        Store store = Hmi.createStore();

        System.out.println("** Welcom to " + store.getName() + " in " + store.getName() + " **");
        int action;

        do{
            System.out.println("What do you want to do ?");
            System.out.println("1) Sign Up as a customer");
            System.out.println("2) Login as customer");
            System.out.println("3) Sign Up as an Admin");
            System.out.println("4) Login as Admin");
            System.out.println("5) add item to cart");
            System.out.println("6) remove item from cart");
            System.out.println("7) print all customer");
            System.out.println("8) print all admins");
            System.out.println("9) Display all item in the cart");
            action = scanner.nextInt();
            switch (action){
                case 1:
                    Hmi.storeService.registerCustomer(); // done
                    break;
                case 2:
                    Hmi.storeService.checkLogInCustomer(); // done
                    break;
                case 3:
                    Hmi.storeService.registerAdmin();  // done
                    break;
                case 4:
                    Hmi.storeService.checkLogInAdmin();  // done
                    break;
                case 5:
                    Hmi.addToCart();
                    break;
                case 7:
                    Hmi.printAllCustomer();  // done
                    break;
                case 8:
                    Hmi.printAllAdmin();      // done
                    break;
                case 9:
                    Hmi.displayCartInfo(); // not work
                    break;
            }

        }while(action  != 0);
    }



    private static void printAllCustomer(){
        for(Customer customer : Hmi.storeService.getStore().getCustomers()){
            System.out.println(customer.getData());
        }
    }

    private static void printAllAdmin(){
        for(Admin admin : Hmi.storeService.getStore().getAdmins()){
            System.out.println(admin.getData());
        }
    }

    private static void addToCart(){
        Scanner scanner = new Scanner(System.in);
        String name;
        double price;
        int quantity;
        System.out.println("enter product name");
        name = scanner.next();
        System.out.println("enter product price");
        price = scanner.nextDouble();
        System.out.println("enter product quantity");
        quantity = scanner.nextInt();

        Products product = new Products(name, price, quantity);

    }


    private static void displayAllProductsInCart(Customer customer){
        for(Products item : Hmi.storeService.getStore().getProducts()){
            if(item.getCart().getCustomer() == customer){
                System.out.println("Product Name: " + item.getName());
                System.out.println("Product Price: " + item.getPrice());
                System.out.println("Quantity: " + item.getQuantity());
                System.out.println("Total Price: " + item.getCart().getTotalPrice());
                System.out.println();
            }
        }
    }


    private static void displayCartInfo(){
        Customer customer = Hmi.storeService.loginCustomer();
        if(customer != null){
            displayAllProductsInCart(customer);
        }
        else{
            System.out.println("Invalid username or password");
        }
    }


}
