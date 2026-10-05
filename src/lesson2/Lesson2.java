package lesson2;

import common.Console;

import java.util.Scanner;

/**
 * Lekcja 2: instrukcje warunkowe (if/else, switch) i operatory logiczne.
 *
 * Zadania:
 * 1. Kategoria BMI: &lt;18,5 niedowaga, &lt;25 norma, &lt;30 nadwaga, od 30 otyłość.
 * 2. Kalkulator z menu (switch): obsługa dzielenia przez zero i nieznanej operacji.
 * 3. Rok przestępny: podzielny przez 4, ale nie przez 100, chyba że przez 400.
 *    Testy: 1900 (nie), 2000 (tak), 2024 (tak), 2026 (nie).
 */
public class Lesson2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        bmiWithCategory(scanner);
        calculatorWithMenu(scanner);
        leapYear(scanner);
    }

    static void bmiWithCategory(Scanner scanner) {
        Console.header("Zadanie 1: kategoria BMI");
        System.out.print("Podaj wzrost (cm): ");
        int heightCm = scanner.nextInt();
        System.out.print("Podaj wagę (kg): ");
        double weightKg = scanner.nextDouble();

        double heightM = heightCm / 100.0;
        double bmi = weightKg / (heightM * heightM);
        System.out.printf("BMI: %.2f%n", bmi);

        if (bmi < 18.5) {
            System.out.println("Niedowaga");
        } else if (bmi < 25) {
            System.out.println("Norma");
        } else if (bmi < 30) {
            System.out.println("Nadwaga");
        } else {
            System.out.println("Otyłość");
        }
    }

    static void calculatorWithMenu(Scanner scanner) {
        Console.header("Zadanie 2: kalkulator z menu");
        System.out.print("Podaj dwie liczby: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        System.out.print("Podaj operator (+, -, *, /): ");
        char operator = scanner.next().charAt(0);

        String result = switch (operator) {
            case '+' -> "Suma: " + (a + b);
            case '-' -> "Różnica: " + (a - b);
            case '*' -> "Iloczyn: " + (a * b);
            case '/' -> {
                if (b == 0) {
                    yield "Błąd: dzielenie przez 0!";
                }
                yield "Iloraz: " + (a / b);
            }
            default -> "Nieznany operator: " + operator;
        };
        System.out.println(result);
    }

    static void leapYear(Scanner scanner) {
        Console.header("Zadanie 3: rok przestępny");
        System.out.print("Podaj rok: ");
        int year = scanner.nextInt();

        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            System.out.println("Rok jest przestępny.");
        } else {
            System.out.println("Rok nie jest przestępny.");
        }
    }
}
