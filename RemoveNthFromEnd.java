public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode from = head, to = head, removed = dummy;
        while (n > 0 && from != null) {
            from = from.next;
            n--;
        }

        if (n > 0) {
            return dummy.next;
        }

        while (from != null) {
            from = from.next;
            to = to.next;
            removed = removed.next;
        }

        removed.next = to.next;


        return dummy.next;
    }
}
