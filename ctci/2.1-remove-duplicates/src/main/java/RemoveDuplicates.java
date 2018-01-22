import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakub on 19/01/2018.
 */
public class RemoveDuplicates {
    public static Node removeDuplicates(Node head) {
        Set<Integer> nums = new HashSet<>();
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            if (prev != null && nums.contains(curr.val)) {
                prev.next = curr.next;
            } else {
                nums.add(curr.val);
                prev = curr;
            }
            curr = curr.next;
        }
        return head;
    }

    public static Node removeDuplicatesNoAdditionalSpace(Node head) {
        Node curr = head;
        while (curr != null) {
            Node runner = curr.next;
            Node prev = curr;
            while (runner != null) {
                if (curr.val == runner.val) {
                    prev.next = runner.next;
                } else {
                    prev = runner;
                }
                runner = runner.next;
            }
            curr = curr.next;
        }
        return head;
    }
}
