package lesson12;

import common.Console;
import lesson8.Student;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Lekcja 12: lambdy i Stream API.
 * Zadania (metody static w tej klasie, w main same testy):
 * 1. Lambdy: posortuj List<String> po długości (list.sort z lambdą), potem alfabetycznie z użyciem
 * Comparator.comparing(String::...). Posortuj listę Student (lekcja 8) po imieniu przez Comparator.comparing.
 * Wypisz listę przez forEach z referencją do metody (System.out::println).
 * Usuń z List<Integer> liczby ujemne przez removeIf (porównaj z lekcją 9).
 * 2. Strumienie liczb (List<Integer>):
 * a) int sumOfSquaresOfEven(List<Integer>) - suma kwadratów liczb parzystych,
 * b) List<Integer> evenSorted(List<Integer>) - tylko parzyste, bez powtórzeń, malejąco,
 * c) double average(List<Integer>) - średnia (pusta lista -> IllegalArgumentException),
 * d) Integer maxOf(List<Integer>) bez pętli, na pustej liście rzuca IllegalArgumentException (użyj Optional).
 * Null zamiast listy -> IllegalArgumentException.
 * 3. Strumienie obiektów (List<Student>):
 * a) List<String> namesOfPassed(List<Student>) - imiona studentów ze średnią >= 3.0, alfabetycznie,
 * b) Student best(List<Student>) - najlepszy student (pusta lista -> IllegalArgumentException),
 * c) String joinNames(List<Student>) - imiona w jednym tekście, oddzielone ", " (pusta lista -> pusty tekst),
 * d) Map<Boolean, List<Student>> passedAndFailed(List<Student>) - podział na zaliczonych (>= 3.0) i niezaliczonych.
 * 4. Tekst: Map<Integer, Long> countByLength(String text) - ile słów ma daną długość
 * (ignoruj wielkość liter, wiele spacji, pusty tekst -> pusta mapa, null -> IllegalArgumentException).
 * Dodatkowo List<String> uniqueWords(String text) - unikalne słowa małymi literami, alfabetycznie.
 * Tu zostaje tylko main z testami, w tym przypadkami brzegowymi. Układ: RESULTS / TESTS / METHODS.
 */
public class Lesson12 {

    public static void main(String[] args) {

        // ====================== RESULTS ======================

        Console.header("Lambdas");
        List<String> values = new ArrayList<>(List.of("Cherry", "apple", "cranberry", "Banana"));

        System.out.println("Before sorting (by length): " + values);
        values.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println("After sorting (by length): " + values);

        System.out.println();

        System.out.println("Before sorting (alphabetically): ");
        values.forEach(System.out::println);

        System.out.println();

        // toLowerCase is only the sorting key, the elements themselves are not changed
        System.out.println("After sorting (alphabetically): ");
        values.sort(Comparator.comparing(String::toLowerCase));
        values.forEach(System.out::println);

        System.out.println();

        List<Student> students = new ArrayList<>(List.of(
                new Student("Charlie", 3.0),
                new Student("Bella", 5.0),
                new Student("Daniel", 4.0),
                new Student("Arthur", 2.0)
        ));
        System.out.println("Before sorting (students): " + students);
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("After sorting (students): ");
        students.forEach(System.out::println);

        System.out.println();

        List<Integer> mixedNumbers = new ArrayList<>(List.of(1, -1, 2, -2, 3, -3, 4, -4, 5, -5));
        System.out.println("Before removeIf: " + mixedNumbers);
        mixedNumbers.removeIf(n -> n < 0);
        System.out.println("After removeIf: " + mixedNumbers);

        Console.header("Stream API");

        List<Integer> nums = new ArrayList<>(List.of(8, 4, 3, 9, 6, 7, 10, 5, 1, 2));

        System.out.println("Sum of squares of even numbers: " + sumOfSquaresOfEven(nums));      // 220
        System.out.println("Even numbers sorted descending: " + evenSorted(nums));              // [10, 8, 6, 4, 2]
        System.out.println("Average number: " + average(nums));                                 // 5.5
        System.out.println("Max: " + maxOf(nums));                                              // 10

        Console.header("Objects streams");

        System.out.println("Names of students with average >= 3.0: " + namesOfPassed(students));
        System.out.println("The best student: " + best(students));
        System.out.println("Joined names: " + joinNames(students));
        System.out.println("Passed and failed: " + passedAndFailed(students));

        Console.header("Text");

        // Punctuation is not removed, so "cat." has length 4
        System.out.println("Length | count: " + countByLength("It is a cat. This cat belongs to her"));
        System.out.println("Unique words: " + uniqueWords("If he can do it you can do too"));

        // ======================= TESTS =======================

        Console.header("TESTS: Lambdas");

        // removeIf: no negative numbers, nothing is removed
        List<Integer> positiveNumbers = new ArrayList<>(List.of(1, 2, 3));
        positiveNumbers.removeIf(n -> n < 0);
        System.out.println(positiveNumbers.equals(List.of(1, 2, 3)) ? "OK: nothing removed" : "ERROR: " + positiveNumbers);

        // removeIf: empty list
        List<Integer> emptyList = new ArrayList<>();
        emptyList.removeIf(n -> n < 0);
        System.out.println(emptyList.isEmpty() ? "OK: empty list" : "ERROR: " + emptyList);

        // removeIf: List.of() is unmodifiable (the exception is thrown even if nothing would be removed)
        try {
            List<Integer> fixed = List.of(3, 1, 2);
            fixed.removeIf(n -> n < 0);
            System.out.println("ERROR: the exception was not thrown");
        } catch (UnsupportedOperationException e) {
            System.out.println("OK: removeIf() on List.of() throws an exception");
        }

        // sort: List.of() is unmodifiable
        try {
            List<String> fixed = List.of("b", "a");
            fixed.sort(Comparator.naturalOrder());
            System.out.println("ERROR: the exception was not thrown");
        } catch (UnsupportedOperationException e) {
            System.out.println("OK: sort() on List.of() throws an exception");
        }

        Console.header("TESTS: sumOfSquaresOfEven");

        try {
            sumOfSquaresOfEven(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        System.out.println(sumOfSquaresOfEven(List.of()) == 0 ? "OK: empty list" : "ERROR: should be 0");
        System.out.println(sumOfSquaresOfEven(List.of(1, 3, 5)) == 0 ? "OK: no even numbers" : "ERROR: should be 0");
        // -4 and 2 are even, -3 is odd: 16 + 4 = 20
        System.out.println(sumOfSquaresOfEven(List.of(-4, -3, 2)) == 20 ? "OK: negative numbers" : "ERROR: should be 20");

        Console.header("TESTS: evenSorted");

        try {
            evenSorted(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        System.out.println(evenSorted(List.of()).isEmpty() ? "OK: empty list" : "ERROR: should be empty");
        System.out.println(evenSorted(List.of(1, 3, 5)).isEmpty() ? "OK: no even numbers" : "ERROR: should be empty");
        System.out.println(evenSorted(List.of(2, 2, 4, -2)).equals(List.of(4, 2, -2))
                ? "OK: duplicates and negative numbers" : "ERROR: " + evenSorted(List.of(2, 2, 4, -2)));

        Console.header("TESTS: average");

        try {
            average(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            average(List.of());
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        System.out.println(average(List.of(7)) == 7.0 ? "OK: one number" : "ERROR: should be 7.0");
        // The result must not be rounded down (no integer division): (1 + 2) / 2 = 1.5
        System.out.println(average(List.of(1, 2)) == 1.5 ? "OK: fractional result" : "ERROR: should be 1.5");
        System.out.println(average(List.of(-2, -4)) == -3.0 ? "OK: negative numbers" : "ERROR: should be -3.0");

        Console.header("TESTS: maxOf");

        try {
            maxOf(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            maxOf(List.of());
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        System.out.println(maxOf(List.of(-5, -2, -9)) == -2 ? "OK: negative numbers" : "ERROR: should be -2");
        System.out.println(maxOf(List.of(4)) == 4 ? "OK: one number" : "ERROR: should be 4");

        Console.header("TESTS: namesOfPassed");

        try {
            namesOfPassed(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        System.out.println(namesOfPassed(List.of()).isEmpty() ? "OK: empty list" : "ERROR: should be empty");

        // 2.9 does not pass, 3.0 passes (the boundary)
        List<Student> boundary = List.of(new Student("Zed", 2.9), new Student("Ola", 3.0));
        System.out.println(namesOfPassed(boundary).equals(List.of("Ola")) ? "OK: boundary 3.0" : "ERROR: " + namesOfPassed(boundary));

        System.out.println(namesOfPassed(List.of(new Student("Zed", 2.0))).isEmpty()
                ? "OK: nobody passed" : "ERROR: should be empty");

        Console.header("TESTS: best");

        try {
            best(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            best(List.of());
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        Student only = new Student("Ola", 3.5);
        System.out.println(best(List.of(only)) == only ? "OK: one student" : "ERROR: wrong student");

        Console.header("TESTS: joinNames");

        try {
            joinNames(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        System.out.println(joinNames(List.of()).equals("") ? "OK: empty list" : "ERROR: should be an empty text");
        System.out.println(joinNames(List.of(only)).equals("Ola") ? "OK: one name, no comma" : "ERROR: " + joinNames(List.of(only)));

        Console.header("TESTS: passedAndFailed");

        try {
            passedAndFailed(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        Map<Boolean, List<Student>> emptyPartition = passedAndFailed(List.of());
        System.out.println(emptyPartition.get(true).isEmpty() && emptyPartition.get(false).isEmpty()
                ? "OK: empty list gives two empty groups" : "ERROR: " + emptyPartition);

        Map<Boolean, List<Student>> allPassed = passedAndFailed(List.of(only));
        System.out.println(allPassed.get(true).size() == 1 && allPassed.get(false).isEmpty()
                ? "OK: everybody passed" : "ERROR: " + allPassed);

        Console.header("TESTS: countByLength");

        try {
            countByLength(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        System.out.println(countByLength("   ").isEmpty() ? "OK: blank text" : "ERROR: should be empty");
        System.out.println(countByLength("  ala   ma  ").equals(Map.of(3, 1L, 2, 1L))
                ? "OK: many spaces" : "ERROR: " + countByLength("  ala   ma  "));
        System.out.println(countByLength("Ala ALA").equals(Map.of(3, 2L))
                ? "OK: ignoring case" : "ERROR: " + countByLength("Ala ALA"));

        Console.header("TESTS: uniqueWords");

        try {
            uniqueWords(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        System.out.println(uniqueWords("   ").isEmpty() ? "OK: blank text" : "ERROR: should be empty");
        System.out.println(uniqueWords("Ala ala ALA").equals(List.of("ala")) ? "OK: ignoring case" : "ERROR: " + uniqueWords("Ala ala ALA"));
        System.out.println(uniqueWords("  ma   ala ").equals(List.of("ala", "ma"))
                ? "OK: many spaces, sorted" : "ERROR: " + uniqueWords("  ma   ala "));
    }

    // ======================= METHODS =======================

    // Note: n * n is an int, so it overflows for numbers bigger than 46340
    public static int sumOfSquaresOfEven(List<Integer> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("The given list cannot be null");
        }
        return givenList.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n * n)
                .sum();
    }

    public static List<Integer> evenSorted(List<Integer> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("The given list cannot be null");
        }
        return givenList.stream()
                .filter(n -> n % 2 == 0)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public static double average(List<Integer> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("The given list cannot be null");
        }
        return givenList.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("The given list cannot be empty"));
    }

    public static Integer maxOf(List<Integer> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("The given list cannot be null");
        }
        return givenList.stream()
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("The given list cannot be empty"));
    }

    public static List<String> namesOfPassed(List<Student> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("The given list cannot be null");
        }
        return givenList.stream()
                .filter(s -> s.getAverage() >= 3.0)
                .map(Student::getName)
                .sorted()
                .toList();
    }

    public static Student best(List<Student> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("The given list cannot be null");
        }
        return givenList.stream()
                .max(Comparator.comparingDouble(Student::getAverage))
                .orElseThrow(() -> new IllegalArgumentException("The given list cannot be empty"));
    }

    public static String joinNames(List<Student> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("The given list cannot be null");
        }
        return givenList.stream()
                .map(Student::getName)
                .collect(Collectors.joining(", "));
    }

    public static Map<Boolean, List<Student>> passedAndFailed(List<Student> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("The given list cannot be null");
        }
        return givenList.stream()
                .collect(Collectors.partitioningBy(s -> s.getAverage() >= 3.0));
    }

    // An empty text must be handled separately: "".split("\\s+") gives one empty word
    public static Map<Integer, Long> countByLength(String text) {
        if (text == null) {
            throw new IllegalArgumentException("The given string cannot be null");
        }
        if (text.isBlank()) {
            return Map.of();
        }
        return Arrays.stream(text.trim().toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.counting()
                ));
    }

    public static List<String> uniqueWords(String text) {
        if (text == null) {
            throw new IllegalArgumentException("The given string cannot be null");
        }
        if (text.isBlank()) {
            return List.of();
        }
        return Arrays.stream(text.trim().toLowerCase().split("\\s+"))
                .distinct()
                .sorted()
                .toList();
    }
}