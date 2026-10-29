package lesson11;

public class Pair<A, B> {
    private final A first;
    private final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    // Returns a NEW pair with swapped types
    public Pair<B, A> swap() {
        return new Pair<>(second, first);
    }

    @Override
    public String toString() {
        return String.valueOf(first) + " " + String.valueOf(second);
    }
}