package lesson6;

import common.Console;

/**
 * Lekcja 6: klasy i obiekty (pola, konstruktory, this, enkapsulacja, toString).
 * Zadania:
 * 1. Book: klasa z tytułem, autorem i liczbą stron (pola prywatne, konstruktor, gettery, setter z walidacją, toString).
 *    Utwórz 3 książki, wypisz je, zmień liczbę stron setterem i sprawdź, że wartość ujemna rzuca wyjątek.
 * 2. BankAccount: pola prywatne owner (String) i balance (double).
 *    Metody: deposit(double amount), withdraw(double amount), getBalance(), toString().
 *    Wpłata i wypłata muszą być większe od zera, a wypłata nie może przekroczyć salda
 *    (w błędnych przypadkach rzuć IllegalArgumentException z sensownym komunikatem).
 * 3. Rectangle: pola width i height (muszą być dodatnie, sprawdź w konstruktorze).
 *    Metody: getArea(), getPerimeter(), isSquare(), toString().
 * 4. Tablica obiektów: tablica Book[] z 5 książkami. W pętli wypisz wszystkie,
 *    znajdź najgrubszą książkę i policz średnią liczbę stron.
 * Każda klasa (Book, BankAccount, Rectangle) w osobnym pliku w pakiecie lesson6.
 * Tu zostaje tylko main z testami, w tym przypadkami brzegowymi.
 */
public class Lesson6 {

    public static void main(String[] args) {
        Console.header("Book");
        Book b1 = new Book("Władca Pierścieni", "J.R.R. Tolkien", 150);
        Book b2 = new Book("Rok 1984", "George Orwell", 250);
        Book b3 = new Book("Lalka", "Bolesław Prus", 510);
        Book b4 = new Book("Mistrz i Małgorzata", "Michaił Bułhakow", 1000);
        Book b5 = new Book("Quo vadis", "Henryk Sienkiewicz", 309);

        // Display all objects
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b4);
        System.out.println(b5);

        // Exception try/catch
        try {
            b1.setNumberOfPages(-50);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            b1.setNumberOfPages(0);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Getters
        System.out.println();
        System.out.println("Author: " + b1.getAuthor());
        System.out.println("Title: " + b1.getTitle());
        System.out.println("Pages: " + b1.getPages());

        // Correct working method
        b1.setNumberOfPages(50);
        System.out.println("Pages after change: " + b1.getPages());


        Console.header("BankAccount");
        BankAccount ba1 = new BankAccount("Konrad", 20000);
        BankAccount ba2 = new BankAccount("Alexander", 100);

        System.out.println(ba1);
        System.out.println(ba2);


        // Exception try/catch
        try {
            BankAccount ba3 = new BankAccount("Kamil", -5000);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }
        try {
            ba1.deposit(0);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            ba1.deposit(-50);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            ba2.withdraw(0);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            ba2.withdraw(-50);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            ba2.withdraw(200);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Getters
        System.out.println();
        System.out.println("Owner: " + ba1.getOwner());
        System.out.println("Balance: " + ba1.getBalance());

        // Correct working method
        System.out.println();
        ba1.withdraw(5000);
        System.out.println("Balance after change: " + ba1.getBalance() + "$");
        ba1.deposit(2500);
        System.out.println("Balance after change: " + ba1.getBalance() + "$");


        Console.header("Rectangle");
        Rectangle r1 = new Rectangle(5, 5);
        Rectangle r2 = new Rectangle(10, 2);

        // Display all objects
        System.out.println(r1);
        System.out.println(r2);

        // Exception try/catch
        try {
            Rectangle r3 = new Rectangle(0, 5);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }
        try {
            Rectangle r4 = new Rectangle(5, -1);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Getters
        System.out.println();
        System.out.println("Width: " + r1.getWidth());
        System.out.println("Height: " + r1.getHeight());

        // Correct working method
        System.out.println();
        System.out.println("R1 is square: " + r1.isSquare());
        System.out.println("R2 is square: " + r2.isSquare());
        System.out.println("R1 Area: " + r1.getArea());
        System.out.println("R1 Perimeter: " + r1.getPerimeter());
        System.out.println();
        System.out.println("R2 Area: " + r2.getArea());
        System.out.println("R2 Perimeter: " + r2.getPerimeter());

        Console.header("Array of objects");
        Book [] tab = new Book[5];
        tab[0] = b1;
        tab[1] = b2;
        tab[2] = b3;
        tab[3] = b4;
        tab[4] = b5;

        // Display all objects
        for (Book t : tab) {
            System.out.println(t);
        }

        // Looking for the thickest book method
        int maxPages= tab[0].getPages();
        int idx = 0;
        for (int i = 1; i < tab.length; i++) {
            if (tab[i].getPages() > maxPages) {
                maxPages = tab[i].getPages();
                idx = i;
            }
        }
        System.out.println("The thickest book: " + tab[idx].getTitle());
        System.out.println("Number of pages: " + maxPages);

        // Calculating the average of all pages method
        int sumPages = 0;
        for (Book t : tab) {
            sumPages += t.getPages();
        }
        double average = (double) sumPages / tab.length;
        System.out.println();
        System.out.printf("Average number of pages: %.1f%n", average);
    }
}