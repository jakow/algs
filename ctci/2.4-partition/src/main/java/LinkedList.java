import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 19/01/2018.
 */
public class LinkedList {
    public static Node of(int... vals) {
        if (vals.length == 0) {
            return null;
        }
        Node head = new Node(vals[0]);
        Node curr = head;
        for (int i = 1; i < vals.length; ++i) {
            curr.next = new Node(vals[i]);
            curr = curr.next;
        }
        return head;
    }

    public static List<Integer> toList(Node head) {
        List<Integer> list = new ArrayList<>();
        Node curr = head;
        while (curr != null) {
            list.add(curr.val);
            curr = curr.next;
        }
        return list;
    }
}
