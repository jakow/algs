import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;
/**
 * Created by jakub on 03/02/2017.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int sz;
    public RandomizedQueue() {
        arr = (Item[]) new Object[2];
        sz = 0;
    }

    public int size() {
        return sz;
    }

    public boolean isEmpty() {
        return sz == 0;
    }

    private void resize(int capacity) {
        assert capacity >= sz;
        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < sz; i++) {
            temp[i] = arr[i];
        }
        arr = temp;

        // alternative implementation
        // a = java.util.Arrays.copyOf(a, capacity);

    }
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("Deque.addFirst(null)");
        if (sz == arr.length) resize(2*arr.length);    // double size of array if necessary
        arr[sz++] = item;                            // add item
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue.dequeue(): Queue underflow");
        int index = StdRandom.uniform(sz); // pick index randomly
        Item item = arr[index];
        arr[index] = arr[sz-1]; // swap the selected and last element
        arr[sz-1] = null; // and remove the element from array
        sz--;
        // shrink size of array if necessary
        if (sz > 0 && sz == arr.length/4) resize(arr.length/2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue.sample(): Empty queue");
        return arr[StdRandom.uniform(sz)];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] indices;
        private int nextIndex;
        private RandomizedQueueIterator() {
            indices = new int[sz];
            nextIndex = 0;
            for (int i = 0; i < sz; ++i) {
                indices[i] = i;
            }
            // now shuffle them randomly - Fisher-Yates
            int j, temp;
            for (int i = sz-1; i >= 1; --i ) {
                j = StdRandom.uniform(0,i+1); // [0, i], not [0, i)
                //swap i-th and j-th elements
                temp = indices[j];
                indices[j] = indices[i];
                indices[i] = temp;
            }
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("RandomizedQueueIterator.next()");
            return arr[indices[nextIndex++]];

        }
        public boolean hasNext() {
            return nextIndex < sz;
        }
    }

}
