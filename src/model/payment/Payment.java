package model.payment;

/**
 * The Payment class represents a payment made using an electronic wallet and loyalty points.
 */
public class Payment
{

    /* Instance Attributes */

    /**
     * The electronic wallet used for payment.
     */
    private EWallet Ewallet;

    /**
     * The loyalty points associated with the payment.
     */
    private int loyaltyPoints;

    /* Constructors */

    /**
     * Constructs a Payment object with 0 loyalty points and a new EWallet.
     */
    public Payment()
    {
        this.loyaltyPoints = 0;
        this.Ewallet = new EWallet();
    }

    /**
     * Constructs a Payment object with a specified number of loyalty points and an existing EWallet.
     *
     * @param points     the number of loyalty points associated with the payment
     * @param t_eWallet  the electronic wallet used for payment
     */
    public Payment(int points, EWallet t_eWallet)
    {
        setLoyaltyPoints(points);
        this.Ewallet = t_eWallet;
    }

    /* Setters and Getters */

    /**
     * Sets the electronic wallet used for payment.
     *
     * @param ewallet  the electronic wallet to set
     */
    public void setEwallet(EWallet ewallet)
    {
        this.Ewallet = ewallet;
    }

    /**
     * Sets the loyalty points associated with the payment.
     *
     * @param loyaltyPoints  the loyalty points to set
     */
    public void setLoyaltyPoints(int loyaltyPoints)
    {
        if (loyaltyPoints >= 0)
        {
            this.loyaltyPoints = loyaltyPoints;
        }
    }

    /**
     * Retrieves the electronic wallet used for payment.
     *
     * @return the electronic wallet used for payment
     */
    public EWallet getEwallet()
    {
        return Ewallet;
    }

    /**
     * Retrieves the loyalty points associated with the payment.
     *
     * @return the loyalty points associated with the payment
     */
    public int getLoyaltyPoints()
    {
        return loyaltyPoints;
    }

    /* Instance Methods */

    /**
     * Adds a specified number of points to the loyalty points.
     *
     * @param points  the number of points to add
     * @return true if the points were added successfully, false otherwise
     */
    public boolean addPoints(int points)
    {
        // note: points already valid by its setter method
        this.loyaltyPoints += points;

        // added
        return true;
    }

    /**
     * Withdraws a specified number of points from the loyalty points.
     *
     * @param points  the number of points to withdraw
     * @return true if the points were withdrawn successfully, false otherwise
     */
    public boolean withDrawPoints(int points)
    {
        boolean done = false;

        if ((this.loyaltyPoints - points) >= 0)
        {
            this.loyaltyPoints -= points;
            done = true;
        }
        else
        {
            System.out.println("!! Not Enough Points !!");
        }

        return done;
    }

    /**
     * Prints the details of the payment, including loyalty points and the associated electronic wallet.
     */
    public void print()
    {
        System.out.println("---------------------------------------- Payment Details ----------------------------------------");
        System.out.println("Loyalty Points: " + getLoyaltyPoints() + "\n");
        Ewallet.print();
    }
}
