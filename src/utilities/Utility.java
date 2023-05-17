package utilities;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.*;
import static java.util.Map.entry;

/**
 * This a utility class that holds helper functions only.
 */
public class Utility
{/**
 * Generates an ID by combining the object type, object count, and a prefix.
 *
 * @param objectType  The type of the object.
 * @param objectCount The count of the object.
 * @return The generated ID in the format "FCAI-{objectType}-{objectCount}".
 */
public static String generateId(String objectType, int objectCount) {
    return "FCAI-" + objectType.toUpperCase() + "-" + objectCount;
}

    /**
     * A map of prompts for the customer application menu.
     * The keys represent the menu options, and the values represent the corresponding prompt message.
     */
    public final static Map<String, String> CUSTOMER_APP_PROMPTS = Map.ofEntries(
            entry("Main", "1- Select a product\n2- Orders\n3- Cart\n4- Profile\n5- Log Out\n\nPlease enter a choice: "),
            entry("Orders", "1- Display orders\n2- Modify order\n3- Clear orders\n4- Back to previous menu\n\nPlease enter a choice: "),
            entry("Payment", "1- Loyalty points\n2- Ewallet\n3- On delivery\n4- Back to previous menu\n\nHow would you like to pay: "),
            entry("Cart", "1- Display cart\n2- Remove item from cart\n3- CheckOut items\n4- ClearCart\n5- Back to previous menu\n\nPlease enter a choice: "),
            entry("ModifyOrders", "1- Cancel order\n2- Return order\n3- Remove order\n4- Back to previous menu\n\nPlease enter a choice: "),
            entry("PersonalInfo", "1- Display personal information\n2- Update personal information\n3- Update financials\n4- Update shipping information\n5- Back to previous menu\n\nPlease enter a choice: "),
            entry("personalUpdates", "1- User name\n2- Phone number\n3- Email\n\nPlease select a choice to update: ")
    );

    /**
     * A map of prompts for the admin application menu.
     * The keys represent the menu options, and the values represent the corresponding prompt message.
     */
    public final static Map<String, String> ADMIN_APP_PROMPTS = Map.ofEntries(
            entry("Main", "1- Manage Categories\n2- Manage products\n3- Manage customers\n4- Manage orders\n5- Personal Information\n6- Log Out\n\nPlease enter a choice: "),
            entry("ModifyCategories", "1- Display all categories\n2- Add new category\n3- Delete category\n4- Clear all categories\n5- Back to previous menu\n\nPlease enter a choice: "),
            entry("ModifyProducts", "1- Add new product to certain category\n2- Delete product\n3- Update product\n4- Clear all products\n5- Back to previous menu\n\nPlease enter a choice: "),
            entry("ModifyOrders", "1- Display all orders\n2- Delete order\n3- Clear all orders\n4- Back to previous menu\n\nPlease enter a choice: "),
            entry("ModifyCustomers", "1- Display all customers\n2- Delete a customer\n3- Clear all customers\n4- Back to previous menu\n\nPlease enter a choice: "),
            entry("PersonalInfo", "1- Display personal information \n2- Update Information\n3- Back to previous menu\n\nPlease enter a choice: "),
            entry("UpdateAdminInformation", "1- User name \n2- Phone number\n3- Email\n4- Back to previous menu\n\nPlease enter a choice: "),
            entry("ProductUpdates", "1- Product name \n2- Product description\n3- Product available amount\n4- Product price\n5- Back to previous menu\n\nPlease enter a choice: ")
    );

    /**
     * A map of prompts for the Toffee Store application.
     * The keys represent the menu options, and the values represent the corresponding prompt message.
     */
    public final static Map<String, String> TOFFEE_STORE_PROMPTS = Map.ofEntries(
            entry("Main", "1- Login as admin\n2- Login as customer\n3- SignUp\n4- Exit\n\nPlease enter a choice: ")
    );

    /**
     * The separator used for formatting messages.
     */
    public static String sep = "############################";

    /**
     * Prints a formatted message to the console.
     *
     * @param msg         The message to be printed.
     * @param takingInput Specifies whether the message expects user input or not.
     */
    public static void printFormatedMessage(String msg, boolean takingInput) {
        System.out.println(sep);

        if (takingInput) {
            System.out.print(msg);
        } else {
            System.out.println(msg);
        }
    }

    /**
     * Validates a name by checking if it meets the required format.
     *
     * @param name The name to be validated.
     * @return {@code true} if the name is valid, {@code false} otherwise.
     */
    public static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]{2,}(?: [a-zA-Z]+){0,3}$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /**
     * Validates a phone number by checking if it meets the required format.
     *
     * @param phoneNumber The phone number to be validated.
     * @return {@code true} if the phone number is valid, {@code false} otherwise.
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^(010|011|012|015)\\d{8}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    /**
     * Validates an address. This method always returns {@code true}.
     *
     * @param address The address to be validated.
     * @return {@code true} always.
     */
    public static boolean isValidAddress(String address) {
        return true;
    }

    /**
     * Validates an email by checking if it meets the required format.
     *
     * @param email The email to be validated.
     * @return {@code true} if the email is valid, {@code false} otherwise.
     */
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9_]+@(gmail|hotmail)\\.com");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Validates a city by checking if it meets the required format.
     *
     * @param city The city to be validated.
     * @return {@code true} if the city is valid, {@code false} otherwise.
     */
    public static boolean isValidCity(String city) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$");
        Matcher matcher = pattern.matcher(city);
        return matcher.matches();
    }

    /**
     * Validates a customer ID by checking if it meets the required format.
     *
     * @param t_customerId The customer ID to be validated.
     * @return {@code true} if the customer ID is valid, {@code false} otherwise.
     */
    public static boolean isValidCustomerId(String t_customerId) {
        Pattern pattern = Pattern.compile("^FCAI-CUSTOMER-\\d+$");
        Matcher matcher = pattern.matcher(t_customerId);
        return matcher.matches();
    }

    /**
     * Validates an order ID by checking if it meets the required format.
     *
     * @param t_orderId The order ID to be validated.
     * @return {@code true} if the order ID is valid, {@code false} otherwise.
     */
    public static boolean isValidOrderId(String t_orderId) {
        Pattern pattern = Pattern.compile("^FCAI-ORDER-\\d+$");
        Matcher matcher = pattern.matcher(t_orderId);
        return matcher.matches();
    }

    /**
     * Validates an OTP (One-Time Password) by checking if it meets the required format.
     *
     * @param otp The OTP to be validated.
     * @return {@code true} if the OTP is valid, {@code false} otherwise.
     */
    public static boolean isValidOTP(String otp) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(otp);
        return matcher.matches();
    }

    /**
     * Validates a price by checking if it meets the required format.
     *
     * @param price The price to be validated.
     * @return {@code true} if the price is valid, {@code false} otherwise.
     */
    public static boolean isValidPrice(String price) {
        Pattern pattern = Pattern.compile("^\\d+\\.?\\d+$");
        Matcher matcher = pattern.matcher(price);
        return matcher.matches();
    }
    /**
     * Prompts the user to enter a valid name and returns it.
     *
     * @param forRenewal Determines if the name is for renewal or not.
     * @return The valid name entered by the user.
     */
    public static String getValidNameFromUser(boolean forRenewal) {
        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);
        String name;

        // Prompt the user to enter the name
        System.out.print((forRenewal) ? "New user name: " : "User name: ");
        name = scanner.nextLine();

        // Validate the entered name
        while (!isValidName(name)) {
            System.out.println("###########################");
            System.out.println("!! Invalid Name !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Valid Name (e.g., Fady Kamal): ");
            name = scanner.nextLine();
        }

        return name;
    }

    /**
     * Prompts the user to enter a valid email and returns it.
     *
     * @param forRenewal Determines if the email is for renewal or not.
     * @return The valid email entered by the user.
     */
    public static String getValidEmailFromUser(boolean forRenewal) {
        Scanner scanner = new Scanner(System.in);
        String email;

        System.out.print((forRenewal) ? "New email: " : "Email: ");
        email = scanner.nextLine();

        while (!isValidEmail(email)) {
            System.out.println("###########################");
            System.out.println("!! Invalid Email !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Valid Email (e.g., abc@gmail.com): ");
            email = scanner.nextLine();
        }

        return email;
    }

    /**
     * Prompts the user to enter a valid phone number and returns it.
     *
     * @param forRenewal Determines if the phone number is for renewal or not.
     * @return The valid phone number entered by the user.
     */
    public static String getValidPhoneNumber(boolean forRenewal) {
        Scanner scanner = new Scanner(System.in);
        String phoneNumber;

        System.out.print((forRenewal) ? "New phone number: " : "Phone number: ");
        phoneNumber = scanner.nextLine();

        while (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("###########################");
            System.out.println("!! Invalid PhoneNumber !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Valid Phone Number (e.g., 01212345678): ");
            phoneNumber = scanner.nextLine();
        }

        return phoneNumber;
    }

    /**
     * Prompts the user to enter a valid price and returns it.
     *
     * @param forRenewal Determines if the price is for renewal or not.
     * @return The valid price entered by the user.
     */
    public static double getValidPrice(boolean forRenewal) {
        Scanner scanner = new Scanner(System.in);
        String price;

        System.out.print((forRenewal) ? "New price: " : "Price per unit: ");
        price = scanner.nextLine();

        while (!isValidPrice(price)) {
            System.out.println("###########################");
            System.out.println("!! Invalid Price !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Valid Price (e.g., 45.5): ");
            price = scanner.nextLine();
        }

        return Double.parseDouble(price);
    }

    /**
     * Prompts the user to enter a valid address and returns it.
     *
     * @param forRenewal Determines if the address is for renewal or not.
     * @return The valid address entered by the user.
     */
    public static String getValidAddressFromUser(boolean forRenewal) {
        Scanner scanner = new Scanner(System.in);
        String address;

        System.out.print((forRenewal) ? "New address: " : "Address: ");
        address = scanner.nextLine();

        while (!isValidAddress(address)) {
            System.out.println("###########################");
            System.out.println("!! Invalid Address !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Valid Address: ");
            address = scanner.nextLine();
        }

        return address;
    }

    /**
     * Prompts the user to enter a valid city and returns it.
     *
     * @return The valid city entered by the user.
     */
    public static String getValidCityFromUser() {
        Scanner scanner = new Scanner(System.in);
        String city;

        System.out.print("City: ");
        city = scanner.nextLine();

        while (!isValidCity(city)) {
            System.out.println("###########################");
            System.out.println("!! Invalid City !!");
            System.out.println("###########################");

            System.out.print("Please Enter A City (e.g., Cairo): ");
            city = scanner.nextLine();
        }

        return city;
    }

    /**
     * Prompts the user to enter a valid password and returns it.
     *
     * @return The valid password entered by the user.
     */
    public static String getPasswordFromUser() {
        Scanner scanner = new Scanner(System.in);
        String password;

        System.out.print("Password: ");
        password = scanner.nextLine();

        while (password.isEmpty()) {
            System.out.println("###########################");
            System.out.println("!! Invalid Password !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Password: ");
            password = scanner.nextLine();
        }
        return password;
    }

    /**
     * Prompts the user to enter a valid amount and returns it.
     *
     * @return The valid amount entered by the user.
     */
    public static double getValidAmountOfNumberFromUser() {
        Scanner scanner = new Scanner(System.in);
        double amount;

        System.out.print("Amount: ");
        amount = scanner.nextDouble();

        while (amount < 0) {
            System.out.println("###########################");
            System.out.println("!! Invalid !!");
            System.out.println("###########################");
            System.out.print("Please Enter A Valid Amount (e.g., 50): ");
            amount = scanner.nextDouble();
        }
        return amount;
    }

    /**
     * Prompts the user to enter a valid OTP and returns it.
     *
     * @return The valid OTP entered by the user.
     */
    public static String getValidOTPFromUser() {
        Scanner scanner = new Scanner(System.in);
        String otp;

        System.out.print("OTP: ");
        otp = scanner.nextLine();

        while (!isValidOTP(otp)) {
            System.out.println("###########################");
            System.out.println("!! Invalid OTP, Must be 10 Digits !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Valid OTP: ");
            otp = scanner.nextLine();
        }
        return otp;
    }

    /**
     * Prompts the user to enter a valid category name and returns it.
     *
     * @param forRenewal Determines if the category name is for renewal or not.
     * @return The valid category name entered by the user.
     */
    public static String getValidCategoryNameFromUser(boolean forRenewal) {
        Scanner scanner = new Scanner(System.in);
        String category;

        System.out.print((forRenewal) ? "New name: " : "Category: ");
        category = scanner.nextLine();

        while (!isValidName(category)) {
            System.out.println("###########################");
            System.out.println("!! Invalid Name !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Valid Category (e.g., Dairy): ");
            category = scanner.nextLine();
        }
        return category;
    }

    /**
     * Prompts the user to enter a valid product name and returns it.
     *
     * @param forRenewal Determines if the product name is for renewal or not.
     * @return The valid product name entered by the user.
     */
    public static String getValidProductNameFromUser(boolean forRenewal) {
        Scanner scanner = new Scanner(System.in);
        String product;

        System.out.print((forRenewal) ? "New name: " : "Product name: ");
        product = scanner.nextLine();

        while (!isValidName(product)) {
            System.out.println("###########################");
            System.out.println("!! Invalid Name !!");
            System.out.println("###########################");

            System.out.print("Please Enter A Valid Product (e.g., Milk chocolate): ");
            product = scanner.nextLine();
        }
        return product;
    }

    public static void printWelcomingMessage(String message) {
        System.out.println(sep);
        System.out.println(message);
        System.out.println(sep);
    }

    /**
     * Converts a string to title case.
     *
     * @param input The string to be converted.
     * @return The converted string in title case.
     */
    public static String convertToTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            } else {
                c = Character.toLowerCase(c);
            }

            titleCase.append(c);
        }
        return titleCase.toString();
    }

}
