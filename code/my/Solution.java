package code.my;

import java.util.PriorityQueue;

class Node {
    int index;
    int value;
    Node prev;
    Node next;

    public Node(int index, int value) {
        this.index = index;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}

class PQItem implements Comparable<PQItem> {
    Node first;
    Node second;
    int total;

    public PQItem(Node first, Node second, int total) {
        this.first = first;
        this.second = second;
        this.total = total;
    }

    @Override
    public int compareTo(PQItem other) {
        if (this.total == other.total) {
            return this.first.index < other.first.index ? -1 : 1;
        } else {
            return this.total < other.total ? -1 : 1;
        }
    }
}

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = { 5, 2, 3, 1 };
        // int[] nums = {2,2,-1,3,-2,2,1,1,1,0,-1};
        int v = sol.minimumPairRemoval(nums);
        System.out.println(v);
    }

    public int minimumPairRemoval(int[] nums) {
        PriorityQueue<PQItem> queue = new PriorityQueue<>();
        Node head = new Node(0, nums[0]);
        Node current = head;
        int decreaseCount = 0;
        boolean[] merged = new boolean[nums.length];
        for (int i = 1; i < nums.length; i++) {
            Node next = new Node(i, nums[i]);
            current.next = next;
            next.prev = current;
            queue.offer(new PQItem(current, next, current.value + next.value));
            current = next;
            if (nums[i] < nums[i - 1]) {
                decreaseCount++;
            }
        }

        int count = 0;
        while (decreaseCount > 0) {
            PQItem item = queue.poll();
            Node first = item.first;
            Node second = item.second;
            if (merged[first.index] || merged[second.index] || first.value + second.value != item.total) {
                continue;
            }

            count++;
            Node prevNode = first.prev;
            Node nextNode = second.next;
            if (first.value > second.value) {
                decreaseCount--;
            }
            first.next = nextNode;
            if (prevNode != null) {
                if (prevNode.value <= first.value && prevNode.value > item.total) {
                    decreaseCount++;
                } else if (prevNode.value > first.value && prevNode.value <= item.total) {
                    decreaseCount--;
                }
                queue.offer(new PQItem(prevNode, first, prevNode.value + item.total));
            }

            if (nextNode != null) {
                if (nextNode.value >= second.value && nextNode.value < item.total) {
                    decreaseCount++;
                } else if (nextNode.value < second.value && nextNode.value >= item.total) {
                    decreaseCount--;
                }
                queue.offer(new PQItem(first, nextNode, item.total + nextNode.value));
            }

            first.value = count;
            merged[second.index] = true;
        }
        return count;
    }
}