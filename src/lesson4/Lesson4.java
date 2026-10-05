package lesson4;

import common.Console;

import java.util.Arrays;

/**
 * Lekcja 4: metody. Refaktoryzacja wcześniejszych zadań na małe, nazwane funkcje.
 *
 * Zadania:
 * 1. isLeapYear, bmiCategory, calculate, average.
 * 2. isPrime i wypisanie liczb pierwszych od 1 do 100.
 * 3. reverse: nowa tablica z elementami w odwrotnej kolejności.
 * 4. Silnia iteracyjnie i rekurencyjnie (z kontrolą przepełnienia).
 */
public class Lesson4 {

    public static void main(String[] args) {
        Console.header("Rok przestępny");
        for (int year : new int[]{1900, 2000, 2024, 2026}) {
            System.out.println(year + ": " + isLeapYear(year));
        }

        Console.header("Kategorie BMI");
        for (double bmi : new double[]{17.0, 18.5, 24.9, 25.0, 29.9, 30.0}) {
            System.out.println(bmi + ": " + bmiCategory(bmi));
        }

        Console.header("Kalkulator");
        System.out.println("10 + 5 = " + calculate(10, 5, '+'));
        System.out.println("10 / 4 = " + calculate(10, 4, '/'));

        Console.header("Średnia");
        System.out.println(average(new double[]{1, 2, 3, 4, 5}));

        Console.header("Liczby pierwsze 1-100");
        printPrimes(100);

        Console.header("Odwracanie tablicy");
        int[] original = {1, 2, 3, 4, 10};
        int[] reversed = reverse(original);
        System.out.println("Oryginał: " + Arrays.toString(original));
        System.out.println("Odwrócona: " + Arrays.toString(reversed));

        Console.header("Silnia");
        System.out.println("5! iteracyjnie: " + factorial(5));
        System.out.println("5! rekurencyjnie: " + factorialRecursive(5));
        System.out.println("20! = " + factorial(20));
        try {
            factorial(21);
        } catch (ArithmeticException e) {
            System.out.println("21! przekracza zakres long: " + e.getMessage());
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public static String bmiCategory(double bmi) {
        if (bmi < 18.5) {
            return "Niedowaga";
        } else if (bmi < 25) {
            return "Norma";
        } else if (bmi < 30) {
            return "Nadwaga";
        } else {
            return "Otyłość";
        }
    }

    public static double calculate(double a, double b, char operator) {
        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) {
                    throw new ArithmeticException("Dzielenie przez zero");
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Nieznany operator: " + operator);
        };
    }

    public static double average(double[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Tablica jest null lub pusta");
        }
        double sum = 0.0;
        for (double number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        // (long) chroni przed przepełnieniem i/*i dla dużych n.
        for (int i = 2; (long) i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void printPrimes(int limit) {
        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    /** Zwraca nową tablicę, oryginał pozostaje bez zmian. */
    public static int[] reverse(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Tablica jest null");
        }
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[array.length - 1 - i];
        }
        return result;
    }

    /** Silnia pętlą. Przy przepełnieniu long rzuca ArithmeticException (dla n > 20). */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Silnia nie istnieje dla liczb ujemnych");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = Math.multiplyExact(result, i);
        }
        return result;
    }

    /** Silnia rekurencyjnie. Dla bardzo dużych n skończy się StackOverflowError. */
    public static long factorialRecursive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Silnia nie istnieje dla liczb ujemnych");
        }
        if (n <= 1) {
            return 1;
        }
        return Math.multiplyExact(n, factorialRecursive(n - 1));
    }
}
