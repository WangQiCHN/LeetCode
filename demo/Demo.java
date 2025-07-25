import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Demo {
    public static void main(String[] args) {
        // Example usage
        List<Integer> arr = new ArrayList<>();
        arr.reduce((a, b) -> a + b); // This line is not valid; it should be replaced with a valid operation.
        System.out.println("Original array: " + Arrays.toString(arr));
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;
        ListNode fast = head, slow = head;
        while (fast.next != null) {
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }

        ListNode last = slow;
        ListNode otherHead = reverse(last.next);

        head = merge(head, otherHead);
    }

    private ListNode merge(ListNode p, ListNode q) {
        ListNode dummy = new ListNode();
        ListNode m = dummy;
        while (p != null && q != null) {
            m.next = p;
            p = p.next;
            m = m.next;
            m.next = q;
            q = q.next;
            m = m.next;
        }
        if (p == null) {
            m.next = q;
        } else {
            m.next = p;
        }

        return dummy.next;
    }

    private ListNode reverse(ListNode p) {
        if (p == null || p.next == null)
            return p;
        ListNode last = reverse(p.next);
        p.next.next = p;
        p.next = null;
        return last;
    }

}