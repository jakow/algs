import java.util.NoSuchElementException;

/**
 * Created by jakub on 19/01/2018.
 */
public class RemoveMiddleNode {
    public static Node removeMiddleNode(Node head, int val) {
        Node curr = head;
        while (curr != null && curr.val != val) {
            curr = curr.next;
        }
        if (curr == null) {
            throw new NoSuchElementException(String.format("Node %d in list", val));
        }

        if (curr == head || curr.next == null) {
            throw new IllegalArgumentException("Only nodes in the middle can be deleted");
        }

        curr.val = curr.next.val;
        curr.next = curr.next.next;

        return head;
    }
}
