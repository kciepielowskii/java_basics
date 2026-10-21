package lesson10;

public class InsufficientFundsException extends Exception {
    private final double missing;

    public InsufficientFundsException(String message, double missing) {
        super(message);
        this.missing = missing;
    }

    public double getMissing() {
        return missing;
    }
}