/**
 * Created by Drew on 6/30/2015.
 */
public interface Heap<E extends Comparable<E>> {

    public void add(E item);
    public E peek();
    public void deleteRoot();

}
