package model.users;

/**
 * This class simulates user of toffee system, and base class for costumer and user
 */
public class User {

    private String userName;
    private String password;
    private String email;
    private String phoneNumber;


    /**
     * Default constructor of User class.
     */
    public User() {}

    /**
     * Parametrized constructor, initializes User class instance attributes.
     * @param t_name User name
     * @param t_password User password
     * @param t_phoneNumber User phone number
     * @param t_email User email
     */
    public User(String t_name, String t_password, String t_phoneNumber, String t_email) {
        userName = t_name;
        password = t_password;
        phoneNumber = t_phoneNumber;
        email = t_email;
    }

    /**
     * A setter methods that sets instance attribute (username) via given name.
     * @param t_name username used in initializing instance attribute (username).
     */
    public void setUserName(String t_name) {
        userName = t_name;
    }

    /**
     * A getter method that returns User username
     * @return User username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * A setter methods that sets instance attribute (password) via given password.
     * @param t_password The given password
     */
    public void setPassword(String t_password) {
        password = t_password;
    }

    /**
     * A getter method that returns User password
     * @return User password
     */
    public String getPassword() {
        return password;
    }

    /**
     * A setter methods that sets instance attribute (email) with a given (email).
     * @param t_email Email used in initializing instance attribute (email).
     */
    public void setEmail(String t_email) {
        email = t_email;
    }

    /**
     * A getter method that returns User email
     * @return User email
     */
    public String getEmail() {
        return email;
    }

    /**
     * A setter methods that sets instance attribute (phoneNumber) with a given (phone number).
     * @param t_phoneNumber Phone number used in initializing instance attribute (phoneNumber).
     */
    public void setPhoneNumber(String t_phoneNumber) {
        phoneNumber = t_phoneNumber;
    }


    /**
     * A getter method that returns User phone number
     * @return User phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * this function displays all user information such as: username, phone Number and email.
     */
    public void displayInfo()
    {
        System.out.println("User Name: " + userName);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
    }
}

