import java.util.Comparator;

/**
 * Created by jakub on 26/02/2017.
 */
public interface Heap<T> {
    T remove();
    T peek();
    void insert(T elem);
    int size();
}
