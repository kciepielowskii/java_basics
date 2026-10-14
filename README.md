# Java Basics

Zadania z nauki Javy od podstaw (droga do pierwszej pracy jako junior Java developer).
Każda lekcja to osobna klasa z własną metodą `main`, a treść zadań jest w komentarzu nad klasą.

## Struktura

```
src/
├── common/Console.java     # pomocnicze wypisywanie nagłówków
├── lesson1/Lesson1.java    # zmienne, typy, Scanner, BMI
├── lesson2/Lesson2.java    # if/else, switch, rok przestępny
├── lesson3/Lesson3.java    # pętle, tablice, FizzBuzz
├── lesson4/Lesson4.java    # metody, liczby pierwsze, silnia
└── lesson5/Lesson5.java    # String, palindrom, szyfr Cezara, anagramy
└── lesson6/
    ├── Lesson6.java       # testowanie klas i obiektów
    ├── Book.java          # klasa Book
    ├── BankAccount.java   # klasa BankAccount
    └── Rectangle.java     # klasa Rectangle
```

## Uruchomienie

Wymagane: JDK 21.

W IntelliJ IDEA: otwórz folder projektu, oznacz `src` jako *Sources Root* i uruchom wybraną klasę (zielony trójkąt przy `main`).

Z terminala:

```bash
javac -d out $(find src -name "*.java")
java -cp out lesson5.Lesson5
```

## Postęp

- [x] Lekcja 1: zmienne i typy
- [x] Lekcja 2: instrukcje warunkowe
- [x] Lekcja 3: pętle i tablice
- [x] Lekcja 4: metody
- [x] Lekcja 5: String i StringBuilder
- [x] Lekcja 6: Programowanie obiektowe 
- [ ] Kolekcje, wyjątki, generyki
- [ ] Stream API