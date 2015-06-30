/**
 * Created by Drew on 6/28/2015.
 */
import java.util.Arrays;
import java.util.Random;

public class SortLibrary {

    public static void main(String[] args)
    {
        int[] unsorted = generateRandomArray(100, 0, 100);

        if(unsorted == null)
        {
            System.err.println("Could not produce unsorted array, terminating.");
            System.exit(-1);
        }
        String unsortedString = Arrays.toString(unsorted);

        int[] sorted = heapSort(unsorted);
        String sortedString = Arrays.toString(sorted);

        System.out.println("Unsorted: " + unsortedString);
        System.out.println("Sorted: " + sortedString);
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
        return arr;
    }

    /**
     * Performs recursive quicksort on the supplied array.  This quicksort has worst-case runtime O(n^2), with this
     * behavior occurring when the array consists of a single repeated element.  The average-case runtime of this
     * algorithm is O(n log n).  The algorithm could be improved by k-sorting the array then calling insertion sort
     * on the array.
     * @param arr The array to be sorted.
     * @return A sorted array of integers.
     */
    public static int[] recursiveQuickSort(int[] arr)
    {
        quickSort(arr, 0, arr.length - 1);

        return arr;
    }

    /**
     * Private helper method for recursive quicksort.  This method chooses a pivot and swaps elements of the subarray
     * determined by low and high so that all elements less than the pivot value are to the left of the pivot and all
     * elements greater than the pivot value are to the right of the pivot.
     * @param arr The array to be partitioned.
     * @param low The lower bound of the range to be partitioned.
     * @param high The upper bound of the range to be partitioned.
     * @return An array with the specified range partitioned.
     */
    private static int partition(int[] arr, int low, int high)
    {
        Random generator = new Random(); //this implementation chooses pivot randomly
        //chose a pivot index between low and high, inclusive
        int range = high - low;
        int pivotIndex = generator.nextInt(range + 1) + low;
        int pivotValue = arr[pivotIndex];

        //put the chosen pivot at arr[high]
        int temp = arr[high];
        arr[high] = pivotValue;
        arr[pivotIndex] = temp;

        int storeIndex = low;
        for(int i = low; i < high; i++)
        {
            if(arr[i] < pivotValue) {
                temp = arr[storeIndex];
                arr[storeIndex] = arr[i];
                arr[i] = temp;
                storeIndex++;
            }
        }

        //move pivot to its final resting place
        temp = arr[high];
        arr[high] = arr[storeIndex];
        arr[storeIndex] = temp;
        return storeIndex;
    }

    /**
     * Private helper method for recursive quicksort.  Performs quicksort on the subarray arr[low,high].  This method
     * either does nothing and returns the array (in case the range to be sorted is size 0) or partitions the array and
     * recursively calls itself on the unsorted subarrays to either side of the partition.  Warning: this doesn't use
     * tail recursion so it's not optimal by any means.  Not for use with large arrays.
     * @param arr The array to be sorted.
     * @param low The lower bound of the subarray to be sorted.
     * @param high The upper bound of the subarray to be sorted.
     * @return A array with the specified subarray sorted.
     */
    private static int[] quickSort(int[] arr, int low, int high)
    {

        if(low < high)
        {
            int partition = partition(arr, low, high);
            quickSort(arr, low, partition - 1);
            quickSort(arr, partition + 1, high);
        }

        return arr;
    }


    /**
     * Performs mergesort on the specified array.  Splits the array, sorts each half, and merges the two lists.
     *
     * Runs in worst case O(n log n).  Uses non-optimized recursion, use caution.
     * @param arr The array to be sorted.
     * @return The sorted array.
     */
    public static int[] mergeSort(int[] arr)
    {
        if(arr.length <= 1) return arr;

        int len = arr.length;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, len);

        //recursive call
        mergeSort(left);
        mergeSort(right);

        //perform the merge
        merge(left, right, arr);

        return arr;
    }

    /**
     * Merges two sorted subarrays into the target array.
     * @param left The left sorted array.
     * @param right The right sorted array.
     * @param target The array that we will merge into.
     */
    private static void merge(int[] left, int[] right, int[] target)
    {
        int leftLen = left.length;
        int rightLen = right.length;
        int i = 0; //keeps track of our position in the left array
        int j = 0; //keeps track of our position in the right array
        int k = 0; //keeps track of our position in the target array

        while(i < leftLen && j < rightLen)
        {
            if(left[i] <= right[j])
            {
                target[k] = left[i];
                i++;
            } else {
                target[k] = right[j];
                j++;
            }
            k++;

        }

        //handle leftovers in case the subarrays are different sizes.
        while(i < leftLen)
        {
            target[k] = left[i];
            i++;
            k++;
        }

        while(j < rightLen)
        {
            target[k] = right[j];
            j++;
            k++;
        }
    }

    public static int[] heapSort(int[] arr)
    {
        int len = arr.length;
        IntegerMinheap heap = new IntegerMinheap();
        for(int i = 0; i < len; i++)
        {
            heap.add(arr[i]);
        }

        int[] result = new int[len];
        for(int i = 0; i < len; i++)
        {
            result[i] = heap.peek();
            heap.deleteRoot();
        }
        return result; //TODO
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

        int range;

        if(upperBound - lowerBound > Integer.MAX_VALUE)
        {
            System.err.println("Range of values too large!");
            return null;
        } else {
            range = upperBound - lowerBound;
        }

        for(int i = 0; i < size; i++)
        {
            int randVal = generator.nextInt(range + 1);
            randVal += lowerBound;
            result[i] = randVal;
        }
        return result;
    }



}
