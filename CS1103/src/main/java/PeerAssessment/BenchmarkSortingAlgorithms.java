package PeerAssessment;

import java.util.Arrays;

/**
 * ****** Report ************ The time that has been token to run the
 * SelectionSort Method when ArraySize is 1,000 The time is 0.009 sec * when
 * ArraySize is 10,000 the time is 0.141 sec * when ArraySize is 100,000 the
 * time is 9.286 * ***** The time that has been token to run the Array.sort
 * Method **** when ArraySize is 1,000 The time is 0.016 sec * when ArraySize is
 * 10,000 the time is 0.002 sec * when ArraySize is 100,000 the time is 0.002
 * sec
 */
public class BenchmarkSortingAlgorithms {

    public static void main(String[] args) {
        // initializing the array size
        int maxArraySize = 100000;// Maximal Array size
        int[] maxNumber_Humans = new int[maxArraySize];//First array
        int[] maxNumber_animals = new int[maxArraySize];//Second array
        //Constructor
        for (int i = 0; i < maxNumber_Humans.length; i++) {
            //Filling two arrays with same random numbers
            maxNumber_Humans[i] = (int) (Integer.MAX_VALUE * Math.random());
            maxNumber_animals[i] = maxNumber_Humans[i];
        }
        
        // calculating runtime for selection sort
        long startTime_Humans = System.currentTimeMillis(); // start computing time for Humans array

        selectionSort(maxNumber_Humans); // sorting
        long runTime_Humans = System.currentTimeMillis() - startTime_Humans; // Time to run the selectionSort
        /**
         * max number Humans array sorting here!
         */
        long startTime_animals = System.currentTimeMillis(); // computing time for animals array
        Arrays.sort(maxNumber_animals);// sorting maximal number of Humans array with Array.sort
        long runTime_animals = System.currentTimeMillis() - startTime_animals; // Time to run the Array.sort
        System.out.println(" Output: selectionSort time(Sec) is: " + runTime_Humans / 1000.0); // print output
        System.out.println(" Output: Array.sort time(Sec) is: "
                + runTime_animals / 1000.0); // print output
        
        System.out.println(Arrays.toString(maxNumber_Humans));
        System.out.println(Arrays.toString(maxNumber_animals));
    }

    static void selectionSort(int[] A) {
// Sort A into increasing order, using selection sort
        for (int lastPlace = A.length - 1; lastPlace > 0; lastPlace--) {
            // Find the largest item among A[0], A[1], ...,
            // A[lastPlace], and move it into position lastPlace
            // by swapping it with the number that is currently
            // in position lastPlace.
            int maxLoc = 0;  // Location of largest item seen so far.
            for (int j = 1; j <= lastPlace; j++) {
                if (A[j] > A[maxLoc]) {
                    // Since A[j] is bigger than the maximum we’ve seen
                    // so far, j is the new location of the maximum value
                    // we’ve seen so far.
                    maxLoc = j;
                }
            }
            int temp = A[maxLoc];  // Swap largest item with A[lastPlace].
            A[maxLoc] = A[lastPlace];
            A[lastPlace] = temp;
        }
    }
}
