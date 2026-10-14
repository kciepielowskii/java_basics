package lesson6;

public class BankAccount {
    private final String owner;
    private double balance;

    public BankAccount(String owner, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException(
                    "Initial balance cannot be negative."
            );
        }
        this.owner = owner;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    public void deposit (double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("The withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Exceeding the account balance");
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return "Owner: " + owner + "\nBalance: " + balance + "$" + "\n";
    }
}
