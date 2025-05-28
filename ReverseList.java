public class ReverseList {
    public static void main() {
        ReverseList r = new ReverseList();
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode result = r.reverseBetween(head, 2, 4);
        System.out.println(result);
    }
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseHead(head, right);
        } else {
            head.next = reverseBetween(head.next, left - 1, right - 1);
            return head;
        }
    }

    // private ListNode reverseHead(ListNode head, int right) {
    //     if (right == 1) {
    //         successor = head.next;
    //         return head;
    //     }

    //     ListNode last = reverseHead(head.next, right - 1);
    //     head.next.next = head;
    //     head.next = successor;

    //     return last;
    // }

    private ListNode reverseHead(ListNode head, int right) {
        if (right == 1) {
            return head;
        }
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null && right > 0) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
            right--;
        }
        head.next = curr;
        
        return prev;
    }
}