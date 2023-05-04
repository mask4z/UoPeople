package Unit4.LearningJournal;

import Test.TextIO;

import java.util.TreeSet;


/**
 * This program is a set calculator that can compute
 * the union, intersection, and difference of two sets of
 * non-negative integers.
 * For example:
 * [1, 2, 3] + [4, 0, 2, 3].
 * Spaces are allowed.
 * The program will log an error if it occurs.
 * The program ends when the user inputs an empty line.
 */
public class SetCalculator {

    public static void main(String[] args) {

        System.out.println("This program will compute union, intersection, and difference of sets of integers.");
        System.out.println();
        System.out.println("Hit return if you wish to end the program.");
        System.out.println("Please enter two sets of integers with an operator:\n?");


        while (true) {

            char mainOperator;
            TextIO.skipBlanks();

            if (TextIO.peek() == '\n') {

                // The input line is empty.
                break;
            }

            try {

                calculateSets(); // Reads and processes one line of input.

            } catch (IllegalArgumentException iae) {

                System.out.println("Error: " + iae.getMessage()); //prints error message
            }

            do {
                mainOperator = TextIO.getAnyChar();

            } while (mainOperator != '\n');
        }

    } // end of main() method.


    /**
     * Reads the sets provided from the user's input and perform the calculation.
     *
     * @throws IllegalArgumentException if an operator is not provided.
     */
    private static void calculateSets() {

        TreeSet<Integer> A;
        TreeSet<Integer> B;
        char operator;

        A = readNewSet(); // Read the first set.

        TextIO.skipBlanks();

        if (TextIO.peek() != '*'
                && TextIO.peek() != '+'
                && TextIO.peek() != '-') {

            throw new IllegalArgumentException("Expected *, +, or '-'");
        }

        operator = TextIO.getAnyChar(); // Read the operator.

        B = readNewSet(); // Read the second set.

        TextIO.skipBlanks();

        if (TextIO.peek() != '\n') {

            throw new IllegalArgumentException("Extra unexpected input.");
        }

        // Perform the calculations with the given sets.

        switch (operator) {
            case '+' -> A.addAll(B);
            case '-' -> A.removeAll(B);
            case '*' -> A.retainAll(B);
            default -> {
            } // Do nothing, as no operator was given.
        }
        System.out.print("Value of A is  " + A);

    } // end of calculateSets() method.


    /**
     * Reads a set of integers from the user's input,
     * and stores them in a TreeSet to prevent non-negative numbers.
     * Must contain a starting "[", followed by integers seperated by a comma, and end with a "]".
     *
     * @throws IllegalArgumentException If the input is not of the correct form.
     */
    private static TreeSet<Integer> readNewSet() {

        TreeSet<Integer> set = new TreeSet<>();

        TextIO.skipBlanks();

        if (TextIO.peek() != '[') {
            throw new IllegalArgumentException("Expected '[' at start of set.");
        }
        TextIO.getAnyChar(); // Read the '['.

        TextIO.skipBlanks();
        if (TextIO.peek() == ']') {

            TextIO.getAnyChar(); // Read the ']'.
            return set;
        }

        while (true) {

            TextIO.skipBlanks();

            if (!Character.isDigit(TextIO.peek())) {
                throw new IllegalArgumentException("Expected an integer.");
            }

            int n = TextIO.getInt(); // Read the integer.
            set.add(n);
            TextIO.skipBlanks();

            if (TextIO.peek() == ']') {
                break;
            } else if (TextIO.peek() == ',') {
                TextIO.getAnyChar(); // Read a comma.
            } else
                throw new IllegalArgumentException("Expected ',' or ']'");
        }
        TextIO.getAnyChar(); // Read the final ']'.

        return set;

    } // end readNewSet()
} //end of class