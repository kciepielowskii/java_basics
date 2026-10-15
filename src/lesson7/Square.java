package lesson7;

public class Square extends Rectangle {
    public Square (String name, double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("The side must be positive");
        }
        super(name, side, side);
    }
}
