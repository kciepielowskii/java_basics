package lesson8;

public class Student implements Comparable<Student> {
    private final String name;
    private final double average;

    public Student (String name, double average) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The name must not be empty");
        }
        if (average < 2.0 || average > 5.0) {
            throw new IllegalArgumentException("The average must be between 2.0 and 5.0");
        }
        this.name = name;
        this.average = average;
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(this.average, other.average);
    }

    @Override
    public String toString() {
        return name + " - " + average;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getAverage() {
        return average;
    }
}
