package lesson13;

public record Product(String name, double price) {
    public Product {
        if (price < 0) {
            throw new IllegalArgumentException("The price cannot be negative");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The name cannot be null or blank");
        }
    }
}
