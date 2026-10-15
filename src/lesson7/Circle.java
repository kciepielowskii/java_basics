package lesson7;

public class Circle extends Shape {

    private final double radius;
    public Circle(String name, double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("The radius must be positive");
        }
        super(name);
        this.radius = radius;
    }

    // Getters
    public double getRadius() {
        return radius;
    }

    // Method toString()
    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}
