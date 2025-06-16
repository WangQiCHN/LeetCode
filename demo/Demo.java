package demo;
import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {
        Demo d = new Demo(2);
        d.put(1,1);
        d.put(2,2);
        int result = d.get(1);
        System.out.println(result);
        d.put(3,3); 
        result = d.get(2);
        System.out.println(result);
        result = d.get(3);
        System.out.println(result);
        d.put(4,4); 
        result = d.get(1);
        System.out.println(result);
        result = d.get(3);
        System.out.println(result);
        result = d.get(4);
        System.out.println(result);
    }
    private int cap;
    private int size;
    private HashMap<Integer, DoubleList> freqToList = new HashMap<>();
    private HashMap<Integer, ListNode> keyToValue = new HashMap<>();

    public Demo(int capacity) {
        this.cap = capacity;
        this.size = 0;
    }
    
    public int get(int key) {
        if (keyToValue.containsKey(key)) {
            ListNode node = keyToValue.get(key);
            increaseNodeFreq(node);
            return node.val;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (keyToValue.containsKey(key)) {
            ListNode node = keyToValue.get(key);
            node.val = value;
            increaseNodeFreq(node);
        } else {
            if (size == cap) {
                // test
                int freq = 1;
                DoubleList list;
                while (true) {
                    list = freqToList.get(freq);
                    if (list == null || list.getSize() == 0) {
                        freq++;
                        continue;
                    } else {
                        break;
                    }
                }
                ListNode node = list.removeFirst();
                keyToValue.remove(node.key);
                size--;
            }
            addNewNode(new ListNode(key, value));
            size++;
        }
    }

    private void increaseNodeFreq(ListNode node) {
        DoubleList list = freqToList.get(node.freq);
        list.removeNode(node);
        node.freq++;
        if (freqToList.containsKey(node.freq)) {
            list = freqToList.get(node.freq);
            list.addNode(node);
        } else {
            list = new DoubleList();
            list.addNode(node);
            freqToList.put(node.freq, list);
        }
    }

    private void addNewNode(ListNode node) {
        if (freqToList.containsKey(node.freq)) {
            DoubleList list = freqToList.get(node.freq);
            list.addNode(node);
        } else {
            DoubleList list = new DoubleList();
            list.addNode(node);
            freqToList.put(node.freq, list);
        }
        keyToValue.put(node.key, node);
    }
}


class ListNode {
    ListNode next;
    ListNode prev;
    int val;
    int key;
    int freq;

    public ListNode(int key, int val) {
        this.val = val;
        this.key = key;
        this.freq = 1;
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

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
