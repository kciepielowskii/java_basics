package lesson1;

import common.Console;

import java.util.Scanner;

/**
 * Lekcja 1: zmienne, typy podstawowe, wczytywanie danych przez Scanner.
 *
 * Zadania:
 * 1. Wypisz imię, miasto i ulubiony język programowania (trzy zmienne String).
 * 2. Kalkulator: dwie liczby (double), wypisz sumę, różnicę, iloczyn i iloraz.
 * 3. Przelicznik: stopnie Celsjusza na Fahrenheita (C * 9 / 5 + 32).
 * 4. BMI: wzrost w cm (int) i waga w kg (double), BMI = waga / (wzrost w m)^2.
 */
public class Lesson1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printPersonalInfo();
        calculator(scanner);
        temperatureConverter(scanner);
        bmi(scanner);
    }

    static void printPersonalInfo() {
        Console.header("Zadanie 1: dane osobowe");
        String name = "Konrad";
        String city = "Gorlice";
        String language = "Java";

        System.out.println(name);
        System.out.println(city);
        System.out.println(language);
    }

    static void calculator(Scanner scanner) {
        Console.header("Zadanie 2: kalkulator");
        System.out.print("Podaj dwie liczby: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();

        System.out.printf("Suma: %.2f%n", a + b);
        System.out.printf("Różnica: %.2f%n", a - b);
        System.out.printf("Iloczyn: %.2f%n", a * b);
        // Dla double dzielenie przez 0 daje Infinity/NaN, a nie wyjątek.
        System.out.printf("Iloraz: %.2f%n", a / b);
    }

    static void temperatureConverter(Scanner scanner) {
        Console.header("Zadanie 3: Celsjusz na Fahrenheita");
        System.out.print("Podaj temperaturę w stopniach Celsjusza: ");
        double celsius = scanner.nextDouble();

        double fahrenheit = celsius * 9 / 5 + 32;
        System.out.printf("Temperatura: %.1f °F%n", fahrenheit);
    }

    static void bmi(Scanner scanner) {
        Console.header("Zadanie 4: BMI");
        System.out.print("Podaj wzrost (cm): ");
        int heightCm = scanner.nextInt();
        System.out.print("Podaj wagę (kg): ");
        double weightKg = scanner.nextDouble();

        // Dzielenie przez 100.0, a nie 100: inaczej dzielenie całkowite obcina wynik.
        double heightM = heightCm / 100.0;
        double bmi = weightKg / (heightM * heightM);
        System.out.printf("BMI: %.2f%n", bmi);
    }
}
