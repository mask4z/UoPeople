package ren.cs1102.Entities;

import lib.TextIO;

public class Unit3MathProblem {

    public static void main(String[] args) {
        System.out.println("This program will print out 3N+1 sequence");
        System.out.println("for starting values that you specify.");
        System.out.println();
        int K;
        do {
            System.out.println("Enter a starting value.");
            System.out.println("To end the program, put in 0:");
            K = TextIO.getInt();
            if (K > 0) {
                print3NSequence(K);
            }
        } while (K > 0);
    }


    static void print3NSequence(int startingValue) {

        int N;
        int count;

        N = startingValue;

        count = 1;

        System.out.println("The 3N Sequence starting from " + N);
        System.out.println();
        System.out.println(N);

        while (N > 1) {
            if (N % 2 == 1)
                N = 3 * N + 1;
            else
                N = N / 2;
            count++;
            System.out.println(N);
            System.out.println("There were " + count + " terms in the sequence.");
        }
    }
}
