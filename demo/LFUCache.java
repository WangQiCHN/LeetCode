package demo;

import java.util.Map;
import java.util.HashMap;

public class LFUCache {
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // returns 1
        cache.put(3, 3); // evicts key 2
        System.out.println(cache.get(2)); // returns -1 (not found)
        cache.put(4, 4); // evicts key 1
        System.out.println(cache.get(1)); // returns -1 (not found)
        System.out.println(cache.get(3)); // returns 3
        System.out.println(cache.get(4)); // returns 4
    }
    private int capacity;
    private int size;
    private Map<Integer, ListNode> keyToValue = new HashMap<>();
    private Map<Integer, DoubleList> freqToList = new HashMap<>();
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
    }
    
    public int get(int key) {
        if (keyToValue.containsKey(key)) {
            ListNode node = keyToValue.get(key);
            updateNodeFreq(node);
            return node.val;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (keyToValue.containsKey(key)) {
            ListNode node = keyToValue.get(key);
            node.val = value;
            updateNodeFreq(node);
        } else {
            if(capacity == size) {
                int i = 1;
                while (true) {
                    DoubleList cache = freqToList.get(i);
                    if (cache.getSize() == 0) {
                        i++;
                        continue;
                    } else {
                        ListNode node = cache.removeFirst();
                        keyToValue.remove(node.key);
                        size--;
                        break;
                    }
                }
            }
            ListNode node = new ListNode(key, value);
            DoubleList cache1;
            if (!freqToList.containsKey(1)) {
                cache1 = new DoubleList();
                freqToList.put(1, cache1);
            } else {
                cache1 = freqToList.get(1);
            }
            cache1.addNode(node);
            size++;
            keyToValue.put(key, node);
        }
    }

    private void updateNodeFreq(ListNode node) {
        DoubleList cache = freqToList.get(node.freq);
        cache.removeNode(node);
        node.freq++;
        DoubleList another;
        if (!freqToList.containsKey(node.freq)) {
            another = new DoubleList();
            freqToList.put(node.freq, another);
        } else {
            another = freqToList.get(node.freq);
        }
        another.addNode(node);
    }
}

class ListNode {
    ListNode prev;
    ListNode next;
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
    ListNode head;
    ListNode tail;
    int size;

    public DoubleList() {
        size = 0;
        head = new ListNode(0, 0);
        tail = new ListNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int getSize() {
        return size;
    }

    public void removeNode(ListNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.next = null;
        node.prev = null;
        size--;
    }

    public void addNode(ListNode node) {
        tail.prev.next = node;
        node.prev = tail.prev;
        tail.prev = node;
        node.next = tail;
        size++;
    }

    public ListNode removeFirst() {
        ListNode node = head.next;
        removeNode(node);
        return node;
    }
}