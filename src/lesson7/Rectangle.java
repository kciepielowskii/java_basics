package lesson7;

public class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(String name, double width, double height) {
        super(name);
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("The width and height must be positive");
        }
        this.width = width;
        this.height = height;
    }

    // Getters
    public double getWeight() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    // Method toString()
    @Override
    public double getArea() {
        return width * height;
    }
}
