package lesson10;

import common.Console;

/**
 * Lesson 10: exceptions in detail (checked/unchecked, custom exceptions, finally, try-with-resources).
 * Tasks:
 * 1. Parsing: method parseAge(String text) returns an int. It uses Integer.parseInt().
 * The age must be in the range 0-150, otherwise throw IllegalArgumentException with a clear message.
 * Tests: "25", "0", "150", "abc", "", "-5", "200", null.
 * In main, catch NumberFormatException and IllegalArgumentException in two separate catch blocks
 * and explain in a comment why the order matters
 * (NumberFormatException extends IllegalArgumentException).
 * 2. Custom exception: InsufficientFundsException extends Exception (checked), with a field missing
 * (how much is missing) and a getter. Class Wallet (owner, balance, validation like in lesson 6),
 * method withdraw(double amount) declares throws InsufficientFundsException.
 * Amount <= 0 -> IllegalArgumentException (unchecked). Not enough funds -> InsufficientFundsException
 * with a message and the missing amount. Edge cases: withdrawing exactly the whole balance (OK),
 * withdrawing 0.01 more than the balance (exception, check getMissing()).
 * 3. finally and try-with-resources: class Resource implements AutoCloseable (name; the constructor prints
 * "open <name>", close() prints "close <name>"). Show the order of the printouts for:
 * a) try without an exception, b) try with an exception inside, c) two resources in one try-with-resources
 * (closed in reverse order). Also add a method with a finally block that runs
 * despite a return in try, and print what you see.
 * Only main with tests stays here, including edge cases.
 */
public class Lesson10 {

    public static void main(String[] args) {

        // ====================== RESULTS ======================

        Console.header("Parsing");
        String[] inputs = {"25", "0", "150", "abc", "", "-5", "200", null};

        // The order of catch blocks matters: NumberFormatException is a subclass of IllegalArgumentException,
        // so the more specific one must come first. If IllegalArgumentException were first, it would catch
        // NumberFormatException too, and the second block would never run (the compiler reports:
        // "Exception 'java.lang.NumberFormatException' has already been caught").

        for (String input : inputs) {
            try {
                System.out.println("\"" + input + "\" -> " + parseAge(input));
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" -> NumberFormatException: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("\"" + input + "\" -> IllegalArgumentException: " + e.getMessage());
            }
        }

        Console.header("Wallet");

        // Edge case 1: withdrawing the whole balance is allowed
        try {
            Wallet w = new Wallet("Konrad", 1000);
            w.withdraw(1000);
            System.out.println(w.getAmount() == 0 ? "OK: balance is 0" : "ERROR: " + w.getAmount());
        } catch (InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // Edge case 2: withdrawing 0.01 more than the balance throws a checked exception
        try {
            Wallet test = new Wallet("Konrad", 1000);
            test.withdraw(1000.01);
            System.out.println("ERROR: the exception was not thrown");
        } catch (InsufficientFundsException e) {
            System.out.println("OK: " + e.getMessage());
            System.out.printf("Missing: %.2f$%n", e.getMissing());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        Console.header("Resources");

        // a) Try without an exception
        System.out.println("a) No exception");

        try (Resource r = new Resource("A")) {
            System.out.println("inside try");
        }

        // b) Try with an exception (the resource is closed BEFORE the catch block runs)
        System.out.println("\nb) Exception inside try");

        try (Resource r = new Resource("B")) {
            System.out.println("inside try");
            throw new RuntimeException("Test exception");
        } catch (RuntimeException e) {
            System.out.println("caught: " + e.getMessage());
        }

        // c) Two resources - they are closed in reverse order
        System.out.println("\nc) Two resources");

        try (Resource first = new Resource("First");
             Resource second = new Resource("Second")) {
            System.out.println("inside try");
        }

        // d) Finally runs despite the return
        System.out.println("\nd) Finally and return");

        int result = testFinally();
        System.out.println("returned: " + result);

        // ======================= TESTS =======================

        Console.header("TESTS: Wallet");

        // The constructor: owner is null
        try {
            Wallet test = new Wallet(null, 1000);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // The constructor: owner is blank
        try {
            Wallet test = new Wallet(" ", 1000);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // The constructor: negative balance
        try {
            Wallet test = new Wallet("Konrad", -1);
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // Partial withdrawal
        try {
            Wallet w = new Wallet("Konrad", 1000);
            w.withdraw(300);
            System.out.println(w.getAmount() == 700 ? "OK: balance is 700" : "ERROR: " + w.getAmount());
        } catch (InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // Amount 0 and negative -> IllegalArgumentException
        double[] badAmounts = {0, -5};
        for (double bad : badAmounts) {
            try {
                new Wallet("Konrad", 1000).withdraw(bad);
                System.out.println("ERROR: the exception was not thrown");
            } catch (IllegalArgumentException e) {
                System.out.println("OK: " + e.getMessage());
            } catch (InsufficientFundsException e) {
                System.out.println("ERROR: wrong exception: " + e.getMessage());
            }
        }

        Console.header("TESTS: Resources");

        // The constructor throws before the object exists, so neither "open" nor "close" is printed
        try (Resource r = new Resource(null)) {
            System.out.println("ERROR: the exception was not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }
    }

    // ======================= METHODS =======================

    public static int parseAge(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("The text cannot be null or empty");
        }
        String trimText = text.trim();
        int result = Integer.parseInt(trimText);
        if (result < 0 || result > 150) {
            throw new IllegalArgumentException("The age must be in the range 0 - 150, but was " + result);
        }
        return result;
    }

    public static int testFinally() {
        try {
            System.out.println("inside try — returning 10");
            return 10;
        } finally {
            System.out.println("inside finally");
        }
    }
}