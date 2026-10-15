package lesson7;

import lesson6.BankAccount;

public class SavingsAccount extends BankAccount {
    private final double interestRate;

    public SavingsAccount(String name, double balance, double interestRate) {
        super (name, balance);
        if (interestRate < 0) {
            throw new IllegalArgumentException("The interest rate cannot be negative");
        }
        this.interestRate = interestRate;
    }

    public double addInterest() {
        double interest = getBalance() * interestRate / 100;
        if (interest > 0) {
            deposit(interest);
        }
        return interest;
    }

    // Getters
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public String toString() {
        return super.toString() + ", interest rate: " + interestRate + "%";
    }
}
