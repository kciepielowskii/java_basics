package lesson7;

public class Shape {
    private final String name;

    public Shape (String name) {
        this.name = name;
    }

    public double getArea() {
        return 0.0;
    }

    // Getters
    public String getName() {
        return name;
    }

    // Method toString()
    @Override
    public String toString() {
        return name + ", area: " + String.format("%.2f", getArea());
    }
}
