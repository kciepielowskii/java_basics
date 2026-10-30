package lesson13;

public record Point(int x, int y) {
    public double distanceTo(Point other) {
        return Math.hypot(other.x - x, other.y - y);
    }

    public static Point origin() {
        return new Point(0, 0);
    }

    // Providing a custom implementation of the default toString() method.
    @Override
    public String toString() {
        return "Point(" + x + "," + y + ")";
    }
}