package lesson8;

import common.Console;

import java.util.Arrays;

/**
 * Lekcja 8: klasy abstrakcyjne i interfejsy (abstract, interface, implements, Comparable).
 * Zadania:
 * 1. Zwierzęta: abstrakcyjna klasa Animal (imię; konstruktor; abstrakcyjna metoda makeSound();
 *    zwykła metoda describe() zwracająca np. "Rex says Woof" przez makeSound()).
 *    Podklasy: Dog, Cat, Cow. Imię nie może być puste (IllegalArgumentException).
 *    Tablica Animal[] z 4 zwierząt: wypisz describe() każdego.
 *    W komentarzu zostaw linię z "new Animal(...)" i dopisz, jaki błąd zgłasza kompilator.
 * 2. Interfejsy: Flyable (void fly()), Swimmable (void swim()).
 *    Klasy: Duck (oba interfejsy), Fish (tylko Swimmable), Plane (tylko Flyable).
 *    Tablica Flyable[] i tablica Swimmable[]: wywołaj metody polimorficznie.
 *    Dodatkowo: tablica Object[] z trzema obiektami i pętla z instanceof,
 *    która dla każdego sprawdza, czy umie latać i czy umie pływać.
 * 3. Sortowanie: Student (imię, średnia ocen 2.0-5.0) implements Comparable<Student>.
 *    compareTo porównuje średnie. Posortuj Student[] przez Arrays.sort() i wypisz od najlepszego
 *    do najgorszego. Przypadki brzegowe: dwóch studentów z tą samą średnią,
 *    tablica z jednym studentem, średnia poza zakresem -> wyjątek.
 * Tu zostaje tylko main z testami, w tym przypadkami brzegowymi.
 */
public class Lesson8 {

    public static void main(String[] args) {
        Console.header("Animals");
        Animal[] animals = {
                new Cat("Kot"),
                new Dog ("Pies"),
                new Cow ("Krowa"),
                new Cat ("Cat")
        };

        for (Animal a : animals) {
            System.out.println(a.describe());
        }

        // Animal x = new Animal("Zwierzak");   // błąd: 'Animal' is abstract; cannot be instantiated

        // Tests
        try {
            Dog d1 = new Dog(null);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            Dog d1 = new Dog("");
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        Console.header("Flyable / Swimmable");
        Flyable[] flyables = {
                new Duck(),
                new Plane()
        };

        Swimmable[] swimmables = {
                new Duck(),
                new Fish()
        };

        for (Flyable f : flyables) {
            f.fly();
        }

        for (Swimmable s : swimmables) {
            s.swim();
        }

        Duck d1 = new Duck();
        Fish f1 = new Fish();
        Plane p1 = new Plane();

        Object[] objects = {d1, f1, p1};

        for (Object obj : objects) {
            System.out.println(obj.getClass().getSimpleName()
                    + ": can fly = " + (obj instanceof Flyable)
                    + ", can swim = " + (obj instanceof Swimmable));
        }

        Console.header("Students");
        Student[] students = {
                new Student("Konrad", 4.0),
                new Student("Karolina", 5.0),
                new Student("Seweryn", 2.0),
                new Student("Michał", 3.0)
        };

        System.out.println("Before sort:");
        for (Student st : students) {
            System.out.println(st);
        }
        System.out.println();
        Arrays.sort(students);

        System.out.println("After sort: ");
        for (int i = students.length - 1; i >= 0; i--) {
            System.out.println(students[i]);
        }

        try {
            Student s1 = new Student("Emil", 1.9);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            Student s1 = new Student("Emil", 5.1);
            System.out.println("ERROR: exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        Student[] one = { new Student("Emil", 3.0) };
        Arrays.sort(one);
        System.out.println(Arrays.toString(one));

        Student[] two = { new Student("Ala", 4.0), new Student("Ola", 4.0) };
        Arrays.sort(two);
        System.out.println(Arrays.toString(two));
    }
}