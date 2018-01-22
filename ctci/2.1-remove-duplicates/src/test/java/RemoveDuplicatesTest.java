import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jakub on 19/01/2018.
 */
public class RemoveDuplicatesTest {
    @Test
    public void testRemoveDups() {
        Node head1 = LinkedList.of(1,2,2,2,3,3,3,3,4,5,5,7,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,1,1,1,2,2,2);
        Node head2 = LinkedList.of(1,2,3,4,5,7);
        Assert.assertEquals(LinkedList.toList(head2), LinkedList.toList(RemoveDuplicates.removeDuplicates(head1)));
    }

    @Test
    public void testRemoveDupsNoSpace() {
        Node head1 = LinkedList.of(1,2,2,2,3,3,3,3,4,5,5,7,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,1,1,1,2,2,2);
        Node head2 = LinkedList.of(1,2,3,4,5,7);
        Assert.assertEquals(LinkedList.toList(head2), LinkedList.toList(RemoveDuplicates.removeDuplicatesNoAdditionalSpace(head1)));
    }
}
