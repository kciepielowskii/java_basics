package lesson5;


import common.Console;

import java.util.Arrays;

/**
 * Lekcja 5: String i StringBuilder.
 *
 * Zadania:
 * 1. Palindrom: czy tekst czyta się tak samo od przodu i od tyłu (ignorując wielkość liter i znaki niebędące literami).
 * 2. Liczenie: liczba samogłosek i liczba słów w tekście.
 * 3. Szyfr Cezara: szyfrowanie i deszyfrowanie liter a-z i A-Z z przesunięciem, inne znaki bez zmian.
 * 4. Anagramy: czy dwa teksty składają się z tych samych liter.
 */

public class Lesson5 {
    public static void main (String[] args) {
        Console.header("Palindrom");
        System.out.println(isPalindrom("Kobyła ma mały bok."));
        System.out.println(isPalindrom("Muzo raz daj jad za rozum"));
        System.out.println(isPalindrom("abc a"));
        System.out.println(isPalindrom("Java"));
        System.out.println(isPalindrom(""));

        Console.header("Liczenie ");
        System.out.println("Ilość samogłosek: " + countVowel("Anna ma kota"));
        System.out.println("Ilość samogłosek: " + countVowel("aąeęioóuy"));
        System.out.println("Ilość słów: " + countWords("woda"));
        System.out.println("Ilość słów: " + countWords("  ala   ma kota "));
        System.out.println("Ilość słów: " + countWords("   "));

        Console.header("Szyfr Cezara");
        System.out.println(encrypt("Hello World!", 1));
        System.out.println(decrypt("Ifmmp Xpsme!", 1));
        System.out.println(encrypt("xyz XYZ", 3));
        System.out.println(encrypt("abc", -3));
        System.out.println(decrypt(encrypt("Hello, World!", 29), 29));

        Console.header("Anagramy");
        System.out.println(areAnagrams("karate", "ratake"));
        System.out.println(areAnagrams("kajak", "kakaj"));
        System.out.println(areAnagrams("ala", "ola"));
    }

    public static boolean isPalindrom (String text) {
        if (text == null) {
            return false;
        }
        String textCaseInsensitive = text.toLowerCase();
        int j = textCaseInsensitive.length() - 1;
        for (int i = 0; i < j; i++) {
            if (!Character.isLetter(textCaseInsensitive.charAt(i))) {
                continue;
            }
            while (j > i && !Character.isLetter(textCaseInsensitive.charAt(j))) {
                j--;
            }
            if (textCaseInsensitive.charAt(i) != textCaseInsensitive.charAt(j)) {
                return false;
            }
            j--;
        }
        return true;
    }

    public static int countVowel (String text) {
        if (text == null) {
            return 0;
        }
        String lowerText = text.toLowerCase();
        int result = 0;
        for (int i = 0; i < lowerText.length(); i++) {
            if (isVowel(lowerText.charAt(i))) {
                result++;
            }
        }
        return result;
    }

    // Funkcja pomocnicza do metody countVowel
    private static boolean isVowel (char ch) {
        return ch == 'a' || ch == 'ą' || ch == 'e' || ch == 'ę' || ch == 'i'
                || ch == 'o' || ch == 'ó' || ch == 'u' || ch == 'y';
    }

    public static int countWords (String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
    }


    public static String encrypt(String text, int shift) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        String result = "";
        shift = ((shift % 26) + 26) % 26;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                ch = (char) ('a' + (ch - 'a' + shift) % 26);
            } else if (ch >= 'A' && ch <= 'Z') {
                ch = (char) ('A' + (ch - 'A' + shift) % 26);
            }

            result += ch;
        }
        return result;
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, -shift);
    }

    public static boolean areAnagrams(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }
        char [] tabA = a.toLowerCase().toCharArray();
        char [] tabB = b.toLowerCase().toCharArray();

        Arrays.sort(tabA);
        Arrays.sort(tabB);

        return Arrays.equals(tabA, tabB);
    }
}