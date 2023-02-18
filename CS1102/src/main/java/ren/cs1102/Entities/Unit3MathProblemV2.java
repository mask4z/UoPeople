package ren.cs1102.Entities;
import lib.TextIO;

public class Unit3MathProblemV2 {

    public static void main(String[] args) {

        System.out.println("This program will print out 3N+1 sequences");
        System.out.println("for starting values that you supply.");
        System.out.println();

        int K;
        do {
            System.out.println("Enter a starting value:");
            System.out.print("To end the program, enter 0: ");
            K = TextIO.getlnInt();
            if (K > 0) {
                print3NSequence(K);
            }
        } while (K > 0);
    }

    static void print3NSequence(int startingValue) {

        int N;
        int count;
        int onLine;

        N = startingValue;
        count = 1;

        System.out.println("The 3N+1 sequence starting from " + N);
        System.out.println();
        System.out.printf("%8d", N);
        onLine = 1;

        while (N > 1) {
            N = nextN(N);
            count++;
            if (onLine == 5) {
                System.out.println();
                onLine = 0;
            }
            System.out.printf("%8d", N);
            onLine++;
        }

        System.out.println();
        System.out.println();
        System.out.println("There were " + count + " terms in the sequence.");
    }

    /**
     * This function perpetuates the 3N+1 sequence;
     * The precondition is that the actual parameter must be of type 'int';
     * The postcondition is that the method will either return 'currentN % 2 == 1'
     * or 'currentN / 2'
     * **/

    static int nextN(int currentN) {
        if (currentN % 2 == 1)
            return 3 * currentN + 1;
        else
            return currentN / 2;
    }
}
