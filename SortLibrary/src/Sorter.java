/**
 * Created by Drew on 6/30/2015.
 */

import java.util.Scanner;
import java.util.Arrays;
public class Sorter {

    private static boolean done = false;

    public static void main(String[] args)
    {
        welcome();

        while(!done)
        {
            Scanner scanner = new Scanner(System.in);
            int[] arr = generateArray(scanner);
            String unsortedString = Arrays.toString(arr);

            SortType type = chooseSort(scanner);

            long start = System.currentTimeMillis();
            int[] sorted = sort(type, arr);
            long end = System.currentTimeMillis();
            long timeTaken = end - start;

            String sortedString = Arrays.toString(sorted);

            boolean success = checkSorted(sorted);
            if(success)
            {
                System.out.println("Sort was successful!");
            } else {
                System.out.println("Sort was unsuccessful!");
            }

            System.out.println("Time taken: " + timeTaken + " ms");
            System.out.println("Unsorted array: " + unsortedString);
            System.out.println("Sorted array: " + sortedString);
            done = !again(scanner);
        }
    }

    private static void welcome()
    {
        System.out.println("This program will generate a random unsorted integer array and sort it for you.");
    }

    private static int[] generateArray(Scanner scanner)
    {

        System.out.println("How large would you like your array to be?");
        int size = 100;
        if(scanner.hasNextInt())
        {
            size = scanner.nextInt();
        } else {
            System.out.println("That's not a real size, defaulting to 100.");
        }

        int lowerBound = 0;
        System.out.println("Lower bound (inclusive) on array elements?");
        if(scanner.hasNextInt())
        {
            lowerBound = scanner.nextInt();
        } else {
            System.out.println("Not a valid lower bound, defaulting to 0.");
        }

        int upperBound = 100;
        System.out.println("Upper bound (inclusive) on array elements?");
        if(scanner.hasNextInt())
        {
            upperBound = scanner.nextInt();
        } else {
            System.out.println("Not a valid upper bound, defaulting to 100.");
        }


        return SortLibrary.generateRandomArray(size, lowerBound, upperBound);
    }

    private static SortType chooseSort(Scanner scanner)
    {


        System.out.println("What type of sort to perform? [S]election/[I]nsertion/[Q]uick/[M]erge/[H]eap");

        while(true)
        {
            char choice = '\0';
            if(scanner.hasNext())
            {
                String input = scanner.next();
                choice = input.toCharArray()[0];
            }


            switch(choice) {
                case 's':
                case 'S':

                    return SortType.SELECTION;
                case 'i':
                case 'I':

                    return SortType.INSERTION;
                case 'q':
                case 'Q':

                    return SortType.QUICK;
                case 'm':
                case 'M':

                    return SortType.MERGE;
                case 'h':
                case 'H':

                    return SortType.HEAP;
                default:
                    System.out.println("Not a valid option!  Choose S, I, Q, M, or H.");
                    break;

            }

        }


    }

    private static int[] sort(SortType type, int[] arr)
    {
        switch(type) {
            case QUICK:
                return SortLibrary.recursiveQuickSort(arr);
            case MERGE:
                return SortLibrary.mergeSort(arr);
            case SELECTION:
                return SortLibrary.selectionSort(arr);
            case INSERTION:
                return SortLibrary.insertionSort(arr);
            case HEAP:
                return SortLibrary.heapSort(arr);
        }
        return null;
    }

    private static boolean again(Scanner scanner)
    {


        System.out.println("Sort another array? (Y/N)");
        while(true)
        {
            char choice = '\0';
            if(scanner.hasNext()) {
                String input = scanner.next();
                choice = input.toCharArray()[0];
            }

            switch(choice) {
                case 'y':
                case 'Y':
                    return true;
                case 'n':
                case 'N':
                    return false;
                default:
                    System.out.println("Not a valid choice, type Y or N");
                    break;
            }
        }

    }

    private static boolean checkSorted(int[] candidate)
    {
        int len = candidate.length - 1;
        for(int i = 0; i < len; i++)
        {
            if(candidate[i] > candidate[i+1])
            {
                System.out.println(candidate[i] + " is greater than " + candidate[i+1] + " at index " + i);
                return false;
            }
        }
        return true;
    }



    private enum SortType {QUICK, MERGE, SELECTION, INSERTION, HEAP}

}
