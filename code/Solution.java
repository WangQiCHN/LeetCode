package code;

import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = { 0,5,5 };
        long result = sol.minOperations(nums);
        System.out.println(result);
    }

    public int minOperations(int[] nums) {
        LinkedList<Integer> q = new LinkedList<>();
        int size = 0;
        for (int n : nums) {
            while (!q.isEmpty() && q.getLast() > n) {
                q.poll();
            }
            if (q.isEmpty() || q.getLast() < n) {
                q.offer(n);
                size++;
            }
        }

        return q.getFirst() == 0 ? size - 1 : size;
    }
}