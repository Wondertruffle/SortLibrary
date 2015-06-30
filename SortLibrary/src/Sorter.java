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
            int[] arr = generateArray();
            String unsortedString = Arrays.toString(arr);

            SortType type = chooseSort();

            long start = System.currentTimeMillis();
            int[] sorted = sort(type, arr);
            long end = System.currentTimeMillis();
            long timeTaken = end - start;

            String sortedString = Arrays.toString(sorted);

//            boolean success = checkSorted(sorted);
//            if(success)
//            {
//                System.out.println("Sort was successful!");
//            } else {
//                System.out.println("Sort was unsuccessful!");
//            }

            System.out.println("Time taken: " + timeTaken);
            System.out.println("Unsorted array: " + unsortedString);
            System.out.println("Sorted array: " + sortedString);
            done = !again();
        }
    }

    private static void welcome()
    {
        System.out.println("This program will generate a random unsorted integer array and sort it for you.");
    }

    private static int[] generateArray()
    {
        Scanner scanner = new Scanner(System.in);
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

        scanner.close();
        return SortLibrary.generateRandomArray(size, lowerBound, upperBound);
    }

    private static SortType chooseSort()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What type of sort to perform? [S]election/[I]nsertion/[Q]uick/[M]erge/[H]eap");
        boolean goodChoice = false;
        while(!goodChoice)
        {

            if(scanner.hasNext())
            {
                char choice = scanner.next().toCharArray()[0];
                switch(choice) {
                    case 's':
                    case 'S':
                        scanner.close();
                        return SortType.SELECTION;
                    case 'i':
                    case 'I':
                        scanner.close();
                        return SortType.INSERTION;
                    case 'q':
                    case 'Q':
                        scanner.close();
                        return SortType.QUICK;
                    case 'm':
                    case 'M':
                        scanner.close();
                        return SortType.MERGE;
                    case 'h':
                    case 'H':
                        scanner.close();
                        return SortType.HEAP;
                    default:
                        System.out.println("Not a valid option!  Choose S, I, Q, M, or H.");
                        break;

                }
            }
        }

        return null;
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

    private static boolean again()
    {
        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sort another array? (Y/N)");
        while(!done)
        {
            char choice = '\0';
            if(scanner.hasNext())
            {
                choice = scanner.next().toCharArray()[0];
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
            }
        }
        return false;
    }

    private static boolean checkSorted(int[] candidate)
    {
        int len = candidate.length - 1;
        for(int i = 0; i < len; i++)
        {
            if(candidate[i] > candidate[i+1]) return false;
        }
        return true;
    }



    private enum SortType {QUICK, MERGE, SELECTION, INSERTION, HEAP}

}
