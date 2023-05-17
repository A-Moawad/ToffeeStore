package model.payment;
/**
 * The EWallet class represents an electronic wallet used for payment.
 */
public class EWallet
{

    /* Static Attributes (related to class only and have nothing to do with instances) */

    /**
     * The total count of all EWallets created.
     */
    private static int s_walletsCount;

    /* Instance Attributes (instance properties) */

    /**
     * The unique identifier of the EWallet.
     */
    private String id;

    /**
     * The balance of the EWallet.
     */
    private double balance;

    /* Constructors */

    /**
     * Constructs an EWallet object with a balance of 0.
     * It generates a unique ID based on the count of EWallets created.
     */
    public EWallet()
    {
        this.id = "FCAI-EWALLET-" + Integer.toString(++s_walletsCount);
        this.balance = 0;
    }

    /**
     * Constructs an EWallet object with a specified balance.
     * It generates a unique ID based on the count of EWallets created.
     *
     * @param t_balance the initial balance of the EWallet
     */
    public EWallet(double t_balance)
    {
        this.id = "FCAI-EWALLET-" + Integer.toString(++s_walletsCount);
        this.balance = t_balance;
    }

    /* Setters and Getters */

    /**
     * Retrieves the unique identifier of the EWallet.
     *
     * @return the EWallet identifier
     */
    public String getId()
    {
        return id;
    }

    /**
     * Sets the balance of the EWallet.
     *
     * @param t_balance the new balance to set
     * @return true if the balance was updated successfully, false otherwise
     * @throws RuntimeException if the balance is less than 0
     */
    boolean setBalance(double t_balance)
    {
        boolean updated = false;

        if (t_balance >= 0) {
            this.balance = t_balance;
            updated = true;
        } else {
            throw new RuntimeException("!! Balance < 0 !!");
        }

        return updated;
    }

    /**
     * Retrieves the balance of the EWallet.
     *
     * @return the balance of the EWallet
     */
    public double getBalance()
    {
        return balance;
    }

    /* Instance Methods */

    /**
     * Prints the details of the EWallet.
     */
    public void print()
    {
        System.out.println("************* Ewallet Details ************");
        System.out.println("id: " + this.getId());
        System.out.println("Current Balance: " + getBalance() + "$");
    }

    /**
     * Checks if the EWallet is empty (has a balance of 0).
     *
     * @return true if the EWallet is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return this.balance == 0;
    }

    /**
     * Withdraws a specified amount from the EWallet.
     *
     * @param t_amount the amount to withdraw
     * @return true if the withdrawal was successful, false otherwise
     */
    public boolean withdraw(double t_amount)
    {
        boolean withdrawn = false;

        if (!isEmpty()) {
            if (isThereEnoughMoneyToWithdraw(t_amount))
            {
                double currentBalance = getBalance();
                double newBalance = currentBalance - t_amount;

                if (setBalance(newBalance))
                {
                    withdrawn = true;
                }
            }
            else
            {
                System.out.println("!! Not Enough Balance In Ewallet !!");
            }
        }
        else
        {
            System.out.println("!! Ewallet is empty !!");
        }

        return withdrawn;
    }

    /**
     * Deposits a specified amount into the EWallet.
     *
     * @param amount the amount to deposit
     * @return true if the deposit was successful, false otherwise
     */
    public boolean deposit(double amount)
    {
        boolean deposited = false;
        double newBalance = balance + amount;

        if (setBalance(newBalance))
        {
            deposited = true;
        }

        return deposited;
    }

    /**
     * Retrieves the number of EWallets created so far by this class.
     *
     * @return the number of EWallets created
     */
    public static int getNumberWalletsThisClassMadeSoFar()
    {
        return s_walletsCount;
    }

    /* Private Methods */

    /**
     * Checks if there is enough money in the EWallet to withdraw a specified amount.
     *
     * @param amount the amount to withdraw
     * @return true if there is enough money, false otherwise
     */
    private boolean isThereEnoughMoneyToWithdraw(double amount)
    {
        return (this.balance - amount) >= 0;
    }
}
