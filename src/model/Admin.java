package model;

public class Admin extends User{
    private int id;

    public Admin(String username, String email, String password) {
        super(username, email, password);
    }

    public Admin() {
    }

    public Admin(String userName, String email, String password, int id) {
        super(userName, email, password);
        this.id = id;
    }
@Override
    public  String getData() {
    return this.getUserName() + " " + this.getEmail() + " " + this.getPassword();

}
    public boolean  checkPassword(String password){
        return this.getPassword().equals(password);
    }

}
