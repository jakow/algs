import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

/**
 * Created by jakub on 26/02/2017.
 */
public class BinaryHeap<T> implements Heap<T> {
    private static final int DEFAULT_INITIAL_CAPACITY = 15;
    private Object[] arr; // resizing array for the heap
    private int sz;
    private final Comparator<? super T> comp;
    public BinaryHeap() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }
    public BinaryHeap(int initialCapacity) {
        this(initialCapacity, null);
    }



    public BinaryHeap(int initialCapacity, Comparator<T> comparator) {
        if (initialCapacity < 1) throw new IllegalArgumentException();
        arr = new Object[initialCapacity+1];
        comp = comparator;
    }
    @Override
    @SuppressWarnings("unchecked")
    public T remove() {
        T key = (T) arr[1];
        exch(1, sz--);
        sink(1);
        arr[sz+1] = null; // prevent loitering
        if (sz+1 <= arr.length/4) resize(arr.length/2);
        return key;
    }



    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        return (T) arr[1];
    }




    @Override
    public void insert(T elem) {
//        System.out.println("Size: " + sz);
        if (sz+1 == arr.length) { // reach max capacity
            resize(arr.length*2);
        }
        arr[++sz] = elem;
        swim(sz);

    }

    @Override
    public int size() {
        return sz;
    }

    private void sink(int k) {
        // k - current element
        // j - child element
        int j;
        while(2*k <= sz) {
            j = 2 * k;
            if (j < sz && less(j, j + 1)) j++;
            if(!less(k,j)) break; // do not swap if equal
            exch(k, j);
            k = j;
        }
    }

    private void swim(int k) {
        while(k > 1 && less(k/2, k)) { // keep swapping while the child (k) is larger than the parent (k/2)
            exch(k, k/2);
            k = k/2;
        }
    }

    private void resize(int newSize) {
        assert(newSize >= sz+1);
        Object[] temp = new Object[newSize];
        System.arraycopy(arr, 1, temp, 1, sz);
        arr = temp;
    }

    @SuppressWarnings("unchecked")
    private boolean less(int a, int b) {
//        System.out.println("A: " + (T) arr[a] + ", B: " + (T) arr[b]);
        if (comp == null) {
            Comparable<? super T> objA = (Comparable<? super T>) arr[a];
            return objA.compareTo((T) arr[b]) < 0;
        } else {
            return comp.compare((T) arr[a], (T) arr[b]) < 0;
        }

    }

    private void exch(int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
