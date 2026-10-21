package lesson10;

public class Resource implements AutoCloseable {
    private final String name;

    public Resource (String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The name cannot be null or empty");
        }
        this.name = name;
        System.out.println("open " + name);
    }

    @Override
    public void close() {
        System.out.println("close " + name);
    }

}
