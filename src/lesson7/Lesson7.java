package lesson7;

import common.Console;

/**
 * Lekcja 7: dziedziczenie i polimorfizm (extends, super, @Override, instanceof).
 * Zadania:
 * 1. Figury: klasa Shape z metodą getArea() zwracającą 0 i toString().
 *    Podklasy: Circle (promień, Math.PI), Rectangle (szerokość, wysokość)
 *    oraz Square extends Rectangle (konstruktor wywołuje super(side, side)).
 *    Wymiary muszą być dodatnie (IllegalArgumentException).
 *    Tablica Shape[] z 5 figur: wypisz je, policz sumę pól i wskaż figurę o największym polu.
 * 2. Pracownicy: Employee (imię, pensja podstawowa, getSalary()),
 *    Manager extends Employee (premia; getSalary() = super.getSalary() + premia),
 *    Developer extends Employee (liczba nadgodzin i stawka za godzinę; getSalary() = pensja + nadgodziny).
 *    Tablica Employee[]: wypisz pracowników, policz sumę wypłat dla firmy i wskaż najlepiej zarabiającego.
 * 3. SavingsAccount extends BankAccount (z lekcji 6): oprocentowanie w procentach (nie może być ujemne).
 *    Metoda addInterest() dopisuje odsetki (getBalance() * rate / 100) przez deposit().
 *    toString() używa super.toString() i dodaje informację o oprocentowaniu.
 *    Uwaga: konstruktor BankAccount musi być public, a w SavingsAccount potrzebny jest import lesson6.BankAccount.
 * Każda klasa w osobnym pliku w pakiecie lesson7. Tu zostaje tylko main z testami, w tym przypadkami brzegowymi.
 */
public class Lesson7 {

    public static void main(String[] args) {
        Console.header("Shapes");
        Shape[] shapes = {
                new Circle("Circle", 3),
                new Rectangle("Rectangle", 5, 4),
                new Square("Square", 10),
                new Rectangle("Rectangle", 6, 5),
                new Square("Square", 2)
        };

        for (Shape sh : shapes) {
            System.out.println(sh);
        }
        double sumAllOfAreas = 0;
        Shape largest = shapes[0];
        for (Shape sh : shapes) {
            sumAllOfAreas += sh.getArea();
            if (sh.getArea() > largest.getArea()) {
                largest = sh;
            }
        }
        System.out.printf("Sum of the areas of all the figures = %.2f", sumAllOfAreas);
        System.out.println("\nThe largest shape = " + largest);

        System.out.println();
        try {
            Shape sh1 = new Circle("Circle", 0);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            Shape sh2 = new Rectangle("Rectangle", -1, 5);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            Shape sh3 = new Square("Square", 0);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        System.out.println();
        Shape sh4 = new Square("Square", 4);
        Shape sh5 = new Circle("Circle", 1);

        System.out.println("Area of a square(4) = " + sh4.getArea());
        System.out.println("(Area of a circle(1) == Math.PI) = " + (sh5.getArea() == Math.PI));


        Console.header("Employees");
        Employee[] employees = {
                new Employee("Jan", 7500),
                new Manager("Piotr", 7500, 2500),
                new Developer("Kamil", 5000, 25, 35)
        };

        for (Employee e : employees) {
            System.out.println(e);
        }

        System.out.println();
        double sumGrossSalary = 0;
        Employee employee = employees[0];
        for (Employee e : employees) {
            sumGrossSalary += e.getSalary();
            if (employee.getSalary() < e.getSalary()) {
                employee = e;
            }
        }
        System.out.printf("Sum of gross salaries: %.2f%n", sumGrossSalary);
        System.out.println("The highest-earning: " + employee);

        System.out.println();
        try {
            Employee e1 = new Employee("Michał", -1000);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            Employee e2 = new Manager("Konrad", 4500, -1);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            Employee e3 = new Developer("Paulina", 6000, -1, 35);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            Employee e4 = new Developer("Paulina", 6000, 10, 0);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        Employee e5 = new Manager("Alexander", 4500, 0);
        System.out.println(e5.getSalary() == 4500 ? "OK: bonus 0" : "ERROR: " + e5.getSalary());

        Employee e6 = new Developer("Dominika", 6000, 0, 35);
        System.out.println(e6.getSalary() == 6000 ? "OK: no overtime" : "ERROR: " + e6.getSalary());

        Console.header("SavingsAccount");

        SavingsAccount account1 = new SavingsAccount("Konrad", 10000, 10);
        System.out.println("Amount of interest: " + account1.addInterest() + "$");

        System.out.println();
        System.out.println(account1);

        System.out.println();
        account1.deposit(5000);
        System.out.println(account1.getBalance() == 16000 ? "OK: Met condition" : "ERROR: The amounts are different");
        account1.withdraw(1000);
        System.out.println(account1.getBalance() == 15000 ? "OK: Met condition" : "ERROR: The amounts are different");

        try {
            SavingsAccount account2 = new SavingsAccount("Małgorzata", 5000, -1);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        SavingsAccount account3 = new SavingsAccount("Kamila", 2500, 0);
        double interest = account3.addInterest();

        System.out.println(interest == 0
                ? "OK: no interest at 0%"
                : "ERROR: " + interest);

        System.out.println(account3.getBalance() == 2500
                ? "OK: balance unchanged"
                : "ERROR: " + account3.getBalance());
    }
}