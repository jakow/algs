import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by jakub on 03/02/2017.
 */
public class DequeTest {
    @Test
    public void createTest() {
        Deque<String> deque = new Deque<>();
        deque.addFirst("Hello");
        deque.addLast("World");
        deque.addLast("!");
        Assert.assertEquals(3, deque.size());
    }
    @Test
    public void addFirstRemoveLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        int[] arr = new int[3];
        arr[0] = deque.removeLast();
        arr[1] = deque.removeLast();
        arr[2] = deque.removeLast();
        Assert.assertArrayEquals(arr, new int [] {1, 2 , 3});

    }
    @Test
    public void addLastRemoveFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(211);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(5);
        deque.addLast(8);
        int first = 0;
        for (int i = 0; i < 5; ++i) {
            first = deque.removeFirst();
        }
        Assert.assertEquals(8, first);
        Assert.assertEquals(0, deque.size());
    }

    @Test
    public void iteratorTest() {
        Deque<Integer> deque = new Deque<>();
        int[] arrFib = new int[5];
        int a = 0 , b = 1;
        int temp;
        for (int i = 0; i < arrFib.length; ++i) {
            temp = a+b;
            a = b;
            b = temp;
            arrFib[i] = b; // sum of 2 previous nums - fib
            deque.addLast(b);

        }
        String arrString = Arrays.toString(arrFib);
        String iterString = "[";
        int count = 0;
        for(int item : deque) {
            count++;
            iterString += Integer.toString(item);
            if (count < 5) iterString += ", ";
        }
        iterString += "]";
        Assert.assertEquals(arrString, iterString);
        Assert.assertEquals(5, count);
    }

    @Test
    public void nestedIteratorTest() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        String s = "";
        for(int a : deque) {
            for (int b: deque) {
                s += a + b;
            }
        }
        Assert.assertEquals(s, "2334");
    }

    @Test(expected = NullPointerException.class)
    public void insertNull1() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void insertNull2() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirstFromEmpty() {
        Deque<Integer> deque = new Deque<>();
        deque.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLastFromEmpty() {
        Deque<Integer> deque = new Deque<>();
        deque.removeLast();
    }



}
