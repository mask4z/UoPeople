
// for array_size = 1000 we have:  Time to sort array1 is 11 ms, and Time to sort array2 is 0 ms

//for array_size = 10000 we have:  Time to sort array1 is 226 ms, and Time to sort array2 is 1 ms

//for array_size = 100000 we have:  Time to sort array1 is 13830 ms, and Time to sort array2 is 2 ms

package PeerAssessment;

import java.util.Arrays;


public class Unit_1 {

	    public static int array_size = 100000;

	    public static void main(String[] args) {
	        int[] array1 = new int[array_size];
	        int[] array2 = new int[array_size];

	        for (int i = 0; i < array_size; i++) {
	        	int randomInt = (int)(Integer.MAX_VALUE * Math.random());
	            array1[i] = randomInt;
	            array2[i] = randomInt;
	        }
	        
	        long startTime = System.currentTimeMillis();
	        selectionSort(array1);
	        selectionSort(array2);
	        long runTime = System.currentTimeMillis() - startTime;
	        
	        long startTime2 = System.currentTimeMillis();
	        Arrays.sort(array2);
	        long runTime2 = System.currentTimeMillis() - startTime2;
	        
	        
//	        System.out.println("Time to sort array1: " + runTime + " ms");
//	        System.out.println("Time to sort array2: " + runTime2 + " ms");
                System.out.println(Arrays.toString(array1));
                System.out.println(Arrays.toString(array2));

	      
	    }
	






static void selectionSort(int[] array) {
	// Sort array into increasing order, using selection sort
	for (int lastPlace = array.length-1; lastPlace > 0; lastPlace--) {
	// Find the largest item among array[0], array[1], ...,
	// array[lastPlace], and move it into position lastPlace
	// by swapping it with the number that is currently
	// in position lastPlace.
	int maxLoc = 0; // Location of largest item seen so far.
	for (int j = 1; j <= lastPlace; j++) {
	if (array[j] > array[maxLoc]) {
	// Since array[j] is bigger than the maximum we’ve seen
	// so far, j is the new location of the maximum value
	// we’ve seen so far.
	maxLoc = j;
	}
	}
	int temp = array[maxLoc]; // Swap largest item with array[lastPlace].
	array[maxLoc] = array[lastPlace];
	array[lastPlace] = temp;
	} // end of for loop
	}
}



