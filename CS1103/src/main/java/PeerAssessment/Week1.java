package PeerAssessment;

/**
 * Array_size = 1000: Insertion sort time: 2 miliseconds, Arrays.sort() time: 0
 * miliseconds Array_size = 10000: Insertion sort time: 24 miliseconds,
 * Arrays.sort() time: 3 miliseconds Array_size = 100000: Insertion sort time:
 * 1789 miliseconds, Arrays.sort() time: 31 miliseconds
 *
 */
import java.util.Arrays;

public class Week1 {

    // constant for array size
    private static final int ARRAY_SIZE = 1000;

    public static void main(String[] args) {
        // create the two arrays
        int[] array1 = new int[ARRAY_SIZE];
        int[] array2 = new int[ARRAY_SIZE];

        // fill the arrays with random integers
        for (int i = 0; i < ARRAY_SIZE; i++) {
            int randomInt = (int) (Integer.MAX_VALUE * Math.random());
            array1[i] = randomInt;
            array2[i] = randomInt;
        }

        // sort the first array using insertion sort
        long startTime1 = System.currentTimeMillis();
        insertionSort(array1);
        long endTime1 = System.currentTimeMillis();
        long elapsedTime1 = endTime1 - startTime1;
        System.out.println("Insertion sort time: " + elapsedTime1 + " miliseconds");

        // sort the second array using Arrays.sort()
        long startTime2 = System.currentTimeMillis();
        Arrays.sort(array2);
        long endTime2 = System.currentTimeMillis();
        long elapsedTime2 = endTime2 - startTime2;
        System.out.println("Arrays.sort() time: " + elapsedTime2 + " miliseconds");

        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
    }

    // insertion sort method
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }
}
