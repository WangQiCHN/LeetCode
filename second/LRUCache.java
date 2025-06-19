import java.util.HashMap;

import first.ListNode;

public class LRUCache {
    public static void main() {
        // [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
        // [[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]
        // [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
        // LRUCache a = new LRUCache(2);
        // a.put(1,1);
        // a.put(2,2);
        // a.get(1);
        // a.put(3,3);
        // a.get(2);
        // a.put(4,4);
        // a.get(1);
        // a.get(3);
        // a.get(4);
        // a.put(2,1);
        // a.put(1,1);
        // a.put(2,3);
        // a.put(4,1);
        // a.get(1);
        // a.get(2);
        // [[1],[6],[8],[12,1],[2],[15,11],[5,2],[1,15],[4,2],[5],[15,15]]
        LRUCache a = new LRUCache(1);
        System.out.println(a.get(6));
        System.out.println(a.get(8));
        a.put(12,1);
        System.out.println(a.get(2));
        a.put(15,11);
        a.put(5,2);
        a.put(1,15);
        a.put(4,2);
        System.out.println(a.get(5));
        a.put(15,15);
    }
    private int capacity;
    private HashMap<Integer, Integer> cache = new HashMap<>();
    private ListNode head = null;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (cache.containsKey(key)) {
            ListNode p = head;
            while (p != null) {
                if (p.key == key) {
                    ListNode newHead = new ListNode(key);
                    newHead.next = head;
                    newHead.pre = null;
                    head.pre = newHead;
                    if (p.next != null) {
                        p.next.pre = p.pre;
                    }
                    p.pre.next = p.next;
                    head = newHead;
                    break;
                } else {
                    p = p.next;
                }
            }
            return cache.get(key);
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            ListNode p = head;
            while (p != null) {
                if (p.key == key) {
                    ListNode newHead = new ListNode(key);
                    newHead.next = head;
                    newHead.pre = null;
                    head.pre = newHead;
                    if (p.next != null) {
                        p.next.pre = p.pre;
                    }
                    if (p.pre != null) {
                        p.pre.next = p.next;
                    }
                    head = newHead;
                    break;
                } else {
                    p = p.next;
                }
            }
        } else {
            ListNode newHead = new ListNode(key);
            newHead.next = head;
            newHead.pre = null;
            if (head != null) {
                head.pre = newHead;
            }
            head = newHead;
            int size = 0;
            ListNode p = head;
            while (p.next != null) {
                p = p.next;
                size++;
            }
            if (size == capacity) {
                p.pre.next = null;
                cache.remove(p.key);
            }
        }
        cache.put(key, value);
    }
}

class ListNode {
    public int key;
    public ListNode next;
    public ListNode pre;

    public ListNode(int key) {
        this.key = key;
        this.next = null;
        this.pre = null;
    }
}