import org.junit.Assert;
import org.junit.Test;

public class LoopDetectionTest {

    @Test
    public void test1() {
        Node head = makeCircularList(new int[] {1,2,3,4,5,6,7,8,9,10,11,12}, 6);
        Assert.assertEquals(6, LoopDetection.detectLoopStart(head).val);
    }

    @Test
    public void test2() {
        Node head = makeCircularList(new int[] {1,2,3,4}, 2);
        Assert.assertEquals(2, LoopDetection.detectLoopStart(head).val);
    }

    @Test
    public void test3() {
        Node head = makeCircularList(range(0, 10000), 560);
        Assert.assertEquals(560, LoopDetection.detectLoopStart(head).val);
    }

    @Test
    public void test4() {
        Node head = makeCircularList(range(0, 100000), 2);
        Assert.assertEquals(2, LoopDetection.detectLoopStart(head).val);
    }


    @Test
    public void test5() {
        Node head = makeCircularList(range(0, 1000000), 200123);
        Assert.assertEquals(200123, LoopDetection.detectLoopStart(head).val);
    }

    private int[] range(int start, int end) {
        int[] ret = new int[end - start];
        for (int i = 0; i < end; ++i) {
            ret[i] = i;
        }
        return ret;
    }

    Node makeCircularList(int[] elements, int start) {
        if (elements.length == 0) {
            throw new IllegalArgumentException("Circular linked list cannot have a zero length");
        }
        Node head = new Node(elements[0]), loopStart = null;
        Node curr = head;
        for (int i = 1; i < elements.length; ++i) {
            int element = elements[i];
            Node n = new Node(element);
                curr.next = n;
                curr = curr.next;
            if (n.val == start) {
                loopStart = n;
            }
        }
        if (loopStart == null) {
            throw new IllegalArgumentException("Not a circular linked list");
        }
        curr.next = loopStart;
        return head;
    }
}
