import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by jakub on 03/02/2017.
 */

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node prev;
        Node next;
        Node(Item i, Node nextNode, Node prevNode) {
            item = i;
            prev = null;
            next = null;
            link(nextNode);
            if (prevNode != null) prevNode.link(this);
        }
        void link(Node other) {
            if (other != null) other.prev = this;
            next = other;
        }
    }
    private class DequeIterator implements Iterator<Item> {
        private int itemsLeft;
        private Node node;
        public DequeIterator(Node first, int size) {
            itemsLeft = size;
            node = first;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("DequeIterator.next()");
            }
            Item item = node.item;
            node = node.next;
            itemsLeft--;
            return item;
        }
        public boolean hasNext() {
            return itemsLeft > 0;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("DequeIterator.remove()");
        }
    }
    private int sz;
    private Node first;
    private Node last;
    public Deque() {
        sz = 0;
        first = null;
        last = null;
    }

    public int size() {
        return sz;
    }

    public boolean isEmpty() {
        return  sz == 0;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Deque.addFirst(null)");
        }
        first = new Node(item, first, null);
        sz++;
        if (sz <= 1) last = first;

    }
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("Deque.addFirst(null)");
        last = new Node(item, null, last);
        sz++;
        if (sz <= 1) first = last;

    }
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("First element in empty deque");
        sz--;
        Item ret = first.item;
        first = first.next; // old first gets garbage collected?
        if (first != null) first.prev = null; //avoid loitering- remove reference to old first
        if (sz <= 1) last = first;
        return ret;
    }
    public Item removeLast()  {
        if (isEmpty()) throw new NoSuchElementException("Last element in empty deque");
        sz--;
        Item ret = last.item;
        last = last.prev;
        if (last != null) last.next = null; //avoids loitering
        if (sz <= 1) first = last; // first is last or null if sz <= 1
        return ret;
    }


    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator(first, sz);
    }

}
