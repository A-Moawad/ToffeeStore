package model;
import java.lang.String;

import java.util.ArrayList;

public class Customer extends User {
    private String number;
    private int loyaltyPoints;
    private double amountInWallet;
    private ArrayList<Integer> giftVoucher;
    private Cart cart;

    public Customer(String userName, String email, String password) {
        super(userName, email, password);
    }

    @Override
    public String getData() {
        return this.getUserName() + " " + this.getEmail() + " " + this.getPassword();

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public double getAmountInWallet() {
        return amountInWallet;
    }

    public void setAmountInWallet(double amountInWallet) {
        this.amountInWallet = amountInWallet;
    }

    public ArrayList<Integer> getGiftVoucher() {
        return giftVoucher;
    }

    public void setGiftVoucher(ArrayList<Integer> giftVoucher) {
        this.giftVoucher = giftVoucher;
    }

    public Cart getCart(){
        return cart;
    }
    public boolean  checkPassword(String password){
        return this.getPassword().equals(password);
    }

}
