public class Demo {
    public static void main(String[] args) {
        // Create an instance of the class
        Demo myObject = new Demo();
        ListNode headA = new ListNode(2);
        ListNode headAA = new ListNode(2);
        ListNode headAAA = new ListNode(2);
        headA.next = headAA;
        headAA.next = headAAA;

        ListNode headB = new ListNode(1);
        ListNode headBB = new ListNode(5);
        headB.next = headBB;
        myObject.getIntersectionNode(headA, headB);
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode oHeadA = headA, oHeadB = headB;
        boolean changA = false;
        boolean changB = false;
        while (headA != null && headB != null) {
            if (headA.next != null) {
                headA = headA.next;
            } else {
                if (changA) {
                    headA = null;
                }
                changA = true;
                headA = oHeadB;
            }
            if (headB.next != null) {
                headB = headB.next;
            } else {
                if (changB) {
                    headB = null;
                }
                changB = true;
                headB = oHeadA;
            }

            if (headA == headB) break;
        }

        return headA;
    }
}