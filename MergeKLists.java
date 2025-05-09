public class MergeKLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        int sz = lists.length;
        if (sz == 0) {
            return null;
        }

        while (sz > 1) {
            int j = 0, i = 0;
            for (; i < sz; i = i + 2) {
                if (i + 1 == sz) break;
                ListNode temp = mergeTwoLists(lists[i], lists[i + 1]);
                lists[j] = temp;
                j++;
            }
            if (i + 1 == sz) {
                lists[j] = lists[i];
            }
            sz = (sz + 1) / 2;
        }

        return lists[0];
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode p = new ListNode();
        ListNode dummy = p;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                p.next = list2;
                list2 = list2.next;
            } else {
                p.next = list1;
                list1 = list1.next;
            }
            p = p.next;
        }
        if (list1 != null) {
            p.next = list1;
        } else if (list2 != null) {
            p.next = list2;
        }
        return dummy.next;
    }
}
