import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        int[] nums = {1,2,3,1};
        int indexDiff = 3;
        int valueDiff = 0;
        boolean result = demo.containsNearbyAlmostDuplicate(nums, indexDiff, valueDiff);
        System.out.println("Result: " + result); // Expected output: true
    }
    DoubleList buffer = new DoubleList();
    Map<Integer, Node> dict = new HashMap<>();
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        int n = nums.length;
        int left = 0, right = 0;
        while (right < n) {
            int v = nums[right];
            if (isFound(right, v, valueDiff)) {
                return true;
            }
            right++;

            if (right - left > indexDiff) {
                Node node = dict.get(left);
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.next = null;
                node.prev = null;
                left++;
            }
        }

        return false;
    }

    private boolean isFound(int index, int value, int diff) {
        Node p = buffer.head.next;
        Node tail = buffer.tail;
        while (p != tail) {
            if (p.key == value) return true;
            else if (p.key < value) {
                if (value - p.key <= diff) return true;
                p = p.next;
            } else {
                if (p.key - value <= diff) return true;
                Node n = new Node(value);
                n.next = p;
                n.prev = p.prev;
                p.prev = n;
                dict.put(index, n);
                return false;
            }
        }
        Node n = new Node(value);
        n.next = tail;
        n.prev = tail.prev;
        tail.prev = n;
        dict.put(index, n);
        return false;
    }
}

class Node {
    int key;
    Node next;
    Node prev;

    public Node(int key) {
        this.key = key;
    }
}

class DoubleList {
    Node head;
    Node tail;

    public DoubleList() {
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }
}

// TrieNode class to represent each node in the Trie
    