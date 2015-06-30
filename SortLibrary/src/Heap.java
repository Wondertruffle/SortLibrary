/**
 * Created by Drew on 6/30/2015.
 */

/**
 * Interface for a heap to be used for heapsort.
 * @param <E> The data type of the elements stored in the heap.
 */
public interface Heap<E extends Comparable<E>> {

    public void add(E item);
    public E peek();
    public void deleteRoot();

}
