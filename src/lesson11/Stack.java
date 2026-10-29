package lesson11;

import java.util.ArrayList;

public class Stack<T> {
    private final ArrayList<T> stack;

    public Stack() {
        stack = new ArrayList<>();
    }

    public void push(T value) {
        stack.add(value);
    }

    public T pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.removeLast();
    }

    public T peek() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.getLast();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}