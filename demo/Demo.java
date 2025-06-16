package demo;
import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {
        Demo d = new Demo(1);
        d.put(2,1);
        int result = d.get(2);
        System.out.println(result); 
    }

    private int cap;
    private DoubleList cache;
    private HashMap<Integer, ListNode> map = new HashMap<Integer, ListNode>();

    public Demo(int capacity) {
        this.cap = capacity;
        this.cache = new DoubleList();
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            cache.removeNode(node);
            cache.addNode(node);
            return node.val;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            cache.removeNode(node);
            cache.addNode(node);
            return;
        } else {
            if (cap == cache.getSize()) {
                ListNode node = cache.removeFirst();
                if (node != null) {
                    map.remove(node.key);
                }
            }
            ListNode newNode = new ListNode(key, value);
            map.put(key, newNode);
            cache.addNode(newNode);
        }
    }
}

class ListNode {
    ListNode next;
    ListNode prev;
    int val;
    int key;

    public ListNode(int key, int val) {
        this.val = val;
        this.key = key;
    }
}

class DoubleList {
    private ListNode head, tail;
    private int size;
    public DoubleList() {
        this.head = new ListNode(0, 0);
        this.tail = new ListNode(0, 0);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    public void addNode(ListNode node) {
        if (node == null) {
            return;
        }

        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
        size++;
    }

    public int getSize() {
        return size;
    }

    public void removeNode(ListNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.prev = null;
        node.next = null;

        size--;
    }

    public ListNode removeFirst() {
        if (size == 0) {
            return null;
        }
        ListNode node = head.next;
        removeNode(node);

        return node;
    }
}