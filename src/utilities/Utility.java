package utilities;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.*;
import static java.util.Map.entry;

public class Utility {
    public  static String generateId(String objectType, int objectCount)
    {
        return ("FCAI-" + objectType.toUpperCase() + "-" + objectCount);
    }

    public final static Map<String, String> CUSTOMER_APP_PROMPTS = Map.ofEntries(
            entry("Main", "1- Select a product\n2- Orders\n3- Cart\n4- Profile\n5- Log Out\n\nPlease enter a choice: "),
            entry("Orders", "1- Display orders\n2- Cancel order\n3- Clear orders\n4- Back to previous menu\n\nPlease enter a choice: "),
            entry("Payment", "1- Loyalty points\n2- Ewallet\n3- On delivery\n4- Back to previous menu\n\nHow would you like to pay: "),
            entry("Cart", "1- Display cart\n2- Remove item from cart\n3- CheckOut items\n4- ClearCart\n5- Back to previous menu\n\nPlease enter a choice: ")
    );


    public final static Map<String, String> ADMIN_APP_PROMPTS = Map.ofEntries(
            entry("Main", "1- Manage Categories\n2- Manage products\n3- Manage customers\n4- Manage orders\n5- Personal Information\n6- Log Out\n\nPlease enter a choice: "),
            entry("ModifyCategories", "1- Display all categories\n2- Add new category\n3- Delete category\n4- Clear all categories\n5- Back to previous menu\n\nPlease enter a choice: "),
            entry("ModifyProducts", "1- Add new product to certain category\n2- Delete product\n3- Update product\n4- Clear all products\n5- Back to previous menu\n\nPlease enter a choice: "),
            entry("ModifyOrders", "1- Display all orders\n2- Change status of order\n3- Delete order\n4- Clear all orders\n5- Back to previous menu\n\nPlease enter a choice: "),
            entry("ModifyCustomers", "1- Display all customers\n2- Delete a customer\n3- Clear all customers\n4- Back to previous menu\n\nPlease enter a choice: "),
            entry("PersonalInfo", "1- Display personal information \n2- Update Information\n3- Back to previous menu\n\nPlease enter a choice: ")
    );

    public final static Map<String, String> TOFFEE_STORE_PROMPTS = Map.ofEntries(
            entry("Main", "1- Login as admin\n2- Login as customer\n3- SignUp\n4- Exit\n\nPlease enter a choice: ")
    );

    public static  String sep = "############################";

    public static void printFormatedMessage(String msg, boolean takingInput)
    {
        System.out.println(sep);

        if (takingInput)
        {
            System.out.print(msg);
        }
        else
        {
            System.out.println(msg);

        }
    }


    ////////////////////////////////////// Validators /////////////////////////////////////////
    public static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]{2,}(?: [a-zA-Z]+){0,3}$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^(010|011|012|015)\\d{8}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean isValidAddress(String address) {
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    public static boolean isValidEmail(String  email)
    {
        Pattern pattern = Pattern.compile("[A-Za-z0-9_]+@(gmail|hotmail)\\.com");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidCity(String city)
    {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$");
        Matcher matcher = pattern.matcher(city);
        return matcher.matches();
    }


    public static boolean isValidCustomerId(String t_customerId) {
        Pattern pattern = Pattern.compile("^FCAI-CUSTOMER-\\d+$");
        Matcher matcher = pattern.matcher(t_customerId);
        return matcher.matches();
    }
    public static boolean isValidOrderId(String t_orderId) {
        Pattern pattern = Pattern.compile("^FCAI-ORDER-\\d+$");
        Matcher matcher = pattern.matcher(t_orderId);
        return matcher.matches();
    }

    public static boolean isValidOTP(String otp) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(otp);
        return matcher.matches();
    }


    /////////////////////////////////////////////////////// Takers //////////////////////////////////////////
    public static String getValidNameFromUser() {
        Scanner scanner = new Scanner(System.in);
        String name;

        // take input from user
        System.out.print("user name: ");
        name = scanner.nextLine();

        while (!isValidName(name)) {
            System.out.println("###########################");
            System.out.println("!! Invalid Name !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Valid Name  (e.g, Fady Kamal) : ");
            name = scanner.nextLine();
        }

        return name;
    }
    public static String getValidEmailFromUser()
    {
        Scanner scanner = new Scanner(System.in);
        String email;

        // take input from user
        System.out.print("email: ");
        email = scanner.nextLine();

        while (!isValidEmail(email)) {
            System.out.println("###########################");
            System.out.println("!! Invalid Email !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Valid Email  (e.g, abc@gmail.com) : ");
            email = scanner.nextLine();
        }

        return email;
    }

    public static String getValidPhoneNumber()
    {
        Scanner scanner = new Scanner(System.in);
        String phoneNumber;

        // take input from user
        System.out.print("phone number: ");
        phoneNumber = scanner.nextLine();

        while (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("###########################");
            System.out.println("!! Invalid PhoneNumber !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Valid Phone Number (e.g, 01212345678) : ");
            phoneNumber = scanner.nextLine();
        }

        return phoneNumber;
    }
    public static String getValidAddressFromUser()
    {
        Scanner scanner = new Scanner(System.in);
        String address;

        // take input from user
        System.out.print("address: ");
        address = scanner.nextLine();

        while (!isValidName(address)) {
            System.out.println("###########################");
            System.out.println("!! Invalid Address !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Valid Address : ");
            address = scanner.nextLine();
        }

        return address;
    }
    public static String getValidCityFromUser()
    {
        Scanner scanner = new Scanner(System.in);
        String city;

        // take input from user
        System.out.print("city: ");
        city = scanner.nextLine();

        while (!isValidCity(city)) {
            System.out.println("###########################");
            System.out.println("!! Invalid City !!");
            System.out.println("###########################");

            System.out.print("Please Enter A City (e.g, Cairo) : ");
            city = scanner.nextLine();
        }

        return city;
    }

    public static String getPasswordFromUser()
    {
        Scanner scanner = new Scanner(System.in);
        String password;

        // take input from user
        System.out.print("password: ");
        password = scanner.nextLine();

        while (password.isEmpty())
        {
            System.out.println("###########################");
            System.out.println("!! Invalid !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Password : ");
            password = scanner.nextLine();
        }
        return password;
    }

    public static double getValidAmountOfNumberFromUser()
    {
        Scanner scanner = new Scanner(System.in);
        double amount;

        // take input from user
        System.out.print("Amount: ");
        amount = scanner.nextDouble();

        while (amount <= 0)
        {
            System.out.println("###########################");
            System.out.println("!! Invalid !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Valid Amount (e.g, 50): ");
            amount = scanner.nextDouble();
        }
        return amount;
    }

    public static String getValidOTPFromUser()
    {
        Scanner scanner = new Scanner(System.in);
        String otp;

        // take input from user
        System.out.print("OTP: ");
        otp = scanner.nextLine();

        while (!isValidOTP(otp))
        {
            System.out.println("###########################");
            System.out.println("!! Invalid OTP, Must be 10 Digits !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Valid OTP: ");
            otp = scanner.nextLine();
        }
        return otp;
    }



    public static void printWelcomingMessage(String s) {
        System.out.println(sep);
        System.out.println(s);
        System.out.println(sep);
    }


}