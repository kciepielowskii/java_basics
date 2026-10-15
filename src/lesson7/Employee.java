package lesson7;

public class Employee {
    private final String name;
    private final double baseSalary;

    // Constructor
    public Employee (String name, double baseSalary) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name must be filled");
        } else if (baseSalary <= 0) {
            throw new IllegalArgumentException("The salary must be positive");
        }
        this.name = name;
        this.baseSalary = baseSalary;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getSalary() {
        return baseSalary;
    }

    @Override
    public String toString() {
        return name + String.format(", salary: %.2f", getSalary());
    }
}
