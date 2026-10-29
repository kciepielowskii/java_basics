package lesson11;

public class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() { return value; }

    public void setValue(T givenValue) {
        value = givenValue;
    }

    public boolean isEmpty() { return value == null; }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}