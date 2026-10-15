package lesson7;

public class Manager extends Employee {
    private final double bonus;

    public Manager (String name, double baseSalary, double bonus) {
        super(name, baseSalary);
        if (bonus < 0) {
            throw new IllegalArgumentException("The bonus must be greather then zero");
        }
        this.bonus = bonus;
    }

    // Getters
    public double getBonus() {
        return bonus;
    }

    @Override
    public double getSalary() {
        return super.getSalary() + bonus;
    }
}
