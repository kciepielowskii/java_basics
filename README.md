# Java Basics

Zadania z nauki Javy od podstaw (droga do pierwszej pracy jako junior Java developer).
Każda lekcja to osobna klasa z własną metodą `main`, a treść zadań jest w komentarzu nad klasą.

## Struktura

```
src/
├── common/
│   └── Console.java          # pomocnicze wypisywanie nagłówków
├── lesson1/
│   └── Lesson1.java          # zmienne, typy, Scanner, BMI
├── lesson2/
│   └── Lesson2.java          # if/else, switch, rok przestępny
├── lesson3/
│   └── Lesson3.java          # pętle, tablice, FizzBuzz
├── lesson4/
│   └── Lesson4.java          # metody, liczby pierwsze, silnia
├── lesson5/
│   └── Lesson5.java          # String, palindrom, szyfr Cezara, anagramy
├── lesson6/
│   ├── Lesson6.java          # testowanie klas i obiektów
│   ├── Book.java             # klasa Book
│   ├── BankAccount.java      # klasa BankAccount
│   └── Rectangle.java        # klasa Rectangle
├── lesson7/
│   ├── Lesson7.java          # testowanie dziedziczenia i polimorfizmu
│   ├── Shape.java            # klasa bazowa figur geometrycznych
│   ├── Circle.java           # koło
│   ├── Rectangle.java        # prostokąt
│   ├── Square.java           # kwadrat dziedziczący po Rectangle
│   ├── Employee.java         # klasa bazowa pracownika
│   ├── Manager.java          # kierownik z premią
│   ├── Developer.java        # programista z nadgodzinami
│   └── SavingsAccount.java   # konto oszczędnościowe z oprocentowaniem
├── lesson8/
│   ├── Lesson8.java          # testowanie klas abstrakcyjnych i interfejsów
│   ├── Animal.java           # abstrakcyjna klasa bazowa zwierząt
│   ├── Dog.java              # pies
│   ├── Cat.java              # kot
│   ├── Cow.java              # krowa
│   ├── Flyable.java          # interfejs: umie latać
│   ├── Swimmable.java        # interfejs: umie pływać
│   ├── Duck.java             # kaczka (Flyable i Swimmable)
│   ├── Fish.java             # ryba (Swimmable)
│   ├── Plane.java            # samolot (Flyable)
│   └── Student.java          # student sortowany przez Comparable
├── lesson9/
│   └── Lesson9.java          # kolekcje: ArrayList, HashMap, HashSet
├── lesson10/
│   ├── Lesson10.java                   # wyjątki: checked/unchecked, finally, try-with-resources
│   ├── InsufficientFundsException.java # własny wyjątek (checked)
│   ├── Wallet.java                     # portfel z metodą withdraw()
│   └── Resource.java                   # zasób AutoCloseable
├── lesson11/
│   ├── Lesson11.java         # generyki: metody generyczne, typy ograniczone
│   ├── Box.java              # klasa generyczna Box<T>
│   ├── Pair.java             # klasa generyczna Pair<A, B>
│   └── Stack.java            # generyczny stos Stack<T>
├── lesson12/
│   └── Lesson12.java         # lambdy i Stream API: filter, map, sorted, collect, groupingBy
└── lesson13/
    ├── Lesson13.java         # Optional, rekordy, switch expressions (testy i metody)
    ├── Point.java            # record Point z distanceTo() i origin()
    ├── Temperature.java      # record Temperature z walidacją i toFahrenheit()
    └── Product.java          # record Product (nazwa i cena z walidacją)
```

## Uruchomienie

Wymagane: JDK 21 lub nowszy.

W IntelliJ IDEA: otwórz folder projektu, oznacz `src` jako *Sources Root* i uruchom wybraną klasę (zielony trójkąt przy `main`).

Z terminala:

```bash
javac -d out $(find src -name "*.java")
java -cp out lesson13.Lesson13
```

## Postęp

- [x] Lekcja 1: zmienne i typy
- [x] Lekcja 2: instrukcje warunkowe
- [x] Lekcja 3: pętle i tablice
- [x] Lekcja 4: metody
- [x] Lekcja 5: String (palindrom, szyfr Cezara, anagramy)
- [x] Lekcja 6: klasy i obiekty (enkapsulacja, wyjątki)
- [x] Lekcja 7: dziedziczenie i polimorfizm (extends, super, @Override, instanceof)
- [x] Lekcja 8: klasy abstrakcyjne i interfejsy (abstract, interface, Comparable)
- [x] Lekcja 9: kolekcje (ArrayList, HashMap, HashSet)
- [x] Lekcja 10: wyjątki (checked/unchecked, własne wyjątki, finally, try-with-resources)
- [x] Lekcja 11: generyki (klasy generyczne, metody generyczne, typy ograniczone)
- [x] Lekcja 12: lambdy i Stream API (Comparator, removeIf, filter, map, Optional, Collectors)
- [x] Lekcja 13: Optional, rekordy (record), switch expressions (pattern matching, yield)