package lesson3;

import common.Console;

import java.util.Random;
import java.util.Scanner;

/**
 * Lekcja 3: pętle (for, while) i tablice.
 *
 * Zadania:
 * 1. Tabliczka mnożenia dla liczby n (1-10) oraz pełna tabela 10x10 (pętla zagnieżdżona).
 * 2. Zgadnij liczbę: losowanie 1-100, podpowiedzi, liczba prób.
 * 3. Statystyki tablicy: 5 liczb, suma, średnia, min, max (bez metod bibliotecznych).
 * 4. FizzBuzz dla liczb 1-100.
 */
public class Lesson3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        multiplicationTable(scanner);
        guessTheNumber(scanner);
        arrayStatistics(scanner);
        fizzBuzz();
    }

    static void multiplicationTable(Scanner scanner) {
        Console.header("Zadanie 1: tabliczka mnożenia");
        System.out.print("Podaj liczbę: ");
        int n = scanner.nextInt();
        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " x " + i + " = " + (n * i));
        }

        System.out.println();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.printf("%4d", i * j);
            }
            System.out.println();
        }
    }

    static void guessTheNumber(Scanner scanner) {
        Console.header("Zadanie 2: zgadnij liczbę");
        int secret = new Random().nextInt(1, 101);
        int attempts = 0;

        while (true) {
            System.out.print("Podaj liczbę (1-100): ");
            int guess = scanner.nextInt();
            attempts++; // liczymy próbę zaraz po wczytaniu, także tę trafną

            if (guess == secret) {
                System.out.println("Zgadłeś! Szukana liczba to " + secret + ".");
                break;
            } else if (guess > secret) {
                System.out.println("Za dużo!");
            } else {
                System.out.println("Za mało!");
            }
        }
        System.out.println("Liczba prób: " + attempts);
    }

    static void arrayStatistics(Scanner scanner) {
        Console.header("Zadanie 3: statystyki tablicy");
        int[] numbers = new int[5];
        int sum = 0;

        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Podaj liczbę " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
            sum += numbers[i];
        }

        int min = numbers[0];
        int max = numbers[0];
        for (int number : numbers) {
            if (number < min) {
                min = number;
            }
            if (number > max) {
                max = number;
            }
        }

        // Rzutowanie na double przed dzieleniem, inaczej wynik byłby całkowity.
        double average = (double) sum / numbers.length;

        System.out.println("Suma: " + sum);
        System.out.printf("Średnia: %.2f%n", average);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
    }

    static void fizzBuzz() {
        Console.header("Zadanie 4: FizzBuzz");
        for (int i = 1; i <= 100; i++) {
            // Najpierw przypadek najbardziej szczegółowy (podzielne przez 3 i 5).
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }
}
