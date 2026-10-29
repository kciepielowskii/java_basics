package lesson11;

import common.Console;
import lesson8.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Lekcja 11: generyki (klasy generyczne, metody generyczne, typy ograniczone).
 * Zadania:
 * 1. Klasy generyczne: Box<T> (wartość, która może być pusta = null; getValue(), setValue(), isEmpty(), toString())
 * oraz Pair<A, B> (getFirst(), getSecond(), swap() zwraca NOWĄ parę Pair<B, A>, toString()).
 * Przetestuj je dla String, Integer i Student (lekcja 8). Zostaw w komentarzu linię, która wkłada zły typ
 * do Box<String>, i obok wpisz błąd kompilatora.
 * 2. Metody generyczne (static, w tej klasie):
 * a) <T> void swap(T[] array, int i, int j) - zamienia miejscami dwa elementy, błędne indeksy -> IllegalArgumentException,
 * b) <T> int countOccurrences(List<T> list, T element) - liczy elementy równe podanemu
 * (użyj equals(), element może być null), lista null -> IllegalArgumentException,
 * c) <T> List<T> reverse(List<T> list) - zwraca NOWĄ odwróconą listę, oryginał bez zmian.
 * 3. Typy ograniczone: <T extends Comparable<T>> T maxOf(List<T> list) oraz
 * <T extends Comparable<T>> boolean isSorted(List<T> list) (rosnąco).
 * Przetestuj dla Integer, String i Student. Przypadki brzegowe: lista null lub pusta (maxOf rzuca
 * IllegalArgumentException), lista pusta i jednoelementowa są posortowane, równe elementy też są posortowane.
 * 4. Generyczny stos: klasa Stack<T> oparta na ArrayList<T> z metodami push(T), pop(), peek(), isEmpty(), size().
 * pop() i peek() na pustym stosie rzucają IllegalStateException z czytelnym komunikatem.
 * Przetestuj kolejność LIFO (ostatni wchodzi, pierwszy wychodzi) dla Integer i String.
 * Tu zostaje tylko main z testami, w tym przypadkami brzegowymi.
 */
public class Lesson11 {

    public static void main(String[] args) {

        // ====================== RESULTS ======================

        Console.header("Box and Pair");
        Box<String> b1 = new Box<>("1");
        System.out.println(b1);
        Box<Integer> i1 = new Box<>(1);
        System.out.println(i1);
        Box<Student> st1 = new Box<>(new Student("Konrad", 3.0));
        System.out.println(st1);

        // Box<String> test = new Box<>(1);
        /* java: incompatible types: cannot infer type arguments for lesson11.Box<>
           reason: inference variable T has incompatible bounds
           equality constraints: java.lang.String
           lower bounds: java.lang.Integer */

        Box<String> n1 = new Box<>(null);
        System.out.println(n1);
        System.out.println(n1.isEmpty());
        System.out.println(n1.getValue());

        System.out.println();

        Pair<String, String> b2 = new Pair<>("1", "2");
        System.out.println(b2);
        Pair<Integer, Integer> i2 = new Pair<>(1, 2);
        System.out.println(i2);
        Pair<Student, Student> st2 =
                new Pair<>(new Student("Kamil", 2.0),
                        new Student("Jacek", 5.0));
        System.out.println(st2);

        Pair<Student, Student> testSwap = st2.swap();
        System.out.println(testSwap);

        Pair<String, Integer> st3 = new Pair<>("Konrad", 3);
        Pair<Integer, String> testSwap2 = st3.swap();

        System.out.println(st3);
        System.out.println(testSwap2);

        System.out.println(st3.getFirst());
        System.out.println(st3.getSecond());

        Console.header("Generic methods");

        Integer[] nums = new Integer[]{1, 2, 3};
        System.out.println(Arrays.toString(nums));
        swap(nums, 0, 2);
        System.out.println(Arrays.toString(nums));

        String[] nums2 = new String[]{"1", "2", "3"};
        System.out.println(Arrays.toString(nums2));
        swap(nums2, 0, 2);
        System.out.println(Arrays.toString(nums2));

        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 2, 4, 2));
        System.out.println(numbers);
        System.out.println("Count: " + countOccurrences(numbers, 2));

        List<String> numbers2 = new ArrayList<>(List.of("1", "2", "3", "2", "4", "2"));
        System.out.println(numbers2);
        System.out.println("Count: " + countOccurrences(numbers2, "2"));

        List<Integer> reversed = reverse(numbers);
        System.out.println("Original: " + numbers);
        System.out.println("Reversed: " + reversed);

        Console.header("Bounded types");

        List<Integer> nums4 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<String> nums5 = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
        List<Student> nums6 = new ArrayList<>(Arrays.asList(
                new Student("Konrad", 2.0),
                new Student("Kamil", 3.0),
                new Student("Karolina", 5.0)));

        System.out.println(nums4);
        System.out.println(nums5);
        System.out.println(nums6);

        System.out.println();

        System.out.println("The highest value (Integer): " + maxOf(nums4));
        System.out.println("The highest value (String): " + maxOf(nums5));
        System.out.println("The highest value (Student): " + maxOf(nums6));

        System.out.println();

        System.out.println("Is sorted (Integer): " + isSorted(nums4));
        System.out.println("Is sorted (String): " + isSorted(nums5));
        System.out.println("Is sorted (Student): " + isSorted(nums6));

        Console.header("Stack");

        Stack<Integer> stack = new Stack<>();
        Stack<String> stack2 = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack2.push("1");
        stack2.push("2");
        stack2.push("3");

        System.out.println(stack);
        System.out.println(stack2);

        System.out.println("Popped: " + stack.pop());
        System.out.println("Popped: " + stack2.pop());

        System.out.println(stack);
        System.out.println(stack2);

        System.out.println("Peek: " + stack.peek());
        System.out.println("Peek: " + stack2.peek());

        // ======================= TESTS =======================

        Console.header("TESTS: Generic methods");

        // swap: index below zero
        try {
            Integer[] testArray = new Integer[]{1, 2, 3, 4, 5};
            swap(testArray, -1, 2);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // swap: index equal to the length of the array
        try {
            Integer[] testArray = new Integer[]{1, 2, 3, 4, 5};
            swap(testArray, 0, 5);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // swap: array is null
        try {
            Integer[] testArray = null;
            swap(testArray, 0, 1);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // countOccurrences: list is null
        try {
            List<Integer> testList = null;
            countOccurrences(testList, 2);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // countOccurrences: the element can be null, it is counted like any other element
        List<Integer> withNulls = new ArrayList<>(Arrays.asList(1, null, 2, null));
        System.out.println(countOccurrences(withNulls, null) == 2 ? "OK: 2 nulls" : "ERROR: wrong count of nulls");
        System.out.println(countOccurrences(withNulls, 5) == 0 ? "OK: no 5" : "ERROR: wrong count of 5");

        // reverse: the original list stays unchanged
        List<String> original = new ArrayList<>(List.of("a", "b", "c"));
        List<String> reversedCopy = reverse(original);
        System.out.println(reversedCopy.equals(List.of("c", "b", "a")) ? "OK: reversed" : "ERROR: " + reversedCopy);
        System.out.println(original.equals(List.of("a", "b", "c")) ? "OK: original unchanged" : "ERROR: " + original);

        // reverse: empty list
        System.out.println(reverse(new ArrayList<Integer>()).isEmpty() ? "OK: empty list" : "ERROR: should be empty");

        // reverse: list is null
        try {
            List<Integer> testList = null;
            reverse(testList);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        Console.header("TESTS: Bounded types");

        // maxOf: list is empty
        try {
            List<Integer> testList = new ArrayList<>();
            maxOf(testList);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // maxOf: list is null
        try {
            List<Integer> testList = null;
            maxOf(testList);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // isSorted: equal elements
        List<Integer> equalElements = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1));
        System.out.println(isSorted(equalElements) ? "OK: equal elements" : "ERROR: should be sorted");

        // isSorted: one element
        List<Integer> oneElement = new ArrayList<>(List.of(1));
        System.out.println(isSorted(oneElement) ? "OK: one element" : "ERROR: should be sorted");

        // isSorted: empty list
        List<Integer> emptyList = new ArrayList<>();
        System.out.println(isSorted(emptyList) ? "OK: empty list" : "ERROR: should be sorted");

        // isSorted: not sorted lists
        List<Integer> notSorted = new ArrayList<>(Arrays.asList(3, 1, 2));
        System.out.println(!isSorted(notSorted) ? "OK: not sorted (Integer)" : "ERROR: should not be sorted");

        List<Student> notSortedStudents = new ArrayList<>(Arrays.asList(
                new Student("Karolina", 5.0),
                new Student("Konrad", 2.0)));
        System.out.println(!isSorted(notSortedStudents) ? "OK: not sorted (Student)" : "ERROR: should not be sorted");

        // isSorted: list is null
        try {
            List<Integer> testList = null;
            isSorted(testList);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        Console.header("TESTS: Stack");

        // The LIFO order: the last element in is the first one out
        Stack<Integer> lifo = new Stack<>();
        lifo.push(1);
        lifo.push(2);
        lifo.push(3);
        System.out.println(lifo.size() == 3 ? "OK: size is 3" : "ERROR: size is " + lifo.size());
        System.out.println(lifo.pop() == 3 ? "OK: popped 3" : "ERROR: wrong element");
        System.out.println(lifo.pop() == 2 ? "OK: popped 2" : "ERROR: wrong element");
        System.out.println(lifo.pop() == 1 ? "OK: popped 1" : "ERROR: wrong element");
        System.out.println(lifo.isEmpty() ? "OK: stack is empty" : "ERROR: stack is not empty");

        // pop and peek on an empty stack
        Stack<Integer> emptyStack = new Stack<>();
        try {
            emptyStack.pop();
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalStateException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            emptyStack.peek();
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalStateException e) {
            System.out.println("OK: " + e.getMessage());
        }
    }

    // ======================= METHODS =======================

    public static <T> void swap(T[] array, int i, int j) {
        if (array == null) {
            throw new IllegalArgumentException("The array cannot be null");
        }
        if (i >= array.length || j >= array.length || i < 0 || j < 0) {
            throw new IllegalArgumentException("Index: " + i + ", " + j + ", Size: " + array.length);
        }
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Counts the elements equal to the given one (the element can be null, so Objects.equals is used)
    public static <T> int countOccurrences(List<T> givenList, T key) {
        if (givenList == null) {
            throw new IllegalArgumentException("Given list cannot be null");
        }
        int count = 0;
        for (int i = 0; i < givenList.size(); i++) {
            if (Objects.equals(givenList.get(i), key)) {
                count++;
            }
        }
        return count;
    }

    // Returns a NEW reversed list, the original stays unchanged
    public static <T> List<T> reverse(List<T> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("Given list cannot be null");
        }
        List<T> result = new ArrayList<>();
        for (int i = givenList.size() - 1; i >= 0; i--) {
            result.add(givenList.get(i));
        }
        return result;
    }

    public static <T extends Comparable<T>> T maxOf(List<T> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("Given list cannot be null");
        }
        if (givenList.isEmpty()) {
            throw new IllegalArgumentException("Given list cannot be empty");
        }

        T max = givenList.getFirst();
        for (int i = 1; i < givenList.size(); i++) {
            if (max.compareTo(givenList.get(i)) < 0) {
                max = givenList.get(i);
            }
        }
        return max;
    }

    public static <T extends Comparable<T>> boolean isSorted(List<T> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("Given list cannot be null");
        }
        for (int i = 1; i < givenList.size(); i++) {
            if (givenList.get(i).compareTo(givenList.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }
}