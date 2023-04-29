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
            System.out.println("1) Sign Up");
            System.out.println("2) Login");
            System.out.println("3) add item to cart");
            System.out.println("4) remove item from cart");
            System.out.println("5) print all customer");
            System.out.println("6) Display all item in the cart");
            action = scanner.nextInt();
            switch (action){
                case 1:
                    Hmi.storeService.registerCustomer();
                    break;
                case 2:
                    Hmi.storeService.checkLogIn();
                    break;
                case 3:
                    Hmi.addToCart();
                case 5:
                    Hmi.printAllCustomer();
                    break;
            }
        }while(action  != 0);
    }



//    private static void setDataUser(User user){
//        Scanner scanner = new Scanner(System.in);
//
//        if(user instanceof Customer){
//            System.out.println("Please enter customer user name");
//            user.setUserName(scanner.next());
//            System.out.println("Please enter customer email");
//            user.setEmail(scanner.next());
//            System.out.println("Please enter customer password");
//            user.setPassword(scanner.next());
//        }
//        else{
//            System.out.println("Please enter admin user name");
//            user.setUserName(scanner.next());
//            System.out.println("Please enter admin email");
//            user.setEmail(scanner.next());
//            System.out.println("Please enter admin password");
//            user.setPassword(scanner.next());
//        }
//
//    }

//    private static void RegisterNewCustomer(Actions actions){
//        Customer customer = new Customer();
//        if(actions == Actions.CUSTOMER) {
//            Hmi.setDataUser(customer);
//            Hmi.storeService.addCustomer(customer);
//        }
//        else{
//            Admin admin = (Admin) user;
//            Hmi.setDataUser(admin);
//            Hmi.storeService.addAdmin(admin);
//        }
//
//    }

    private static void printAllCustomer(){
        for(Customer customer : Hmi.storeService.getStore().getCustomers()){
            System.out.println(customer.getData());
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
//        System.out.println("enter product quantity");
//        quantity = scanner.nextInt();

        Products product = new Products(name, price);

    }

    private static void displayAllProductsInCart(Customer customer){
        for(CartItem item : Hmi.storeService.getStore().getProducts()){
            if(item.getCustomer() == customer){
                System.out.println("Product Name: " + item.getProduct().getName());
                System.out.println("Product Price: " + item.getProduct().getPrice());
                System.out.println("Quantity: " + item.getQuantity());
                System.out.println("Total Price: " + item.getTotalPrice());
                System.out.println();
            }
        }
    }

//    private static User findUserByName(Actions action){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please enter the name");
//        String name = scanner.next();
//        User user = null;
//        if(action == Actions.CUSTOMER){
//            user = Hmi.storeService.getCustomerByName(name);
//        }
//        else{
//            user = Hmi.storeService.getCustomerByName(name);
//        }
//        return user;
//    }

}
