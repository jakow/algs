import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Heap class that tracks where its items are
 */

public class IndexHeap<T extends Comparable<T>> {
    private T[] arr;
    private Map<T, Integer> positions;
    int sz;

    IndexHeap() {
        arr = (T[]) new Comparable[2];
        sz = 0;
        positions = new HashMap<>();

    }

    public void insert(T element) {
        if (positions.containsKey(element)) {
            throw new IllegalArgumentException("The IndexHeap cannot contain duplicates");
        }
        if (sz + 1 == arr.length) {
            resize(2 * arr.length);
        }
        sz += 1;
        arr[sz] = element;
        positions.put(element, sz);
        swim(sz);
    }

    public boolean contains(T element) {
        return positions.containsKey(element);
    }

    public int getIndex(T key) {
        if (!positions.containsKey(key)) {
            throw new NoSuchElementException("Key does not exist");
        }
        return positions.get(key);
    }

    public int size() {
        return sz;
    }

    public boolean isEmpty() {
        return sz == 0;
    }

    public T getItem(int index) {
        return arr[index];
    }

    public T delMin() {
        return delIndex(1);
    }

    public T delItem(T key) {
        int index = getIndex(key);
        return delIndex(index);
    }

    public T delIndex(int i) {
        exchange(i, sz);
        T elem = arr[sz];
        arr[sz] = null;
        // nullify to prevent loitering
        // clear from positions
        positions.remove(elem);
        // shrink numberOfVertices
        sz--;
        sink(i);
        // resize array as required
        if (sz < arr.length / 4) {
            resize(arr.length / 2);
        }
        return elem;
    }

    @Override
    public String toString() {
        return String.format("IndexHeap%s", Arrays.toString(Arrays.stream(arr, 1, sz + 1).toArray()));
    }

    public void reheapify(T item) {
        int index = getIndex(item);
        swim(index);
        sink(index);
    }

    private void sink(int k) {
        // k - current element
        // j - child element
        int j;
        while (2*k <= sz) { // while k-th element has children
            j = 2 * k;
            if (j < sz && less(j + 1, j)) j++; // pick smaller child
            if(!less(j,k)) break; // stop swapping if child larger or equal
            exchange(k, j);
            k = j;
        }
    }

    private void swim(int k) {
        while(k > 1 && less(k, k / 2)) { // keep swapping while the child (k) is SMALLER than the parent (k/2)
            exchange(k, k/2);
            k = k/2;
        }
    }

    private void exchange(int leftIdx, int rightIdx) {
        T left = arr[leftIdx];
        T right = arr[rightIdx];
        arr[rightIdx] = left;
        arr[leftIdx] = right;
        positions.put(left, rightIdx);
        positions.put(right, leftIdx);
    }

    private boolean less(int left, int right) {
        return arr[left].compareTo(arr[right]) < 0;
    }

    private void resize(int newSize) {
        assert(newSize >= sz+1);
        Comparable[] temp = new Comparable[newSize];
        System.arraycopy(arr, 1, temp, 1, sz);
        arr = (T[]) temp;
    }
}
