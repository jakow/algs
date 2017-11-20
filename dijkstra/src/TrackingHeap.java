import java.util.HashMap;
import java.util.Map;

/**
 * Heap class that
 */

public class TrackingHeap<T extends Comparable<T>> {
    private T[] arr;
    private Map<T, Integer> positions;
    int sz;
    TrackingHeap() {
        arr = (T[]) new Object[16];
        sz = 0;
        positions = new HashMap<>();

    }

    void insert(T element) {
        if (positions.containsKey(element)) {
            throw new IllegalArgumentException("The TrackingHeap cannot contain duplicates");
        }
        if (sz + 1 == arr.length) {
            resize(2 * arr.length);
        }
        sz += 1;
        arr[sz] = element;
        positions.put(element, sz);
        sink(sz);
    }

    T delMin() {
        return delIndex(1);
    }

    private T delIndex(int i) {
        T elem = arr[i];
        exch(i, sz);
        sink(i);
        // nullify to prevent loitering
        arr[i] = null;
        // clear from positions
        positions.remove(elem);
        return elem;
    }


    private void sink(int k) {
        // k - current element
        // j - child element
        int j;
        while(2*k <= sz) { // while k-th element has children
            j = 2 * k;
            if (j < sz && less(j + 1, j)) j++; // pick smaller child
            if(!less(j,k)) break; // stop swapping if child larger or equal
            exch(k, j);
            k = j;
        }
    }

    private void swim(int k) {
        while(k > 1 && less(k, k / 2)) { // keep swapping while the child (k) is SMALLER than the parent (k/2)
            exch(k, k/2);
            k = k/2;
        }
    }

    private void exch(int leftIdx, int rightIdx) {
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
        Object[] temp = new Object[newSize];
        System.arraycopy(arr, 1, temp, 1, sz);
        arr = (T[]) temp;
    }


}
