package lesson9;

import common.Console;
import lesson8.Student;

import java.util.*;

/**
 * Lekcja 9: kolekcje (ArrayList, HashMap, HashSet).
 * Zadania (metody static w tej klasie, w main same testy):
 * 1. Liczby: metoda sumEvenNumbers(List<Integer>), maxOf(List<Integer>) oraz removeNegatives(List<Integer>),
 * która zwraca NOWĄ listę bez liczb ujemnych (oryginał bez zmian).
 * Pusta lista: sumEvenNumbers zwraca 0, a maxOf rzuca IllegalArgumentException.
 * Pokaż też różnicę remove(int index) i remove(Integer.valueOf(x)) na ArrayList<Integer>.
 * 2. Częstość słów: Map<String, Integer> countWords(String text) - zlicza, ile razy występuje
 * każde słowo (ignoruj wielkość liter). Wypisz mapę i słowo, które występuje najczęściej.
 * Przypadki brzegowe: pusty tekst (pusta mapa), kilka spacji między słowami, null.
 * 3. Zbiory: boolean hasDuplicates(List<String>) oraz List<Integer> commonElements(List<Integer>, List<Integer>)
 * (elementy wspólne dla obu list, bez powtórzeń, użyj HashSet).
 * 4. Obiekty: ArrayList<Student> (klasa z lekcji 8). Dodaj 4 studentów, posortuj przez
 * Collections.sort(list) (korzysta z Comparable), usuń studenta po imieniu,
 * wypisz najlepszego i średnią średnich. Przypadek brzegowy: usuwanie imienia, którego nie ma.
 * Tu zostaje tylko main z testami, w tym przypadkami brzegowymi.
 */
public class Lesson9 {

    public static void main(String[] args) {

        // ====================== RESULTS ======================

        Console.header("Numbers");
        List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Sum of even numbers: " + sumEvenNumbers(list1));
        System.out.println("Max: " + maxOf(list1));

        List<Integer> list2 = new ArrayList<>(List.of(1, -2, 3, -4, 5, -6, 7, -8, 9, -10));
        System.out.println("Without negatives: " + removeNegatives(list2));

        // remove(int index) removes the element at that position;
        // remove(Integer.valueOf(x)) removes the first element equal to x
        List<Integer> demo = new ArrayList<>(List.of(10, 20, 30, 2));
        demo.remove(2);
        System.out.println(demo);
        demo.remove(Integer.valueOf(2));
        System.out.println(demo);

        Console.header("Word frequency");
        Map<String, Integer> map1 = countWords("Ala ma ale a kot ma kota");
        System.out.println(map1);
        System.out.println("The most frequent word: " + mostFrequentWord(map1));

        Console.header("Sets");
        List<String> list3 = new ArrayList<>(List.of("Konrad", "Ala", "Kamil", "Aga", "Aga"));
        System.out.println("Has duplicates: " + hasDuplicates(list3));
        System.out.println("Common elements: " + commonElements(list1, list2));

        Console.header("Students list");
        ArrayList<Student> students = new ArrayList<>(List.of(
                new Student("Aga", 2.0),
                new Student("Barbara", 5.0),
                new Student("Kamil", 4.0),
                new Student("Zenek", 3.0)
        ));

        System.out.println("Before sort:");
        for (Student s : students) {
            System.out.println(s);
        }

        Collections.sort(students);
        System.out.println("\nAfter sort (best first):");
        for (int i = students.size() - 1; i >= 0; i--) {
            System.out.println(students.get(i));
        }

        System.out.println("\nRemoved Aga: " + removeByName(students, "Aga"));

        System.out.println();
        for (int i = students.size() - 1; i >= 0; i--) {
            System.out.println(students.get(i));
        }

        Student best = theBestStudent(students);
        System.out.println("The best student: " + best);
        System.out.println("Average: " + countAverageAverages(students));

        // ======================= TESTS =======================

        Console.header("TESTS: Numbers");

        // Let's assume the list is null
        try {
            List<Integer> test = null;
            sumEvenNumbers(test);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Let's assume the list is null
        try {
            List<Integer> test = null;
            maxOf(test);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Let's assume the list is empty
        try {
            List<Integer> test = new ArrayList<>();
            maxOf(test);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Let's assume the list is null
        try {
            List<Integer> test = null;
            removeNegatives(test);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // removeNegatives must not change the original list
        List<Integer> original = new ArrayList<>(List.of(-1, 5, -2, 0));
        System.out.println("removeNegatives: " + removeNegatives(original));
        System.out.println("original: " + original);

        System.out.println("sumEvenNumbers (empty): " + sumEvenNumbers(new ArrayList<>()));
        System.out.println("maxOf (negatives): " + maxOf(List.of(-5, -2, -9)));

        Console.header("TESTS: Word frequency");

        // Let's assume the text is null
        try {
            countWords(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        System.out.println("countWords (empty): " + countWords(""));
        System.out.println("countWords (spaces): " + countWords("  ala   ALA ma "));

        Console.header("TESTS: Sets");
        System.out.println("hasDuplicates (none): " + hasDuplicates(List.of("a", "b")));
        System.out.println("commonElements (none): " + commonElements(List.of(1, 2), List.of(3)));

        Console.header("TESTS: Students list");

        // Let's assume the list is null
        try {
            List<Student> test = null;
            removeByName(test, "Kamil");
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Let's assume the name is null
        try {
            removeByName(students, null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Looking for a name where it is not there
        if (removeByName(students, "Jack")) {
            System.out.println("ERROR: the name was found, but it should not exist");
        } else {
            System.out.println("OK: the name hasn't been found");
        }
    }

    // ======================= METHODS =======================

    // A separate condition for empty lists is unnecessary,
    // because the loop will not execute and will result in 0.
    public static int sumEvenNumbers(List<Integer> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("The object cannot be null");
        }
        int sum = 0;
        for (int number : givenList) {
            if (number % 2 == 0) {
                sum += number;
            }
        }
        return sum;
    }

    public static int maxOf(List<Integer> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("The object cannot be null");
        }
        if (givenList.isEmpty()) {
            throw new IllegalArgumentException("The object cannot be empty");
        }
        int max = givenList.getFirst();
        for (int i : givenList) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    public static List<Integer> removeNegatives(List<Integer> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("The object cannot be null");
        }
        List<Integer> result = new ArrayList<>();
        for (int number : givenList) {
            if (number >= 0) {
                result.add(number);
            }
        }
        return result;
    }

    public static Map<String, Integer> countWords(String text) {
        if (text == null) {
            throw new IllegalArgumentException("The object cannot be null");
        }
        if (text.isBlank()) {
            return new HashMap<>();
        }
        String[] splitText = text.toLowerCase().trim().split("\\s+");
        HashMap<String, Integer> result = new HashMap<>();

        for (String word : splitText) {
            result.put(word, result.getOrDefault(word, 0) + 1);
        }
        return result;
    }

    public static String mostFrequentWord(Map<String, Integer> words) {
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("The map cannot be null or empty");
        }
        String best = null;
        int max = 0;
        for (String word : words.keySet()) {
            if (words.get(word) > max) {
                max = words.get(word);
                best = word;
            }
        }
        return best;
    }

    public static boolean hasDuplicates(List<String> givenList) {
        if (givenList == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
        if (givenList.isEmpty()) {
            return false;
        }
        HashSet<String> noDuplicates = new HashSet<>();
        for (String s : givenList) {
            if (!noDuplicates.add(s)) {
                return true;
            }
        }
        return false;
    }

    public static List<Integer> commonElements(List<Integer> givenList1, List<Integer> givenList2) {
        if (givenList1 == null || givenList2 == null) {
            throw new IllegalArgumentException("The lists cannot be null");
        }
        if (givenList1.isEmpty() || givenList2.isEmpty()) {
            return new ArrayList<>();
        }
        HashSet<Integer> commonElementsList = new HashSet<>(givenList1);
        commonElementsList.retainAll(givenList2);
        return new ArrayList<>(commonElementsList);
    }

    public static boolean removeByName(List<Student> students, String name) {
        if (students == null || name == null) {
            throw new IllegalArgumentException("The list or name cannot be null");
        }
        if (students.isEmpty() || name.isBlank()) {
            return false;
        }
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(name)) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public static Student theBestStudent(List<Student> students) {
        if (students == null || students.isEmpty()) {
            throw new IllegalArgumentException("The list cannot be null or empty");
        }
        Student best = students.getFirst();
        for (Student s : students) {
            if (s.getAverage() > best.getAverage()) {
                best = s;
            }
        }
        return best;
    }

    public static double countAverageAverages (List<Student> students) {
        if (students == null || students.isEmpty()) {
            throw new IllegalArgumentException("The list cannot be null or empty");
        }
        double sum = 0.0;
        for (Student s : students) {
            sum += s.getAverage();
        }
        return sum / students.size();
    }
}