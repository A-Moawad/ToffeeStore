package model;

public abstract class User {
    private String userName;
    private String email;
    private  String password;

    public User() {
    }

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//     boolean  checkPassword(String password);



    public void signUp(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void login(String userName, String password){ // login with username and pass only
        this.userName = userName;
        this.password = password;
    }

    public abstract String getData();

}
