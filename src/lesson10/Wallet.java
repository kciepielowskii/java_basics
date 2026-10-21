package lesson10;

public class Wallet {
    private final String owner;
    private double amount;

    public Wallet(String owner, double amount) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("The name cannot be null or empty");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("The balance cannot be negative");
        }
        this.owner = owner;
        this.amount = amount;
    }

    public void withdraw(double value) throws InsufficientFundsException {
        if (value <= 0) {
            throw new IllegalArgumentException("The amount must be positive");
        }
        if (value > amount) {
            throw new InsufficientFundsException("Not enough funds", value - amount);
        }
        amount -= value;
    }

    public String getOwner() {
        return owner;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return owner + ", " + amount + "$";
    }
}
