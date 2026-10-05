package common;

/**
 * Drobne narzędzia do wypisywania w konsoli, współdzielone przez wszystkie lekcje.
 */
public final class Console {

    private Console() {
    }

    /** Wypisuje nagłówek zadania, żeby wynik w konsoli był czytelny. */
    public static void header(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
