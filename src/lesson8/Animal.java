package lesson8;

public abstract class Animal {
    private final String name;

    public Animal (String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("This field cannot be empty");
        }
        this.name = name;
    }

    // Abstract method
    public abstract String makeSound();

    public String describe() {
        return name + " says " + makeSound();
    }

    // Getters
    public String getName() {
        return name;
    }
}
