package lesson8;

public class Cow extends Animal {

    public Cow (String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return "muuu";
    }
}
