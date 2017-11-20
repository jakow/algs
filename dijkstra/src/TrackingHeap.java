import javax.sound.midi.Track;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * A heap that tracks
 */
public class TrackingHeap<T extends Comparable<T>> {
    private T[] arr;
    private int sz;
    private Map<T, Integer> positions;
    public TrackingHeap() {
        this(16);
    }
    public TrackingHeap(int initialSize) {
        positions = new HashMap<>();
        arr = (T[]) new Object[initialSize + 1];
        sz = 0;
    }

    public void insert(T elem) {
        if (positions.containsKey(elem)) {
            throw new Error("TrackingHeap does not support duplicate keys");
        }
        if (sz + 1== arr.length) {
            resizeArr(arr.length * 2);
        }
        arr[++sz] = elem;
        positions.put(elem, sz);
        swim(sz);
    }

    public int positionOf(T elem) {
        if (!positions.containsKey(elem)) {
            throw new NoSuchElementException("Element not in heap");
        }
        return positions.get(elem);
    }

    public T delMin() {
        exch(1, sz); // swap min with last
        T elem = arr[sz];
        arr[sz] = null;
        sink(1);
    }

    public T delIndex(int index) {
         if (sz + 1 < arr.length / 4) {
            resizeArr(arr.length / 2);
        }
    }

    private void sink(int k) {
        // k - current element
        // j - child element
        int j;
        while(2*k <= sz) {
            j = 2 * k;
            if (j < sz // right child of k exists?
                    && less(j + 1, j)) j++; // choose smaller child
            if(!less(k,j)) break; // do not swap if the child is bigger
            exch(k, j); // swap
            k = j;
        }
    }

    private void swim(int k) {
        while(k > 1 && less(k, k / 2)) { // keep swapping while the child (k) is smaller than the parent (k/2)
            exch(k, k/2);
            k = k/2;
        }
    }

    private boolean less(int a, int b) {
        return arr[a].compareTo(arr[b]) < 0;
    }

    private void exch(int a, int b) {
        T elemA = arr[a];
        T elemB = arr[b];
        arr[a] = elemB;
        arr[b] = elemA;
        positions.put(elemA, b);
        positions.put(elemB, a);
    }

    private void resizeArr(int newSize) {
        T[] resized = (T[]) new Object[newSize];
        for (int i = 1; i <= sz; ++i) {
            resized[i] = arr[i];
        }
    }
}
