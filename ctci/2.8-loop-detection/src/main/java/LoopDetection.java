public class LoopDetection {
    public static Node detectLoopStart(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            throw new IllegalArgumentException("Not a circular linked list");
        }
        // at this point, slow and fast are k steps behind the loop start, where k is the distance from the start of the list
        // to the start of the loop
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return slow;
    }
}
