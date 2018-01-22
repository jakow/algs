import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jakub on 19/01/2018.
 */
public class RemoveMiddleNodeTest {
    @Test
    public void removeMiddleNodeTest() {
        Node head = LinkedList.of(1,2,3,4,5,6,7);
        Node expected = LinkedList.of(1,2,3,4,6,7);
        Assert.assertEquals(
                LinkedList.toList(expected),
                LinkedList.toList(RemoveMiddleNode.removeMiddleNode(head, 5))
        );

    }
}
