package model.payment;

public class Payment
{
    private EWallet Ewallet;
    private int loyaltyPoints;

    public Payment() {
        this.loyaltyPoints = 0;
        this.Ewallet = new EWallet();
    }

    public Payment(int points, EWallet t_eWallet)
    {
        setLoyaltyPoints(points);
        this.Ewallet = t_eWallet;
    }

    public void setEwallet(EWallet ewallet) {
        this.Ewallet = ewallet;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        if (loyaltyPoints >= 0)
        {
            this.loyaltyPoints = loyaltyPoints;

        }

        // throw exception

    }

    public EWallet getEwallet() {
        return Ewallet;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public boolean addPoints(int points) {
        // note: points already valid by its setter method
        this.loyaltyPoints += points;

        // added
        return true;
    }

    public boolean withDrawPoints(int points) {
        boolean done = false;
        if ((this.loyaltyPoints - points) >= 0) {
            this.loyaltyPoints -= points;
            done = true;
        } else {
            System.out.println("!! Not Enough Points !!");
        }
        return done;
    }

    public void print()
    {
        System.out.println("---------------------------------------- Payment Details ----------------------------------------");
        System.out.println("Loyalty Points: " + getLoyaltyPoints() + "\n");
        Ewallet.print();
    }
}
