package model.users;

public class User {

    private String userName;
    private String password;
    private String email;
    private String phoneNumber;


    public User() {}

    public User(String t_name, String t_password, String t_phoneNumber, String t_email) {
        userName = t_name;
        password = t_password;
        phoneNumber = t_phoneNumber;
        email = t_email;
    }

    public void setUserName(String t_name) {
        userName = t_name;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String t_password) {
        password = t_password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String t_email) {
        email = t_email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String t_phoneNumber) {
        phoneNumber = t_phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void displayInfo() {
        System.out.println("User Name: " + userName);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
    }
}

