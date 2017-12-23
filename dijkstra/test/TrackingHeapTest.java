import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for the Tracking Heap data structure
 */
public class TrackingHeapTest {
    @Test
    public void sizeTest() {
        TrackingHeap<Integer> heap = new TrackingHeap<>();
        heap.insert(12);
        heap.insert(8);
        heap.insert(6);
        heap.insert(1);
        heap.insert(4);
        heap.insert(5);
        heap.insert(100);
        Assert.assertEquals(7, heap.size());
    }
    @Test
    public void delMinTest() {
        TrackingHeap<Integer> heap = new TrackingHeap<>();
        heap.insert(12);
        heap.insert(8);
        heap.insert(6);
        heap.insert(1);
        heap.insert(4);
        heap.insert(5);
        heap.insert(100);
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(8);
        expected.add(12);
        expected.add(100);
        List<Integer> actual = new ArrayList<>();
        while (heap.size() > 0) {
            actual.add(heap.delMin());
        }
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noDuplicatesTest() {
        TrackingHeap<Integer> heap = new TrackingHeap<>();
        heap.insert(12);
        heap.insert(12);
    }

    @Test
    public void delIndexTest() {
        TrackingHeap<Integer> heap = new TrackingHeap<>();
    }


}
