import apps.CustomerApp;
import apps.ToffeeStore;
import model.categoriesmanger.CategoriesManger;
import model.categoriesmanger.Category;
import model.payment.Payment;
import model.tool.Cart;
import model.users.Customer;
import model.users.details.Order;
import model.users.details.ShippingInformation;
import utilities.Utility;
import services.OTP.otpSystem;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        Category electorincs = new Category("Electronics");

        electorincs.addProduct("T.V", "", 5000, 10);
        electorincs.addProduct("Sweeper", "", 5000, 10);
        electorincs.addProduct("Laptop", "", 20000, 5);

        Category Dairy = new Category("Dairy");
        Dairy.addProduct("Milik", "", 23, 20);
        Dairy.addProduct("Butter", "", 250, 40);
        Dairy.addProduct("Cheese", "", 40, 20);


        ArrayList<Category> cats = new ArrayList<Category>(10);
        cats.add(Dairy);
        cats.add(electorincs);

        CategoriesManger catManger = new CategoriesManger(cats);
//        catManger.isEmpty();
//        catManger.removeCategoryByName("Dairy");
//        catManger.displayAllCategories();
//        Order o = new Order();
//        Order o2 = new Order();
//        o.print();
//        o2.print();
//
//        Cart cart = new Cart();
//
//        Product p =  electorincs.getProductByName("Sweeper");
//
//        cart.addToCart(p, p.getM_availableAmount());
//        cart.increaseProductAmount("Sweeper", 10);
//        cart.increaseProductAmount("Sweeper", 10);
//        cart.print();
//        Admin admin = new Admin("fady kamal", "01220680646", "01220680646", "popfadykamal151617@gmail.com", catManger);
//        admin.displayInfo();
//        catManger.displayAllCategories();
//        System.out.println("after admin clear");
//        admin.clearCategories();

//        AdminApp adminApp = new AdminApp();
//
//        adminApp.run();

//        ToffeeStore store = new ToffeeStore();
//        store.run();
        ArrayList<Order> orders = new ArrayList<Order>();
        Customer c = new Customer("fady kamal", "24221510", "popfadykamal151617@gmail.com", "01277157747", new Cart(), new Payment(), new ShippingInformation());

        CustomerApp customerApp = new CustomerApp(c, catManger, orders);

        customerApp.run();



    }
}