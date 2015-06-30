/**
 * Created by Drew on 6/30/2015.
 */
public class IntegerMinheap implements Heap<Integer> {

    private int[] arr;
    private int indexOfLast;

    public IntegerMinheap()
    {
        arr = new int[100]; //initial size is 100, can resize later
        indexOfLast = -1;
    }

    @Override
    public void add(Integer item)
    {
        if(indexOfLast >= arr.length) resize();

        arr[++indexOfLast] = item;
        siftUp();

    }

    @Override
    public Integer peek()
    {
        return arr[0];
    }

    @Override
    public void deleteRoot()
    {
        if(indexOfLast <= 0) return;  //heap is empty, nothing to be done
        //swap the root with the last element
        swap(0, indexOfLast);
        siftDown();
        indexOfLast--;
    }

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
        }
    }

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
        }

    }


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

    private void swap(int a, int b)
    {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
