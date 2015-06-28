/**
 * Created by Drew on 6/28/2015.
 */
import java.util.Arrays;
import java.util.Random;

public class SortLibrary {

    public static void main(String[] args)
    {
        int[] unsorted = generateRandomArray(100, 0, 100);
        int[] sorted = insertionSort(unsorted);
        String output = Arrays.toString(sorted);
        System.out.println(output);
    }

    /**
     * Performs selection sort on an unsorted integer array, returning an array sorted in ascending order.
     * The algorithm works by iteratively growing a sorted array from the left of the unsorted array.  The unsorted
     * portion of the list is searched to find the index of the minimum (or maximum, depending on the desired sorting
     * of the array) element, then swaps it with the element at the beginning of the unsorted list, putting it in sorted
     * order.  The bounds of the sorted list are then incremented.
     *
     * Runs in O(n^2) time, so is not efficient for large lists.
     * @param arr The unsorted array.
     * @return An array sorted in ascending order.
     */
    public static int[] selectionSort(int[] arr)
    {

        int len = arr.length;
        for(int i = 0; i < len; i++)
        {
            int indexOfSmallest = i;
            for(int j = i; j < len; j++)
            {
                // find the index of the smallest value in the unsorted array
                if(arr[j] < arr[indexOfSmallest]) indexOfSmallest = j;

            }
            // perform the swap
            int temp = arr[i];
            arr[i] = arr[indexOfSmallest];
            arr[indexOfSmallest] = temp;

        }

        return arr;
    }

    /**
     * Performs an insertion sort on the supplied array.  This algorithm works by iteratively growing a sorted array at
     * the head of the unsorted array.  On each iteration, the correct position of the first unsorted element is found
     * in the sorted array.  A series of swaps is then performed which inserts the element at its correct position and
     * the bounds of the sorted list are incremented.
     *
     * Runs in O(n^2) time and so is not suitable for large input arrays.  Generally faster than selection sort on small
     * input.  Runs in O(nk) time when each element is no more than k places from its correct sorted position.
     * @param arr The unsorted array.
     * @return The sorted array.
     */
    public static int[] insertionSort(int[] arr)
    {
        for(int i = 1; i < arr.length; i++) //start at 1 since the 1-element array consisting of arr[0] is trivially sorted
        {
            int j = i;
            while(j > 0 && arr[j-1] > arr[j])
            {
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                j--;
            }
        }
        return arr; //TODO
    }


    /**
     * Generates an array of random integers between the specified lower and upper bounds (inclusive).
     * @param size The size of the array to be generated
     * @param lowerBound The lower bound on entries in the array.
     * @param upperBound The upper bound on entries in the array.
     * @return an unsorted array of random integers.
     */
    public static int[] generateRandomArray(int size, int lowerBound, int upperBound)
    {
        Random generator = new Random();
        int[] result = new int[size];

        for(int i = 0; i < size; i++)
        {
            int randVal;
            do {
                randVal = generator.nextInt(upperBound) + 1;

            } while (!(randVal >= lowerBound));
            result[i] = randVal;
        }
        return result;
    }


}
