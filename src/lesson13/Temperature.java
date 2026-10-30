package lesson13;

public record Temperature(double celsius) {
    public Temperature {
        if (celsius < -273.15) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero");
        }
    }

    public double toFahrenheit() {
        return celsius * 1.8 + 32;
    }
}
