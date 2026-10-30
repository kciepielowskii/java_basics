package lesson13;

import common.Console;
import lesson8.Student;

import java.util.*;

/**
 * Lekcja 13: Optional, rekordy (record), switch expressions.
 * Zadania (metody static w Lesson13, w main same testy; rekordy mogą być w osobnych plikach):
 * 1. Optional:
 * a) Optional<Student> findByName(List<Student>, String name) - student o danym imieniu lub pusty Optional
 * (lista null lub imię null -> IllegalArgumentException),
 * b) Optional<Integer> firstEven(List<Integer>) - pierwsza parzysta liczba lub pusty Optional.
 * Pokaż użycie: orElse, ifPresent, map(Student::getAverage), orElseThrow z własnym wyjątkiem.
 * Pokaż też w try/catch: get() na pustym Optional, Optional.of(null) oraz Optional.ofNullable(null).
 * 2. Rekordy:
 * a) record Point(int x, int y) z metodą double distanceTo(Point other) i static Point origin() (0, 0),
 * b) record Temperature(double celsius) - konstruktor kompaktowy: poniżej -273.15 -> IllegalArgumentException
 * (dokładnie -273.15 jest poprawne), metoda double toFahrenheit().
 * Pokaż toString, equals dla dwóch punktów o tych samych współrzędnych (a == b i a.equals(b)),
 * oraz wrzuć dwa równe punkty do HashSet i zobacz rozmiar.
 * 3. Switch expressions:
 * a) String dayType(int day) - 1-5 "weekday", 6-7 "weekend", inna wartość -> IllegalArgumentException,
 * b) int daysInMonth(int month, int year) - liczba dni (rok przestępny z lekcji 2, luty = 28 lub 29),
 * zły miesiąc -> IllegalArgumentException; użyj case z wieloma wartościami i bloku z yield dla lutego,
 * c) String describe(Object o) - switch z dopasowaniem wzorca: null -> "null", Integer > 0 -> "positive int",
 * pozostałe Integer -> "other int", String -> "text of length N", reszta -> "something else".
 * 4. Połączenie: record Product(String name, double price) (cena nie może być ujemna) oraz
 * Optional<Product> cheapest(List<Product>) przez Stream (min z Comparator.comparingDouble). Pusta lista -> pusty Optional,
 * null -> IllegalArgumentException.
 * Tu zostaje tylko main z testami, w tym przypadkami brzegowymi. Układ: RESULTS / TESTS / METHODS.
 */

public class Lesson13 {
    public static void main(String[] args) {
        Console.header("Optional");

        // RESULTS
        List<Student> students = List.of(
                new Student("Oliver", 2.0),
                new Student("Ethan", 5.0),
                new Student("Ethan", 4.0),
                new Student("Chloe", 3.0)
        );

        // Case with found value
        System.out.println("List of students: " + students);
        Optional<Student> foundStudent = findByName(students, "Ethan");
        System.out.println("Found student: " + foundStudent); // Optional type

        // Case with missing value
        List<Student> emptyStudents = List.of();
        Optional<Student> emptyResult = findByName(emptyStudents, "Chloe");
        System.out.println("Empty result: " + emptyResult); // Optional type
        System.out.println();

        // Case with found value
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        System.out.println("List of numbers: " + numbers);
        Optional<Integer> firstEvenNumber = firstEven(numbers);
        System.out.println("First even number: " + firstEvenNumber); // Optional type

        // Case with missing value
        List<Integer> emptyNumbers = List.of();
        Optional<Integer> emptyResult2 = firstEven(emptyNumbers);
        System.out.println("Empty result: " + emptyResult2); // Optional type

        // Case with orElse() - returns the value if present, otherwise the default value
        int foundNumber = firstEven(numbers).orElse(-1);
        System.out.println("Found number: " + foundNumber);

        // Case with orElse and empty list
        int notFoundValue = firstEven(emptyNumbers).orElse(-1);
        System.out.println("Not found number: " + notFoundValue);

        // Case with ifPresent, returns void type
        System.out.print("Found number: ");
        firstEven(numbers).ifPresent(System.out::println);

        // Case with ifPresent, returns void type
        System.out.print("Not found number: ");
        firstEven(emptyNumbers).ifPresent(System.out::println);
        // ifPresent() executes the action only when a value is present.

        // Case with map(Student::getAverage)
        System.out.println();
        Optional<Double> foundAverage = findByName(students, "Oliver").map(Student::getAverage);
        System.out.println("Found average: " + foundAverage);
        // It returns Optional type in foundAverage variable

        // Case with map(Student::getAverage) and empty list
        Optional<Double> notFoundAverage = findByName(emptyStudents, "Oliver").map(Student::getAverage);
        System.out.println("Not found average: " + notFoundAverage);
        // It returns an empty Optional.

        // Case with Optional.ofNullable(null)
        Optional<Integer> test2 = Optional.ofNullable(null);
        System.out.println("OK: " + test2);
        // Use Optional.ofNullable when the value might be null.
        // If the value is null, it returns Optional.empty().
        // If the value is not null, it returns Optional containing that value.
        // It prevents NullPointerException when wrapping a potentially null value.

        // Case with Optional.ofNullable() and a non-null value
        Optional<Integer> test3 = Optional.ofNullable(10);
        System.out.println("OK: " + test3); // Optional[10]

        Console.header("Records");

        // Creating two records Temperature
        Temperature t1 = new Temperature(20);
        // It is worth noting that the value -273.15 is permitted.
        Temperature t2 = new Temperature(-273.15);

        // Testing toFahrenheit method.
        System.out.println("Temperatura t1 [F] = " + t1.toFahrenheit());
        System.out.println("Temperatura t2 [F] = " + t2.toFahrenheit());

        // Displaying objects
        System.out.println("T1: " + t1);
        System.out.println("T2: " + t2);
        System.out.println();

        // Creating two records with the same coordinates
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        Point origin = Point.origin();

        // Displaying objects using the toString() method
        System.out.println("P1: " + p1);
        System.out.println("P2: " + p2);
        System.out.println("Origin: " + origin);

        // Calculating the distance between two points
        System.out.printf("P1.distanceTo(p2): %.2f%n", p1.distanceTo(p2));
        System.out.printf("Origin.distanceTo(Point(3, 4)): %.2f%n", origin.distanceTo(new Point(3, 4))); // 5.00

        // Comparing two objects by reference (returns boolean)
        System.out.println("Comparison (p1 == p2): " + (p1 == p2));
        // The result is false, because method compares references
        // It checks whether both references point to the same object.

        // Comparing two objects by value using equals() (returns boolean)
        System.out.println("Comparison (p1.equals(p2)): " + p1.equals(p2));
        // The result is true because the contents of both objects are equal.

        // Creating a HashSet collection and adding objects to it
        HashSet<Point> set = new HashSet<>(List.of(p1, p2));
        System.out.println("Set: " + set);
        // A HashSet does not allow duplicate elements.

        // Displaying the size of the set (returns an int).
        System.out.println("Set size: " + set.size());
        // The result is 1 because the set contains only one unique element.

        Console.header("Switch expressions");

        // Testing a weekday.
        System.out.println("Day type: " + dayType(5));
        // Testing a weekend.
        System.out.println("Day type: " + dayType(7));

        // Testing the daysInMonth() method.
        System.out.println("Number of days: " + daysInMonth(2, 2001)); // 28 days
        System.out.println("Number of days: " + daysInMonth(2, 2000)); // 29 days
        System.out.println("Number of days: " + daysInMonth(12, 2001)); // 31 days
        System.out.println("Number of days: " + daysInMonth(2, 1900)); // 28 days
        System.out.println("Number of days: " + daysInMonth(2, 2024)); // 29 days

        // A negative year represents a year BCE (February depends on the year, so it is a meaningful test).
        System.out.println("Number of days: " + daysInMonth(2, -400)); // 29 days
        System.out.println("Number of days: " + daysInMonth(2, -100)); // 28 days
        System.out.println();

        // Testing values for describe() method
        System.out.println("Tests describe(): ");
        System.out.println(describe(null));
        System.out.println(describe(-1));
        System.out.println(describe(0));
        System.out.println(describe(1));
        System.out.println(describe(3.14));
        System.out.println(describe("Hello"));
        System.out.println(describe("")); // text of length 0

        Console.header("Common record-Optional");

        Product pr1 = new Product("Milk", 2.89);
        Product pr2 = new Product("Cheese", 3.50);
        Product pr3 = new Product("Nutella", 9.89);
        Product pr4 = new Product("Tomato", 0.89);

        List<Product> products = List.of(pr1, pr2, pr3, pr4);
        Optional<Product> productOptional = cheapest(products);
        // Testing the cheapest product.
        System.out.println("Cheapest product: " + productOptional); // Result: Tomato, 0.89

        List<Product> emptyProducts = List.of();
        Optional<Product> emptyProductOptional = cheapest(emptyProducts);
        // Testing the cheapest product with empty list
        System.out.println("Empty products: " + emptyProductOptional); // Result: Optional empty

        // TESTS
        Console.header("TESTS");
        // Testing findByName() with a null list
        try {
            findByName(null, "Oliver");
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Testing firstEven() with a null list
        try {
            firstEven(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Testing findByName() with a null name
        try {
            findByName(students, null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Testing findByName() with a name that does not exist
        System.out.println("Missing name: " + findByName(students, "Nobody")); // Optional.empty

        // Testing firstEven() with a list containing a null element
        System.out.println("List with null: " + firstEven(Arrays.asList(1, null, 2))); // Optional[2]

        // Case with orElseThrow and empty list
        try {
            int notFoundValue2 = firstEven(emptyNumbers)
                    .orElseThrow(() -> new IllegalStateException("Not found given value"));

            System.out.println("Not found number: " + notFoundValue2);
        } catch (IllegalStateException e) {
            System.out.println("OK: " + e.getMessage());
        }
        // In that case, an exception was thrown and operation was not performed

        // Try-catch get() method on empty Optional
        try {
            emptyResult2.get();
            System.out.println("ERROR: the exception was not thrown");
        } catch (NoSuchElementException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Try-catch Optional.of(null)
        try {
            Optional.of(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (NullPointerException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Try-catch Optional.ofNullable(null) - no exception, empty Optional
        try {
            Optional<Object> nullable = Optional.ofNullable(null);
            System.out.println("OK: no exception, isPresent = " + nullable.isPresent());
        } catch (NullPointerException e) {
            System.out.println("ERROR: unexpected exception");
        }

        // Testing record Temperature with exactly -273.15 (boundary, valid)
        try {
            Temperature boundary = new Temperature(-273.15);
            System.out.println("OK: " + boundary);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: -273.15 should be valid");
        }

        // Testing record Temperature with value less than -273.15
        try {
            new Temperature(-273.16);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Testing the switch expression with an invalid integer using try-catch.
        try {
            dayType(-1);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Testing dayType() with 0 and 8 (just outside the valid range).
        try {
            dayType(0);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }
        try {
            dayType(8);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Testing daysInMonth() with a month greater than 12.
        try {
            daysInMonth(13, 2000);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Testing daysInMonth() with a month less than 1.
        try {
            daysInMonth(0, 2000);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Testing cheapest() with null parameter
        try {
            cheapest(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Testing record Product with blank name
        try {
            new Product("    ", 2.89);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Testing record Product with null name
        try {
            new Product(null, 2.89);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Testing record Product with negative price
        try {
            new Product("Milk", -0.01);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Testing record Product with price 0 (boundary, valid)
        try {
            Product free = new Product("Sample", 0);
            System.out.println("OK: " + free);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: price 0 should be valid");
        }
    }

    // METHODS
    public static Optional<Student> findByName(List<Student> givenList, String name) {
        if (givenList == null) {
            throw new IllegalArgumentException("The given list cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("The given name cannot be null");
        }
        return givenList.stream()
                .filter(s -> name.equals(s.getName()))
                .findFirst();
    }

    public static Optional<Integer> firstEven(List<Integer> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("The given list cannot be null");
        }
        return givenList.stream()
                .filter(Objects::nonNull)
                .filter(s -> s % 2 == 0)
                .findFirst();
    }

    public static String describe(Object o) {
        return switch (o) {
            case null -> "null";
            case Integer i when i > 0 -> "positive int";
            case Integer i -> "other int";
            case String s -> "text of length " + s.length();
            default -> "something else";
        };
    }

    public static String dayType(int day) {
        return switch (day) {
            case 1, 2, 3, 4, 5 -> "weekday";
            case 6, 7 -> "weekend";
            default -> throw new IllegalArgumentException("Invalid day");
        };
    }

    public static int daysInMonth(int month, int year) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> {
                if (isLeapYear(year)) {
                    yield 29;
                } else {
                    yield 28;
                }
            }
            default -> throw new IllegalArgumentException("Invalid month");
        };
    }

    private static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    public static Optional<Product> cheapest(List<Product> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("The given list cannot be null");
        }
        return givenList.stream()
                .min(Comparator.comparingDouble(Product::price));
    }
}