package model.payment;

public class EWallet
{
    /* static attributes (Related to class only and have nothing to do with instance) */
    private static int s_walletsCount;

    /* Instance Attributes (instance properties) */
    private String id;
    private double balance;

    /* Constructors */
    public EWallet()
    {
        // generate id based on wallet whole count
        this.id = "FCAI-EWALLET-" + Integer.toString(++s_walletsCount);

        // init balance
        this.balance = 0;

    }
    public EWallet(double t_balance)
    {
        // generate id based on wallet whole count
        this.id = "FCAI-EWALLET-" + Integer.toString(++s_walletsCount);

        // init balance
        this.balance = t_balance;
    }

    /* Setters And Getters  */

    public String getId() {
        return id;
    }

    boolean setBalance(double t_balance) {
        boolean updated = false;

        // validation
        if (t_balance >= 0) {
            // set it
            this.balance = t_balance;
            updated = true;
        } else {
            // throw logical error argument
            throw new RuntimeException("!! Balance < 0 !!");
        }

        return updated;
    }

    public double getBalance() {
        return balance;
    }

    public void print()
    {
        System.out.println("************* Ewallet Details ************");
        System.out.println("id: " + this.getId());
        System.out.println("Current Balance: " + getBalance() + "$");
    }

    public boolean isEmpty()
    {

        return this.balance == 0;
    }

    public boolean WithDraw(double t_amount) {
        // To know whether withdrawn or not
        boolean withdrawn = false;

        if (!isEmpty()) {
            if (isThereEnoughMoneyToWithdraw(t_amount)) {
                // Withdraw
                double currentBalance = getBalance();
                double newBalance = currentBalance - t_amount;

                // Update balance
                if (setBalance(newBalance)) {
                    withdrawn = true;
                }
            } else {
                System.out.println("!! Not Enough Balance In Ewallet !!");
            }
        } else {
            System.out.println("!! Ewallet is empty !!");
        }

        return withdrawn;
    }

    public boolean deposit(double amount) {
        boolean deposited = false;
        double newBalance = balance + amount;
        if (setBalance(newBalance)) {
            deposited = true;
        }
        return deposited;
    }

    public static int getNumberWalletsThisClassMadeSoFar() {
        return s_walletsCount;
    }

    private boolean isThereEnoughMoneyToWithdraw(double amount) {
        return (this.balance - amount) >= 0;
    }

    public boolean withdraw(double amount) {
        boolean withdrawn = false;
        if (!isEmpty()) {
            if (isThereEnoughMoneyToWithdraw(amount)) {
                double currentBalance = getBalance();
                double newBalance = currentBalance - amount;
                if (setBalance(newBalance)) {
                    withdrawn = true;
                }
            } else {
                System.out.println("!! Not Enough Balance In Ewallet !!");
            }
        } else
        {

            System.out.println("!! Ewallet is empty !!");
        }
        return withdrawn;
    }

}
