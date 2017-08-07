import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakub on 04/02/2017.
 */
public class RandomizedQueueTest {

//    private class RandomizedQueueInspected<Item> extends RandomizedQueue<Item> {
//        @Override
//        public String toString() {
//            String buf = "[";
//            for (int i = 0; i < size()-1; ++i) {
//                buf += arr[i] + ", ";
//            }
//            return buf + arr[size()-1] + "]";
//        }
//    }
//    @Test
//    public void create() {
//        RandomizedQueueInspected<Integer> rq = new RandomizedQueueInspected<>();
//        rq.enqueue(1);
//        rq.enqueue(2);
//        rq.enqueue(3);
//        Assert.assertEquals(3, rq.size());
//        Assert.assertEquals("[1, 2, 3]", rq.toString());
//    }
    @Test
    public void randomDequeue() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        Set<Integer> set = new HashSet<>();

        int[] a = {1, 5, 10, 300, 100, 700, 1000,5000};
        for (int elem : a) {
            rq.enqueue(elem);
            set.add(elem);
        }
        int x;
        int sz = a.length;
        while (rq.size() != 0) {
            x = rq.dequeue();
//            System.out.println(x);
            Assert.assertEquals(true, set.contains(x));
            Assert.assertEquals(rq.size(), --sz);
        }
        Assert.assertEquals(0, rq.size());

    }
    @Test
    public void sampleTest() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
//        rq.enqueue(1);
        rq.enqueue(2);
        int sample = rq.sample();
        Assert.assertEquals(2, sample);
        rq.enqueue(1);
        Assert.assertEquals(true, sample == 2 || sample == 1);
        Assert.assertEquals(2, rq.size());
    }
    @Test
    public void iteratorTest() {
        int[] a = {1, 5, 10, 100, 300, 700, 1000,5000};
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        for (int elem : a) {
            rq.enqueue(elem);
        }
        for (int elem : rq) {
            System.out.println(elem);
        }
    }
}

