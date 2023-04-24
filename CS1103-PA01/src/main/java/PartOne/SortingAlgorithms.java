package PartOne;

import java.util.Arrays;
import java.util.Random;

/**
 * This program is meant to measure the time it takes for an insertion sorting
 * algorithm to sort an array of 1 000, 10 000, and 100 000. Results:
 *
 * Insertion sorting algorithm: 
 * 1 000 takes 2 millisecond. 
 * 10 000 takes 17 milliseconds. 
 * 100 000 takes 772 milliseconds.
 *
 * Arrays.sort(): 
 * 1 000 takes 0 millisecond. 
 * 10 000 takes 3 milliseconds. 
 * 100 000 takes 31 milliseconds. 
 * 1 000 000 takes 194 milliseconds.
 *
 * @author renos
 */
public class SortingAlgorithms {

    static final Integer arraySize = 1000000;

    public static void main(String[] args) {

        // Creates two int arrays and fill them with random integers.
        Random random = new Random();
        int[] arrayOne = random.ints(arraySize).toArray();
        int[] arrayTwo = new int[arraySize];

        System.arraycopy(arrayOne, 0, arrayTwo, 0, arrayTwo.length);

        //Records the time it takes for an insertion sorting method is used to sort an array of large sizes.
//        long startTime = System.currentTimeMillis();
//        insertionSort(arrayOne);
//        long runTime = System.currentTimeMillis() - startTime;
//        System.out.println(runTime);

        // Records the time it takes for Arrays.sort() to sort an array of large sizes.
        long startTime1 = System.currentTimeMillis();
        Arrays.sort(arrayTwo);
        long runTime1 = System.currentTimeMillis() - startTime1;
        System.out.println(runTime1);
    }

    static void insertionSort(int[] A) {

        int sortedElement;

        for (sortedElement = 1; sortedElement < A.length; sortedElement++) {

            int temp = A[sortedElement];
            int location = sortedElement - 1;

            while (location >= 0 && A[location] > temp) {
                A[location + 1] = A[location];
                location = location - 1;
            }
            A[location + 1] = temp;
        }
    }
}
