import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

/**
 * Created by jakub on 20/01/2018.
 */
public class MinStack<T extends Comparable<T>> {
    Deque<T> stack;
    Deque<T> mins;

    MinStack() {
        stack = new ArrayDeque<>();
        mins = new ArrayDeque<>();
    }

    public void push(T t) {
        stack.addLast(t);
        if (mins.isEmpty() || t.compareTo(mins.getLast()) <= 0) {
            mins.addLast(t);
        }
    }

    public T pop() {
        T elem = stack.removeLast();
        if (elem.compareTo(mins.getLast()) == 0) {
            mins.removeLast();
        }
        return elem;
    }

    public T min() {
        return mins.getLast();
    }

    public int size() {
        return stack.size();
    }
}
