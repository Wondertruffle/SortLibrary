/**
 * Created by Drew on 6/30/2015.
 */

/**
 * A class that represents a binary minheap with integer-valued nodes.  The backing data structure for the heap is an
 * an array which can be dynamically resized.
 * @author Andrew Smith
 */
public class IntegerMinheap implements Heap<Integer> {

    /**
     * The array backing the heap.
     */
    private int[] arr;

    /**
     * Stores the index of the rightmost node on the bottom level of the heap.
     */
    private int indexOfLast;

    /**
     * Constructs an empty binary minheap with initial size 100.
     */
    public IntegerMinheap()
    {
        arr = new int[100]; //initial size is 100, can resize later
        indexOfLast = -1;
    }

    /**
     * Adds an item to the heap.
     * @param item The item to be added to the heap.
     */
    @Override
    public void add(Integer item)
    {
        if(indexOfLast >= arr.length) resize();

        arr[++indexOfLast] = item;
        siftUp();

    }

    /**
     * Returns, but does not destroy, the root element of the heap.
     * @return The root element of the heap.
     */
    @Override
    public Integer peek()
    {
        return arr[0];
    }

    /**
     * Deletes the root of the heap and re-heapifies.
     */
    @Override
    public void deleteRoot()
    {
        if(indexOfLast <= 0) return;  //heap is empty, nothing to be done
        //swap the root with the last element
        swap(0, indexOfLast);
        siftDown();
        indexOfLast--;
    }

    /**
     * Performs a sift-up on the heap, necessary to maintain the heap property after adding an element.
     */
    private void siftUp()
    {
        if(indexOfLast == 0) return; //heap with one element has heap property trivially.
        int currentIndex = indexOfLast;
        int parent = (int)Math.floor(((double)currentIndex - 1) / 2);
        while(arr[currentIndex] < arr[parent])
        {
            swap(currentIndex, parent);
            currentIndex = parent;
            parent = (int)Math.floor(((double)currentIndex - 1) / 2);
            if(parent < 0) break; //this occurs when we have reached the root
        }
    }

    /**
     * Performs a sift-down on the heap, necessary to maintain the heap property after deleting the root.
     */
    private void siftDown()
    {
        int currentIndex = 0;
        int leftChild = 2 * currentIndex + 1;
        int rightChild = 2 * currentIndex + 2;

        while(arr[currentIndex] > arr[leftChild] || arr[currentIndex] > arr[rightChild])
        {
            //swap with the smaller child
            if(arr[leftChild] < arr[rightChild])
            {
                swap(currentIndex, leftChild);
                currentIndex = leftChild;
            } else {
                swap(currentIndex, rightChild);
                currentIndex = rightChild;
            }
            leftChild = 2 * currentIndex + 1;
            rightChild = 2 * currentIndex + 2;
            if(leftChild > indexOfLast || rightChild > indexOfLast) break; //this occurs when we have reached the bottom of the heap
        }

    }


    /**
     * Utility method to resize the backing array if necessary.  Creates a new array with double the size of the
     * current and copies all values over in the same position as before.
     */
    private void resize()
    {
        int newSize = arr.length * 2;
        int[] newArr = new int[newSize];

        for(int i = 0; i < arr.length; i++)
        {
            newArr[i] = arr[i];
        }

        arr = newArr;
    }

    /**
     * Utility method to swap two elements of the backing array.
     * @param a The index of the first element to be swapped.
     * @param b The index of the second element to be swapped.
     */
    private void swap(int a, int b)
    {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
